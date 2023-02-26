package ua.aleh1s.hotelepam.model.queryspecification;


import ua.aleh1s.hotelepam.model.querybuilder.specification.Column;
import ua.aleh1s.hotelepam.model.queryspecification.node.*;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

import static java.util.Objects.isNull;

public class QuerySpecification {
    private static final ValueNode QUESTION_MARK = new ValueNode("?");
    private final StringJoiner query;
    private final Map<Column, Object> parameters;

    public QuerySpecification(SqlBase sqlBase, String tableName) {
        if (isNull(sqlBase) || isNull(tableName))
            throw new IllegalArgumentException("Sql base and table name cannot be null!");

        this.parameters = new HashMap<>();
        this.query = new StringJoiner(" ", sqlBase.toString(), ";")
                .add(String.format(" \"%s\"", tableName));
    }

    public static QuerySpecification newSpecification(SqlBase sqlBase, String tableName) {
        return new QuerySpecification(sqlBase, tableName);
    }

    public String getQuery() {
        return this.query.toString();
    }

    public QuerySpecification where(Predicate predicate) {
        this.query.add(buildWhereCondition(predicate));
        return this;
    }

    public QuerySpecification order(Order... conditions) {
        this.query.add(buildOrderByCondition(conditions));
        return this;
    }

    public QuerySpecification limit(int number) {
        this.query.add("limit").add(String.valueOf(number));
        return this;
    }

    public QuerySpecification offset(int number) {
        this.query.add("offset").add(String.valueOf(number));
        return this;
    }

    public Predicate like(Column column, String pattern) {
        this.parameters.put(column, pattern);
        return Predicate.of(Operator.LIKE, new ValueNode(column.getName()), QUESTION_MARK);
    }

    public Predicate notLike(Column column, String pattern) {
        this.parameters.put(column, pattern);
        return Predicate.of(Operator.NOT_LIKE, new ValueNode(column.getName()), QUESTION_MARK);
    }

    public Predicate eq(Column column, Object value) {
        this.parameters.put(column, value);
        return Predicate.of(Operator.EQUAL, new ValueNode(column.getName()), QUESTION_MARK);
    }

    public Predicate notEq(Column column, Object value) {
        this.parameters.put(column, value);
        return Predicate.of(Operator.NOT_EQUAL, new ValueNode(column.getName()), QUESTION_MARK);
    }

    public Predicate gt(Column column, Object value) {
        this.parameters.put(column, value);
        return Predicate.of(Operator.GREATER_THAN, new ValueNode(column.getName()), QUESTION_MARK);
    }

    public Predicate lt(Column column, Object value) {
        this.parameters.put(column, value);
        return Predicate.of(Operator.LESS_THAN, new ValueNode(column.getName()), QUESTION_MARK);
    }

//    public Predicate between(Column column, Object v1, Object v2) {
//        this.parameters.addAll(List.of(v1, v2));
//        return Predicate.of(Operator.BETWEEN, new ValueNode(column.getName()), and(QUESTION_MARK, QUESTION_MARK));
//    }
//
//    public Predicate in(Column column, Object... values) {
//        this.parameters.addAll(List.of(values));
//        return Predicate.of(Operator.IN, new ValueNode(column.getName()), new ValueNode(questionMarks(values.length)));
//    }
//
//    public Predicate notIn(Column column, Object... values) {
//        this.parameters.addAll(List.of(values));
//        return Predicate.of(Operator.NOT_IN, new ValueNode(column.getName()), new ValueNode(questionMarks(values.length)));
//    }

    public Predicate and(ExpressionNode... operands) {
        return Predicate.of(Operator.AND, operands);
    }

    public Predicate or(ExpressionNode... operands) {
        return Predicate.of(Operator.OR, operands);
    }

    public void injectValues(PreparedStatement statement) throws SQLException {
        int counter = 1;
        for (Map.Entry<Column, Object> param : parameters.entrySet()) {
            Column key = param.getKey();
            Object value = param.getValue();

            if (value instanceof LocalDate ld)
                value = Date.valueOf(ld);

            if (value instanceof LocalDateTime ldt)
                value = Timestamp.valueOf(ldt);

            statement.setObject(counter++, value, key.getType());
        }
    }

    private String questionMarks(int number) {
        StringJoiner joiner = new StringJoiner(", ", "(", ")");
        for (int i = 0; i < number; i++)
            joiner.add("?");
        return joiner.toString();
    }

    private String buildWhereCondition(Predicate predicate) {
        StringJoiner condition = new StringJoiner(" ", "where ", "");
        buildCondition(predicate, condition);
        return condition.toString();
    }

    private String buildOrderByCondition(Order[] conditions) {
        StringJoiner orderByJoiner = new StringJoiner(", ", "order by ", "");
        for (Order condition : conditions)
            orderByJoiner.add(String.format("%s %s", condition.getColumn().getName(), condition.getDirection()));

        return orderByJoiner.toString();
    }

    private void buildCondition(ExpressionNode node, StringJoiner query) {
        if (node instanceof ValueNode vn) {
            query.add(vn.getValue());
        } else if (node instanceof Predicate cn) {
            query.add("(");
            ExpressionNode[] operands = cn.getOperands();
            for (int i = 0; i < operands.length; i++) {
                buildCondition(operands[i], query);
                if (i != operands.length - 1)
                    query.add(cn.getOperator().toString());
            }
            query.add(")");
        } else {
            throw new IllegalArgumentException("Unknown type of node");
        }
    }
}
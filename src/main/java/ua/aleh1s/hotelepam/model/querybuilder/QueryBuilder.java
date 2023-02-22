package ua.aleh1s.hotelepam.model.querybuilder;

import ua.aleh1s.hotelepam.model.querybuilder.node.ExpressionNode;
import ua.aleh1s.hotelepam.model.querybuilder.node.Operator;
import ua.aleh1s.hotelepam.model.querybuilder.node.Predicate;
import ua.aleh1s.hotelepam.model.querybuilder.node.ValueNode;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;

import static java.util.Objects.isNull;

public class QueryBuilder {

    private static final ValueNode QUESTION_MARK = new ValueNode("?");
    private final StringJoiner query;
    private final List<Object> parameters;

    public QueryBuilder(SqlBase sqlBase, String tableName) {
        if (isNull(sqlBase) || isNull(tableName))
            throw new IllegalArgumentException("Sql base and table name cannot be null!");

        this.parameters = new LinkedList<>();
        this.query = new StringJoiner(" ", sqlBase.toString(), ";")
                .add(String.format(" \"%s\"", tableName));
    }

    public static QueryBuilder newBuilder(SqlBase sqlBase, String tableName) {
        return new QueryBuilder(sqlBase, tableName);
    }

    public PreparedStatement buildStatement(Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query.toString());

        int counter = 1;
        for (Object parameter : parameters) {
            statement.setObject(counter++, parameter);
        }

        return statement;
    }

    public String getQuery() {
        return this.query.toString();
    }

    public QueryBuilder where(Predicate predicate) {
        this.query.add(buildWhereCondition(predicate));
        return this;
    }

    public QueryBuilder orderBy() {
        throw new IllegalArgumentException("Method not implemented");
    }

    public QueryBuilder limit(int number) {
        this.query.add("limit").add(String.valueOf(number));
        return this;
    }

    public QueryBuilder offset(int number) {
        this.query.add("offset").add(String.valueOf(number));
        return this;
    }

    public Predicate eq(String fieldName, Object value) {
        this.parameters.add(value);
        return Predicate.of(Operator.EQUAL, new ValueNode(fieldName), QUESTION_MARK);
    }

    public Predicate neq(String fieldName, Object value) {
        this.parameters.add(value);
        return Predicate.of(Operator.NOT_EQUAL, new ValueNode(fieldName), QUESTION_MARK);
    }

    public Predicate gt(String fieldName, Object value) {
        this.parameters.add(value);
        return Predicate.of(Operator.GREATER_THAN, new ValueNode(fieldName), QUESTION_MARK);
    }

    public Predicate lt(String fieldName, Object value) {
        this.parameters.add(value);
        return Predicate.of(Operator.LESS_THAN, new ValueNode(fieldName), QUESTION_MARK);
    }

    public Predicate between(String fieldName, Object v1, Object v2) {
        this.parameters.addAll(List.of(v1, v2));
        return Predicate.of(Operator.BETWEEN, new ValueNode(fieldName), and(QUESTION_MARK, QUESTION_MARK));
    }

    public Predicate in(String fieldName, Object... values) {
        this.parameters.addAll(List.of(values));
        return Predicate.of(Operator.IN, new ValueNode(fieldName), new ValueNode(questionMarks(values.length)));
    }

    public Predicate nin(String fieldName, Object... values) {
        this.parameters.addAll(List.of(values));
        return Predicate.of(Operator.NOT_IN, new ValueNode(fieldName), new ValueNode(questionMarks(values.length)));
    }

    public String questionMarks(int number) {
        StringJoiner joiner = new StringJoiner(", ", "(", ")");
        for (int i = 0; i < number; i++)
            joiner.add("?");
        return joiner.toString();
    }

    public Predicate and(ExpressionNode... operands) {
        return Predicate.of(Operator.AND, operands);
    }

    public Predicate or(ExpressionNode... operands) {
        return Predicate.of(Operator.OR, operands);
    }

    private String buildWhereCondition(Predicate predicate) {
        StringJoiner condition = new StringJoiner(" ", "where ", "");
        buildCondition(predicate, condition);
        return condition.toString();
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
            throw new IllegalArgumentException("");
        }
    }

    public List<Object> getParameters() {
        return parameters;
    }
}
package ua.aleh1s.hotelepam.model.querybuilder;

import ua.aleh1s.hotelepam.model.pagination.PageRequest;
import ua.aleh1s.hotelepam.model.querybuilder.specification.orderby.OrderSpecification;
import ua.aleh1s.hotelepam.model.querybuilder.specification.where.WhereCriteria;
import ua.aleh1s.hotelepam.model.querybuilder.specification.where.WhereSpecification;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.StringJoiner;

import static java.util.Objects.*;

public class QueryBuilder {

    private final StringJoiner query;
    private WhereSpecification whereSpecification;
    private OrderSpecification orderSpecification;
    private PageRequest pageRequest;

    private QueryBuilder(StringJoiner stringJoiner) {
        this.query = stringJoiner;
    }

    public static QueryBuilder newQueryBuilder(SqlBase sqlBase, String tableName) {
        StringJoiner stringJoiner = new StringJoiner(" ", sqlBase.toString(), ";")
                .add(String.format(" \"%s\"", tableName));
        return new QueryBuilder(stringJoiner);
    }

    public void addWhereSpecification(WhereSpecification specification) {
        this.whereSpecification = specification;
    }

    public void addOrderSpecification(OrderSpecification specification) {
        this.orderSpecification = specification;
    }

    public void addPageRequest(PageRequest pageRequest) {
        this.pageRequest = pageRequest;
    }

    public String build() {
        if (nonNull(whereSpecification))
            query.add(whereSpecification.getCondition());

        if (nonNull(orderSpecification))
            query.add(orderSpecification.getCondition());

        if (nonNull(pageRequest))
            query.add("offset").add(String.valueOf(pageRequest.getOffset()))
                    .add("limit").add(String.valueOf(pageRequest.getLimit()));

        return query.toString();
    }

    public void injectValues(PreparedStatement statement) throws SQLException {
        int counter = 1;
        for (WhereCriteria whereCriteria : whereSpecification.getCriteriaList()) {
            Object value = whereCriteria.getValue();

            if (value instanceof LocalDate ld)
                value = Date.valueOf(ld);

            if (value instanceof LocalDateTime ldt)
                value = Timestamp.valueOf(ldt);

            statement.setObject(counter++, value, whereCriteria.getKey().getType());
        }
    }
}
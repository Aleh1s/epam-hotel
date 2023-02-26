package ua.aleh1s.hotelepam.model.querybuilder.specification.orderby;

import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;

public class OrderSpecification {

    private final List<OrderCriteria> criteriaList;

    private OrderSpecification() {
        this.criteriaList = new LinkedList<>();
    }

    public static OrderSpecification newOrderSpecification() {
        return new OrderSpecification();
    }

    public void addCriteria(OrderCriteria criteria) {
        criteriaList.add(criteria);
    }

    public List<OrderCriteria> getCriteriaList() {
        return criteriaList;
    }

    public String getCondition() {
        StringJoiner orderByCondition = new StringJoiner(", ", "order by ", "");

        for (OrderCriteria criteria : criteriaList) {
            String columnName = criteria.getColumn().getName();
            String orderDirection = criteria.getDirection().name().toLowerCase();
            orderByCondition.add(String.format("%s %s", columnName, orderDirection));
        }

        return orderByCondition.toString();
    }
}

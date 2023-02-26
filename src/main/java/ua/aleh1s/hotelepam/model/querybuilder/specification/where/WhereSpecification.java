package ua.aleh1s.hotelepam.model.querybuilder.specification.where;

import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;

public class WhereSpecification {

    private final List<WhereCriteria> whereCriteriaList;

    private WhereSpecification() {
        this.whereCriteriaList = new LinkedList<>();
    }

    public static WhereSpecification newSpecification() {
        return new WhereSpecification();
    }

    public void addCriteria(WhereCriteria whereCriteria) {
        whereCriteriaList.add(whereCriteria);
    }

    public String getCondition() {
        StringJoiner whereCondition = new StringJoiner(" ", "where ", "");

        for (WhereCriteria whereCriteria : whereCriteriaList)
            whereCondition.add(String.format("%s %s ?", whereCriteria.getKey().getName(), whereCriteria.getOperation()));

        return whereCondition.toString();
    }

    public List<WhereCriteria> getCriteriaList() {
        return whereCriteriaList;
    }
}

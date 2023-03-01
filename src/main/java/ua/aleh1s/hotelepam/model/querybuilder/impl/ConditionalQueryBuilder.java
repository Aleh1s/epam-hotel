package ua.aleh1s.hotelepam.model.querybuilder.impl;

import ua.aleh1s.hotelepam.model.querybuilder.Parameter;
import ua.aleh1s.hotelepam.model.querybuilder.QueryBuilder;
import ua.aleh1s.hotelepam.model.querybuilder.Root;
import ua.aleh1s.hotelepam.model.querybuilder.node.*;

import java.util.StringJoiner;

public abstract class ConditionalQueryBuilder<T> extends QueryBuilder<T> {

    private static final String QUESTION_MARK = "?";

    protected ConditionalQueryBuilder(Root<T> root, StringJoiner query) {
        super(root, query);
    }

    public ConditionalQueryBuilder<T> where(PredicateNode predicateNode) {
        query.add("where");
        buildCondition(predicateNode);
        return this;
    }

    private void buildCondition(ExpressionNode node) {
        if (node instanceof PredicateNode p) {
            query.add("(");
            if (p instanceof MultiplePredicateNode mp) {
                PredicateNode[] predicateNodes = mp.getPredicates();
                for (int i = 0; i < predicateNodes.length; i++) {
                    buildCondition(predicateNodes[i]);
                    if (i != predicateNodes.length - 1)
                        query.add(mp.getOperator().toString());
                }
            } else if (p instanceof ComparisonNode cn) {
                Parameter parameter = Parameter.of(cn.getComparedColumn(), cn.getComparedWith());
                this.root.addParameter(parameter);
                query.add(cn.getComparedColumn().getName())
                        .add(cn.getOperator().toString())
                        .add(QUESTION_MARK);
            } else if (p instanceof ContainsNode<?> cn) {
                query.add(cn.getComparedColumn().getName())
                        .add(cn.getOperator().toString());
                StringJoiner questionMarks = new StringJoiner(", ", "(", ")");
                for (Object value : cn.getComparedWith()) {
                    Parameter parameter = Parameter.of(cn.getComparedColumn(), value);
                    this.root.addParameter(parameter);
                    questionMarks.add(QUESTION_MARK);
                }
                query.add(questionMarks.toString());
            } else if (p instanceof RangeNode<?> rn) {
                Parameter leftBound = Parameter.of(rn.getColumn(), rn.getLeftBound());
                Parameter rightBound = Parameter.of(rn.getColumn(), rn.getRightBound());
                this.root.addParameter(leftBound);
                this.root.addParameter(rightBound);
                query.add(rn.getColumn().getName())
                        .add(rn.getOperator().toString())
                        .add(QUESTION_MARK)
                        .add("and")
                        .add(QUESTION_MARK);
            } else if (p instanceof NullCheckNode ncn) {
                query.add(ncn.getComparedColumn().getName())
                        .add(ncn.getOperator().toString());
            } else {
                throw new IllegalArgumentException("Unknown type of predicate");
            }
            query.add(")");
        } else {
            throw new IllegalArgumentException("Unknown type of node");
        }
    }
}

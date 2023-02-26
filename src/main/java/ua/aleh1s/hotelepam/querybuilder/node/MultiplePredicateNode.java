package ua.aleh1s.hotelepam.querybuilder.node;

import ua.aleh1s.hotelepam.querybuilder.Operator;

public class MultiplePredicateNode extends PredicateNode {

    private final PredicateNode[] predicateNodes;

    private MultiplePredicateNode(Operator operator, PredicateNode[] predicateNodes) {
        super(operator);
        this.predicateNodes = predicateNodes;
    }

    public static MultiplePredicateNode of(Operator operator, PredicateNode... predicateNodes) {
        return new MultiplePredicateNode(operator, predicateNodes);
    }

    public PredicateNode[] getPredicates() {
        return predicateNodes;
    }
}

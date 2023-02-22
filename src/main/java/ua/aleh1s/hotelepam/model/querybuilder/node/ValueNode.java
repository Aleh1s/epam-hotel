package ua.aleh1s.hotelepam.model.querybuilder.node;

public class ValueNode extends ExpressionNode {
    private final String value;

    public ValueNode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

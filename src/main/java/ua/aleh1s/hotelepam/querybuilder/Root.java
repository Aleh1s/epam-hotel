package ua.aleh1s.hotelepam.querybuilder;

import ua.aleh1s.hotelepam.querybuilder.entityconfiguration.Column;
import ua.aleh1s.hotelepam.querybuilder.entityconfiguration.EntityConfiguration;
import ua.aleh1s.hotelepam.querybuilder.entityconfiguration.EntityConfigurationHolder;
import ua.aleh1s.hotelepam.querybuilder.impl.DeleteQueryBuilder;
import ua.aleh1s.hotelepam.querybuilder.impl.InsertQueryBuilder;
import ua.aleh1s.hotelepam.querybuilder.impl.SelectQueryBuilder;
import ua.aleh1s.hotelepam.querybuilder.impl.UpdateQueryBuilder;
import ua.aleh1s.hotelepam.querybuilder.node.MultiplePredicateNode;
import ua.aleh1s.hotelepam.querybuilder.node.PredicateNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Root<T> {

    private static final EntityConfigurationHolder configurationHolder
            = EntityConfigurationHolder.getInstance();

    private final String tableName;
    private final Map<String, Column> columnMap;
    private final List<Parameter> parameters;


    private Root(String tableName, Map<String, Column> columnMap) {
        this.tableName = tableName;
        this.columnMap = columnMap;
        this.parameters = new ArrayList<>();
    }

    public static <T> Root<T> valueOf(Class<T> clazz) {
        EntityConfiguration ec = configurationHolder.valueOf(clazz);
        return new Root<>(ec.getTableName(), ec.getColumnMap());
    }

    public SelectQueryBuilder<T> select() {
        return new SelectQueryBuilder<>(this);
    }

    public SelectQueryBuilder<T> select(AggregateFunctionBuilder aggregateFunctionBuilder) {
        return new SelectQueryBuilder<>(this, aggregateFunctionBuilder);
    }

    public InsertQueryBuilder<T> insert() {
        return new InsertQueryBuilder<>(this);
    }

    public UpdateQueryBuilder<T> update() {
        return new UpdateQueryBuilder<>(this);
    }

    public DeleteQueryBuilder<T> delete() {
        return new DeleteQueryBuilder<>(this);
    }

    public String getTableName() {
        return tableName;
    }

    public ColumnDecorator get(String name) {
        return ColumnDecorator.decorate(columnMap.get(name));
    }

    public OrderUnit get(String name, OrderUnit.Direction direction) {
        return OrderUnit.by(columnMap.get(name), direction);
    }

    public MultiplePredicateNode and(PredicateNode... predicateNodes) {
        return MultiplePredicateNode.of(Operator.AND, predicateNodes);
    }

    public MultiplePredicateNode or(PredicateNode... predicateNodes) {
        return MultiplePredicateNode.of(Operator.OR, predicateNodes);
    }

    public AggregateFunctionBuilder count(ColumnDecorator columnDecorator) {
        return AggregateFunctionBuilder.newBuilder(AggregateFunction.COUNT)
                .param(columnDecorator);
    }

    public AggregateFunctionBuilder countAll() {
        return AggregateFunctionBuilder.newBuilder(AggregateFunction.COUNT_ALL);
    }

    public void addParameter(Parameter parameter) {
        this.parameters.add(parameter);
    }

    public List<Parameter> getParameters() {
        return parameters;
    }
}

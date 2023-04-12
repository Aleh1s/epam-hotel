package ua.aleh1s.hotelepam.model.querybuilder;

import ua.aleh1s.hotelepam.model.querybuilder.aggregatefunc.AggregateFunction;
import ua.aleh1s.hotelepam.model.querybuilder.aggregatefunc.AggregateFunctionBuilder;
import ua.aleh1s.hotelepam.model.querybuilder.entityconfiguration.Column;
import ua.aleh1s.hotelepam.model.querybuilder.entityconfiguration.EntityConfiguration;
import ua.aleh1s.hotelepam.model.querybuilder.entityconfiguration.EntityConfigurationHolder;
import ua.aleh1s.hotelepam.model.querybuilder.impl.DeleteQueryBuilder;
import ua.aleh1s.hotelepam.model.querybuilder.impl.InsertQueryBuilder;
import ua.aleh1s.hotelepam.model.querybuilder.impl.SelectQueryBuilder;
import ua.aleh1s.hotelepam.model.querybuilder.impl.UpdateQueryBuilder;
import ua.aleh1s.hotelepam.model.querybuilder.node.MultiplePredicateNode;
import ua.aleh1s.hotelepam.model.querybuilder.node.PredicateNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Root<T> {

    private static final EntityConfigurationHolder configurationHolder
            = EntityConfigurationHolder.getInstance();

    private final String tableName;
    private final Map<String, Column> columnMap;
    private List<Parameter> parameters;

    public Root(String tableName, Map<String, Column> columnMap) {
        this.tableName = tableName;
        this.columnMap = columnMap;
    }

    public SelectQueryBuilder<T> select() {
        this.parameters = new LinkedList<>();
        return new SelectQueryBuilder<>(this);
    }

    public SelectQueryBuilder<T> select(AggregateFunctionBuilder aggregateFunctionBuilder) {
        this.parameters = new LinkedList<>();
        return new SelectQueryBuilder<>(this, aggregateFunctionBuilder);
    }

    public SelectQueryBuilder<T> select(ColumnDecorator columnDecorator) {
        this.parameters = new LinkedList<>();
        return new SelectQueryBuilder<>(this, columnDecorator);
    }

    public InsertQueryBuilder<T> insert() {
        this.parameters = new LinkedList<>();
        return new InsertQueryBuilder<>(this);
    }

    public UpdateQueryBuilder<T> update() {
        this.parameters = new LinkedList<>();
        return new UpdateQueryBuilder<>(this);
    }

    public DeleteQueryBuilder<T> delete() {
        this.parameters = new LinkedList<>();
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

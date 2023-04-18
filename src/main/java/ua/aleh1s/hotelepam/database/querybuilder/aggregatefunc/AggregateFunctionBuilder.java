package ua.aleh1s.hotelepam.database.querybuilder.aggregatefunc;

import ua.aleh1s.hotelepam.database.querybuilder.ColumnDecorator;

import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;

public class AggregateFunctionBuilder {

    private final AggregateFunction aggregateFunction;
    private final List<ColumnDecorator> params;

    private AggregateFunctionBuilder(AggregateFunction aggregateFunction) {
        this.aggregateFunction = aggregateFunction;
        this.params = new LinkedList<>();
    }

    public static AggregateFunctionBuilder newBuilder(AggregateFunction aggregateFunction) {
        return new AggregateFunctionBuilder(aggregateFunction);
    }

    public AggregateFunctionBuilder param(ColumnDecorator columnDecorator) {
        this.params.add(columnDecorator);
        return this;
    }

    public String build() {
        StringJoiner parameters = new StringJoiner(", ");
        for (ColumnDecorator param : params)
            parameters.add(param.getColumn().getName());
        return String.format(aggregateFunction.toString(), parameters);
    }

}

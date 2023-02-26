package ua.aleh1s.hotelepam.model.sqlmapper;

import java.sql.ResultSet;
import java.util.function.Function;

public interface SqlEntityMapper<T> extends Function<ResultSet, T> {
}

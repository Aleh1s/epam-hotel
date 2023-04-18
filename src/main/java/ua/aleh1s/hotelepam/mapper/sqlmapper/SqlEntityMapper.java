package ua.aleh1s.hotelepam.mapper.sqlmapper;

import java.sql.ResultSet;
import java.util.function.Function;

public interface SqlEntityMapper<T> extends Function<ResultSet, T> {
}

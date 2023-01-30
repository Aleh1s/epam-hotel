package ua.aleh1s.hotelepam.model.mapper;

import ua.aleh1s.hotelepam.model.mapper.exception.SqlEntityMapperException;

import java.sql.ResultSet;
public interface SqlEntityMapper<T> {
    T map(ResultSet source) throws SqlEntityMapperException;
}

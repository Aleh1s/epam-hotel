package ua.aleh1s.hotelepam.model.mapper;

import ua.aleh1s.hotelepam.model.mapper.exception.SqlEntityMapperException;

import java.sql.ResultSet;
import java.util.Optional;
public interface SqlEntityMapper<T> {
    Optional<T> map(ResultSet source) throws SqlEntityMapperException;
}

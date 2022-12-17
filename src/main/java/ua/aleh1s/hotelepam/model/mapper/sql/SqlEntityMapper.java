package ua.aleh1s.hotelepam.model.mapper.sql;

import ua.aleh1s.hotelepam.model.mapper.sql.exception.SqlEntityMapperException;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public interface SqlEntityMapper<E> {
    Optional<E> mapOne(ResultSet resultSet) throws SqlEntityMapperException;
    List<E> mapAll(ResultSet resultSet) throws SqlEntityMapperException;
}

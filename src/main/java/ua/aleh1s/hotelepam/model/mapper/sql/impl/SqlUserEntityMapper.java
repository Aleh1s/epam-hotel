package ua.aleh1s.hotelepam.model.mapper.sql.impl;

import ua.aleh1s.hotelepam.model.constant.SqlFieldName;
import ua.aleh1s.hotelepam.model.entity.UserEntity;
import ua.aleh1s.hotelepam.model.entity.role.UserRole;
import ua.aleh1s.hotelepam.model.mapper.sql.SqlEntityMapper;
import ua.aleh1s.hotelepam.model.mapper.sql.exception.SqlEntityMapperException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.*;

import static ua.aleh1s.hotelepam.model.constant.SqlFieldName.*;

public class SqlUserEntityMapper implements SqlEntityMapper<UserEntity> {

    @Override
    public Optional<UserEntity> mapOne(ResultSet resultSet) throws SqlEntityMapperException {
        try {
            if (resultSet.next())
                return Optional.of(buildUserEntity(resultSet));
        } catch (SQLException e) {
            throw new SqlEntityMapperException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<UserEntity> mapAll(ResultSet resultSet) throws SqlEntityMapperException {
        List<UserEntity> result = new ArrayList<>();
        try {
            while (resultSet.next())
                result.add(buildUserEntity(resultSet));
        } catch (SQLException e) {
            throw new SqlEntityMapperException(e);
        }
        return result;
    }

    private UserEntity buildUserEntity(ResultSet resultSet) throws SQLException {
        return UserEntity.Builder.newBuilder()
                .id(resultSet.getLong(USER_ID))
                .login(resultSet.getString(USER_LOGIN))
                .password(resultSet.getString(USER_PASSWORD))
                .timezone(ZoneId.of(resultSet.getString(USER_TIMEZONE)))
                .locale(new Locale(resultSet.getString(USER_LOCALE)))
                .role(UserRole.valueOf(resultSet.getString(USER_ROLE)))
                .build();
    }
}

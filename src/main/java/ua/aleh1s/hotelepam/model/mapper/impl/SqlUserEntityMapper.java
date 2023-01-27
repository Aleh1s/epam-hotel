package ua.aleh1s.hotelepam.model.mapper.impl;

import ua.aleh1s.hotelepam.model.entity.UserEntity;
import ua.aleh1s.hotelepam.model.entity.UserRole;
import ua.aleh1s.hotelepam.model.mapper.exception.SqlEntityMapperException;
import ua.aleh1s.hotelepam.model.mapper.SqlEntityMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Optional;

import static ua.aleh1s.hotelepam.model.constant.SqlFieldName.*;

public class SqlUserEntityMapper implements SqlEntityMapper<UserEntity> {

    @Override
    public Optional<UserEntity> map(ResultSet resultSet) throws SqlEntityMapperException {
        try {
            if (resultSet.next())
                return Optional.of(buildUserEntity(resultSet));
        } catch (SQLException e) {
            throw new SqlEntityMapperException(e);
        }
        return Optional.empty();
    }

    private UserEntity buildUserEntity(ResultSet resultSet) throws SQLException {
        return UserEntity.Builder.newBuilder()
                .id(resultSet.getLong(USER_ID))
                .email(resultSet.getString(USER_EMAIL))
                .password(resultSet.getString(USER_PASSWORD))
                .timezone(ZoneId.of(resultSet.getString(USER_TIMEZONE)))
                .locale(new Locale(resultSet.getString(USER_LOCALE)))
                .role(UserRole.valueOf(resultSet.getString(USER_ROLE)))
                .build();
    }
}

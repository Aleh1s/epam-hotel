package ua.aleh1s.hotelepam.model.mapper.impl;

import ua.aleh1s.hotelepam.model.entity.UserEntity;
import ua.aleh1s.hotelepam.model.entity.UserRole;
import ua.aleh1s.hotelepam.model.mapper.SqlEntityMapper;
import ua.aleh1s.hotelepam.model.mapper.exception.SqlEntityMapperException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Locale;

import static ua.aleh1s.hotelepam.model.constant.SqlFieldName.*;

public class SqlUserEntityMapper implements SqlEntityMapper<UserEntity> {

    @Override
    public UserEntity map(ResultSet resultSet) throws SqlEntityMapperException {
        try {
            return buildUserEntity(resultSet);
        } catch (SQLException e) {
            throw new SqlEntityMapperException(e);
        }
    }

    private UserEntity buildUserEntity(ResultSet resultSet) throws SQLException {
        return UserEntity.Builder.newBuilder()
                .id(resultSet.getLong(USER_ID))
                .email(resultSet.getString(USER_EMAIL))
                .firstName(resultSet.getString(USER_FIRST_NAME))
                .lastName(resultSet.getString(USER_LAST_NAME))
                .phoneNumber(resultSet.getString(USER_PHONE_NUMBER))
                .password(resultSet.getString(USER_PASSWORD))
                .timezone(ZoneId.of(resultSet.getString(USER_TIMEZONE)))
                .locale(new Locale(resultSet.getString(USER_LOCALE)))
                .role(UserRole.valueOf(resultSet.getString(USER_ROLE)))
                .build();
    }
}

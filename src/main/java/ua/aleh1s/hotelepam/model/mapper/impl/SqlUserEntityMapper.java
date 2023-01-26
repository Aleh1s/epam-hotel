package ua.aleh1s.hotelepam.model.mapper.impl.sql.impl;

import ua.aleh1s.hotelepam.model.entity.UserEntity;
import ua.aleh1s.hotelepam.model.entity.role.UserRole;
import ua.aleh1s.hotelepam.model.mapper.exception.EntityMapperException;
import ua.aleh1s.hotelepam.model.mapper.impl.sql.SqlEntityMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Optional;

import static ua.aleh1s.hotelepam.model.constant.SqlFieldName.*;

public class SqlUserEntityMapper implements SqlEntityMapper<UserEntity> {

    @Override
    public Optional<UserEntity> map(ResultSet resultSet) throws EntityMapperException {
        try {
            if (resultSet.next())
                return Optional.of(buildUserEntity(resultSet));
        } catch (SQLException e) {
            throw new EntityMapperException(e);
        }
        return Optional.empty();
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

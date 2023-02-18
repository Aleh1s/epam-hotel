package ua.aleh1s.hotelepam.model.sqlmapper;

import ua.aleh1s.hotelepam.model.entity.UserEntity;
import ua.aleh1s.hotelepam.model.entity.UserRole;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

import static ua.aleh1s.hotelepam.model.constant.SqlFieldName.*;

public class SqlUserEntityMapper {

    public UserEntity map(ResultSet resultSet) throws SQLException {
        return UserEntity.Builder.newBuilder()
                .id(resultSet.getLong(USER_ID))
                .email(resultSet.getString(USER_EMAIL))
                .firstName(resultSet.getString(USER_FIRST_NAME))
                .lastName(resultSet.getString(USER_LAST_NAME))
                .phoneNumber(resultSet.getString(USER_PHONE_NUMBER))
                .password(resultSet.getString(USER_PASSWORD))
                .locale(new Locale(resultSet.getString(USER_LOCALE)))
                .role(UserRole.valueOf(resultSet.getString(USER_ROLE)))
                .account(resultSet.getBigDecimal(USER_ACCOUNT))
                .build();
    }
}

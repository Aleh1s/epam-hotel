package ua.aleh1s.hotelepam.model.sqlmapper;

import ua.aleh1s.hotelepam.model.entity.UserEntity;
import ua.aleh1s.hotelepam.model.entity.UserRole;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

import static ua.aleh1s.hotelepam.model.constant.SqlField.UserTable.*;

public class SqlUserEntityMapper {

    public UserEntity map(ResultSet resultSet) throws SQLException {
        return UserEntity.Builder.newBuilder()
                .id(resultSet.getLong(ID))
                .email(resultSet.getString(EMAIL))
                .firstName(resultSet.getString(FIRST_NAME))
                .lastName(resultSet.getString(LAST_NAME))
                .phoneNumber(resultSet.getString(PHONE_NUMBER))
                .password(resultSet.getString(PASSWORD))
                .locale(new Locale(resultSet.getString(LOCALE)))
                .role(UserRole.valueOf(resultSet.getString(ROLE)))
                .account(resultSet.getBigDecimal(ACCOUNT))
                .build();
    }
}

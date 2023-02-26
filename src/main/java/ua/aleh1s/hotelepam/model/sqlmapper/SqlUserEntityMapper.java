package ua.aleh1s.hotelepam.model.sqlmapper;

import ua.aleh1s.hotelepam.model.entity.UserEntity;
import ua.aleh1s.hotelepam.model.entity.UserRole;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

import static ua.aleh1s.hotelepam.constant.SqlColumn.UserTable.*;

public class SqlUserEntityMapper {

    public UserEntity map(ResultSet resultSet) throws SQLException {
        return UserEntity.Builder.newBuilder()
                .id(resultSet.getLong(ID.getName()))
                .email(resultSet.getString(EMAIL.getName()))
                .firstName(resultSet.getString(FIRST_NAME.getName()))
                .lastName(resultSet.getString(LAST_NAME.getName()))
                .phoneNumber(resultSet.getString(PHONE_NUMBER.getName()))
                .password(resultSet.getString(PASSWORD.getName()))
                .locale(new Locale(resultSet.getString(LOCALE.getName())))
                .role(UserRole.valueOf(resultSet.getString(ROLE.getName())))
                .account(resultSet.getBigDecimal(ACCOUNT.getName()))
                .build();
    }
}

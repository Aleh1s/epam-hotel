package ua.aleh1s.hotelepam.model.mapper.impl;

import ua.aleh1s.hotelepam.model.entity.CustomerEntity;
import ua.aleh1s.hotelepam.model.entity.Gender;
import ua.aleh1s.hotelepam.model.mapper.exception.SqlEntityMapperException;
import ua.aleh1s.hotelepam.model.mapper.SqlEntityMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

import static ua.aleh1s.hotelepam.model.constant.SqlFieldName.*;

public class SqlCustomerEntityMapper implements SqlEntityMapper<CustomerEntity> {
    @Override
    public Optional<CustomerEntity> map(ResultSet resultSet) throws SqlEntityMapperException {
        try {
            if (resultSet.next())
                return Optional.of(buildCustomerEntity(resultSet));
        } catch (SQLException e) {
            throw new SqlEntityMapperException(e);
        }
        return Optional.empty();
    }

    private CustomerEntity buildCustomerEntity(ResultSet resultSet) throws SQLException {
        Instant instant = resultSet.getObject(CUSTOMER_DATE_OF_BIRTH, Instant.class);
        ZonedDateTime dateOfBirth = ZonedDateTime.ofInstant(instant, ZoneId.of("UTC")); //todo: make dynamic ZoneId instead "UTC"
        return CustomerEntity.Builder.newBuilder()
                .id(resultSet.getLong(CUSTOMER_ID))
                .firstName(resultSet.getString(CUSTOMER_FIRST_NAME))
                .lastName(resultSet.getString(CUSTOMER_LAST_NAME))
                .email(resultSet.getString(CUSTOMER_EMAIL))
                .country(resultSet.getString(CUSTOMER_COUNTRY))
                .dateOfBirth(dateOfBirth)
                .gender(Gender.valueOf(resultSet.getString(CUSTOMER_GENDER)))
                .userId(resultSet.getLong(CUSTOMER_USER_ID))
                .build();
    }
}

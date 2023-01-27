package ua.aleh1s.hotelepam.model.mapper.impl;

import ua.aleh1s.hotelepam.model.entity.CustomerEntity;
import ua.aleh1s.hotelepam.model.mapper.SqlEntityMapper;
import ua.aleh1s.hotelepam.model.mapper.exception.SqlEntityMapperException;

import java.sql.ResultSet;
import java.sql.SQLException;
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
        return CustomerEntity.Builder.newBuilder()
                .id(resultSet.getLong(CUSTOMER_ID))
                .firstName(resultSet.getString(CUSTOMER_FIRST_NAME))
                .lastName(resultSet.getString(CUSTOMER_LAST_NAME))
                .phoneNumber(resultSet.getString(CUSTOMER_PHONE_NUMBER))
                .userId(resultSet.getLong(CUSTOMER_USER_ID))
                .build();
    }
}

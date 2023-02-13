package ua.aleh1s.hotelepam.model.mapper.impl;

import ua.aleh1s.hotelepam.model.constant.SqlFieldName;
import ua.aleh1s.hotelepam.model.entity.RequestEntity;
import ua.aleh1s.hotelepam.model.entity.RequestStatus;
import ua.aleh1s.hotelepam.model.mapper.SqlEntityMapper;
import ua.aleh1s.hotelepam.model.mapper.exception.SqlEntityMapperException;

import java.sql.ResultSet;
import java.sql.SQLException;

import static ua.aleh1s.hotelepam.model.constant.SqlFieldName.*;

public class SqlRequestEntityMapper implements SqlEntityMapper<RequestEntity> {
    @Override
    public RequestEntity map(ResultSet source) throws SqlEntityMapperException {
        try {
            return buildEntity(source);
        } catch (SQLException e) {
            throw new SqlEntityMapperException(e);
        }
    }

    private RequestEntity buildEntity(ResultSet source) throws SQLException {
        return RequestEntity.Builder.newBuilder()
                .id(source.getLong(REQUEST_ID))
                .customerId(source.getLong(REQUEST_CUSTOMER_ID))
                .roomNumber(source.getInt(REQUEST_ROOM_NUMBER))
                .status(RequestStatus.atIndex(source.getInt(REQUEST_STATUS)))
                .entryDate(source.getDate(REQUEST_ENTRY_DATE).toLocalDate())
                .leavingDate(source.getDate(REQUEST_LEAVING_DATE).toLocalDate())
                .totalAmount(source.getBigDecimal(REQUEST_TOTAL_AMOUNT))
                .build();
    }
}

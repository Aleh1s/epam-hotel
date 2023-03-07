package ua.aleh1s.hotelepam.model.sqlmapper.impl;

import ua.aleh1s.hotelepam.model.entity.RequestEntity;
import ua.aleh1s.hotelepam.model.entity.RequestStatus;
import ua.aleh1s.hotelepam.model.sqlmapper.SqlEntityMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static ua.aleh1s.hotelepam.constant.SqlColumn.RequestTable.*;

public class SqlRequestEntityMapper implements SqlEntityMapper<RequestEntity> {
    public RequestEntity apply(ResultSet source) {
        RequestEntity requestEntity = null;
        try {
            requestEntity = RequestEntity.builder()
                    .id(source.getLong(ID.getName()))
                    .customerId(source.getLong(CUSTOMER_ID.getName()))
                    .roomNumber(source.getInt(ROOM_NUMBER.getName()))
                    .status(RequestStatus.atIndex(source.getInt(STATUS.getName())))
                    .checkIn(source.getDate(CHECK_IN.getName()).toLocalDate())
                    .checkOut(source.getDate(CHECK_OUT.getName()).toLocalDate())
                    .totalAmount(source.getBigDecimal(TOTAL_AMOUNT.getName()))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requestEntity;
    }
}

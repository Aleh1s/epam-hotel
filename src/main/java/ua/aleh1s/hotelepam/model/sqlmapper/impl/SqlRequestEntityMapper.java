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
            requestEntity = RequestEntity.Builder.newBuilder()
                    .id(source.getLong(ID.getName()))
                    .customerId(source.getLong(CUSTOMER_ID.getName()))
                    .roomNumber(source.getInt(ROOM_NUMBER.getName()))
                    .status(RequestStatus.atIndex(source.getInt(STATUS.getName())))
                    .entryDate(source.getDate(ENTRY_DATE.getName()).toLocalDate())
                    .leavingDate(source.getDate(LEAVING_DATE.getName()).toLocalDate())
                    .totalAmount(source.getBigDecimal(TOTAL_AMOUNT.getName()))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requestEntity;
    }
}

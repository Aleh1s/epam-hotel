package ua.aleh1s.hotelepam.model.sqlmapper;

import ua.aleh1s.hotelepam.model.entity.RequestEntity;
import ua.aleh1s.hotelepam.model.entity.RequestStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

import static ua.aleh1s.hotelepam.constant.SqlField.RequestTable.*;

public class SqlRequestEntityMapper {
    public RequestEntity map(ResultSet source) throws SQLException {
        return RequestEntity.Builder.newBuilder()
                .id(source.getLong(ID))
                .customerId(source.getLong(CUSTOMER_ID))
                .roomNumber(source.getInt(ROOM_NUMBER))
                .status(RequestStatus.atIndex(source.getInt(STATUS)))
                .entryDate(source.getDate(ENTRY_DATE).toLocalDate())
                .leavingDate(source.getDate(LEAVING_DATE).toLocalDate())
                .totalAmount(source.getBigDecimal(TOTAL_AMOUNT))
                .build();
    }
}

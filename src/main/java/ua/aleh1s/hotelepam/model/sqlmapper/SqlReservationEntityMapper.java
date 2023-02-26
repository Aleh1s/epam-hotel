package ua.aleh1s.hotelepam.model.sqlmapper;

import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.model.entity.ReservationStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import static ua.aleh1s.hotelepam.constant.SqlColumn.ReservationTable.*;

public class SqlReservationEntityMapper {
    public ReservationEntity map(ResultSet source) throws SQLException {
        Timestamp payedAt = source.getTimestamp(PAYED_AT.getName());
        return ReservationEntity.Builder.newBuilder()
                .id(source.getLong(ID.getName()))
                .roomNumber(source.getInt(ROOM_NUMBER.getName()))
                .customerId(source.getLong(CUSTOMER_ID.getName()))
                .entryDate(source.getDate(ENTRY_DATE.getName()).toLocalDate())
                .leavingDate(source.getDate(LEAVING_DATE.getName()).toLocalDate())
                .createdAt(source.getTimestamp(CREATED_AT.getName()).toLocalDateTime())
                .expiredAt(source.getTimestamp(EXPIRED_AT.getName()).toLocalDateTime())
                .payedAt(payedAt != null ? payedAt.toLocalDateTime() : null)
                .totalAmount(source.getBigDecimal(TOTAL_AMOUNT.getName()))
                .status(ReservationStatus.atIndex(source.getInt(STATUS.getName())))
                .build();
    }
}
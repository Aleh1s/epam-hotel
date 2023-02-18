package ua.aleh1s.hotelepam.model.sqlmapper;

import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.model.entity.ReservationStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import static ua.aleh1s.hotelepam.constant.SqlField.ReservationTable.*;

public class SqlReservationEntityMapper {
    public ReservationEntity map(ResultSet source) throws SQLException {
        Timestamp payedAt = source.getTimestamp(PAYED_AT);
        return ReservationEntity.Builder.newBuilder()
                .id(source.getLong(ID))
                .roomNumber(source.getInt(ROOM_NUMBER))
                .customerId(source.getLong(CUSTOMER_ID))
                .entryDate(source.getDate(ENTRY_DATE).toLocalDate())
                .leavingDate(source.getDate(LEAVING_DATE).toLocalDate())
                .createdAt(source.getTimestamp(CREATED_AT).toLocalDateTime())
                .expiredAt(source.getTimestamp(EXPIRED_AT).toLocalDateTime())
                .payedAt(payedAt != null ? payedAt.toLocalDateTime() : null)
                .totalAmount(source.getBigDecimal(TOTAL_AMOUNT))
                .status(ReservationStatus.atIndex(source.getInt(STATUS)))
                .build();
    }
}
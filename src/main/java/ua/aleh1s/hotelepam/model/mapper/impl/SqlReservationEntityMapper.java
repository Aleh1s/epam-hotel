package ua.aleh1s.hotelepam.model.mapper.impl;

import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.model.entity.ReservationStatus;
import ua.aleh1s.hotelepam.model.mapper.SqlEntityMapper;
import ua.aleh1s.hotelepam.model.mapper.exception.SqlEntityMapperException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import static ua.aleh1s.hotelepam.model.constant.SqlFieldName.*;

public class SqlReservationEntityMapper implements SqlEntityMapper<ReservationEntity> {
    @Override
    public ReservationEntity map(ResultSet source) throws SqlEntityMapperException {
        try {
            return buildEntity(source);
        } catch (SQLException e) {
            throw new SqlEntityMapperException(e);
        }
    }

    private ReservationEntity buildEntity(ResultSet source) throws SQLException {
        Timestamp payedAt = source.getTimestamp(RESERVATION_PAYED_AT);
        return ReservationEntity.Builder.newBuilder()
                .id(source.getLong(RESERVATION_ID))
                .roomNumber(source.getInt(RESERVATION_ROOM_NUMBER))
                .customerId(source.getLong(RESERVATION_CUSTOMER_ID))
                .entryDate(source.getDate(RESERVATION_ENTRY_DATE).toLocalDate())
                .leavingDate(source.getDate(RESERVATION_LEAVING_DATE).toLocalDate())
                .createdAt(source.getTimestamp(RESERVATION_CREATED_AT).toLocalDateTime())
                .expiredAt(source.getTimestamp(RESERVATION_EXPIRED_AT).toLocalDateTime())
                .payedAt(payedAt != null ? payedAt.toLocalDateTime() : null)
                .totalAmount(source.getBigDecimal(RESERVATION_TOTAL_AMOUNT))
                .status(ReservationStatus.atIndex(source.getInt(RESERVATION_STATUS)))
                .build();
    }
}

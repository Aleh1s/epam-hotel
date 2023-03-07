package ua.aleh1s.hotelepam.model.sqlmapper.impl;

import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.model.entity.ReservationStatus;
import ua.aleh1s.hotelepam.model.sqlmapper.SqlEntityMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import static ua.aleh1s.hotelepam.constant.SqlColumn.ReservationTable.*;

public class SqlReservationEntityMapper implements SqlEntityMapper<ReservationEntity> {
    public ReservationEntity apply(ResultSet source) {
        ReservationEntity reservation = null;
        try {
            Timestamp payedAt = source.getTimestamp(PAYED_AT.getName());
            reservation = ReservationEntity.builder()
                    .id(source.getLong(ID.getName()))
                    .roomNumber(source.getInt(ROOM_NUMBER.getName()))
                    .customerId(source.getLong(CUSTOMER_ID.getName()))
                    .checkIn(source.getDate(CHECK_IN.getName()).toLocalDate())
                    .checkOut(source.getDate(CHECK_OUT.getName()).toLocalDate())
                    .createdAt(source.getTimestamp(CREATED_AT.getName()).toLocalDateTime())
                    .expiredAt(source.getTimestamp(EXPIRED_AT.getName()).toLocalDateTime())
                    .payedAt(payedAt != null ? payedAt.toLocalDateTime() : null)
                    .totalAmount(source.getBigDecimal(TOTAL_AMOUNT.getName()))
                    .status(ReservationStatus.atIndex(source.getInt(STATUS.getName())))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservation;
    }
}
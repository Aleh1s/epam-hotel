package ua.aleh1s.hotelepam.mapper.sqlmapper.impl;

import ua.aleh1s.hotelepam.model.entity.ReservationTokenEntity;
import ua.aleh1s.hotelepam.mapper.sqlmapper.SqlEntityMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import static ua.aleh1s.hotelepam.constant.SqlColumn.ReservationTokenTable.*;

public class SqlReservationTokenEntityMapper implements SqlEntityMapper<ReservationTokenEntity> {

    public ReservationTokenEntity apply(ResultSet resultSet) {
        ReservationTokenEntity reservationToken = null;
        try {
            Timestamp confirmedAt = resultSet.getTimestamp(CONFIRMED_AT.getName());
            reservationToken = ReservationTokenEntity.builder()
                    .id(resultSet.getString(ID.getName()))
                    .createdAt(resultSet.getTimestamp(CREATED_AT.getName()).toLocalDateTime())
                    .expiredAt(resultSet.getTimestamp(EXPIRED_AT.getName()).toLocalDateTime())
                    .confirmedAt(confirmedAt != null ? confirmedAt.toLocalDateTime() : null)
                    .reservationId(resultSet.getLong(RESERVATION_ID.getName()))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservationToken;
    }

}

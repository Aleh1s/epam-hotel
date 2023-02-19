package ua.aleh1s.hotelepam.model.sqlmapper;

import ua.aleh1s.hotelepam.constant.SqlField;
import ua.aleh1s.hotelepam.model.entity.ReservationTokenEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import static ua.aleh1s.hotelepam.constant.SqlField.ReservationTokenTable.*;

public class SqlReservationTokenEntityMapper {

    public ReservationTokenEntity map(ResultSet resultSet) throws SQLException {
        Timestamp confirmedAt = resultSet.getTimestamp(CONFIRMED_AT);
        return ReservationTokenEntity.Builder.newBuilder()
                .id(resultSet.getString(ID))
                .createdAt(resultSet.getTimestamp(CREATED_AT).toLocalDateTime())
                .expiredAt(resultSet.getTimestamp(EXPIRED_AT).toLocalDateTime())
                .confirmedAt(confirmedAt != null ? confirmedAt.toLocalDateTime() : null)
                .reservationId(resultSet.getLong(RESERVATION_ID))
                .build();
    }

}

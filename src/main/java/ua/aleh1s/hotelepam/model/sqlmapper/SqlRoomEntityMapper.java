package ua.aleh1s.hotelepam.model.sqlmapper;

import ua.aleh1s.hotelepam.model.entity.RoomClass;
import ua.aleh1s.hotelepam.model.entity.RoomEntity;
import ua.aleh1s.hotelepam.model.entity.RoomStatus;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import static ua.aleh1s.hotelepam.model.constant.SqlFieldName.*;

public class SqlRoomEntityMapper {
    public RoomEntity map(ResultSet source) throws SQLException {
        Date date = source.getDate(ROOM_BUSY_UNTIL);
        return RoomEntity.Builder.newBuilder()
                .roomNumber(source.getInt(ROOM_ROOM_NUMBER))
                .roomClass(RoomClass.atIndex(source.getInt(ROOM_CLASS)))
                .status(RoomStatus.atIndex(source.getInt(ROOM_STATUS)))
                .description(source.getString(ROOM_DESCRIPTION))
                .busyUntil(date != null ? date.toLocalDate() : null)
                .price(BigDecimal.valueOf(source.getDouble(ROOM_PRICE)))
                .name(source.getString(ROOM_NAME))
                .attributes(source.getString(ROOM_ATTRIBUTES).split(","))
                .bedsNumber(source.getInt(ROOM_BEDS_NUMBER))
                .personsNumber(source.getInt(ROOM_PERSONS_NUMBER))
                .area(source.getInt(ROOM_AREA))
                .build();
    }
}

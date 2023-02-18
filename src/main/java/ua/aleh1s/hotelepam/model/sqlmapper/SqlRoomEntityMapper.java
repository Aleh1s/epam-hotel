package ua.aleh1s.hotelepam.model.sqlmapper;

import ua.aleh1s.hotelepam.model.entity.RoomClass;
import ua.aleh1s.hotelepam.model.entity.RoomEntity;
import ua.aleh1s.hotelepam.model.entity.RoomStatus;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import static ua.aleh1s.hotelepam.constant.SqlField.RoomTable.*;

public class SqlRoomEntityMapper {
    public RoomEntity map(ResultSet source) throws SQLException {
        Date date = source.getDate(BUSY_UNTIL);
        return RoomEntity.Builder.newBuilder()
                .roomNumber(source.getInt(ROOM_NUMBER))
                .roomClass(RoomClass.atIndex(source.getInt(CLASS)))
                .status(RoomStatus.atIndex(source.getInt(STATUS)))
                .description(source.getString(DESCRIPTION))
                .busyUntil(date != null ? date.toLocalDate() : null)
                .price(BigDecimal.valueOf(source.getDouble(PRICE)))
                .name(source.getString(NAME))
                .attributes(source.getString(ATTRIBUTES).split(","))
                .bedsNumber(source.getInt(BEDS_NUMBER))
                .personsNumber(source.getInt(PERSONS_NUMBER))
                .area(source.getInt(AREA))
                .build();
    }
}

package ua.aleh1s.hotelepam.model.sqlmapper.impl;

import ua.aleh1s.hotelepam.model.entity.RoomClass;
import ua.aleh1s.hotelepam.model.entity.RoomEntity;
import ua.aleh1s.hotelepam.model.entity.RoomStatus;
import ua.aleh1s.hotelepam.model.sqlmapper.SqlEntityMapper;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import static ua.aleh1s.hotelepam.constant.SqlColumn.RoomTable.*;

public class SqlRoomEntityMapper implements SqlEntityMapper<RoomEntity> {
    public RoomEntity apply(ResultSet source) {
        RoomEntity roomEntity = null;
        try {
            Date date = source.getDate(BUSY_UNTIL.getName());
            roomEntity = RoomEntity.Builder.newBuilder()
                    .roomNumber(source.getInt(ROOM_NUMBER.getName()))
                    .roomClass(RoomClass.atIndex(source.getInt(CLASS.getName())))
                    .status(RoomStatus.atIndex(source.getInt(STATUS.getName())))
                    .description(source.getString(DESCRIPTION.getName()))
                    .busyUntil(date != null ? date.toLocalDate() : null)
                    .price(BigDecimal.valueOf(source.getDouble(PRICE.getName())))
                    .name(source.getString(NAME.getName()))
                    .attributes(source.getString(ATTRIBUTES.getName()).split(","))
                    .bedsNumber(source.getInt(BEDS_NUMBER.getName()))
                    .personsNumber(source.getInt(PERSONS_NUMBER.getName()))
                    .area(source.getInt(AREA.getName()))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomEntity;
    }
}

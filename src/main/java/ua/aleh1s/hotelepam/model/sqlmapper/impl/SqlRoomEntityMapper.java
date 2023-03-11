package ua.aleh1s.hotelepam.model.sqlmapper.impl;

import ua.aleh1s.hotelepam.model.entity.RoomClass;
import ua.aleh1s.hotelepam.model.entity.RoomEntity;
import ua.aleh1s.hotelepam.model.sqlmapper.SqlEntityMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import static ua.aleh1s.hotelepam.constant.SqlColumn.RoomTable.*;

public class SqlRoomEntityMapper implements SqlEntityMapper<RoomEntity> {
    public RoomEntity apply(ResultSet source) {
        RoomEntity roomEntity = null;
        try {
            roomEntity = RoomEntity.builder()
                    .number(source.getInt(NUMBER.getName()))
                    .clazz(RoomClass.atIndex(source.getInt(CLAZZ.getName())))
                    .description(source.getString(DESCRIPTION.getName()))
                    .price(BigDecimal.valueOf(source.getDouble(PRICE.getName())))
                    .title(source.getString(TITLE.getName()))
                    .attributes(source.getString(ATTRIBUTES.getName()).split(","))
                    .beds(source.getInt(BEDS.getName()))
                    .guests(source.getInt(GUESTS.getName()))
                    .area(source.getInt(AREA.getName()))
                    .isUnavailable(source.getBoolean(IS_UNAVAILABLE.getName()))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomEntity;
    }
}

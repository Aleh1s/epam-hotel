package ua.aleh1s.hotelepam.mapper.sqlmapper.impl;

import ua.aleh1s.hotelepam.model.entity.RoomClass;
import ua.aleh1s.hotelepam.model.entity.RoomEntity;
import ua.aleh1s.hotelepam.mapper.sqlmapper.SqlEntityMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static ua.aleh1s.hotelepam.constant.SqlColumn.RoomTable.*;

public class SqlRoomEntityMapper implements SqlEntityMapper<RoomEntity> {
    public RoomEntity apply(ResultSet source) {

        RoomEntity roomEntity = null;
        try {
            String attributeStr = source.getString(ATTRIBUTES.getName());
            List<String> attributeList = Arrays.stream(attributeStr.split(",")).toList();
            roomEntity = RoomEntity.builder()
                    .number(source.getInt(NUMBER.getName()))
                    .clazz(RoomClass.atIndex(source.getInt(CLAZZ.getName())))
                    .description(source.getString(DESCRIPTION.getName()))
                    .price(BigDecimal.valueOf(source.getDouble(PRICE.getName())))
                    .title(source.getString(TITLE.getName()))
                    .attributes(attributeList)
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

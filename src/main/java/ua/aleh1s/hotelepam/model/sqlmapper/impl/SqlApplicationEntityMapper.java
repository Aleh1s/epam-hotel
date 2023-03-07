package ua.aleh1s.hotelepam.model.sqlmapper.impl;

import ua.aleh1s.hotelepam.model.entity.ApplicationEntity;
import ua.aleh1s.hotelepam.model.entity.ApplicationStatus;
import ua.aleh1s.hotelepam.model.entity.RoomClass;
import ua.aleh1s.hotelepam.model.sqlmapper.SqlEntityMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static ua.aleh1s.hotelepam.constant.SqlColumn.ApplicationTable.*;

public class SqlApplicationEntityMapper implements SqlEntityMapper<ApplicationEntity> {

    public ApplicationEntity apply(ResultSet source) {
        ApplicationEntity application = null;
        try {
            application = ApplicationEntity.builder()
                    .id(source.getLong(ID.getName()))
                    .guests(source.getInt(GUESTS.getName()))
                    .clazz(RoomClass.atIndex(source.getInt(ROOM_CLASS.getName())))
                    .checkIn(source.getDate(CHECK_IN.getName()).toLocalDate())
                    .checkOut(source.getDate(CHECK_OUT.getName()).toLocalDate())
                    .status(ApplicationStatus.atIndex(source.getInt(STATUS.getName())))
                    .customerId(source.getLong(CUSTOMER_ID.getName()))
                    .createdAt(source.getTimestamp(CREATED_AT.getName()).toLocalDateTime())
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return application;
    }
}

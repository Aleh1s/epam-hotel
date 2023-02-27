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
            application = ApplicationEntity.Builder.newBuilder()
                    .id(source.getLong(ID.getName()))
                    .guestsNumber(source.getInt(NUMBER_OF_GUESTS.getName()))
                    .roomClass(RoomClass.atIndex(source.getInt(ROOM_CLASS.getName())))
                    .entryDate(source.getDate(ENTRY_DATE.getName()).toLocalDate())
                    .leavingDate(source.getDate(LEAVING_DATE.getName()).toLocalDate())
                    .status(ApplicationStatus.atIndex(source.getInt(STATUS.getName())))
                    .customerId(source.getLong(CUSTOMER_ID.getName()))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return application;
    }
}

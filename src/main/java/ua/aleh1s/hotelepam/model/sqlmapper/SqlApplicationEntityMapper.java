package ua.aleh1s.hotelepam.model.sqlmapper;

import ua.aleh1s.hotelepam.model.entity.ApplicationEntity;
import ua.aleh1s.hotelepam.model.entity.ApplicationStatus;
import ua.aleh1s.hotelepam.model.entity.RoomClass;

import java.sql.ResultSet;
import java.sql.SQLException;

import static ua.aleh1s.hotelepam.model.constant.SqlFieldName.*;

public class SqlApplicationEntityMapper {
    public ApplicationEntity map(ResultSet source) throws SQLException {
        return ApplicationEntity.Builder.newBuilder()
                .id(source.getLong(APPLICATION_ID))
                .guestsNumber(source.getInt(APPLICATION_NUMBER_OF_GUESTS))
                .roomClass(RoomClass.atIndex(source.getInt(APPLICATION_ROOM_CLASS)))
                .entryDate(source.getDate(APPLICATION_ENTRY_DATE).toLocalDate())
                .leavingDate(source.getDate(APPLICATION_LEAVING_DATE).toLocalDate())
                .status(ApplicationStatus.atIndex(source.getInt(APPLICATION_STATUS)))
                .customerId(source.getLong(APPLICATION_CUSTOMER_ID))
                .build();
    }
}

package ua.aleh1s.hotelepam.model.sqlmapper;

import ua.aleh1s.hotelepam.model.entity.ApplicationEntity;
import ua.aleh1s.hotelepam.model.entity.ApplicationStatus;
import ua.aleh1s.hotelepam.model.entity.RoomClass;

import java.sql.ResultSet;
import java.sql.SQLException;

import static ua.aleh1s.hotelepam.model.constant.SqlField.ApplicationTable.*;

public class SqlApplicationEntityMapper {
    public ApplicationEntity map(ResultSet source) throws SQLException {
        return ApplicationEntity.Builder.newBuilder()
                .id(source.getLong(ID))
                .guestsNumber(source.getInt(NUMBER_OF_GUESTS))
                .roomClass(RoomClass.atIndex(source.getInt(ROOM_CLASS)))
                .entryDate(source.getDate(ENTRY_DATE).toLocalDate())
                .leavingDate(source.getDate(LEAVING_DATE).toLocalDate())
                .status(ApplicationStatus.atIndex(source.getInt(STATUS)))
                .customerId(source.getLong(CUSTOMER_ID))
                .build();
    }
}

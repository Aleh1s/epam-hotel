package ua.aleh1s.hotelepam.model.dao.impl;

import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.model.dao.DAO;
import ua.aleh1s.hotelepam.model.dao.DaoException;
import ua.aleh1s.hotelepam.model.entity.RoomEntity;
import ua.aleh1s.hotelepam.model.sqlmapper.SqlRoomEntityMapper;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ua.aleh1s.hotelepam.constant.SqlField.RoomTable.*;
import static ua.aleh1s.hotelepam.constant.SqlQuery.RoomTable.*;

public class RoomDAO extends DAO {

    private final SqlRoomEntityMapper mapper =
            AppContext.getInstance().getSqlRoomEntityMapper();

    public Optional<RoomEntity> findById(Integer roomNumber) throws DaoException {
        RoomEntity roomEntity = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_BY_ROOM_NUMBER)) {
            statement.setInt(1, roomNumber);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    roomEntity = mapper.map(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(roomEntity);
    }

    public void update(RoomEntity entity) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_BY_ROOM_NUMBER,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            statement.setLong(1, entity.getRoomNumber());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    resultSet.updateInt(ROOM_NUMBER, entity.getRoomNumber());
                    resultSet.updateInt(CLASS, entity.getRoomClass().getIndex());
                    resultSet.updateInt(STATUS, entity.getStatus().getIndex());
                    resultSet.updateString(DESCRIPTION, entity.getDescription());
                    resultSet.updateDate(BUSY_UNTIL, entity.getBusyUntil() != null ? Date.valueOf(entity.getBusyUntil()) : null);
                    resultSet.updateBigDecimal(PRICE, entity.getPrice());
                    resultSet.updateString(NAME, entity.getName());
                    resultSet.updateString(ATTRIBUTES, String.join(",", entity.getAttributes()));
                    resultSet.updateInt(BEDS_NUMBER, entity.getBedsNumber());
                    resultSet.updateInt(PERSONS_NUMBER, entity.getPersonsNumber());
                    resultSet.updateInt(AREA, entity.getArea());
                    resultSet.updateRow();
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public List<RoomEntity> getAll() throws DaoException {
        List<RoomEntity> roomEntityList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    roomEntityList.add(mapper.map(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return roomEntityList;
    }
}

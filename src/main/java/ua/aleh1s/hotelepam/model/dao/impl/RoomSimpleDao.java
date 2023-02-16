package ua.aleh1s.hotelepam.model.dao.impl;

import ua.aleh1s.hotelepam.controller.page.PageRequest;
import ua.aleh1s.hotelepam.model.constant.SqlQuery;
import ua.aleh1s.hotelepam.model.criteria.Criteria;
import ua.aleh1s.hotelepam.model.criteria.impl.RoomListCriteria;
import ua.aleh1s.hotelepam.model.dao.SimpleDao;
import ua.aleh1s.hotelepam.model.dao.exception.DaoException;
import ua.aleh1s.hotelepam.model.entity.RoomEntity;
import ua.aleh1s.hotelepam.model.mapper.exception.SqlEntityMapperException;
import ua.aleh1s.hotelepam.model.mapper.impl.SqlRoomEntityMapper;
import ua.aleh1s.hotelepam.model.pagination.Pagination;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ua.aleh1s.hotelepam.model.constant.SqlFieldName.*;
import static ua.aleh1s.hotelepam.model.constant.SqlQuery.*;
import static ua.aleh1s.hotelepam.model.constant.SqlQuery.ROOM_SELECT_BY_ROOM_NUMBER;

public class RoomSimpleDao extends SimpleDao<Integer, RoomEntity> {
    @Override
    public Optional<RoomEntity> findById(Integer roomNumber) throws DaoException {
        RoomEntity roomEntity = null;
        try (PreparedStatement statement = connection.prepareStatement(ROOM_SELECT_BY_ROOM_NUMBER)) {
            statement.setInt(1, roomNumber);
            try (ResultSet resultSet = statement.executeQuery()) {
                SqlRoomEntityMapper mapper = new SqlRoomEntityMapper();
                if (resultSet.next()) {
                    roomEntity = mapper.map(resultSet);
                }
            }
        } catch (SQLException | SqlEntityMapperException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(roomEntity);
    }

    @Override
    public void delete(RoomEntity entity) throws DaoException {

    }

    @Override
    public void update(RoomEntity entity) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(ROOM_SELECT_BY_ROOM_NUMBER,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            statement.setLong(1, entity.getRoomNumber());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    resultSet.updateInt(ROOM_ROOM_NUMBER, entity.getRoomNumber());
                    resultSet.updateInt(ROOM_CLASS, entity.getRoomClass().getIndex());
                    resultSet.updateInt(ROOM_STATUS, entity.getStatus().getIndex());
                    resultSet.updateString(ROOM_DESCRIPTION, entity.getDescription());
                    resultSet.updateDate(ROOM_BUSY_UNTIL, entity.getBusyUntil() != null ? Date.valueOf(entity.getBusyUntil()) : null);
                    resultSet.updateBigDecimal(ROOM_PRICE, entity.getPrice());
                    resultSet.updateString(ROOM_NAME, entity.getName());
                    resultSet.updateString(ROOM_ATTRIBUTES, String.join(",", entity.getAttributes()));
                    resultSet.updateInt(ROOM_BEDS_NUMBER, entity.getBedsNumber());
                    resultSet.updateInt(ROOM_PERSONS_NUMBER, entity.getPersonsNumber());
                    resultSet.updateInt(ROOM_AREA, entity.getArea());
                    resultSet.updateRow();
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void save(RoomEntity entity) throws DaoException {

    }

    public List<RoomEntity> getAll(Criteria criteria, Pagination pagination) throws DaoException {
        List<RoomEntity> roomEntities = new ArrayList<>();
        String query = "select * from room " + criteria.build() + " " + pagination.build();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                SqlRoomEntityMapper mapper = new SqlRoomEntityMapper();
                while (resultSet.next()) {
                    RoomEntity roomEntity = mapper.map(resultSet);
                    roomEntities.add(roomEntity);
                }
            }
        } catch (SQLException | SqlEntityMapperException e) {
            throw new DaoException(e);
        }
        return roomEntities;
    }

    public Integer count(RoomListCriteria criteria) throws DaoException {
        int count = 0;
        String query = "select count(*) from room " + criteria.build();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    count = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return count;
    }

    public List<RoomEntity> getAll(PageRequest pageRequest) throws DaoException {
        List<RoomEntity> roomEntityList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(ROOM_SELECT_ALL_PAGEABLE)) {
            statement.setInt(1, pageRequest.getOffset());
            statement.setInt(2, pageRequest.getLimit());
            try (ResultSet resultSet = statement.executeQuery()) {
                SqlRoomEntityMapper mapper = new SqlRoomEntityMapper();
                while (resultSet.next()) {
                    roomEntityList.add(mapper.map(resultSet));
                }
            }
        } catch (SQLException | SqlEntityMapperException e) {
            throw new DaoException(e);
        }
        return roomEntityList;
    }

    public Integer count() throws DaoException {
        int count = 0;
        try (PreparedStatement statement = connection.prepareStatement(ROOM_COUNT_ALL)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    count = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return count;
    }

    public List<RoomEntity> getAll() throws DaoException {
        List<RoomEntity> roomEntityList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(ROOM_SELECT_ALL)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                SqlRoomEntityMapper mapper = new SqlRoomEntityMapper();
                while (resultSet.next()) {
                    roomEntityList.add(mapper.map(resultSet));
                }
            }
        } catch (SQLException | SqlEntityMapperException e) {
            throw new DaoException(e);
        }
        return roomEntityList;
    }
}

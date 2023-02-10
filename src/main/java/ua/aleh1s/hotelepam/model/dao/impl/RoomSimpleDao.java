package ua.aleh1s.hotelepam.model.dao.impl;

import ua.aleh1s.hotelepam.model.criteria.Criteria;
import ua.aleh1s.hotelepam.model.criteria.RoomListCriteria;
import ua.aleh1s.hotelepam.model.dao.SimpleDao;
import ua.aleh1s.hotelepam.model.dao.exception.DaoException;
import ua.aleh1s.hotelepam.model.entity.RoomEntity;
import ua.aleh1s.hotelepam.model.mapper.exception.SqlEntityMapperException;
import ua.aleh1s.hotelepam.model.mapper.impl.SqlRoomEntityMapper;
import ua.aleh1s.hotelepam.model.pagination.Pagination;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomSimpleDao extends SimpleDao<Integer, RoomEntity> {
    @Override
    public Optional<RoomEntity> findById(Integer id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public void delete(RoomEntity entity) throws DaoException {

    }

    @Override
    public void update(RoomEntity entity) throws DaoException {

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
}

package ua.aleh1s.hotelepam.model.dao.impl;

import ua.aleh1s.hotelepam.model.constant.SqlQuery;
import ua.aleh1s.hotelepam.model.dao.SimpleDao;
import ua.aleh1s.hotelepam.model.dao.exception.DaoException;
import ua.aleh1s.hotelepam.model.entity.RequestEntity;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class RequestSimpleDao extends SimpleDao<Long, RequestEntity> {
    @Override
    public Optional<RequestEntity> findById(Long id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public void delete(RequestEntity entity) throws DaoException {

    }

    @Override
    public void update(RequestEntity entity) throws DaoException {

    }

    @Override
    public void save(RequestEntity entity) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.REQUEST_INSERT)) {
            statement.setInt(1, entity.getRoomNumber());
            statement.setLong(2, entity.getManagerId());
            statement.setLong(3, entity.getCustomerId());
            statement.setInt(4, entity.getStatus().getIndex());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}

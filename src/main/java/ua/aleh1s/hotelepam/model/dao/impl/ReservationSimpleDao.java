package ua.aleh1s.hotelepam.model.dao.impl;

import ua.aleh1s.hotelepam.model.constant.SqlQuery;
import ua.aleh1s.hotelepam.model.dao.SimpleDao;
import ua.aleh1s.hotelepam.model.dao.exception.DaoException;
import ua.aleh1s.hotelepam.model.entity.ReservationEntity;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;

public class ReservationSimpleDao extends SimpleDao<Long, ReservationEntity> {


    @Override
    public Optional<ReservationEntity> findById(Long id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public void delete(ReservationEntity entity) throws DaoException {

    }

    @Override
    public void update(ReservationEntity entity) throws DaoException {

    }

    @Override
    public void save(ReservationEntity entity) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.RESERVATION_INSERT)) {
            statement.setInt(1, entity.getRoomNumber());
            statement.setLong(2, entity.getCustomerId());
            statement.setDate(3, Date.valueOf(entity.getEntryDate()));
            statement.setDate(4, Date.valueOf(entity.getLeavingDate()));
            statement.setTimestamp(5, Timestamp.valueOf(entity.getCreatedAt()));
            statement.setTimestamp(6, Timestamp.valueOf(entity.getExpiredAt()));
            statement.setTimestamp(7, entity.getPayedAt() != null ? Timestamp.valueOf(entity.getPayedAt()) : null);
            statement.setBigDecimal(8, entity.getTotalAmount());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

}

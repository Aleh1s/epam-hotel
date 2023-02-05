package ua.aleh1s.hotelepam.model.dao.impl;

import ua.aleh1s.hotelepam.model.dao.SimpleDao;
import ua.aleh1s.hotelepam.model.dao.exception.DaoException;
import ua.aleh1s.hotelepam.model.entity.ApplicationEntity;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

import static ua.aleh1s.hotelepam.model.constant.SqlQuery.APPLICATION_INSERT;

public class ApplicationSimpleDao extends SimpleDao<Long, ApplicationEntity> {
    @Override
    public Optional<ApplicationEntity> findById(Long id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public void delete(ApplicationEntity entity) throws DaoException {

    }

    @Override
    public void update(ApplicationEntity entity) throws DaoException {

    }

    @Override
    public void save(ApplicationEntity entity) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(APPLICATION_INSERT)) {
            statement.setInt(1, entity.getGuestsNumber());
            statement.setInt(2, entity.getApartmentClass().getIndex());
            statement.setDate(3, Date.valueOf(entity.getEntryDate()));
            statement.setDate(4, Date.valueOf(entity.getLeavingDate()));
            statement.setInt(5, entity.getStatus().getIndex());
            statement.setLong(6, entity.getCustomerId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}

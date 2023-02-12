package ua.aleh1s.hotelepam.model.dao.impl;

import ua.aleh1s.hotelepam.model.criteria.Criteria;
import ua.aleh1s.hotelepam.model.dao.SimpleDao;
import ua.aleh1s.hotelepam.model.dao.exception.DaoException;
import ua.aleh1s.hotelepam.model.entity.ApplicationEntity;
import ua.aleh1s.hotelepam.model.mapper.exception.SqlEntityMapperException;
import ua.aleh1s.hotelepam.model.mapper.impl.SqlApplicationEntityMapper;
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

public class ApplicationSimpleDao extends SimpleDao<Long, ApplicationEntity> {
    @Override
    public Optional<ApplicationEntity> findById(Long id) throws DaoException {
        ApplicationEntity application = null;
        try (PreparedStatement statement = connection.prepareStatement(APPLICATION_SELECT_BY_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    SqlApplicationEntityMapper mapper = new SqlApplicationEntityMapper();
                    application = mapper.map(resultSet);
                }
            }
        } catch (SQLException | SqlEntityMapperException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(application);
    }

    @Override
    public void delete(ApplicationEntity entity) throws DaoException {

    }

    @Override
    public void update(ApplicationEntity entity) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(APPLICATION_SELECT_BY_ID,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            statement.setLong(1, entity.getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    resultSet.updateLong(APPLICATION_ID, entity.getId());
                    resultSet.updateInt(APPLICATION_NUMBER_OF_GUESTS, entity.getGuestsNumber());
                    resultSet.updateInt(APPLICATION_ROOM_CLASS, entity.getRoomClass().getIndex());
                    resultSet.updateDate(APPLICATION_LEAVING_DATE, Date.valueOf(entity.getLeavingDate()));
                    resultSet.updateDate(APPLICATION_ENTRY_DATE, Date.valueOf(entity.getEntryDate()));
                    resultSet.updateInt(APPLICATION_STATUS, entity.getStatus().getIndex());
                    resultSet.updateLong(APPLICATION_CUSTOMER_ID, entity.getCustomerId());
                    resultSet.updateRow();
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void save(ApplicationEntity entity) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(APPLICATION_INSERT)) {
            statement.setInt(1, entity.getGuestsNumber());
            statement.setInt(2, entity.getRoomClass().getIndex());
            statement.setDate(3, Date.valueOf(entity.getEntryDate()));
            statement.setDate(4, Date.valueOf(entity.getLeavingDate()));
            statement.setInt(5, entity.getStatus().getIndex());
            statement.setLong(6, entity.getCustomerId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public List<ApplicationEntity> getAll(Criteria criteria, Pagination pagination) throws DaoException {
        List<ApplicationEntity> applicationList = new ArrayList<>();
        String query = "select * from \"application\" " + criteria.build() + " " + pagination.build();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            SqlApplicationEntityMapper mapper = new SqlApplicationEntityMapper();
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    ApplicationEntity map = mapper.map(resultSet);
                    applicationList.add(map);
                }
            }
        } catch (SQLException | SqlEntityMapperException e) {
            throw new DaoException(e);
        }
        return applicationList;
    }

    public Integer count(Criteria criteria) throws DaoException {
        Integer count = 0;
        String query = "select count(*) from \"application\" " + criteria.build();
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

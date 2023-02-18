package ua.aleh1s.hotelepam.model.dao.impl;

import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.model.dao.DAO;
import ua.aleh1s.hotelepam.model.dao.DaoException;
import ua.aleh1s.hotelepam.model.entity.ApplicationEntity;
import ua.aleh1s.hotelepam.model.entity.ApplicationStatus;
import ua.aleh1s.hotelepam.model.pagination.PageRequest;
import ua.aleh1s.hotelepam.model.sqlmapper.SqlApplicationEntityMapper;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ua.aleh1s.hotelepam.constant.SqlField.ApplicationTable.*;
import static ua.aleh1s.hotelepam.constant.SqlQuery.ApplicationTable.*;

public class ApplicationDAO extends DAO {

    private final SqlApplicationEntityMapper mapper =
            AppContext.getInstance().getSqlApplicationEntityMapper();

    public Optional<ApplicationEntity> findById(Long id) throws DaoException {
        ApplicationEntity application = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_BY_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    application = mapper.map(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(application);
    }

    public void update(ApplicationEntity entity) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_BY_ID,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            statement.setLong(1, entity.getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    resultSet.updateLong(ID, entity.getId());
                    resultSet.updateInt(NUMBER_OF_GUESTS, entity.getGuestsNumber());
                    resultSet.updateInt(ROOM_CLASS, entity.getRoomClass().getIndex());
                    resultSet.updateDate(LEAVING_DATE, Date.valueOf(entity.getLeavingDate()));
                    resultSet.updateDate(ENTRY_DATE, Date.valueOf(entity.getEntryDate()));
                    resultSet.updateInt(STATUS, entity.getStatus().getIndex());
                    resultSet.updateLong(CUSTOMER_ID, entity.getCustomerId());
                    resultSet.updateRow();
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void save(ApplicationEntity entity) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT)) {
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

    public List<ApplicationEntity> getAllByStatus(ApplicationStatus status, PageRequest pageRequest) throws DaoException {
        List<ApplicationEntity> applicationList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_PAGE_BY_STATUS)) {
            statement.setInt(1, status.getIndex());
            statement.setInt(2, pageRequest.getOffset());
            statement.setInt(3, pageRequest.getLimit());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    applicationList.add(mapper.map(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return applicationList;
    }



    public Integer countByStatus(ApplicationStatus status) throws DaoException {
        int count = 0;
        try (PreparedStatement statement = connection.prepareStatement(COUNT_ALL_BY_STATUS)) {
            statement.setInt(1, status.getIndex());
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

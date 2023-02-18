package ua.aleh1s.hotelepam.model.dao.impl;

import ua.aleh1s.hotelepam.AppContext;
import ua.aleh1s.hotelepam.model.criteria.Criteria;
import ua.aleh1s.hotelepam.model.dao.DAO;
import ua.aleh1s.hotelepam.model.dao.exception.DaoException;
import ua.aleh1s.hotelepam.model.entity.RequestEntity;
import ua.aleh1s.hotelepam.model.pagination.Pagination;
import ua.aleh1s.hotelepam.model.sqlmapper.SqlRequestEntityMapper;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ua.aleh1s.hotelepam.constant.SqlField.RequestTable.*;
import static ua.aleh1s.hotelepam.constant.SqlQuery.RequestTable.*;

public class RequestDAO extends DAO {

    private final SqlRequestEntityMapper mapper =
            AppContext.getInstance().getSqlRequestEntityMapper();

    public Optional<RequestEntity> findById(Long id) throws DaoException {
        RequestEntity request = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_BY_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    request = mapper.map(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(request);
    }

    public void update(RequestEntity entity) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_BY_ID,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            statement.setLong(1, entity.getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    resultSet.updateLong(ID, entity.getId());
                    resultSet.updateInt(ROOM_NUMBER, entity.getRoomNumber());
                    resultSet.updateLong(CUSTOMER_ID, entity.getCustomerId());
                    resultSet.updateInt(STATUS, entity.getStatus().getIndex());
                    resultSet.updateDate(ENTRY_DATE, Date.valueOf(entity.getEntryDate()));
                    resultSet.updateDate(LEAVING_DATE, Date.valueOf(entity.getLeavingDate()));
                    resultSet.updateBigDecimal(TOTAL_AMOUNT, entity.getTotalAmount());
                    resultSet.updateRow();
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void save(RequestEntity entity) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT)) {
            statement.setInt(1, entity.getRoomNumber());
            statement.setLong(2, entity.getCustomerId());
            statement.setInt(3, entity.getStatus().getIndex());
            statement.setDate(4, Date.valueOf(entity.getEntryDate()));
            statement.setDate(5, Date.valueOf(entity.getLeavingDate()));
            statement.setBigDecimal(6, entity.getTotalAmount());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public List<RequestEntity> getAll(Criteria criteria, Pagination pagination) throws DaoException {
        List<RequestEntity> requestEntityList = new ArrayList<>();
        String query = "select * from \"request\" " + criteria.build() + " " + pagination.build();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    requestEntityList.add(mapper.map(resultSet));
                }
            }
        } catch (SQLException  e) {
            throw new DaoException(e);
        }
        return requestEntityList;
    }

    public Integer count(Criteria criteria) throws DaoException {
        int count = 0;
        String query = "select count(*) from \"request\" " + criteria.build();
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

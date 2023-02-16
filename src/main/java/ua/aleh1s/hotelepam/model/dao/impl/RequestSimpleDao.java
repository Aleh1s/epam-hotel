package ua.aleh1s.hotelepam.model.dao.impl;

import ua.aleh1s.hotelepam.model.constant.SqlFieldName;
import ua.aleh1s.hotelepam.model.constant.SqlQuery;
import ua.aleh1s.hotelepam.model.criteria.Criteria;
import ua.aleh1s.hotelepam.model.dao.SimpleDao;
import ua.aleh1s.hotelepam.model.dao.exception.DaoException;
import ua.aleh1s.hotelepam.model.entity.RequestEntity;
import ua.aleh1s.hotelepam.model.entity.RequestStatus;
import ua.aleh1s.hotelepam.model.mapper.exception.SqlEntityMapperException;
import ua.aleh1s.hotelepam.model.mapper.impl.SqlRequestEntityMapper;
import ua.aleh1s.hotelepam.model.pagination.Pagination;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ua.aleh1s.hotelepam.model.constant.SqlQuery.REQUEST_SELECT_BY_ID;

public class RequestSimpleDao extends SimpleDao<Long, RequestEntity> {
    @Override
    public Optional<RequestEntity> findById(Long id) throws DaoException {
        RequestEntity request = null;
        try (PreparedStatement statement = connection.prepareStatement(REQUEST_SELECT_BY_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    SqlRequestEntityMapper mapper = new SqlRequestEntityMapper();
                    request = mapper.map(resultSet);
                }
            }
        } catch (SQLException | SqlEntityMapperException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(request);
    }

    @Override
    public void delete(RequestEntity entity) throws DaoException {

    }

    @Override
    public void update(RequestEntity entity) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(REQUEST_SELECT_BY_ID,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            statement.setLong(1, entity.getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    resultSet.updateLong(SqlFieldName.REQUEST_ID, entity.getId());
                    resultSet.updateInt(SqlFieldName.REQUEST_ROOM_NUMBER, entity.getRoomNumber());
                    resultSet.updateLong(SqlFieldName.REQUEST_CUSTOMER_ID, entity.getCustomerId());
                    resultSet.updateInt(SqlFieldName.REQUEST_STATUS, entity.getStatus().getIndex());
                    resultSet.updateDate(SqlFieldName.REQUEST_ENTRY_DATE, Date.valueOf(entity.getEntryDate()));
                    resultSet.updateDate(SqlFieldName.REQUEST_LEAVING_DATE, Date.valueOf(entity.getLeavingDate()));
                    resultSet.updateBigDecimal(SqlFieldName.REQUEST_TOTAL_AMOUNT, entity.getTotalAmount());
                    resultSet.updateRow();
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void save(RequestEntity entity) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.REQUEST_INSERT)) {
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
                SqlRequestEntityMapper mapper = new SqlRequestEntityMapper();
                while (resultSet.next()) {
                    requestEntityList.add(mapper.map(resultSet));
                }
            }
        } catch (SQLException | SqlEntityMapperException e) {
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

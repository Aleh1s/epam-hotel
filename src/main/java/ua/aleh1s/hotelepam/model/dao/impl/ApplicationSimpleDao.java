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

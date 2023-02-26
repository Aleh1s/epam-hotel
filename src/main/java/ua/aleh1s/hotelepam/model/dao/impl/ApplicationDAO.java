package ua.aleh1s.hotelepam.model.dao.impl;

import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.model.dao.DAO;
import ua.aleh1s.hotelepam.model.dao.DaoException;
import ua.aleh1s.hotelepam.model.entity.ApplicationEntity;
import ua.aleh1s.hotelepam.model.queryspecification.QuerySpecification;
import ua.aleh1s.hotelepam.model.sqlmapper.SqlApplicationEntityMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ua.aleh1s.hotelepam.constant.SqlColumn.ApplicationTable.*;
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
                    resultSet.updateLong(ID.getName(), entity.getId());
                    resultSet.updateInt(NUMBER_OF_GUESTS.getName(), entity.getGuestsNumber());
                    resultSet.updateInt(ROOM_CLASS.getName(), entity.getRoomClass().getIndex());
                    resultSet.updateDate(LEAVING_DATE.getName(), Date.valueOf(entity.getLeavingDate()));
                    resultSet.updateDate(ENTRY_DATE.getName(), Date.valueOf(entity.getEntryDate()));
                    resultSet.updateInt(STATUS.getName(), entity.getStatus().getIndex());
                    resultSet.updateLong(CUSTOMER_ID.getName(), entity.getCustomerId());
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

    public List<ApplicationEntity> getAllBySpecification(QuerySpecification specification) throws DaoException {
        String query = specification.getQuery();

        List<ApplicationEntity> applicationList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            specification.injectValues(statement);

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

    public long countBySpecification(QuerySpecification specification) throws DaoException {
        String query = specification.getQuery();

        long count = 0;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            specification.injectValues(statement);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    count = resultSet.getLong(1);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return count;
    }

//    public Page<ApplicationEntity> getPageBySpecification(WhereSpecification whereSpecification, PageRequest pageRequest) throws DaoException {
//        QueryBuilder selectQueryBuilder = QueryBuilder.newQueryBuilder(SqlBase.SELECT, "application");
//        selectQueryBuilder.addWhereSpecification(whereSpecification);
//        selectQueryBuilder.addPageRequest(pageRequest);
//
//        List<ApplicationEntity> applicationEntityList = new ArrayList<>();
//        try (PreparedStatement statement = connection.prepareStatement(selectQueryBuilder.build())) {
//            selectQueryBuilder.injectValues(statement);
//
//            try (ResultSet resultSet = statement.executeQuery()) {
//                while (resultSet.next()) {
//                    applicationEntityList.add(mapper.map(resultSet));
//                }
//            }
//        } catch (SQLException e) {
//            throw new DaoException(e);
//        }
//
//        QueryBuilder countQueryBuilder = QueryBuilder.newQueryBuilder(SqlBase.COUNT, "application");
//        countQueryBuilder.addWhereSpecification(whereSpecification);
//
//        int totalNumber = 0;
//        try (PreparedStatement statement = connection.prepareStatement(countQueryBuilder.build())) {
//            countQueryBuilder.injectValues(statement);
//
//            try (ResultSet resultSet = statement.executeQuery()) {
//                if (resultSet.next()) {
//                    totalNumber = resultSet.getInt(1);
//                }
//            }
//        } catch (SQLException e) {
//            throw new DaoException(e);
//        }
//
//        return Page.of(applicationEntityList, totalNumber);
//    }
}

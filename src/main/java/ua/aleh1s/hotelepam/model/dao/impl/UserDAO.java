package ua.aleh1s.hotelepam.model.dao.impl;

import ua.aleh1s.hotelepam.AppContext;
import ua.aleh1s.hotelepam.model.dao.DAO;
import ua.aleh1s.hotelepam.model.dao.exception.DaoException;
import ua.aleh1s.hotelepam.model.entity.UserEntity;
import ua.aleh1s.hotelepam.model.sqlmapper.SqlUserEntityMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static ua.aleh1s.hotelepam.constant.SqlField.UserTable.*;
import static ua.aleh1s.hotelepam.constant.SqlQuery.UserTable.*;

public class UserDAO extends DAO {

    private final SqlUserEntityMapper mapper =
            AppContext.getInstance().getSqlUserEntityMapper();

    public Optional<UserEntity> findById(Long id) throws DaoException {
        return findAndMap(SELECT_ALL_BY_ID, id);
    }

    public Optional<UserEntity> findByEmail(String email) throws DaoException {
        return findAndMap(SELECT_ALL_BY_EMAIL, email);
    }

    public Optional<UserEntity> findByPhoneNumber(String phoneNumber) throws DaoException {
        return findAndMap(SELECT_ALL_BY_PHONE_NUMBER, phoneNumber);
    }

    public Optional<UserEntity> findAndMap(String query, Object parameter) throws DaoException {
        UserEntity userEntity = null;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            if (parameter instanceof String) {
                statement.setString(1, (String) parameter);
            } else if (parameter instanceof Long) {
                statement.setLong(1, (Long) parameter);
            }
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    userEntity = mapper.map(resultSet);
                }
            }
        } catch (SQLException  e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(userEntity);
    }

    public void update(UserEntity entity) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_BY_ID,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            statement.setLong(1, entity.getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    resultSet.updateLong(ID, entity.getId());
                    resultSet.updateString(EMAIL, entity.getEmail());
                    resultSet.updateString(FIRST_NAME, entity.getFirstName());
                    resultSet.updateString(LAST_NAME, entity.getLastName());
                    resultSet.updateString(PHONE_NUMBER, entity.getPhoneNumber());
                    resultSet.updateString(PASSWORD, entity.getPassword());
                    resultSet.updateString(LOCALE, entity.getLocale().getLanguage());
                    resultSet.updateString(ROLE, entity.getRole().name());
                    resultSet.updateBigDecimal(ACCOUNT, entity.getAccount());
                    resultSet.updateRow();
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void save(UserEntity entity) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT)) {
            statement.setString(1, entity.getEmail());
            statement.setString(2, entity.getFirstName());
            statement.setString(3, entity.getLastName());
            statement.setString(4, entity.getPhoneNumber());
            statement.setString(5, entity.getPassword());
            statement.setString(6, entity.getLocale().getLanguage());
            statement.setString(7, entity.getRole().name());
            statement.setBigDecimal(8, entity.getAccount());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}

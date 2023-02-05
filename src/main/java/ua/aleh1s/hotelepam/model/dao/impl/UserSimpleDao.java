package ua.aleh1s.hotelepam.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.aleh1s.hotelepam.model.dao.SimpleDao;
import ua.aleh1s.hotelepam.model.dao.exception.DaoException;
import ua.aleh1s.hotelepam.model.entity.UserEntity;
import ua.aleh1s.hotelepam.model.mapper.exception.SqlEntityMapperException;
import ua.aleh1s.hotelepam.model.mapper.impl.SqlUserEntityMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import static ua.aleh1s.hotelepam.model.constant.SqlFieldName.*;
import static ua.aleh1s.hotelepam.model.constant.SqlQuery.*;

public class UserSimpleDao extends SimpleDao<Long, UserEntity> {
    @Override
    public Optional<UserEntity> findById(Long id) throws DaoException {
        return findAndMap(USER_SELECT_BY_ID, id);
    }

    public Optional<UserEntity> findByEmail(String email) throws DaoException {
        return findAndMap(USER_SELECT_BY_EMAIL, email);
    }

    public Optional<UserEntity> findByPhoneNumber(String phoneNumber) throws DaoException {
        return findAndMap(USER_SELECT_BY_PHONE_NUMBER, phoneNumber);
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
                    SqlUserEntityMapper userMapper = new SqlUserEntityMapper();
                    userEntity = userMapper.map(resultSet);
                }
            }
        } catch (SQLException | SqlEntityMapperException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(userEntity);
    }

    @Override
    public void delete(UserEntity entity) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(USER_DELETE_BY_ID)) {
            statement.setLong(1, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(UserEntity entity) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(USER_SELECT_BY_ID,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            statement.setLong(1, entity.getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    resultSet.updateLong(USER_ID, entity.getId());
                    resultSet.updateString(USER_EMAIL, entity.getEmail());
                    resultSet.updateString(USER_FIRST_NAME, entity.getFirstName());
                    resultSet.updateString(USER_LAST_NAME, entity.getLastName());
                    resultSet.updateString(USER_PHONE_NUMBER, entity.getPhoneNumber());
                    resultSet.updateString(USER_PASSWORD, entity.getPassword());
                    resultSet.updateString(USER_TIMEZONE, entity.getTimezone().getId());
                    resultSet.updateString(USER_LOCALE, entity.getLocale().getLanguage());
                    resultSet.updateString(USER_ROLE, entity.getRole().name());
                    resultSet.updateRow();
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void save(UserEntity entity) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(USER_INSERT)) {
            statement.setString(1, entity.getEmail());
            statement.setString(2, entity.getFirstName());
            statement.setString(3, entity.getLastName());
            statement.setString(4, entity.getPhoneNumber());
            statement.setString(5, entity.getPassword());
            statement.setString(6, entity.getTimezone().getId());
            statement.setString(7, entity.getLocale().getLanguage());
            statement.setString(8, entity.getRole().name());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}

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

public class UserSimpleDao extends SimpleDao<String, UserEntity> {
    private static final Logger log = LogManager.getLogger(UserSimpleDao.class);

    @Override
    public Optional<UserEntity> findBy(String email) throws DaoException {
        log.trace("Find user entity by email {}", email);
        try (PreparedStatement statement = connection.prepareStatement(USER_SELECT_BY_EMAIL)) {
            statement.setString(1, email);
            Optional<UserEntity> userEntityOptional;
            try (ResultSet resultSet = statement.executeQuery()) {
                SqlUserEntityMapper userMapper = new SqlUserEntityMapper();
                userEntityOptional = userMapper.map(resultSet);
            }
            if (userEntityOptional.isPresent())
                log.trace("User entity with email {} is found", email);
            else
                log.trace("User entity with email {} is not found", email);
            return userEntityOptional;
        } catch (SQLException | SqlEntityMapperException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int delete(UserEntity entity) throws DaoException {
        log.trace("Delete user entity with email {}", entity.getEmail());
        try (PreparedStatement statement = connection.prepareStatement(USER_DELETE_BY_ID)) {
            statement.setLong(1, entity.getId());
            int number = statement.executeUpdate();
            log.trace("Number of deleted user entities is {}", number);
            return number;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int update(UserEntity entity) throws DaoException {
        log.trace("Update user entity with email {}", entity.getEmail());
        try (PreparedStatement statement = connection.prepareStatement(USER_SELECT_BY_ID,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            statement.setLong(1, entity.getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    resultSet.updateLong(USER_ID, entity.getId());
                    resultSet.updateString(USER_EMAIL, entity.getEmail());
                    resultSet.updateString(USER_PASSWORD, entity.getPassword());
                    resultSet.updateString(USER_TIMEZONE, entity.getTimezone().getId());
                    resultSet.updateString(USER_LOCALE, entity.getLocale().getLanguage());
                    resultSet.updateString(USER_ROLE, entity.getRole().name());
                    resultSet.updateRow();
                    log.trace("User with email {} is updated", entity.getEmail());
                    return 1;
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        log.trace("User entity with email {} does not exist", entity.getEmail());
        return 0;
    }

    @Override
    public UserEntity save(UserEntity entity) throws DaoException {
        log.trace("Create user with email {}", entity.getEmail());
        try (PreparedStatement statement = connection.prepareStatement(USER_INSERT,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getEmail());
            statement.setString(2, entity.getPassword());
            statement.setString(3, entity.getTimezone().getId());
            statement.setString(4, entity.getLocale().getLanguage());
            statement.setString(5, entity.getRole().name());
            statement.executeUpdate();
            log.trace("User with email {} was created", entity.getEmail());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return entity;
    }
}

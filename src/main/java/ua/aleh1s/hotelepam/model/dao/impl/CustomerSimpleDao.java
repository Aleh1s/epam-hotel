package ua.aleh1s.hotelepam.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.aleh1s.hotelepam.model.dao.SimpleDao;
import ua.aleh1s.hotelepam.model.dao.exception.DaoException;
import ua.aleh1s.hotelepam.model.entity.CustomerEntity;
import ua.aleh1s.hotelepam.model.mapper.exception.SqlEntityMapperException;
import ua.aleh1s.hotelepam.model.mapper.impl.SqlCustomerEntityMapper;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

import static ua.aleh1s.hotelepam.model.constant.SqlFieldName.*;
import static ua.aleh1s.hotelepam.model.constant.SqlQuery.*;

public class CustomerSimpleDao extends SimpleDao<String, CustomerEntity> {
    private static final Logger log = LogManager.getLogger(CustomerSimpleDao.class);

    @Override
    public Optional<CustomerEntity> findBy(String email) throws DaoException {
        log.trace("Find customer entity by email {}", email);
        try (PreparedStatement statement = connection.prepareStatement(CUSTOMER_SELECT_BY_EMAIL)) {
            statement.setString(1, email);
            Optional<CustomerEntity> customerEntityOptional;
            try (ResultSet resultSet = statement.executeQuery()) {
                SqlCustomerEntityMapper customerMapper = new SqlCustomerEntityMapper();
                customerEntityOptional = customerMapper.map(resultSet);
            }
            if (customerEntityOptional.isPresent())
                log.trace("Customer entity with email {} is found", email);
            else
                log.trace("Customer entity with email {} is not found", email);
            return customerEntityOptional;
        } catch (SQLException | SqlEntityMapperException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int delete(CustomerEntity entity) throws DaoException {
        log.trace("Delete customer entity with email {}", entity.getEmail());
        try (PreparedStatement statement = connection.prepareStatement(CUSTOMER_DELETE_BY_ID)) {
            statement.setLong(1, entity.getId());
            int number = statement.executeUpdate();
            log.trace("Number of deleted customer entities is {}", number);
            return number;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int update(CustomerEntity entity) throws DaoException {
        log.trace("Update customer entity with email {}", entity.getEmail());
        try (PreparedStatement statement = connection.prepareStatement(CUSTOMER_SELECT_BY_ID,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            statement.setLong(1, entity.getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                log.debug("Customer date of birth before transformation to UTC {}", entity.getDateOfBirth());
                ZonedDateTime dateOfBirthInUtc = entity.getDateOfBirth()
                        .withZoneSameInstant(ZoneId.of("UTC"));
                log.debug("Customer date of birth after transformation to UTC {}", dateOfBirthInUtc);
                if (resultSet.next()) {
                    resultSet.updateLong(CUSTOMER_ID, entity.getId());
                    resultSet.updateString(CUSTOMER_FIRST_NAME, entity.getFirstName());
                    resultSet.updateString(CUSTOMER_LAST_NAME, entity.getLastName());
                    resultSet.updateDate(CUSTOMER_DATE_OF_BIRTH, Date.valueOf(dateOfBirthInUtc.toLocalDate()));
                    resultSet.updateString(CUSTOMER_GENDER, entity.getGender().name());
                    resultSet.updateString(CUSTOMER_EMAIL, entity.getEmail());
                    resultSet.updateString(CUSTOMER_COUNTRY, entity.getCountry());
                    resultSet.updateLong(CUSTOMER_USER_ID, entity.getUserId());
                    resultSet.updateRow();
                    log.trace("Customer with email {} is updated", entity.getEmail());
                    return 1;
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        log.trace("Customer entity with email {} does not exist", entity.getEmail());
        return 0;
    }

    @Override
    public CustomerEntity save(CustomerEntity entity) throws DaoException {
        log.trace("Create customer with email {}", entity.getEmail());
        try (PreparedStatement statement = connection.prepareStatement(CUSTOMER_INSERT)) {
            log.debug("Customer date of birth before transformation to UTC {}", entity.getDateOfBirth());
            ZonedDateTime dateOfBirthInUtc = null;
            if (entity.getDateOfBirth() != null)
                dateOfBirthInUtc = entity.getDateOfBirth().withZoneSameInstant(ZoneId.of("UTC"));
            log.debug("Customer date of birth after transformation to UTC {}", dateOfBirthInUtc);
            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getLastName());
            statement.setDate(3, dateOfBirthInUtc != null ? Date.valueOf(dateOfBirthInUtc.toLocalDate()) : null);
            statement.setString(4, entity.getGender() != null ? entity.getGender().name() : null);
            statement.setString(5, entity.getEmail());
            statement.setString(6, entity.getCountry());
            statement.setLong(7, entity.getUserId());
            statement.executeUpdate();
            log.trace("Customer with email {} was created", entity.getEmail());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return entity;
    }
}

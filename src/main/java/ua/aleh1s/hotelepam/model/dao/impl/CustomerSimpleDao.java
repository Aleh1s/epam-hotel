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
    public Optional<CustomerEntity> findBy(String phoneNumber) throws DaoException {
        log.trace("Find customer entity by phoneNumber {}", phoneNumber);
        try (PreparedStatement statement = connection.prepareStatement(CUSTOMER_SELECT_BY_PHONE_NUMBER)) {
            statement.setString(1, phoneNumber);
            Optional<CustomerEntity> customerEntityOptional;
            try (ResultSet resultSet = statement.executeQuery()) {
                SqlCustomerEntityMapper customerMapper = new SqlCustomerEntityMapper();
                customerEntityOptional = customerMapper.map(resultSet);
            }
            if (customerEntityOptional.isPresent())
                log.trace("Customer entity with phoneNumber {} is found", phoneNumber);
            else
                log.trace("Customer entity with phoneNumber {} is not found", phoneNumber);
            return customerEntityOptional;
        } catch (SQLException | SqlEntityMapperException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int delete(CustomerEntity entity) throws DaoException {
        log.trace("Delete customer entity with phone number {}", entity.getPhoneNumber());
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
        log.trace("Update customer entity with phone number {}", entity.getPhoneNumber());
        try (PreparedStatement statement = connection.prepareStatement(CUSTOMER_SELECT_BY_ID,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            statement.setLong(1, entity.getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    resultSet.updateLong(CUSTOMER_ID, entity.getId());
                    resultSet.updateString(CUSTOMER_FIRST_NAME, entity.getFirstName());
                    resultSet.updateString(CUSTOMER_LAST_NAME, entity.getLastName());
                    resultSet.updateString(CUSTOMER_PHONE_NUMBER, entity.getPhoneNumber());
                    resultSet.updateLong(CUSTOMER_USER_ID, entity.getUserId());
                    resultSet.updateRow();
                    log.trace("Customer with phone number {} is updated", entity.getPhoneNumber());
                    return 1;
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        log.trace("Customer entity with phone number {} does not exist", entity.getPhoneNumber());
        return 0;
    }

    @Override
    public CustomerEntity save(CustomerEntity entity) throws DaoException {
        log.trace("Create customer with phone number {}", entity.getPhoneNumber());
        try (PreparedStatement statement = connection.prepareStatement(CUSTOMER_INSERT)) {
            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getLastName());
            statement.setString(3, entity.getPhoneNumber());
            statement.setLong(4, entity.getUserId());
            statement.executeUpdate();
            log.trace("Customer with phone_number {} was created", entity.getPhoneNumber());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return entity;
    }
}

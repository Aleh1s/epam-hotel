package ua.aleh1s.hotelepam.model.repository.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.aleh1s.hotelepam.model.dao.exception.DaoException;
import ua.aleh1s.hotelepam.model.dao.impl.UserSimpleDao;
import ua.aleh1s.hotelepam.model.entity.UserEntity;
import ua.aleh1s.hotelepam.model.repository.UserRepository;
import ua.aleh1s.hotelepam.transaction.Transaction;

import java.util.Optional;


public class UserRepositoryImpl implements UserRepository {

    private static final Logger logger = LogManager.getLogger(UserRepositoryImpl.class);

    @Override
    public void create(UserEntity userEntity) {
        UserSimpleDao dao = new UserSimpleDao();
        try (Transaction transaction = Transaction.start(dao)) {
            try {
                dao.save(userEntity);
                transaction.commit();
            } catch (DaoException e) {
                transaction.rollback();
                logger.error(e.getMessage(), e);
            }
        }
    }

    @Override
    public void update(UserEntity userEntity) {
        UserSimpleDao dao = new UserSimpleDao();
        try (Transaction transaction = Transaction.start(dao)) {
            try {
                dao.update(userEntity);
                transaction.commit();
            } catch (DaoException e) {
                transaction.rollback();
                logger.error(e.getMessage(), e);
            }
        }
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        Optional<UserEntity> userEntity = Optional.empty();
        UserSimpleDao dao = new UserSimpleDao();
        try (Transaction transaction = Transaction.start(dao)) {
            try {
                userEntity = dao.findById(id);
                transaction.commit();
            } catch (DaoException e) {
                transaction.rollback();
                logger.error(e.getMessage(), e);
            }
        }
        return userEntity;
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        Optional<UserEntity> userEntity = Optional.empty();
        UserSimpleDao dao = new UserSimpleDao();
        try (Transaction transaction = Transaction.start(dao)) {
            try {
                userEntity = dao.findByEmail(email);
                transaction.commit();
            } catch (DaoException e) {
                transaction.rollback();
                logger.error(e.getMessage(), e);
            }
        }
        return userEntity;
    }

    @Override
    public Optional<UserEntity> findByPhoneNumber(String phoneNumber) {
        Optional<UserEntity> userEntity = Optional.empty();
        UserSimpleDao dao = new UserSimpleDao();
        try (Transaction transaction = Transaction.start(dao)) {
            try {
                userEntity = dao.findByPhoneNumber(phoneNumber);
                transaction.commit();
            } catch (DaoException e) {
                transaction.rollback();
                logger.error(e.getMessage(), e);
            }
        }
        return userEntity;
    }
}

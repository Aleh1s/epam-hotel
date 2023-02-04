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

    private static final Logger LOGGER = LogManager.getLogger(UserRepositoryImpl.class);

    @Override
    public void create(UserEntity userEntity) {
        UserSimpleDao dao = new UserSimpleDao();
        try (Transaction transaction = Transaction.start(dao)) {
            try {
                dao.save(userEntity);
                transaction.commit();
            } catch (DaoException e) {
                transaction.rollback();
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        Optional<UserEntity> userEntity = Optional.empty();
        UserSimpleDao dao = new UserSimpleDao();
        try (Transaction transaction = Transaction.start(dao)) {
            try {
                userEntity = dao.findBy(email);
                transaction.commit();
            } catch (DaoException e) {
                transaction.rollback();
                LOGGER.error(e.getMessage(), e);
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
                LOGGER.error(e.getMessage(), e);
            }
        }
        return userEntity;
    }
}

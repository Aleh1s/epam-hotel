package ua.aleh1s.hotelepam.model.repository.impl;

import ua.aleh1s.hotelepam.model.dao.exception.DaoException;
import ua.aleh1s.hotelepam.model.dao.impl.UserSimpleDao;
import ua.aleh1s.hotelepam.model.entity.UserEntity;
import ua.aleh1s.hotelepam.model.repository.UserRepository;
import ua.aleh1s.hotelepam.transaction.Transaction;

import java.util.Optional;


public class UserRepositoryImpl implements UserRepository {

    @Override
    public UserEntity create(UserEntity userEntity) {
        UserSimpleDao dao = new UserSimpleDao();
        try (Transaction transaction = Transaction.start(dao)) {
            try {
                dao.save(userEntity);
                transaction.commit();
            } catch (DaoException e) {
                transaction.rollback();
            }
        }
        return userEntity;
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        UserEntity userEntity = null;
        UserSimpleDao dao = new UserSimpleDao();
        try (Transaction transaction = Transaction.start(dao)) {
            try {
                userEntity = dao.findBy(email);
                transaction.commit();
            } catch (DaoException e) {
                transaction.rollback();
            }
        }
        return Optional.ofNullable(userEntity);
    }
}

package ua.aleh1s.hotelepam.model.repository.impl;

import ua.aleh1s.hotelepam.model.dao.exception.DaoException;
import ua.aleh1s.hotelepam.model.dao.impl.UserSimpleDao;
import ua.aleh1s.hotelepam.model.entity.UserEntity;
import ua.aleh1s.hotelepam.model.repository.UserRepository;
import ua.aleh1s.hotelepam.transaction.Transaction;
import ua.aleh1s.hotelepam.transaction.exception.TransactionException;

import java.util.Optional;

import static ua.aleh1s.hotelepam.model.repository.util.TransactionUtil.runInsideTransactionWithReturn;

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
        return runInsideTransactionWithReturn(dao -> dao.findBy(email).orElse(null), new UserSimpleDao());
    }
}

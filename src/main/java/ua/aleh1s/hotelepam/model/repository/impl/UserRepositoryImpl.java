package ua.aleh1s.hotelepam.model.repository.impl;

import ua.aleh1s.hotelepam.model.dao.impl.UserSimpleDao;
import ua.aleh1s.hotelepam.model.entity.UserEntity;
import ua.aleh1s.hotelepam.model.repository.UserRepository;

import java.util.Optional;

import static ua.aleh1s.hotelepam.model.repository.util.TransactionUtil.runInsideTransactionWithReturn;

public class UserRepositoryImpl implements UserRepository {


    @Override
    public Optional<UserEntity> create(UserEntity userEntity) {
        return runInsideTransactionWithReturn(dao -> dao.save(userEntity), new UserSimpleDao());
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return runInsideTransactionWithReturn(dao -> dao.findBy(email).orElse(null), new UserSimpleDao());
    }
}

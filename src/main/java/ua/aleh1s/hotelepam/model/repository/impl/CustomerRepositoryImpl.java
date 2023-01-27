package ua.aleh1s.hotelepam.model.repository.impl;

import ua.aleh1s.hotelepam.model.dao.impl.CustomerSimpleDao;
import ua.aleh1s.hotelepam.model.entity.CustomerEntity;
import ua.aleh1s.hotelepam.model.repository.CustomerRepository;
import ua.aleh1s.hotelepam.model.repository.util.TransactionUtil;

import java.util.Optional;

public class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public Optional<CustomerEntity> create(CustomerEntity customerEntity) {
        return TransactionUtil.runInsideTransactionWithReturn(dao -> dao.save(customerEntity), new CustomerSimpleDao());
    }
}

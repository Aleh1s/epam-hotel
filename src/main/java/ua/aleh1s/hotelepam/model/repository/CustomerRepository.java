package ua.aleh1s.hotelepam.model.repository;

import ua.aleh1s.hotelepam.model.entity.CustomerEntity;

import java.util.Optional;

public interface CustomerRepository {

    Optional<CustomerEntity> create(CustomerEntity customerEntity);

}

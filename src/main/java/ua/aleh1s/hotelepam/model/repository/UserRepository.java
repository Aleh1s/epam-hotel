package ua.aleh1s.hotelepam.model.repository;

import ua.aleh1s.hotelepam.model.entity.UserEntity;

import java.util.Optional;

public interface UserRepository {

    UserEntity create(UserEntity userEntity);
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByPhoneNumber(String phoneNumber);
}

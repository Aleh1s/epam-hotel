package ua.aleh1s.hotelepam.repository;

import ua.aleh1s.hotelepam.model.entity.UserEntity;

import java.util.Optional;

public interface UserRepository {

    void create(UserEntity userEntity);
    void update(UserEntity userEntity);
    Optional<UserEntity> findById(Long id);
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByPhoneNumber(String phoneNumber);
}

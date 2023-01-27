package ua.aleh1s.hotelepam.model.repository;

import ua.aleh1s.hotelepam.model.entity.UserEntity;

import java.util.Optional;

public interface UserRepository {

    Optional<UserEntity> create(UserEntity userEntity);
    Optional<UserEntity> findByEmail(String login);
}

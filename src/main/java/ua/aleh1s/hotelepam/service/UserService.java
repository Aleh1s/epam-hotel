package ua.aleh1s.hotelepam.service;

import ua.aleh1s.hotelepam.model.entity.UserEntity;

public interface UserService {

    UserEntity getById(Long id);
    void update(UserEntity userEntity);
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
    void create(UserEntity user);
    UserEntity getByEmail(String email);
}

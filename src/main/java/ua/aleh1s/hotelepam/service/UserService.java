package ua.aleh1s.hotelepam.service;

import ua.aleh1s.hotelepam.exception.ServiceException;
import ua.aleh1s.hotelepam.model.entity.UserEntity;

public interface UserService {

    UserEntity getById(Long id) throws ServiceException;
    void update(UserEntity userEntity) throws ServiceException;
    boolean existsByEmail(String email) throws ServiceException;
    boolean existsByPhoneNumber(String phoneNumber) throws ServiceException;
    void create(UserEntity user) throws ServiceException;
    UserEntity getByEmail(String email) throws ServiceException;
}

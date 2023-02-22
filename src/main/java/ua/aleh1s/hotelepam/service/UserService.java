package ua.aleh1s.hotelepam.service;

import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.controller.command.ApplicationException;
import ua.aleh1s.hotelepam.model.entity.UserEntity;
import ua.aleh1s.hotelepam.model.repository.UserRepository;

import java.util.Optional;

public class UserService {
    public UserEntity getById(Long id) {
        UserRepository userRepository
                = AppContext.getInstance().getUserRepository();

        return userRepository.findById(id)
                .orElseThrow(ApplicationException::new);
    }

    public void update(UserEntity userEntity) {
        UserRepository userRepository =
                AppContext.getInstance().getUserRepository();

        userRepository.update(userEntity);
    }

    public boolean existsByEmail(String email) {
        UserRepository userRepository =
                AppContext.getInstance().getUserRepository();

        return userRepository.findByEmail(email).isPresent();
    }

    public boolean existsByPhoneNumber(String phoneNumber) {
        UserRepository userRepository =
                AppContext.getInstance().getUserRepository();

        return userRepository.findByPhoneNumber(phoneNumber).isPresent();
    }

    public void create(UserEntity user) {
        UserRepository userRepository
                = AppContext.getInstance().getUserRepository();

        userRepository.create(user);
    }

    public UserEntity getByEmail(String email) {
        UserRepository userRepository =
                AppContext.getInstance().getUserRepository();

        return userRepository.findByEmail(email)
                .orElseThrow(ApplicationException::new);
    }
}

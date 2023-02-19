package ua.aleh1s.hotelepam.service;

import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.model.entity.UserEntity;
import ua.aleh1s.hotelepam.model.repository.UserRepository;

import java.util.Optional;

public class UserService {
    public Optional<UserEntity> getById(Long id) {
        UserRepository userRepository = AppContext.getInstance().getUserRepository();
        return userRepository.findById(id);
    }

}

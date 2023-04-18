package ua.aleh1s.hotelepam.service.impl;

import lombok.AllArgsConstructor;
import ua.aleh1s.hotelepam.exception.ServiceException;
import ua.aleh1s.hotelepam.model.entity.UserEntity;
import ua.aleh1s.hotelepam.repository.UserRepository;
import ua.aleh1s.hotelepam.service.UserService;

import java.util.Optional;

@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserEntity getById(Long id) throws ServiceException {
        return userRepository.findById(id)
                .orElseThrow(() -> new ServiceException("User with id " + id + " does not exist"));
    }

    @Override
    public void update(UserEntity userEntity) {
        userRepository.update(userEntity);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public boolean existsByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber).isPresent();
    }

    @Override
    public void create(UserEntity user) {
        userRepository.create(user);
    }

    @Override
    public UserEntity getByEmail(String email) throws ServiceException{
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(email);

        if (userEntityOptional.isPresent())
            return userEntityOptional.get();

        throw new ServiceException("User with email " + email + " does not exist");
    }
}

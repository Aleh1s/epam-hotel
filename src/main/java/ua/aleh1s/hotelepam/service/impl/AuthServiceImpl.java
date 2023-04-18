package ua.aleh1s.hotelepam.service.impl;

import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import ua.aleh1s.hotelepam.exception.ServiceException;
import ua.aleh1s.hotelepam.model.dto.LoginCredentials;
import ua.aleh1s.hotelepam.model.dto.SignupCredentials;
import ua.aleh1s.hotelepam.model.dto.UserDto;
import ua.aleh1s.hotelepam.mapper.dtomapper.entitytodto.UserDtoMapper;
import ua.aleh1s.hotelepam.model.entity.UserEntity;
import ua.aleh1s.hotelepam.model.entity.UserRole;
import ua.aleh1s.hotelepam.service.AuthService;
import ua.aleh1s.hotelepam.service.UserService;
import ua.aleh1s.hotelepam.service.exception.EmailAlreadyExistsException;
import ua.aleh1s.hotelepam.service.exception.PhoneAlreadyExistsException;
import ua.aleh1s.hotelepam.service.exception.ValidationException;
import ua.aleh1s.hotelepam.service.exception.WrongPasswordException;
import ua.aleh1s.hotelepam.validator.impl.LoginCredentialsValidator;
import ua.aleh1s.hotelepam.validator.impl.SignupCredentialsValidator;

import java.math.BigDecimal;
import java.util.Locale;

@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final UserDtoMapper userDtoMapper;

    @Override
    public void register(SignupCredentials credentials, UserRole role) throws ServiceException {
        SignupCredentialsValidator validator = new SignupCredentialsValidator();
        validator.validate(credentials);

        if (validator.hasErrors())
            throw new ValidationException(validator.getMessagesByRejectedValue());

        if (userService.existsByEmail(credentials.email()))
            throw new EmailAlreadyExistsException("Email already exists");

        if (userService.existsByPhoneNumber(credentials.phoneNumber()))
            throw new PhoneAlreadyExistsException("Phone number already exists");

        String hashedPassword = BCrypt.hashpw(credentials.password(), BCrypt.gensalt());

        UserEntity user = UserEntity.builder()
                .email(credentials.email())
                .firstName(credentials.firstName())
                .lastName(credentials.lastName())
                .phoneNumber(credentials.phoneNumber())
                .locale(new Locale(""))
                .password(hashedPassword)
                .role(role)
                .account(BigDecimal.ZERO)
                .build();

        userService.create(user);
    }

    @Override
    public UserDto login(LoginCredentials credentials) throws ServiceException {
        LoginCredentialsValidator validator = new LoginCredentialsValidator();
        validator.validate(credentials);

        if (validator.hasErrors())
            throw new ValidationException(validator.getMessagesByRejectedValue());

        UserEntity user = userService.getByEmail(credentials.email());

        boolean isPasswordValid = BCrypt.checkpw(credentials.password(), user.getPassword());

        if (!isPasswordValid)
            throw new WrongPasswordException();

        return userDtoMapper.apply(user);
    }
}

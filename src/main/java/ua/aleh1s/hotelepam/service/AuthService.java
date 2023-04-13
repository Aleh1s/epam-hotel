package ua.aleh1s.hotelepam.service;

import ua.aleh1s.hotelepam.exception.ServiceException;
import ua.aleh1s.hotelepam.model.dto.LoginCredentials;
import ua.aleh1s.hotelepam.model.dto.SignupCredentials;
import ua.aleh1s.hotelepam.model.dto.UserDto;
import ua.aleh1s.hotelepam.model.entity.UserRole;

public interface AuthService {
    void register(SignupCredentials credentials, UserRole role) throws ServiceException;
    UserDto login(LoginCredentials credentials) throws ServiceException;
}

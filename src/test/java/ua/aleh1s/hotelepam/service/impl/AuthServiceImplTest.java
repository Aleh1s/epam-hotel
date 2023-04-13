package ua.aleh1s.hotelepam.service.impl;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.aleh1s.hotelepam.model.dto.SignupCredentials;
import ua.aleh1s.hotelepam.model.entity.UserEntity;
import ua.aleh1s.hotelepam.model.entity.UserRole;
import ua.aleh1s.hotelepam.service.exception.EmailAlreadyExistsException;
import ua.aleh1s.hotelepam.service.exception.PhoneAlreadyExistsException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @Mock
    private UserServiceImpl userService;
    @InjectMocks
    private AuthServiceImpl underTest;

    private SignupCredentials credentials;
    private final String inputPassword = "testPassword";

    @BeforeEach
    void setUp() {
        credentials = new SignupCredentials(
                "test@gmail.com",
                "Test",
                "Test",
                "testPhoneNumber",
                "password"
        );
    }

    @Test
    @SneakyThrows
    void canRegister() {
        ArgumentCaptor<UserEntity> userCaptor =
                ArgumentCaptor.forClass(UserEntity.class);

        given(userService.existsByEmail(credentials.email()))
                .willReturn(false);

        given(userService.existsByPhoneNumber(credentials.phoneNumber()))
                .willReturn(false);

        underTest.register(credentials, UserRole.CUSTOMER);

        verify(userService, times(1))
                .create(userCaptor.capture());

        assertNotEquals(inputPassword, userCaptor.getValue().getPassword());
    }

    @Test
    void registerShouldThrowEmailAlreadyExistsException() {
        given(userService.existsByEmail(credentials.email()))
                .willReturn(true);

        assertThrows(EmailAlreadyExistsException.class, () -> underTest.register(credentials, UserRole.CUSTOMER));
    }

    @Test
    void registerShouldThrowPhoneAlreadyExistsException() {
        given(userService.existsByPhoneNumber(credentials.phoneNumber()))
                .willReturn(true);

        assertThrows(PhoneAlreadyExistsException.class, () -> underTest.register(credentials, UserRole.CUSTOMER));
    }
}
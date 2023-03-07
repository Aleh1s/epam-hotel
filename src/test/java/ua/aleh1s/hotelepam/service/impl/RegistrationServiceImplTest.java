package ua.aleh1s.hotelepam.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.aleh1s.hotelepam.controller.command.ApplicationException;
import ua.aleh1s.hotelepam.model.entity.UserEntity;
import ua.aleh1s.hotelepam.service.UserService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceImplTest {

    @Mock
    private UserServiceImpl userService;
    @InjectMocks
    private RegistrationServiceImpl underTest;

    private UserEntity toRegister;
    private final String inputPassword = "testPassword";

    @BeforeEach
    void setUp() {
        toRegister = UserEntity.builder()
                .email("test@gmai.com")
                .phoneNumber("testPhoneNumber")
                .password(inputPassword)
                .build();
    }

    @Test
    void canRegister() {
        ArgumentCaptor<UserEntity> userCaptor =
                ArgumentCaptor.forClass(UserEntity.class);

        given(userService.existsByEmail(toRegister.getEmail()))
                .willReturn(false);

        given(userService.existsByPhoneNumber(toRegister.getPhoneNumber()))
                .willReturn(false);

        underTest.register(toRegister);

        verify(userService, times(1))
                .create(userCaptor.capture());

        assertNotEquals(inputPassword, userCaptor.getValue().getPassword());
    }

    @Test
    void registerShouldThrowApplicationException1() {
        given(userService.existsByEmail(toRegister.getEmail()))
                .willReturn(true);

        assertThrows(ApplicationException.class, () -> underTest.register(toRegister));
    }

    @Test
    void registerShouldThrowApplicationException2() {
        given(userService.existsByPhoneNumber(toRegister.getPhoneNumber()))
                .willReturn(true);

        assertThrows(ApplicationException.class, () -> underTest.register(toRegister));
    }
}
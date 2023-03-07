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
import ua.aleh1s.hotelepam.model.repository.UserRepository;
import ua.aleh1s.hotelepam.model.repository.impl.UserRepositoryImpl;
import ua.aleh1s.hotelepam.service.UserService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepositoryImpl userRepository;
    @InjectMocks
    private UserServiceImpl underTest;

    private UserEntity user;

    @BeforeEach
    void setUp() {
        user = UserEntity.Builder.newBuilder().build();
    }

    @Test
    void canGetById() {
        Long id = 1L;
        ArgumentCaptor<Long> idCaptor =
                ArgumentCaptor.forClass(Long.class);
        given(userRepository.findById(idCaptor.capture()))
                .willReturn(Optional.of(user));

        underTest.getById(id);

        assertEquals(id, idCaptor.getValue());
    }

    @Test
    void getByIdShouldThrowApplicationException() {
        given(userRepository.findById(any(Long.class)))
                .willReturn(Optional.empty());

        assertThrows(ApplicationException.class, () -> underTest.getById(any(Long.class)));
    }

    @Test
    void canUpdate() {
        ArgumentCaptor<UserEntity> userCaptor =
                ArgumentCaptor.forClass(UserEntity.class);

        underTest.update(user);

        verify(userRepository, times(1))
                .update(userCaptor.capture());
        assertEquals(user, userCaptor.getValue());
    }

    @Test
    void canCheckExistsByEmail() {
        given(userRepository.findByEmail(any()))
                .willReturn(Optional.of(user));

        boolean exists = underTest.existsByEmail(any());

        assertTrue(exists);
    }

    @Test
    void canCheckNotExistsByEmail() {
        given(userRepository.findByEmail(any()))
                .willReturn(Optional.empty());

        boolean exists = underTest.existsByEmail(any());

        assertFalse(exists);
    }

    @Test
    void canCheckExistsByPhoneNumber() {
        given(userRepository.findByPhoneNumber(any()))
                .willReturn(Optional.of(user));

        boolean exists = underTest.existsByPhoneNumber(any());

        assertTrue(exists);
    }

    @Test
    void canCheckNotExistsByPhoneNumber() {
        given(userRepository.findByPhoneNumber(any()))
                .willReturn(Optional.empty());

        boolean exists = underTest.existsByPhoneNumber(any());

        assertFalse(exists);
    }

    @Test
    void canCreate() {
        ArgumentCaptor<UserEntity> userCaptor =
                ArgumentCaptor.forClass(UserEntity.class);

        underTest.create(user);

        verify(userRepository, times(1))
                .create(userCaptor.capture());
        assertEquals(user, userCaptor.getValue());
    }

    @Test
    void canGetByEmail() {
        String email = "test@gmail.com";
        ArgumentCaptor<String> emailCaptor =
                ArgumentCaptor.forClass(String.class);
        given(userRepository.findByEmail(any()))
                .willReturn(Optional.of(user));

        underTest.getByEmail(email);

        verify(userRepository, times(1))
                .findByEmail(emailCaptor.capture());
        assertEquals(email, emailCaptor.getValue());
    }

    @Test
    void getByEmailShouldThrowApplicationException() {
        given(userRepository.findByEmail(any()))
                .willReturn(Optional.empty());

        assertThrows(ApplicationException.class, () -> underTest.getByEmail(any()));
    }
}
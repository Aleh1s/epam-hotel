package ua.aleh1s.hotelepam.service.impl;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.aleh1s.hotelepam.exception.ServiceException;
import ua.aleh1s.hotelepam.model.entity.ReservationTokenEntity;
import ua.aleh1s.hotelepam.model.repository.impl.ReservationTokenRepositoryImpl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ReservationTokenServiceImplTest {
    @Mock
    private ReservationTokenRepositoryImpl reservationTokenRepository;
    @InjectMocks
    private ReservationTokenServiceImpl underTest;

    private ReservationTokenEntity reservationToken;

    @BeforeEach
    void setUp() {
        reservationToken = ReservationTokenEntity.builder().build();
    }

    @Test
    void canCreate() {
        ArgumentCaptor<ReservationTokenEntity> reservationTokenCaptor =
                ArgumentCaptor.forClass(ReservationTokenEntity.class);

        underTest.create(reservationToken);

        verify(reservationTokenRepository, times(1))
                .create(reservationTokenCaptor.capture());
        assertEquals(reservationToken, reservationTokenCaptor.getValue());
    }

    @Test
    @SneakyThrows
    void canConfirmToken() {
        ArgumentCaptor<ReservationTokenEntity> reservationTokenCaptor =
                ArgumentCaptor.forClass(ReservationTokenEntity.class);

        underTest.confirmToken(reservationToken);

        verify(reservationTokenRepository, times(1))
                .update(reservationTokenCaptor.capture());

        assertNotNull(reservationTokenCaptor.getValue().getConfirmedAt());
    }

    @Test
    void confirmTokenShouldThrowServiceException() {
        reservationToken.setConfirmedAt(LocalDateTime.now());

        assertThrows(ServiceException.class, () -> underTest.confirmToken(reservationToken));
    }

    @Test
    @SneakyThrows
    void canGetById() {
        String id = UUID.randomUUID().toString();

        ArgumentCaptor<String> idCaptor =
                ArgumentCaptor.forClass(String.class);

        given(reservationTokenRepository.findById(id))
                .willReturn(Optional.of(reservationToken));

        underTest.getById(id);

        verify(reservationTokenRepository, times(1))
                .findById(idCaptor.capture());

        assertEquals(id, idCaptor.getValue());
    }

    @Test
    void getByIdShouldThrowApplicationException() {
        given(reservationTokenRepository.findById(any()))
                .willReturn(Optional.empty());

        assertThrows(ServiceException.class, () -> underTest.getById(any()));
    }
}
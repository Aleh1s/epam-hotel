package ua.aleh1s.hotelepam.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.aleh1s.hotelepam.controller.command.ApplicationException;
import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.model.entity.ReservationStatus;
import ua.aleh1s.hotelepam.model.entity.ReservationTokenEntity;
import ua.aleh1s.hotelepam.model.repository.ReservationRepository;
import ua.aleh1s.hotelepam.model.repository.impl.ReservationRepositoryImpl;
import ua.aleh1s.hotelepam.service.ReservationService;
import ua.aleh1s.hotelepam.utils.Page;
import ua.aleh1s.hotelepam.utils.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationServiceImplTest {

    @Mock
    private ReservationRepositoryImpl reservationRepository;
    @InjectMocks
    private ReservationServiceImpl underTest;

    private ReservationEntity reservation;

    @BeforeEach
    void setUp() {
        reservation = ReservationEntity.Builder.newBuilder().build();
    }

    @Test
    void canCreate() {
        ArgumentCaptor<ReservationEntity> reservationCaptor =
                ArgumentCaptor.forClass(ReservationEntity.class);

        underTest.create(reservation);

        verify(reservationRepository, times(1))
                .create(reservationCaptor.capture());
        assertEquals(reservation, reservationCaptor.getValue());
    }

    @Test
    void canGetById() {
        Long id = 1L;
        ArgumentCaptor<Long> idCaptor =
                ArgumentCaptor.forClass(Long.class);

        given(reservationRepository.getById(any()))
                .willReturn(Optional.of(reservation));

        underTest.getById(id);

        verify(reservationRepository, times(1))
                .getById(idCaptor.capture());
        assertEquals(id, idCaptor.getValue());
    }

    @Test
    void getByIdShouldThrowApplicationException() {
        given(reservationRepository.getById(any()))
                .willReturn(Optional.empty());

        assertThrows(ApplicationException.class, () -> underTest.getById(any()));
    }

    @Test
    void canGetAllActualPayedReservations() {
        PageRequest pageRequest = PageRequest.of(1, 10);

        ArgumentCaptor<PageRequest> pageRequestCaptor =
                ArgumentCaptor.forClass(PageRequest.class);

        Page<ReservationEntity> page = Page.of(List.of(reservation), 1);

        given(reservationRepository.getAllActualReservationByStatus(ReservationStatus.PAYED, pageRequest))
                .willReturn(page);

        Page<ReservationEntity> actual = underTest.getAllActualPayedReservations(pageRequest);

        verify(reservationRepository, times(1))
                .getAllActualReservationByStatus(any(), pageRequestCaptor.capture());

        assertEquals(pageRequest, pageRequestCaptor.getValue());
        assertEquals(page, actual);
    }

    @Test
    void canGetAllReservationsByUserId() {
        Long userId = 1L;
        PageRequest pageRequest = PageRequest.of(1, 10);

        ArgumentCaptor<PageRequest> pageRequestCaptor =
                ArgumentCaptor.forClass(PageRequest.class);

        ArgumentCaptor<Long> userIdCaptor =
                ArgumentCaptor.forClass(Long.class);

        Page<ReservationEntity> page = Page.of(List.of(reservation), 1);

        given(reservationRepository.getAllReservationsByUserId(userId, pageRequest))
                .willReturn(page);

        Page<ReservationEntity> actual = underTest.getAllReservationsByUserId(userId, pageRequest);

        verify(reservationRepository, times(1))
                .getAllReservationsByUserId(userIdCaptor.capture(), pageRequestCaptor.capture());

        assertEquals(userId, userIdCaptor.getValue());
        assertEquals(pageRequest, pageRequestCaptor.getValue());
        assertEquals(page, actual);
    }

    @Test
    void canCancelReservation() {
        ArgumentCaptor<ReservationEntity> reservationCaptor =
                ArgumentCaptor.forClass(ReservationEntity.class);

        underTest.cancelReservation(reservation);

        verify(reservationRepository, times(1))
                .update(reservationCaptor.capture());

        assertEquals(ReservationStatus.CANCELED, reservationCaptor.getValue().getStatus());
    }

    @Test
    void update() {
        ArgumentCaptor<ReservationEntity> reservationCaptor =
                ArgumentCaptor.forClass(ReservationEntity.class);

        underTest.update(reservation);

        verify(reservationRepository, times(1))
                .update(reservationCaptor.capture());

        assertEquals(reservation, reservationCaptor.getValue());

    }
}
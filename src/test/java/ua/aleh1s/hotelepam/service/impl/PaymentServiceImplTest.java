package ua.aleh1s.hotelepam.service.impl;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.model.entity.ReservationStatus;
import ua.aleh1s.hotelepam.model.entity.RoomEntity;
import ua.aleh1s.hotelepam.model.entity.UserEntity;
import ua.aleh1s.hotelepam.service.ReservationService;
import ua.aleh1s.hotelepam.service.RoomService;
import ua.aleh1s.hotelepam.service.UserService;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {

    @Mock
    private ReservationServiceImpl reservationService;
    @Mock
    private UserServiceImpl userService;
    @Mock
    private RoomService roomService;
    @InjectMocks
    private PaymentServiceImpl underTest;

    @BeforeEach
    void setUp() {

    }

    @Test
    @SneakyThrows
    void payReservation() {
        Integer roomNumber = 1;
        Long reservationId = 1L, customerId = 1L;
        ReservationEntity reservation = ReservationEntity.builder()
                .customerId(customerId)
                .roomNumber(roomNumber)
                .totalAmount(BigDecimal.TEN)
                .expiredAt(LocalDateTime.now().plusMinutes(2))
                .build();

        UserEntity user = UserEntity.builder()
                .id(customerId)
                .account(BigDecimal.TEN)
                .build();

        RoomEntity room = RoomEntity.builder()
                .isUnavailable(false)
                .build();

        ArgumentCaptor<UserEntity> userCaptor =
                ArgumentCaptor.forClass(UserEntity.class);

        ArgumentCaptor<ReservationEntity> reservationCaptor =
                ArgumentCaptor.forClass(ReservationEntity.class);

        given(reservationService.getById(reservationId))
                .willReturn(reservation);

        given(roomService.getByRoomNumber(roomNumber))
                .willReturn(room);

        given(userService.getById(customerId))
                .willReturn(user);

        underTest.payReservation(reservationId, customerId);

        verify(reservationService, times(1))
                .update(reservationCaptor.capture());

        verify(userService, times(1))
                .update(userCaptor.capture());

        assertEquals(BigDecimal.ZERO, userCaptor.getValue().getAccount());
        assertNotNull(reservationCaptor.getValue().getPayedAt());
        assertEquals(ReservationStatus.PAYED, reservationCaptor.getValue().getStatus());
    }
}
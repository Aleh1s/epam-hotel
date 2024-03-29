package ua.aleh1s.hotelepam.service.impl;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.aleh1s.hotelepam.exception.ServiceException;
import ua.aleh1s.hotelepam.model.entity.ReservationTokenEntity;
import ua.aleh1s.hotelepam.model.entity.RoomEntity;
import ua.aleh1s.hotelepam.model.entity.UserEntity;
import ua.aleh1s.hotelepam.utils.Mail;
import ua.aleh1s.hotelepam.utils.Period;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BookingServiceImplTest {

    @Mock
    private RoomServiceImpl roomService;
    @Mock
    private ReservationServiceImpl reservationService;
    @Mock
    private ReservationTokenServiceImpl reservationTokenService;
    @Mock
    private MailServiceImpl mailService;
    @Mock
    private UserServiceImpl userService;
    @InjectMocks
    private BookingServiceImpl underTest;

    @Test
    @SneakyThrows
    void canBookRoom() {
        Integer roomNumber = 1;
        Long customerId = 1L, reservationId = 1L;

        Period period = Period.between(
                LocalDate.now(),
                LocalDate.now().plusDays(7)
        );

        RoomEntity room = RoomEntity.builder().build();

        String email = "test@gmail.com";
        UserEntity user = UserEntity.builder()
                .firstName("Test")
                .lastName("Test")
                .email(email)
                .locale(new Locale("ua"))
                .build();

        ArgumentCaptor<ReservationTokenEntity> reservationTokenCaptor =
                ArgumentCaptor.forClass(ReservationTokenEntity.class);

        ArgumentCaptor<Mail> mailCaptor =
                ArgumentCaptor.forClass(Mail.class);

        given(roomService.getByRoomNumber(roomNumber))
                .willReturn(room);

        given(roomService.isRoomAvailable(roomNumber, period))
                .willReturn(true);

        given(userService.getById(customerId))
                .willReturn(user);

        given(roomService.getTotalPrice(room, period))
                .willReturn(BigDecimal.TEN);

        given(reservationService.create(any()))
                .willReturn(reservationId);

        underTest.bookRoom(roomNumber, customerId, period);

        verify(reservationTokenService, times(1))
                .create(reservationTokenCaptor.capture());

        verify(mailService, times(1))
                .send(mailCaptor.capture());

        assertEquals(reservationId, reservationTokenCaptor.getValue().getReservationId());
        assertEquals(email, mailCaptor.getValue().toAddress());
    }

    @Test
    void bookRoomShouldThrowServiceException1() {
        Period invalidPeriod = Period.between(
                LocalDate.of(2023, 10, 6),
                LocalDate.of(2023, 10, 1)
        );

        assertThrows(ServiceException.class, () -> underTest.bookRoom(1, 1L, invalidPeriod));
    }

    @Test
    @SneakyThrows
    void bookRoomShouldThrowServiceException2() {
        Integer roomNumber = 1;

        RoomEntity room = RoomEntity.builder().build();
        Period validPeriod = Period.between(
                LocalDate.now(),
                LocalDate.now().plusDays(7)
        );

        given(roomService.getByRoomNumber(roomNumber))
                .willReturn(room);

        given(roomService.isRoomAvailable(roomNumber, validPeriod))
                .willReturn(false);

        assertThrows(ServiceException.class, () -> underTest.bookRoom(roomNumber, 1L, validPeriod));
    }
}
package ua.aleh1s.hotelepam.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.model.entity.RoomEntity;
import ua.aleh1s.hotelepam.model.repository.impl.ReservationRepositoryImpl;
import ua.aleh1s.hotelepam.model.repository.impl.RoomRepositoryImpl;
import ua.aleh1s.hotelepam.utils.Period;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RoomServiceImplTest {

    @Mock
    private ReservationRepositoryImpl reservationRepository;
    @Mock
    private RoomRepositoryImpl roomRepository;
    @InjectMocks
    private RoomServiceImpl underTest;

    private RoomEntity room;

    @BeforeEach
    void setUp() {
        room = RoomEntity.builder().build();
    }

    @Test
    void canCheckIsRoomAvailableTrue() {
        Integer roomNumber = 1;
        Period period = Period.between(
                LocalDate.of(2023, 10, 1),
                LocalDate.of(2023, 10, 8)
        );

        ReservationEntity reservation1 = ReservationEntity.builder()
                .checkIn(LocalDate.of(2023, 9, 27))
                .checkOut(LocalDate.of(2023, 10, 1))
                .build();

        ReservationEntity reservation2 = ReservationEntity.builder()
                .checkIn(LocalDate.of(2023, 10, 8))
                .checkOut(LocalDate.of(2023, 10, 15))
                .build();

        List<ReservationEntity> actualReservations = List.of(reservation1, reservation2);

        given(reservationRepository.getActualReservationsByRoomNumber(roomNumber))
                .willReturn(actualReservations);

        boolean isRoomAvailable = underTest.isRoomAvailable(roomNumber, period);

        assertTrue(isRoomAvailable);
    }

    @Test
    void canCheckIsRoomAvailableFalse() {
        Integer roomNumber = 1;
        Period period = Period.between(
                LocalDate.of(2023, 10, 1),
                LocalDate.of(2023, 10, 8)
        );

        ReservationEntity reservation1 = ReservationEntity.builder()
                .checkIn(LocalDate.of(2023, 10, 1))
                .checkOut(LocalDate.of(2023, 10, 5))
                .build();

        List<ReservationEntity> actualReservations = List.of(reservation1);

        given(reservationRepository.getActualReservationsByRoomNumber(roomNumber))
                .willReturn(actualReservations);

        boolean isRoomAvailable = underTest.isRoomAvailable(roomNumber, period);

        assertFalse(isRoomAvailable);
    }

    @Test
    void canGetAvailableRooms() {
        Integer guests = 1;
        Period period = Period.between(
                LocalDate.of(2023, 10, 1),
                LocalDate.of(2023, 10, 8)
        );

        ReservationEntity reservation1 = ReservationEntity.builder()
                .roomNumber(1)
                .checkIn(LocalDate.of(2023, 9, 27))
                .checkOut(LocalDate.of(2023, 10, 1))
                .build();

        ReservationEntity reservation2 = ReservationEntity.builder()
                .roomNumber(2)
                .checkIn(LocalDate.of(2023, 10, 8))
                .checkOut(LocalDate.of(2023, 10, 15))
                .build();

        room.setRoomNumber(3);

        given(reservationRepository.getActualReservations())
                .willReturn(List.of(reservation1, reservation2));

        List<RoomEntity> rooms = List.of(room);
        given(roomRepository.getAllByGuests(guests))
                .willReturn(rooms);

        List<RoomEntity> actual = underTest.getAvailableRooms(guests, period);

        assertEquals(rooms, actual);
    }

    @Test
    void canGetTotalPrice() {
        room.setPrice(BigDecimal.TEN);

        Integer roomNumber = 1;
        Period period = Period.between(
                LocalDate.of(2023, 10, 1),
                LocalDate.of(2023, 10, 8)
        );

        BigDecimal nights = BigDecimal.valueOf(period.getDaysBetween());
        BigDecimal expected = room.getPrice().multiply(nights);

        given(roomRepository.getByRoomNumber(roomNumber))
                .willReturn(Optional.of(room));

        BigDecimal actual = underTest.getTotalPrice(roomNumber, period);

        assertEquals(expected, actual);
    }

    @Test
    void canGetByRoomNumber() {
        Integer roomNumber = 1;
        ArgumentCaptor<Integer> roomNumberCaptor =
                ArgumentCaptor.forClass(Integer.class);

        given(roomRepository.getByRoomNumber(any()))
                .willReturn(Optional.of(room));

        underTest.getByRoomNumber(roomNumber);

        verify(roomRepository, times(1))
                .getByRoomNumber(roomNumberCaptor.capture());
        assertEquals(roomNumber, roomNumberCaptor.getValue());
    }

    @Test
    @Disabled
    void getSortedRooms() {}
}
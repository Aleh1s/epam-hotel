package ua.aleh1s.hotelepam.model.repository.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.model.entity.ReservationStatus;
import ua.aleh1s.hotelepam.database.querybuilder.EntityManager;
import ua.aleh1s.hotelepam.database.querybuilder.Root;
import ua.aleh1s.hotelepam.database.querybuilder.impl.InsertQueryBuilder;
import ua.aleh1s.hotelepam.database.querybuilder.impl.SelectQueryBuilder;
import ua.aleh1s.hotelepam.database.querybuilder.impl.UpdateQueryBuilder;
import ua.aleh1s.hotelepam.mapper.sqlmapper.impl.SqlReservationEntityMapper;
import ua.aleh1s.hotelepam.repository.impl.ReservationRepositoryImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationRepositoryImplTest {

    @Mock
    private SqlReservationEntityMapper entityMapper;
    @Spy
    private EntityManager entityManager;
    @InjectMocks
    private ReservationRepositoryImpl underTest;

    private Root<ReservationEntity> root;
    private ReservationEntity reservation;

    @BeforeEach
    void setUp() {
        this.root = spy(entityManager.valueOf(ReservationEntity.class));

        doReturn(this.root).when(this.entityManager)
                .valueOf(ReservationEntity.class);

        this.reservation = ReservationEntity.builder()
                .id(1L)
                .roomNumber(1)
                .customerId(1L)
                .checkIn(LocalDate.now())
                .checkOut(LocalDate.now().plusDays(7))
                .createdAt(LocalDateTime.now())
                .expiredAt(LocalDateTime.now().plusDays(2))
                .payedAt(LocalDateTime.now().plusMinutes(15))
                .totalAmount(BigDecimal.TEN)
                .status(ReservationStatus.PAYED)
                .build();
    }

    @Test
    void create() {
        long expected = 1L;

        InsertQueryBuilder<ReservationEntity> insertQueryBuilder = spy(root.insert());

        given(root.insert())
                .willReturn(insertQueryBuilder);

        doReturn(expected).when(insertQueryBuilder)
                .executeAndReturnPrimaryKey();

        Long actual = underTest.create(reservation);

        verify(insertQueryBuilder, times(1))
                .executeAndReturnPrimaryKey();

        assertEquals(expected, actual);
    }


    @Test
    void findById() {
        SelectQueryBuilder<ReservationEntity> selectQueryBuilder =
                spy(root.select());

        doReturn(selectQueryBuilder).when(root)
                .select();

        doReturn(reservation).when(selectQueryBuilder)
                .getResult(entityMapper);

        ReservationEntity actual = underTest.findById(1L).get();

        assertEquals(reservation, actual);
    }

    @Test
    void update() {
        UpdateQueryBuilder<ReservationEntity> updateQueryBuilder =
                spy(root.update());

        doReturn(updateQueryBuilder).when(root)
                .update();

        doNothing().when(updateQueryBuilder)
                .execute();

        underTest.update(reservation);

        verify(updateQueryBuilder, times(1))
                .execute();
    }
}
package ua.aleh1s.hotelepam.model.repository.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.aleh1s.hotelepam.model.entity.RequestEntity;
import ua.aleh1s.hotelepam.model.entity.RequestStatus;
import ua.aleh1s.hotelepam.database.querybuilder.EntityManager;
import ua.aleh1s.hotelepam.database.querybuilder.Root;
import ua.aleh1s.hotelepam.database.querybuilder.aggregatefunc.AggregateFunctionBuilder;
import ua.aleh1s.hotelepam.database.querybuilder.impl.InsertQueryBuilder;
import ua.aleh1s.hotelepam.database.querybuilder.impl.SelectQueryBuilder;
import ua.aleh1s.hotelepam.database.querybuilder.impl.UpdateQueryBuilder;
import ua.aleh1s.hotelepam.mapper.sqlmapper.impl.SqlRequestEntityMapper;
import ua.aleh1s.hotelepam.repository.impl.RequestRepositoryImpl;
import ua.aleh1s.hotelepam.utils.Page;
import ua.aleh1s.hotelepam.utils.PageRequest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RequestRepositoryImplTest {

    @Mock
    private SqlRequestEntityMapper entityMapper;
    @Spy
    private EntityManager entityManager;
    @InjectMocks
    private RequestRepositoryImpl underTest;

    private Root<RequestEntity> root;
    private RequestEntity request;
    @BeforeEach
    void setUp() {
        this.root = spy(entityManager.valueOf(RequestEntity.class));

        doReturn(this.root).when(entityManager)
                .valueOf(RequestEntity.class);

        this.request = RequestEntity.builder()
                .id(1L)
                .roomNumber(1)
                .customerId(1L)
                .status(RequestStatus.NEW)
                .checkIn(LocalDate.now())
                .checkOut(LocalDate.now().plusDays(7))
                .totalAmount(BigDecimal.TEN)
                .build();
    }

    @Test
    void create() {
        InsertQueryBuilder<RequestEntity> insertQueryBuilder = spy(root.insert());

        given(root.insert())
                .willReturn(insertQueryBuilder);

        doNothing().when(insertQueryBuilder)
                .execute();

        underTest.create(request);

        verify(insertQueryBuilder, times(1))
                .execute();
    }

    @Test
    void getAllActiveByUserId() {
        SelectQueryBuilder<RequestEntity> selectQueryBuilder = spy(root.select());
        SelectQueryBuilder<RequestEntity> selectCountQueryBuilder = spy(root.select(root.countAll()));

        doReturn(selectQueryBuilder).when(root)
                .select();

        doReturn(selectCountQueryBuilder).when(root)
                .select(any(AggregateFunctionBuilder.class));

        List<RequestEntity> resultList = List.of(request);
        doReturn(resultList).when(selectQueryBuilder)
                .getResultList(entityMapper);

        long count = 1L;
        doReturn(count).when(selectCountQueryBuilder)
                .execute(Long.class);

        Page<RequestEntity> actual =
                underTest.getAllActiveByUserId(1L, PageRequest.of(1, 10));

        assertEquals(Page.of(resultList, count), actual);
    }

    @Test
    void findById() {
        SelectQueryBuilder<RequestEntity> selectQueryBuilder =
                spy(root.select());

        doReturn(selectQueryBuilder).when(root)
                .select();

        doReturn(request).when(selectQueryBuilder)
                .getResult(entityMapper);

        RequestEntity actual = underTest.findById(1L).get();

        assertEquals(request, actual);
    }

    @Test
    void update() {
        UpdateQueryBuilder<RequestEntity> updateQueryBuilder =
                spy(root.update());

        doReturn(updateQueryBuilder).when(root)
                .update();

        doNothing().when(updateQueryBuilder)
                .execute();

        underTest.update(request);

        verify(updateQueryBuilder, times(1))
                .execute();
    }
}
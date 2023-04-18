package ua.aleh1s.hotelepam.model.repository.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.aleh1s.hotelepam.model.entity.ApplicationEntity;
import ua.aleh1s.hotelepam.model.entity.ApplicationStatus;
import ua.aleh1s.hotelepam.model.entity.RoomClass;
import ua.aleh1s.hotelepam.database.querybuilder.EntityManager;
import ua.aleh1s.hotelepam.database.querybuilder.Root;
import ua.aleh1s.hotelepam.database.querybuilder.aggregatefunc.AggregateFunctionBuilder;
import ua.aleh1s.hotelepam.database.querybuilder.impl.InsertQueryBuilder;
import ua.aleh1s.hotelepam.database.querybuilder.impl.SelectQueryBuilder;
import ua.aleh1s.hotelepam.database.querybuilder.impl.UpdateQueryBuilder;
import ua.aleh1s.hotelepam.mapper.sqlmapper.impl.SqlApplicationEntityMapper;
import ua.aleh1s.hotelepam.repository.impl.ApplicationRepositoryImpl;
import ua.aleh1s.hotelepam.utils.Page;
import ua.aleh1s.hotelepam.utils.PageRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApplicationRepositoryImplTest {

    @Spy
    private EntityManager entityManager;
    @Mock
    private SqlApplicationEntityMapper entityMapper;
    @InjectMocks
    private ApplicationRepositoryImpl underTest;

    private Root<ApplicationEntity> root;
    private ApplicationEntity application;

    @BeforeEach
    void setUp() {
        this.root = spy(entityManager.valueOf(ApplicationEntity.class));

        doReturn(this.root).when(this.entityManager)
                .valueOf(ApplicationEntity.class);

        this.application = ApplicationEntity.builder()
                .id(1L).roomClass(RoomClass.FAMILY)
                .guests(3)
                .checkIn(LocalDate.now())
                .checkOut(LocalDate.now().plusDays(7))
                .status(ApplicationStatus.NEW)
                .customerId(1L)
                .createdAt(LocalDateTime.now())
                .build();
    }

    @Test
    void create() {
        InsertQueryBuilder<ApplicationEntity> insertQueryBuilder = spy(root.insert());

        given(root.insert())
                .willReturn(insertQueryBuilder);

        doNothing().when(insertQueryBuilder)
                .execute();

        underTest.create(application);

        verify(insertQueryBuilder, times(1))
                .execute();
    }

    @Test
    void getById() {
        SelectQueryBuilder<ApplicationEntity> selectQueryBuilder =
                spy(root.select());

        doReturn(selectQueryBuilder).when(root)
                        .select();

        doReturn(application).when(selectQueryBuilder)
                .getResult(entityMapper);

        ApplicationEntity actual = underTest.findById(1L).get();

        assertEquals(application, actual);
    }

    @Test
    void update() {
        UpdateQueryBuilder<ApplicationEntity> updateQueryBuilder =
                spy(root.update());

        doReturn(updateQueryBuilder).when(root)
                .update();

        doNothing().when(updateQueryBuilder)
                .execute();

        underTest.update(application);

        verify(updateQueryBuilder, times(1))
                .execute();
    }

    @Test
    void getAllByApplicationStatus() {
        SelectQueryBuilder<ApplicationEntity> selectQueryBuilder =
                spy(root.select());

        SelectQueryBuilder<ApplicationEntity> selectCountQueryBuilder =
                spy(root.select(root.countAll()));

        doReturn(selectQueryBuilder).when(root)
                .select();

        List<ApplicationEntity> resultList = List.of(application);
        doReturn(resultList).when(selectQueryBuilder)
                .getResultList(entityMapper);

        doReturn(selectCountQueryBuilder).when(root)
                .select(any(AggregateFunctionBuilder.class));

        long count = 1L;
        doReturn(count).when(selectCountQueryBuilder)
                .execute(Long.class);

        Page<ApplicationEntity> actual =
                underTest.getAllByApplicationStatus(ApplicationStatus.NEW, PageRequest.of(1, 10));

        assertEquals(Page.of(resultList, count), actual);
    }
}
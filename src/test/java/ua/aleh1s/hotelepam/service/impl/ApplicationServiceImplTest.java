package ua.aleh1s.hotelepam.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.aleh1s.hotelepam.controller.command.ApplicationException;
import ua.aleh1s.hotelepam.model.entity.ApplicationEntity;
import ua.aleh1s.hotelepam.model.entity.ApplicationStatus;
import ua.aleh1s.hotelepam.model.repository.impl.ApplicationRepositoryImpl;
import ua.aleh1s.hotelepam.utils.Page;
import ua.aleh1s.hotelepam.utils.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ApplicationServiceImplTest {

    @Mock
    private ApplicationRepositoryImpl applicationRepository;
    @InjectMocks
    private ApplicationServiceImpl underTest;

    private ApplicationEntity application;

    @BeforeEach
    void setUp() {
        application = ApplicationEntity.builder().build();
    }

    @Test
    void canGetApplicationById() {
        Long id = 1L;
        ArgumentCaptor<Long> idCaptor =
                ArgumentCaptor.forClass(Long.class);
        given(applicationRepository.getById(any()))
                .willReturn(Optional.of(application));

        underTest.getApplicationById(id);

        verify(applicationRepository, times(1))
                .getById(idCaptor.capture());
        assertEquals(id, idCaptor.getValue());
    }

    @Test
    void getApplicationByIdShouldThrowApplicationException() {
        given(applicationRepository.getById(any()))
                .willReturn(Optional.empty());

        assertThrows(ApplicationException.class, () -> underTest.getApplicationById(any()));
    }

    @Test
    void canUpdate() {
        ArgumentCaptor<ApplicationEntity> applicationCaptor =
                ArgumentCaptor.forClass(ApplicationEntity.class);

        underTest.update(application);

        verify(applicationRepository, times(1))
                .update(applicationCaptor.capture());
        assertEquals(application, applicationCaptor.getValue());
    }

    @Test
    void canGetAllByApplicationStatus() {
        ApplicationStatus status = ApplicationStatus.NEW;
        PageRequest pageRequest = PageRequest.of(1, 10);

        List<ApplicationEntity> applicationList = List.of(application);
        Page<ApplicationEntity> expected = Page.of(applicationList, 1);

        given(applicationRepository.getAllByApplicationStatus(status, pageRequest))
                .willReturn(expected);

        Page<ApplicationEntity> actual = underTest.getAllByApplicationStatus(status, pageRequest);

        verify(applicationRepository, times(1))
                .getAllByApplicationStatus(status, pageRequest);
        assertEquals(expected, actual);
    }

    @Test
    void canCreate() {
        ArgumentCaptor<ApplicationEntity> applicationCaptor =
                ArgumentCaptor.forClass(ApplicationEntity.class);

        underTest.create(application);

        verify(applicationRepository, times(1))
                .create(applicationCaptor.capture());
        assertEquals(application, applicationCaptor.getValue());
    }
}
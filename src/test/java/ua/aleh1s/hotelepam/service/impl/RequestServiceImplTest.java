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
import ua.aleh1s.hotelepam.model.entity.RequestEntity;
import ua.aleh1s.hotelepam.model.entity.RequestStatus;
import ua.aleh1s.hotelepam.repository.impl.RequestRepositoryImpl;
import ua.aleh1s.hotelepam.utils.Page;
import ua.aleh1s.hotelepam.utils.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static ua.aleh1s.hotelepam.model.entity.RequestStatus.CONFIRMED;
import static ua.aleh1s.hotelepam.model.entity.RequestStatus.NEW;

@ExtendWith(MockitoExtension.class)
class RequestServiceImplTest {

    @Mock
    private RequestRepositoryImpl requestRepository;
    @InjectMocks
    private RequestServiceImpl underTest;
    private RequestEntity request;

    @BeforeEach
    void setUp() {
        request = RequestEntity.builder().build();
    }

    @Test
    void canCreate() {
        ArgumentCaptor<RequestEntity> requestCaptor =
                ArgumentCaptor.forClass(RequestEntity.class);

        underTest.create(request);

        verify(requestRepository, times(1))
                .create(requestCaptor.capture());

        assertEquals(request, requestCaptor.getValue());
    }

    @Test
    @SneakyThrows
    void canGetById() {
        Long id = 1L;

        ArgumentCaptor<Long> idCaptor =
                ArgumentCaptor.forClass(Long.class);

        given(requestRepository.findById(id))
                .willReturn(Optional.of(request));

        underTest.getById(id);

        verify(requestRepository, times(1))
                .findById(idCaptor.capture());

        assertEquals(id, idCaptor.getValue());
    }

    @Test
    void getByIdShouldThrowServiceException() {
        given(requestRepository.findById(any()))
                .willReturn(Optional.empty());

        assertThrows(ServiceException.class, () -> underTest.getById(any()));
    }

    @Test
    void update() {
        ArgumentCaptor<RequestEntity> requestCaptor =
                ArgumentCaptor.forClass(RequestEntity.class);

        underTest.update(request);

        verify(requestRepository, times(1))
                .update(requestCaptor.capture());

        assertEquals(request, requestCaptor.getValue());
    }

    @Test
    void getAllActiveRequestsByUserId() {
        Long userId = 1L;
        PageRequest pageRequest = PageRequest.of(1, 10);

        ArgumentCaptor<Long> userIdCaptor =
                ArgumentCaptor.forClass(Long.class);

        ArgumentCaptor<PageRequest> pageRequestCaptor =
                ArgumentCaptor.forClass(PageRequest.class);

        Page<RequestEntity> page = Page.of(List.of(request), 1);
        given(requestRepository.getAllActiveByUserId(userId, pageRequest))
                .willReturn(page);

        Page<RequestEntity> actual = underTest.getAllActiveRequestsByUserId(userId, pageRequest);

        verify(requestRepository).getAllActiveByUserId(userIdCaptor.capture(), pageRequestCaptor.capture());

        assertEquals(userId, userIdCaptor.getValue());
        assertEquals(pageRequest, pageRequestCaptor.getValue());
        assertEquals(page, actual);
    }

    @Test
    @SneakyThrows
    void changeStatus() {
        request.setStatus(NEW);
        RequestStatus expected = CONFIRMED;

        ArgumentCaptor<RequestEntity> requestCaptor =
                ArgumentCaptor.forClass(RequestEntity.class);

        underTest.changeStatus(request, expected);

        verify(requestRepository, times(1))
                .update(requestCaptor.capture());

        assertEquals(expected, requestCaptor.getValue().getStatus());
    }
}
package ua.aleh1s.hotelepam.service;

import ua.aleh1s.hotelepam.exception.ServiceException;
import ua.aleh1s.hotelepam.model.dto.RequestDto;
import ua.aleh1s.hotelepam.model.entity.RequestEntity;
import ua.aleh1s.hotelepam.model.entity.RequestStatus;
import ua.aleh1s.hotelepam.utils.Page;
import ua.aleh1s.hotelepam.utils.PageRequest;

public interface RequestService {

    void create(RequestEntity entity) throws ServiceException;
    void create(RequestDto requestDto, Long applicationId) throws ServiceException;
    RequestEntity getById(Long id) throws ServiceException;
    void update(RequestEntity entity) throws ServiceException;
    Page<RequestEntity> getAllActiveRequestsByUserId(Long userId, PageRequest pageRequest) throws ServiceException;
    void changeStatus(RequestEntity entity, RequestStatus status) throws ServiceException;
    void confirmRequest(Long requestId) throws ServiceException;
}

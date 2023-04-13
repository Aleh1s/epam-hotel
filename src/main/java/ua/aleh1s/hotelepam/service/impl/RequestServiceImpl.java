package ua.aleh1s.hotelepam.service.impl;

import lombok.AllArgsConstructor;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.exception.ServiceException;
import ua.aleh1s.hotelepam.model.entity.RequestEntity;
import ua.aleh1s.hotelepam.model.entity.RequestStatus;
import ua.aleh1s.hotelepam.model.repository.RequestRepository;
import ua.aleh1s.hotelepam.service.RequestService;
import ua.aleh1s.hotelepam.utils.Page;
import ua.aleh1s.hotelepam.utils.PageRequest;

import static ua.aleh1s.hotelepam.model.entity.RequestStatus.NEW;

@AllArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;

    @Override
    public void create(RequestEntity entity) {
        requestRepository.create(entity);
    }

    @Override
    public RequestEntity getById(Long id) throws ServiceException {
        return requestRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Request with id " + id + " does not exist"));
    }

    @Override
    public void update(RequestEntity entity) {
        requestRepository.update(entity);
    }

    @Override
    public Page<RequestEntity> getAllActiveRequestsByUserId(Long userId, PageRequest pageRequest) {
        return requestRepository.getAllActiveByUserId(userId, pageRequest);
    }

    @Override
    public void changeStatus(RequestEntity request, RequestStatus requestStatus) throws ServiceException {
        if (!request.getStatus().equals(NEW))
            throw new ServiceException("You cannot change status");

        request.setStatus(requestStatus);
        update(request);
    }
}

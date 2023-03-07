package ua.aleh1s.hotelepam.service.impl;

import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.ApplicationException;
import ua.aleh1s.hotelepam.model.entity.RequestEntity;
import ua.aleh1s.hotelepam.model.entity.RequestStatus;
import ua.aleh1s.hotelepam.model.repository.RequestRepository;
import ua.aleh1s.hotelepam.service.RequestService;
import ua.aleh1s.hotelepam.utils.Page;
import ua.aleh1s.hotelepam.utils.PageRequest;

import static ua.aleh1s.hotelepam.model.entity.RequestStatus.NEW;

public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;

    public RequestServiceImpl(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Override
    public void create(RequestEntity entity) {
        requestRepository.create(entity);
    }

    @Override
    public RequestEntity getById(Long id) {
        return requestRepository.findById(id)
                .orElseThrow(ApplicationException::new);
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
    public void changeStatus(RequestEntity request, RequestStatus requestStatus) {
        String path = ResourcesManager.getInstance().getValue("path.command.profile");

        if (!request.getStatus().equals(NEW))
            throw new ApplicationException("You cannot change status", path);

        request.setStatus(requestStatus);
        update(request);
    }
}

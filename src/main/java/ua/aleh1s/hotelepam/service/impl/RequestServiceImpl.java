package ua.aleh1s.hotelepam.service.impl;

import ua.aleh1s.hotelepam.controller.command.ApplicationException;
import ua.aleh1s.hotelepam.model.entity.RequestEntity;
import ua.aleh1s.hotelepam.utils.Page;
import ua.aleh1s.hotelepam.utils.PageRequest;
import ua.aleh1s.hotelepam.model.repository.RequestRepository;
import ua.aleh1s.hotelepam.service.RequestService;

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
    public Page<RequestEntity> getAllActiveByUserId(Long userId, PageRequest pageRequest) {
        return requestRepository.getAllActiveByUserId(userId, pageRequest);
    }
}

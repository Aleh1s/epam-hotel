package ua.aleh1s.hotelepam.service;

import ua.aleh1s.hotelepam.controller.command.ApplicationException;
import ua.aleh1s.hotelepam.model.entity.RequestEntity;
import ua.aleh1s.hotelepam.model.repository.RequestRepository;

public class RequestService {

    private final RequestRepository requestRepository;

    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public void create(RequestEntity requestEntity) {
        requestRepository.create(requestEntity);
    }

    public RequestEntity getById(Long requestId) {
        return requestRepository.findById(requestId)
                .orElseThrow(ApplicationException::new);
    }

    public void update(RequestEntity requestEntity) {
        requestRepository.update(requestEntity);
    }
}

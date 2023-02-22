package ua.aleh1s.hotelepam.service;

import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.controller.command.ApplicationException;
import ua.aleh1s.hotelepam.model.entity.RequestEntity;
import ua.aleh1s.hotelepam.model.repository.RequestRepository;

public class RequestService {


    public void create(RequestEntity requestEntity) {
        RequestRepository requestRepository =
                AppContext.getInstance().getRequestRepository();

        requestRepository.create(requestEntity);
    }

    public RequestEntity getById(Long requestId) {
        RequestRepository requestRepository =
                AppContext.getInstance().getRequestRepository();

        return requestRepository.findById(requestId)
                .orElseThrow(ApplicationException::new);
    }

    public void update(RequestEntity requestEntity) {
        RequestRepository requestRepository =
                AppContext.getInstance().getRequestRepository();

        requestRepository.update(requestEntity);
    }
}

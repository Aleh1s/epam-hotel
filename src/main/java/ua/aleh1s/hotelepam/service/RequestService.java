package ua.aleh1s.hotelepam.service;

import ua.aleh1s.hotelepam.model.entity.RequestEntity;
import ua.aleh1s.hotelepam.utils.Page;
import ua.aleh1s.hotelepam.utils.PageRequest;

public interface RequestService {

    void create(RequestEntity entity);
    RequestEntity getById(Long id);
    void update(RequestEntity entity);

    Page<RequestEntity> getAllActiveByUserId(Long userId, PageRequest pageRequest);
}

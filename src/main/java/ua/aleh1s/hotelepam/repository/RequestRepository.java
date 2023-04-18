package ua.aleh1s.hotelepam.repository;

import ua.aleh1s.hotelepam.model.entity.RequestEntity;
import ua.aleh1s.hotelepam.utils.Page;
import ua.aleh1s.hotelepam.utils.PageRequest;

import java.util.Optional;

public interface RequestRepository {

    void create(RequestEntity request);

    Page<RequestEntity> getAllActiveByUserId(Long userId, PageRequest pageRequest);

    Optional<RequestEntity> findById(Long requestId);

    void update(RequestEntity requestEntity);
}

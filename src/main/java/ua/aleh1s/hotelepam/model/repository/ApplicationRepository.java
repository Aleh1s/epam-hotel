package ua.aleh1s.hotelepam.model.repository;

import ua.aleh1s.hotelepam.controller.page.Page;
import ua.aleh1s.hotelepam.controller.page.PageRequest;
import ua.aleh1s.hotelepam.model.criteria.Criteria;
import ua.aleh1s.hotelepam.model.entity.ApplicationEntity;
import ua.aleh1s.hotelepam.model.entity.ApplicationStatus;
import ua.aleh1s.hotelepam.model.pagination.Pagination;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository {

    void create(ApplicationEntity applicationEntity);

    Page<ApplicationEntity> getAllByStatus(ApplicationStatus status, PageRequest request);


    Optional<ApplicationEntity> getById(Long id);

    void update(ApplicationEntity application);
}

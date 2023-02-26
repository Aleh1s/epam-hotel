package ua.aleh1s.hotelepam.model.repository;

import ua.aleh1s.hotelepam.model.entity.ApplicationStatus;
import ua.aleh1s.hotelepam.model.pagination.Page;
import ua.aleh1s.hotelepam.model.pagination.PageRequest;
import ua.aleh1s.hotelepam.model.entity.ApplicationEntity;

import java.util.Optional;

public interface ApplicationRepository {
    void create(ApplicationEntity applicationEntity);
    Optional<ApplicationEntity> getById(Long id);
    void update(ApplicationEntity application);
    Page<ApplicationEntity> getAllByApplicationStatus(ApplicationStatus status, PageRequest pageRequest);
}

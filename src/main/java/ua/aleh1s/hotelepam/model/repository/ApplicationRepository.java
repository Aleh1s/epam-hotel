package ua.aleh1s.hotelepam.model.repository;

import ua.aleh1s.hotelepam.model.entity.ApplicationStatus;
import ua.aleh1s.hotelepam.utils.Page;
import ua.aleh1s.hotelepam.utils.PageRequest;
import ua.aleh1s.hotelepam.model.entity.ApplicationEntity;

import java.util.Optional;

public interface ApplicationRepository {
    void create(ApplicationEntity applicationEntity);
    Optional<ApplicationEntity> findById(Long id);
    void update(ApplicationEntity application);
    Page<ApplicationEntity> getAllByApplicationStatus(ApplicationStatus status, PageRequest pageRequest);
}

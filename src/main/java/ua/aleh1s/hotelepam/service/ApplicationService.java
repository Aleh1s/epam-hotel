package ua.aleh1s.hotelepam.service;

import ua.aleh1s.hotelepam.model.entity.ApplicationEntity;
import ua.aleh1s.hotelepam.model.entity.ApplicationStatus;
import ua.aleh1s.hotelepam.utils.Page;
import ua.aleh1s.hotelepam.utils.PageRequest;

public interface ApplicationService {

    ApplicationEntity getById(Long id);
    void update(ApplicationEntity entity);
    Page<ApplicationEntity> getAllByApplicationStatus(ApplicationStatus status, PageRequest pageRequest);
    void create(ApplicationEntity entity);

}

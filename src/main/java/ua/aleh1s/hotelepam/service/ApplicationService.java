package ua.aleh1s.hotelepam.service;

import ua.aleh1s.hotelepam.exception.ServiceException;
import ua.aleh1s.hotelepam.model.entity.ApplicationEntity;
import ua.aleh1s.hotelepam.model.entity.ApplicationStatus;
import ua.aleh1s.hotelepam.utils.Page;
import ua.aleh1s.hotelepam.utils.PageRequest;

public interface ApplicationService {

    ApplicationEntity getApplicationById(Long id) throws ServiceException;
    void update(ApplicationEntity entity) throws ServiceException;
    Page<ApplicationEntity> getAllByApplicationStatus(ApplicationStatus status, PageRequest pageRequest) throws ServiceException;
    void create(ApplicationEntity entity) throws ServiceException;

}

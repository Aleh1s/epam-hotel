package ua.aleh1s.hotelepam.service;

import ua.aleh1s.hotelepam.controller.command.ApplicationException;
import ua.aleh1s.hotelepam.model.entity.ApplicationEntity;
import ua.aleh1s.hotelepam.model.entity.ApplicationStatus;
import ua.aleh1s.hotelepam.model.pagination.Page;
import ua.aleh1s.hotelepam.model.pagination.PageRequest;
import ua.aleh1s.hotelepam.model.repository.ApplicationRepository;
import ua.aleh1s.hotelepam.model.querybuilder.specification.where.WhereSpecification;

public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public ApplicationEntity getById(Long id) {
        return applicationRepository.getById(id)
                .orElseThrow(ApplicationException::new);
    }

    public void update(ApplicationEntity application) {
        applicationRepository.update(application);
    }

    public Page<ApplicationEntity> getAllByApplicationStatus(ApplicationStatus status, PageRequest pageRequest) {
        return applicationRepository.getAllByApplicationStatus(status, pageRequest);
    }

    public void create(ApplicationEntity applicationEntry) {
        applicationRepository.create(applicationEntry);
    }
}

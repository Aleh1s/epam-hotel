package ua.aleh1s.hotelepam.service;

import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.controller.command.ApplicationException;
import ua.aleh1s.hotelepam.model.entity.ApplicationEntity;
import ua.aleh1s.hotelepam.model.entity.ApplicationStatus;
import ua.aleh1s.hotelepam.model.pagination.Page;
import ua.aleh1s.hotelepam.model.pagination.PageRequest;
import ua.aleh1s.hotelepam.model.repository.ApplicationRepository;

public class ApplicationService {

    public ApplicationEntity getById(Long id) {
        ApplicationRepository applicationRepository =
                AppContext.getInstance().getApplicationRepository();

        return applicationRepository.getById(id)
                .orElseThrow(ApplicationException::new);
    }

    public void update(ApplicationEntity application) {
        ApplicationRepository applicationRepository =
                AppContext.getInstance().getApplicationRepository();

        applicationRepository.update(application);
    }

    public Page<ApplicationEntity> getAllByStatus(ApplicationStatus status, PageRequest pageRequest) {
        ApplicationRepository applicationRepository
                = AppContext.getInstance().getApplicationRepository();

        return applicationRepository.getAllByStatus(status, pageRequest);
    }

    public void create(ApplicationEntity applicationEntry) {
        ApplicationRepository applicationRepository
                = AppContext.getInstance().getApplicationRepository();

        applicationRepository.create(applicationEntry);
    }
}

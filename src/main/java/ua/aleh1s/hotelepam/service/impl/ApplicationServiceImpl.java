package ua.aleh1s.hotelepam.service.impl;

import lombok.AllArgsConstructor;
import ua.aleh1s.hotelepam.controller.command.ApplicationException;
import ua.aleh1s.hotelepam.model.entity.ApplicationEntity;
import ua.aleh1s.hotelepam.model.entity.ApplicationStatus;
import ua.aleh1s.hotelepam.utils.Page;
import ua.aleh1s.hotelepam.utils.PageRequest;
import ua.aleh1s.hotelepam.model.repository.ApplicationRepository;
import ua.aleh1s.hotelepam.service.ApplicationService;

@AllArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;

    @Override
    public ApplicationEntity getApplicationById(Long id) {
        return applicationRepository.findById(id)
                .orElseThrow(ApplicationException::new);
    }

    @Override
    public void update(ApplicationEntity application) {
        applicationRepository.update(application);
    }

    @Override
    public Page<ApplicationEntity> getAllByApplicationStatus(ApplicationStatus status, PageRequest pageRequest) {
        return applicationRepository.getAllByApplicationStatus(status, pageRequest);
    }

    @Override
    public void create(ApplicationEntity entity) {
        applicationRepository.create(entity);
    }
}

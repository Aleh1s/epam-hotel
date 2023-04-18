package ua.aleh1s.hotelepam.service.impl;

import lombok.AllArgsConstructor;
import ua.aleh1s.hotelepam.exception.ServiceException;
import ua.aleh1s.hotelepam.model.dto.ApplicationDto;
import ua.aleh1s.hotelepam.model.entity.ApplicationEntity;
import ua.aleh1s.hotelepam.model.entity.ApplicationStatus;
import ua.aleh1s.hotelepam.service.exception.ValidationException;
import ua.aleh1s.hotelepam.utils.Page;
import ua.aleh1s.hotelepam.utils.PageRequest;
import ua.aleh1s.hotelepam.repository.ApplicationRepository;
import ua.aleh1s.hotelepam.service.ApplicationService;
import ua.aleh1s.hotelepam.validator.impl.ApplicationDtoValidator;

import java.time.LocalDateTime;

@AllArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;

    @Override
    public ApplicationEntity getApplicationById(Long id) throws ServiceException {
        return applicationRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Application with id " + id +" does not exist"));
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

    @Override
    public void create(ApplicationDto applicationDto, Long customerId) throws ServiceException {
        ApplicationDtoValidator validator = new ApplicationDtoValidator();
        validator.validate(applicationDto);

        if (validator.hasErrors())
            throw new ValidationException(validator.getMessagesByRejectedValue());

        ApplicationEntity applicationEntry = ApplicationEntity.builder()
                .guests(applicationDto.getGuests())
                .roomClass(applicationDto.getRoomClass())
                .checkIn(applicationDto.getCheckIn())
                .checkOut(applicationDto.getCheckOut())
                .status(ApplicationStatus.NEW)
                .customerId(customerId)
                .createdAt(LocalDateTime.now())
                .build();

        applicationRepository.create(applicationEntry);
    }
}

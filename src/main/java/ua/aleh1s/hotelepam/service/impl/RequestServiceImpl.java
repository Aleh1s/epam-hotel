package ua.aleh1s.hotelepam.service.impl;

import lombok.AllArgsConstructor;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.exception.ApplicationException;
import ua.aleh1s.hotelepam.exception.ServiceException;
import ua.aleh1s.hotelepam.model.dto.RequestDto;
import ua.aleh1s.hotelepam.model.entity.*;
import ua.aleh1s.hotelepam.repository.RequestRepository;
import ua.aleh1s.hotelepam.service.ApplicationService;
import ua.aleh1s.hotelepam.service.RequestService;
import ua.aleh1s.hotelepam.service.RoomService;
import ua.aleh1s.hotelepam.service.exception.ValidationException;
import ua.aleh1s.hotelepam.utils.Page;
import ua.aleh1s.hotelepam.utils.PageRequest;
import ua.aleh1s.hotelepam.utils.Period;
import ua.aleh1s.hotelepam.validator.impl.RequestDtoValidator;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static ua.aleh1s.hotelepam.model.entity.RequestStatus.CONFIRMED;
import static ua.aleh1s.hotelepam.model.entity.RequestStatus.NEW;

@AllArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final ApplicationService applicationService;
    private final RoomService roomService;

    @Override
    public void create(RequestEntity entity) {
        requestRepository.create(entity);
    }

    @Override
    public void create(RequestDto requestDto, Long applicationId) throws ServiceException {
        RequestDtoValidator validator = new RequestDtoValidator();
        validator.validate(requestDto);

        if (validator.hasErrors())
            throw new ValidationException(validator.getMessagesByRejectedValue());

        ApplicationEntity application = applicationService.getApplicationById(applicationId);
        if (application.getStatus().equals(ApplicationStatus.PROCESSED))
            throw new ServiceException("Application is already processed");

        if (!roomService.isRoomAvailable(requestDto.getRoomNumber(), Period.between(requestDto.getCheckIn(), requestDto.getCheckOut())))
            throw new ServiceException("Room is unavailable");

        application.setStatus(ApplicationStatus.PROCESSED);
        applicationService.update(application);

        BigDecimal totalPrice = roomService.getTotalPrice(requestDto.getRoomNumber(), Period.between(requestDto.getCheckIn(), requestDto.getCheckOut()));

        RequestEntity requestEntity = RequestEntity.builder()
                .roomNumber(requestDto.getRoomNumber())
                .customerId(application.getCustomerId())
                .status(RequestStatus.NEW)
                .checkIn(requestDto.getCheckIn())
                .checkOut(requestDto.getCheckOut())
                .totalAmount(totalPrice)
                .createdAt(LocalDateTime.now())
                .build();

        requestRepository.create(requestEntity);
    }

    @Override
    public RequestEntity getById(Long id) throws ServiceException {
        return requestRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Request with id " + id + " does not exist"));
    }

    @Override
    public void update(RequestEntity entity) {
        requestRepository.update(entity);
    }

    @Override
    public Page<RequestEntity> getAllActiveRequestsByUserId(Long userId, PageRequest pageRequest) {
        return requestRepository.getAllActiveByUserId(userId, pageRequest);
    }

    @Override
    public void changeStatus(RequestEntity request, RequestStatus requestStatus) throws ServiceException {
        if (!request.getStatus().equals(NEW))
            throw new ServiceException("You cannot change status");

        request.setStatus(requestStatus);
        update(request);
    }

    @Override
    public void confirmRequest(Long requestId) throws ServiceException {
        RequestEntity requestEntity = getById(requestId);
        RoomEntity room = roomService.getByRoomNumber(requestEntity.getRoomNumber());

        if (room.getIsUnavailable())
            throw new ServiceException("Room is unavailable now. Try to pick another room");

        changeStatus(requestEntity, CONFIRMED);
    }
}

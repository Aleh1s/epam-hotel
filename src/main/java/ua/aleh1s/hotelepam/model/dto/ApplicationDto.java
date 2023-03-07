package ua.aleh1s.hotelepam.model.dto;

import ua.aleh1s.hotelepam.model.entity.ApplicationStatus;
import ua.aleh1s.hotelepam.model.entity.RoomClass;

import java.time.LocalDateTime;
import java.util.Date;

public record ApplicationDto(
        Long id,
        Integer guests,
        RoomClass roomClass,
        Date checkIn,
        Date checkOut,
        LocalDateTime createdAt,
        ApplicationStatus status,
        Long customerId
) {}

package ua.aleh1s.hotelepam.model.dto;

import ua.aleh1s.hotelepam.model.entity.RequestStatus;

import java.math.BigDecimal;
import java.util.Date;

public record RequestDto(
        Long id,
        Integer roomNumber,
        Long customerId,
        RequestStatus status,
        Date checkIn,
        Date checkOut,
        BigDecimal totalAmount
) { }

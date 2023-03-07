package ua.aleh1s.hotelepam.model.dto;

import ua.aleh1s.hotelepam.model.entity.ReservationStatus;

import java.math.BigDecimal;
import java.util.Date;

public record ReservationDto(
        Long id,
        Integer roomNumber,
        Long customerId,
        Date checkIn,
        Date checkOut,
        Date createdAt,
        Date expiredAt,
        Date payedAt,
        BigDecimal totalAmount,
        ReservationStatus status
) { }
package ua.aleh1s.hotelepam.controller.dtomapper;

import ua.aleh1s.hotelepam.controller.dto.ReservationDto;
import ua.aleh1s.hotelepam.model.entity.ReservationEntity;

import java.sql.Date;
import java.util.function.Function;

import static ua.aleh1s.hotelepam.utils.Utils.toDate;

public class ReservationDtoMapper implements Function<ReservationEntity, ReservationDto> {

    @Override
    public ReservationDto apply(ReservationEntity entity) {
        return ReservationDto.Builder.newBuilder()
                .id(entity.getId())
                .roomNumber(entity.getRoomNumber())
                .customerId(entity.getCustomerId())
                .checkIn(Date.valueOf(entity.getCheckIn()))
                .checkOut(Date.valueOf(entity.getCheckOut()))
                .expiredAt(toDate(entity.getExpiredAt()))
                .createdAt(toDate(entity.getCreatedAt()))
                .payedAt(toDate(entity.getPayedAt()))
                .totalAmount(entity.getTotalAmount())
                .status(entity.getStatus())
                .build();
    }
}

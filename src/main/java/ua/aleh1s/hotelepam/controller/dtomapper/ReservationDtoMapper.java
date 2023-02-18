package ua.aleh1s.hotelepam.controller.dtomapper;

import ua.aleh1s.hotelepam.controller.dto.ReservationDto;
import ua.aleh1s.hotelepam.model.entity.ReservationEntity;

import java.sql.Date;
import java.util.function.Function;

import static ua.aleh1s.hotelepam.Utils.toDate;

public class ReservationDtoMapper implements Function<ReservationEntity, ReservationDto> {

    @Override
    public ReservationDto apply(ReservationEntity entity) {
        return ReservationDto.Builder.newBuilder()
                .id(entity.getId())
                .roomNumber(entity.getRoomNumber())
                .customerId(entity.getCustomerId())
                .entryDate(Date.valueOf(entity.getEntryDate()))
                .leavingDate(Date.valueOf(entity.getLeavingDate()))
                .expiredAt(toDate(entity.getExpiredAt()))
                .createdAt(toDate(entity.getCreatedAt()))
                .payedAt(toDate(entity.getPayedAt()))
                .totalAmount(entity.getTotalAmount())
                .status(entity.getStatus())
                .build();
    }
}

package ua.aleh1s.hotelepam.controller.mapper;

import ua.aleh1s.hotelepam.controller.dto.ReservationDto;
import ua.aleh1s.hotelepam.model.entity.ReservationEntity;

import java.util.function.Function;

public class ReservationDtoMapper implements Function<ReservationEntity, ReservationDto> {

    @Override
    public ReservationDto apply(ReservationEntity entity) {
        return ReservationDto.Builder.newBuilder()
                .id(entity.getId())
                .roomNumber(entity.getRoomNumber())
                .customerId(entity.getCustomerId())
                .entryDate(entity.getEntryDate())
                .leavingDate(entity.getLeavingDate())
                .createdAt(entity.getCreatedAt())
                .payedAt(entity.getPayedAt())
                .totalAmount(entity.getTotalAmount())
                .status(entity.getStatus())
                .build();
    }
}

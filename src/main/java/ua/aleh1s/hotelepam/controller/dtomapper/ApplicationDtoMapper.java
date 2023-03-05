package ua.aleh1s.hotelepam.controller.dtomapper;

import ua.aleh1s.hotelepam.controller.dto.ApplicationDto;
import ua.aleh1s.hotelepam.model.entity.ApplicationEntity;
import ua.aleh1s.hotelepam.utils.Utils;

import java.util.function.Function;

import static ua.aleh1s.hotelepam.utils.Utils.toDate;

public class ApplicationDtoMapper implements Function<ApplicationEntity, ApplicationDto> {
    @Override
    public ApplicationDto apply(ApplicationEntity entity) {
        return ApplicationDto.Builder.newBuilder()
                .id(entity.getId())
                .guests(entity.getGuests())
                .roomClass(entity.getClazz())
                .checkIn(toDate(entity.getCheckIn()))
                .checkOut(toDate(entity.getCheckOut()))
                .status(entity.getStatus())
                .customerId(entity.getCustomerId())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}

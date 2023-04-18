package ua.aleh1s.hotelepam.mapper.dtomapper.entitytodto;

import ua.aleh1s.hotelepam.model.dto.ApplicationDto;
import ua.aleh1s.hotelepam.model.entity.ApplicationEntity;

import java.util.function.Function;

import static ua.aleh1s.hotelepam.utils.Utils.toDate;

public class ApplicationDtoMapper implements Function<ApplicationEntity, ApplicationDto> {
    @Override
    public ApplicationDto apply(ApplicationEntity entity) {
        return ApplicationDto.builder()
                .id(entity.getId())
                .guests(entity.getGuests())
                .roomClass(entity.getRoomClass())
                .checkIn(entity.getCheckIn())
                .checkOut(entity.getCheckOut())
                .createdAt(entity.getCreatedAt())
                .status(entity.getStatus())
                .customerId(entity.getCustomerId())
                .build();
    }
}

package ua.aleh1s.hotelepam.controller.dtomapper;

import ua.aleh1s.hotelepam.controller.dto.ApplicationDto;
import ua.aleh1s.hotelepam.model.entity.ApplicationEntity;

import java.util.function.Function;

public class ApplicationDtoMapper implements Function<ApplicationEntity, ApplicationDto> {
    @Override
    public ApplicationDto apply(ApplicationEntity applicationEntity) {
        return ApplicationDto.Builder.newBuilder()
                .id(applicationEntity.getId())
                .guests(applicationEntity.getGuests())
                .roomClass(applicationEntity.getClazz())
                .checkIn(applicationEntity.getCheckIn())
                .checkOut(applicationEntity.getCheckOut())
                .status(applicationEntity.getStatus())
                .customerId(applicationEntity.getCustomerId())
                .build();
    }
}

package ua.aleh1s.hotelepam.controller.mapper;

import ua.aleh1s.hotelepam.controller.dto.ApplicationDto;
import ua.aleh1s.hotelepam.model.entity.ApplicationEntity;

import java.util.function.Function;

public class ApplicationDtoMapper implements Function<ApplicationEntity, ApplicationDto> {
    @Override
    public ApplicationDto apply(ApplicationEntity applicationEntity) {
        return ApplicationDto.Builder.newBuilder()
                .id(applicationEntity.getId())
                .guestsNumber(applicationEntity.getGuestsNumber())
                .roomClass(applicationEntity.getRoomClass())
                .entryDate(applicationEntity.getEntryDate())
                .leavingDate(applicationEntity.getLeavingDate())
                .status(applicationEntity.getStatus())
                .customerId(applicationEntity.getCustomerId())
                .build();
    }
}

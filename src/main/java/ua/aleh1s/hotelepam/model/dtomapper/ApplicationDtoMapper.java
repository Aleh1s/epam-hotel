package ua.aleh1s.hotelepam.model.dtomapper;

import ua.aleh1s.hotelepam.model.dto.ApplicationDto;
import ua.aleh1s.hotelepam.model.entity.ApplicationEntity;

import java.util.function.Function;

import static ua.aleh1s.hotelepam.utils.Utils.toDate;

public class ApplicationDtoMapper implements Function<ApplicationEntity, ApplicationDto> {
    @Override
    public ApplicationDto apply(ApplicationEntity entity) {
        return new ApplicationDto(
                entity.getId(),
                entity.getGuests(),
                entity.getClazz(),
                toDate(entity.getCheckIn()),
                toDate(entity.getCheckOut()),
                entity.getCreatedAt(),
                entity.getStatus(),
                entity.getCustomerId()
        );
    }
}

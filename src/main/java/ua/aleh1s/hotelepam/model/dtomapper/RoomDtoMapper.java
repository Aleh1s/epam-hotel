package ua.aleh1s.hotelepam.model.dtomapper;

import ua.aleh1s.hotelepam.model.dto.RoomDto;
import ua.aleh1s.hotelepam.model.entity.RoomEntity;

import java.util.function.Function;

public class RoomDtoMapper implements Function<RoomEntity, RoomDto> {
    @Override
    public RoomDto apply(RoomEntity roomEntity) {
        return new RoomDto(
                roomEntity.getRoomNumber(),
                roomEntity.getRoomClass().name().toLowerCase(),
                roomEntity.getStatus().name().toLowerCase(),
                roomEntity.getDescription(),
                roomEntity.getBusyUntil(),
                roomEntity.getPrice().doubleValue(),
                roomEntity.getName(),
                roomEntity.getAttributes(),
                roomEntity.getBedsNumber(),
                roomEntity.getPersonsNumber(),
                roomEntity.getArea()
        );
    }
}

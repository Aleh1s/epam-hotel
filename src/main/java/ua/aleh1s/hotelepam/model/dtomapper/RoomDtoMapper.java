package ua.aleh1s.hotelepam.model.dtomapper;

import ua.aleh1s.hotelepam.model.dto.RoomDto;
import ua.aleh1s.hotelepam.model.entity.RoomEntity;

import java.util.List;
import java.util.function.Function;

public class RoomDtoMapper implements Function<RoomEntity, RoomDto> {
    @Override
    public RoomDto apply(RoomEntity room) {
        return new RoomDto(
                room.getNumber(),
                room.getClazz(),
                room.getTitle(),
                room.getDescription(),
                List.of(room.getAttributes()),
                room.getBeds(),
                room.getGuests(),
                room.getPrice().doubleValue(),
                room.getArea(),
                room.getIsUnavailable()
        );
    }
}

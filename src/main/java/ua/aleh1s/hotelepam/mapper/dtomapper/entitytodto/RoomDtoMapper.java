package ua.aleh1s.hotelepam.mapper.dtomapper.entitytodto;

import ua.aleh1s.hotelepam.model.dto.RoomDto;
import ua.aleh1s.hotelepam.model.entity.RoomEntity;

import java.util.List;
import java.util.function.Function;

public class RoomDtoMapper implements Function<RoomEntity, RoomDto> {
    @Override
    public RoomDto apply(RoomEntity room) {
        return RoomDto.builder()
                .number(room.getNumber())
                .clazz(room.getClazz())
                .title(room.getTitle())
                .description(room.getDescription())
                .attributes(room.getAttributes())
                .beds(room.getBeds())
                .guests(room.getGuests())
                .price(room.getPrice())
                .area(room.getArea())
                .isUnavailable(room.getIsUnavailable())
                .build();
    }
}

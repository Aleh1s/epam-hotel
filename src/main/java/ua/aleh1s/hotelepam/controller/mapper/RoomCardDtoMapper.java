package ua.aleh1s.hotelepam.controller.mapper;

import ua.aleh1s.hotelepam.controller.dto.RoomCardDto;
import ua.aleh1s.hotelepam.model.entity.RoomEntity;

import java.util.Locale;
import java.util.function.Function;

public class RoomCardDtoMapper implements Function<RoomEntity, RoomCardDto> {
    @Override
    public RoomCardDto apply(RoomEntity room) {
        return RoomCardDto.Builder.newBuilder()
                .roomNumber(room.getRoomNumber())
                .roomStatus(room.getStatus().name().toLowerCase(Locale.ROOT))
                .roomClass(room.getRoomClass().name().toLowerCase(Locale.ROOT))
                .personsNumber(room.getPersonsNumber())
                .bedsNumber(room.getBedsNumber())
                .area(room.getArea())
                .price(room.getPrice().doubleValue())
                .build();
    }
}

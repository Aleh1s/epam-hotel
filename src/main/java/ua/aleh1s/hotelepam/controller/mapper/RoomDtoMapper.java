package ua.aleh1s.hotelepam.controller.mapper;

import ua.aleh1s.hotelepam.controller.dto.RoomDto;
import ua.aleh1s.hotelepam.model.entity.RoomEntity;

import java.util.function.Function;

public class RoomDtoMapper implements Function<RoomEntity, RoomDto> {
    @Override
    public RoomDto apply(RoomEntity roomEntity) {
        return RoomDto.Builder.newBuilder()
                .roomNumber(roomEntity.getRoomNumber())
                .roomClass(roomEntity.getRoomClass().name().toLowerCase())
                .roomStatus(roomEntity.getStatus().name().toLowerCase())
                .description(roomEntity.getDescription())
                .busyUntil(roomEntity.getBusyUntil())
                .price(roomEntity.getPrice().doubleValue())
                .name(roomEntity.getName())
                .attributes(roomEntity.getAttributes())
                .bedsNumber(roomEntity.getBedsNumber())
                .personsNumber(roomEntity.getPersonsNumber())
                .area(roomEntity.getArea())
                .build();
    }
}

package ua.aleh1s.hotelepam.controller.dtomapper;

import ua.aleh1s.hotelepam.controller.dto.RequestDto;
import ua.aleh1s.hotelepam.model.entity.RequestEntity;

import java.util.function.Function;

public class RequestDtoMapper implements Function<RequestEntity, RequestDto> {
    @Override
    public RequestDto apply(RequestEntity request) {
        return RequestDto.Builder.newBuilder()
                .id(request.getId())
                .customerId(request.getCustomerId())
                .roomNumber(request.getRoomNumber())
                .status(request.getStatus())
                .checkIn(request.getCheckIn())
                .checkOut(request.getCheckOut())
                .totalAmount(request.getTotalAmount())
                .build();
    }
}

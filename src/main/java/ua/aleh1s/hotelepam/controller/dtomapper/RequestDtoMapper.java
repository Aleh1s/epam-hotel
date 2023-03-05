package ua.aleh1s.hotelepam.controller.dtomapper;

import ua.aleh1s.hotelepam.controller.dto.RequestDto;
import ua.aleh1s.hotelepam.model.entity.RequestEntity;
import ua.aleh1s.hotelepam.utils.Utils;

import java.util.function.Function;

import static ua.aleh1s.hotelepam.utils.Utils.toDate;

public class RequestDtoMapper implements Function<RequestEntity, RequestDto> {
    @Override
    public RequestDto apply(RequestEntity request) {
        return RequestDto.Builder.newBuilder()
                .id(request.getId())
                .customerId(request.getCustomerId())
                .roomNumber(request.getRoomNumber())
                .status(request.getStatus())
                .checkIn(toDate(request.getCheckIn()))
                .checkOut(toDate(request.getCheckOut()))
                .totalAmount(request.getTotalAmount())
                .build();
    }
}

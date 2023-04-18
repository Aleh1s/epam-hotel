package ua.aleh1s.hotelepam.mapper.dtomapper.entitytodto;

import ua.aleh1s.hotelepam.model.dto.RequestDto;
import ua.aleh1s.hotelepam.model.entity.RequestEntity;

import java.util.function.Function;

import static ua.aleh1s.hotelepam.utils.Utils.toDate;

public class RequestDtoMapper implements Function<RequestEntity, RequestDto> {
    @Override
    public RequestDto apply(RequestEntity request) {
        return RequestDto.builder()
                .id(request.getId())
                .roomNumber(request.getRoomNumber())
                .customerId(request.getCustomerId())
                .status(request.getStatus())
                .checkIn(request.getCheckIn())
                .checkOut(request.getCheckOut())
                .totalAmount(request.getTotalAmount())
                .build();
    }
}

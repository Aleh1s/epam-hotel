package ua.aleh1s.hotelepam.controller.mapper;

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
                .entryDate(request.getEntryDate())
                .leavingDate(request.getLeavingDate())
                .totalAmount(request.getTotalAmount())
                .build();
    }
}

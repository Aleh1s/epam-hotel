package ua.aleh1s.hotelepam.model.dtomapper.entitytodto;

import ua.aleh1s.hotelepam.model.dto.RequestDto;
import ua.aleh1s.hotelepam.model.entity.RequestEntity;

import java.util.function.Function;

import static ua.aleh1s.hotelepam.utils.Utils.toDate;

public class RequestDtoMapper implements Function<RequestEntity, RequestDto> {
    @Override
    public RequestDto apply(RequestEntity request) {
        return new RequestDto(
                request.getId(),
                request.getRoomNumber(),
                request.getCustomerId(),
                request.getStatus(),
                toDate(request.getCheckIn()),
                toDate(request.getCheckOut()),
                request.getTotalAmount()
        );
    }
}

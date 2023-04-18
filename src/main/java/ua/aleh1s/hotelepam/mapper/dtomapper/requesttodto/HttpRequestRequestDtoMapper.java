package ua.aleh1s.hotelepam.mapper.dtomapper.requesttodto;

import jakarta.servlet.http.HttpServletRequest;
import ua.aleh1s.hotelepam.mapper.dtomapper.Mapper;
import ua.aleh1s.hotelepam.model.dto.RequestDto;

public class HttpRequestRequestDtoMapper extends Mapper<HttpServletRequest, RequestDto> {
    @Override
    public RequestDto map(HttpServletRequest request) {
        String roomNumber = "roomNumber", checkIn = "checkIn", checkOut = "checkOut";
        return RequestDto.builder()
                .roomNumber(parseInt(roomNumber, request.getParameter(roomNumber)))
                .checkIn(parseLocalDate(checkIn, request.getParameter(checkIn)))
                .checkOut(parseLocalDate(checkOut, request.getParameter(checkOut)))
                .build();
    }
}

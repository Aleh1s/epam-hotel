package ua.aleh1s.hotelepam.mapper.dtomapper.requesttodto;

import jakarta.servlet.http.HttpServletRequest;
import ua.aleh1s.hotelepam.mapper.dtomapper.Mapper;
import ua.aleh1s.hotelepam.model.dto.ApplicationDto;
import ua.aleh1s.hotelepam.model.entity.RoomClass;

public class HttpRequestApplicationDtoMapper extends Mapper<HttpServletRequest, ApplicationDto> {

    @Override
    public ApplicationDto map(HttpServletRequest request) {
        String guests = "guests", clazz = "clazz", checkIn = "checkIn", checkOut = "checkOut";
        return ApplicationDto.builder()
                .guests(parseInt(guests, request.getParameter(guests)))
                .roomClass(RoomClass.atIndex(parseInt(clazz, request.getParameter(clazz))))
                .checkIn(parseLocalDate(checkIn, request.getParameter(checkIn)))
                .checkOut(parseLocalDate(checkOut, request.getParameter(checkOut)))
                .build();
    }
}

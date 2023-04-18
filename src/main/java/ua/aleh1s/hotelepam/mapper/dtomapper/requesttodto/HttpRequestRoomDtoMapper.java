package ua.aleh1s.hotelepam.mapper.dtomapper.requesttodto;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import ua.aleh1s.hotelepam.mapper.dtomapper.Mapper;
import ua.aleh1s.hotelepam.model.dto.RoomDto;
import ua.aleh1s.hotelepam.model.entity.RoomClass;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class HttpRequestRoomDtoMapper extends Mapper<HttpServletRequest, RoomDto> {
    @Override
    public RoomDto map(HttpServletRequest request) {
        String number = "number", clazz = "class", title = "title",
                description = "description", attributes = "attributes",
                beds = "beds", guests = "guests", price = "price", area = "area",
                image = "image";

        Part part = null;
        try {
            part = request.getPart(image);
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }

        String attributesStr = request.getParameter(attributes);
        List<String> attributeList = Arrays.stream(attributesStr.split(",")).toList();

        return RoomDto.builder()
                .number(parseInt(number, request.getParameter(number)))
                .clazz(RoomClass.atIndex(parseInt(clazz, request.getParameter(clazz))))
                .title(request.getParameter(title))
                .description(request.getParameter(description))
                .attributes(attributeList)
                .beds(parseInt(beds, request.getParameter(beds)))
                .guests(parseInt(guests, request.getParameter(guests)))
                .price(parseBigDecimal(price, request.getParameter(price)))
                .area(parseInt(area, request.getParameter(area)))
                .image(part)
                .build();
    }
}

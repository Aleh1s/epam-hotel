package ua.aleh1s.hotelepam.mapper.dtomapper.requesttodto;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.aleh1s.hotelepam.mapper.dtomapper.Mapper;
import ua.aleh1s.hotelepam.model.dto.RoomDto;
import ua.aleh1s.hotelepam.model.entity.RoomClass;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static java.util.Objects.nonNull;

public class HttpRequestRoomDtoMapper extends Mapper<HttpServletRequest, RoomDto> {

    private static final Logger logger = LogManager.getLogger(HttpRequestRoomDtoMapper.class);
    private static final Integer MAX_IMAGE_SIZE = 1_048_576;

    @Override
    public RoomDto map(HttpServletRequest request) {
        String number = "number", clazz = "class", title = "title",
                description = "description", attributes = "attributes",
                beds = "beds", guests = "guests", price = "price", area = "area",
                image = "image";

        byte[] imageBytes = new byte[0];

        try {
            Part part = request.getPart(image);
            if (nonNull(part)) {
                try (InputStream inputStream = part.getInputStream()) {
                    int actualSizeOfImage = inputStream.available();

                    if (actualSizeOfImage > MAX_IMAGE_SIZE) {
                        rejectValue(image, "Image size limit is reached");
                    } else {
                        imageBytes = new byte[actualSizeOfImage];
                        inputStream.read(imageBytes);
                    }
                }
            }
        } catch (IOException | ServletException e) {
            logger.error(e.getMessage(), e);
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
                .image(imageBytes)
                .build();
    }
}

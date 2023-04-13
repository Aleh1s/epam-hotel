package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.exception.ApplicationException;
import ua.aleh1s.hotelepam.model.entity.RoomClass;
import ua.aleh1s.hotelepam.model.entity.RoomEntity;
import ua.aleh1s.hotelepam.service.RoomService;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import static ua.aleh1s.hotelepam.utils.Utils.getBigDecimalValue;
import static ua.aleh1s.hotelepam.utils.Utils.getIntValue;

public class CreateRoomCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        RoomService roomService = AppContext.getInstance().getRoomService();
        ResourcesManager resourcesManager = ResourcesManager.getInstance();

        Integer number = getIntValue(request, "number");
        Integer classIdx = getIntValue(request, "class");
        Integer guests = getIntValue(request, "guests");
        Integer beds = getIntValue(request, "beds");
        Integer area = getIntValue(request, "area");
        BigDecimal price = getBigDecimalValue(request, "price");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String[] attributes = request.getParameter("attributes").split(",");

        String path = resourcesManager.getValue("path.page.create.room");

        if (roomService.existsByRoomNumber(number))
            throw new ApplicationException(
                    "Room with room number " + number + " already exists.", path);

        int maxSizeOfImage = 1_048_576;
        byte[] imageBytes;

        try {
            Part image = request.getPart("image");
            try (InputStream inputStream = image.getInputStream()) {
                int actualSizeOfImage = inputStream.available();

                if (actualSizeOfImage > maxSizeOfImage)
                    throw new ApplicationException(
                            "Max size of image is reached!!!. Try to pick another image.", path);

                imageBytes = new byte[actualSizeOfImage];
                inputStream.read(imageBytes);
            }

        } catch (IOException | ServletException e) {
            throw new ApplicationException();
        }

        RoomEntity newRoom = RoomEntity.builder()
                .number(number)
                .clazz(RoomClass.atIndex(classIdx))
                .title(title)
                .description(description)
                .attributes(attributes)
                .beds(beds)
                .guests(guests)
                .area(area)
                .price(price)
                .image(imageBytes)
                .isUnavailable(true)
                .build();

        roomService.create(newRoom);
        roomService.updateImage(newRoom);

        path = resourcesManager.getValue("path.command.get.rooms");
        try {
            response.sendRedirect(path);
            path = "redirect";
        } catch (IOException e) {
            throw new ApplicationException();
        }

        return path;
    }
}

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

import static ua.aleh1s.hotelepam.utils.Utils.*;

public class UpdateRoomCommand implements Command {

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
        Boolean isUnavailable = getBoolean(request, "isUnavailable");

        String path = resourcesManager.getValue("path.page.room.editor");

        int maxSizeOfImage = 1_048_576;
        boolean isImagePresent = false;
        byte[] imageBytes = new byte[0];

        try {
            Part image = request.getPart("image");
            if (image.getSize() != 0) {
                try (InputStream inputStream = image.getInputStream()) {
                    int actualSizeOfImage = inputStream.available();

                    if (actualSizeOfImage > maxSizeOfImage)
                        throw new ApplicationException(
                                "Max size of image is reached!!!. Try to pick another image.", path);

                    imageBytes = new byte[actualSizeOfImage];
                    inputStream.read(imageBytes);
                }
                isImagePresent = true;
            }
        } catch (IOException | ServletException e) {
            throw new ApplicationException();
        }

        RoomEntity editedRoom = RoomEntity.builder()
                .number(number)
                .clazz(RoomClass.atIndex(classIdx))
                .title(title)
                .description(description)
                .attributes(attributes)
                .beds(beds)
                .guests(guests)
                .area(area)
                .price(price)
                .isUnavailable(isUnavailable)
                .build();

        roomService.update(editedRoom);

        if (isImagePresent) {
            editedRoom.setImage(imageBytes);
            roomService.updateImage(editedRoom);
        }

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

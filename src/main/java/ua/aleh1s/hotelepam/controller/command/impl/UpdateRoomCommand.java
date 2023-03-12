package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.ApplicationException;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.model.entity.RoomClass;
import ua.aleh1s.hotelepam.model.entity.RoomEntity;
import ua.aleh1s.hotelepam.service.RoomService;

import java.io.IOException;
import java.math.BigDecimal;

import static ua.aleh1s.hotelepam.utils.Utils.*;

public class UpdateRoomCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        RoomService roomService = AppContext.getInstance().getRoomService();

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

        String path = ResourcesManager.getInstance().getValue("path.command.get.rooms");
        try {
            response.sendRedirect(path);
            path = "redirect";
        } catch (IOException e) {
            throw new ApplicationException();
        }

        return path;
    }
}

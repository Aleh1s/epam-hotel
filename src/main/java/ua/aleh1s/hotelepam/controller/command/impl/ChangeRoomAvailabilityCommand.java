package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.ApplicationException;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.model.entity.RoomEntity;
import ua.aleh1s.hotelepam.service.RoomService;
import ua.aleh1s.hotelepam.utils.Utils;

import java.io.IOException;

import static ua.aleh1s.hotelepam.utils.Utils.getBoolean;
import static ua.aleh1s.hotelepam.utils.Utils.getIntValue;

public class ChangeRoomAvailabilityCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        RoomService roomService = AppContext.getInstance().getRoomService();

        Integer number = getIntValue(request, "number");
        Boolean value = getBoolean(request, "value");

        RoomEntity room = roomService.getByRoomNumber(number);
        room.setIsUnavailable(value);
        roomService.update(room);

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

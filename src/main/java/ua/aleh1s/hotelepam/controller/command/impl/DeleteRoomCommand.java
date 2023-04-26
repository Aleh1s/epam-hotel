package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.exception.ApplicationException;
import ua.aleh1s.hotelepam.exception.ServiceException;
import ua.aleh1s.hotelepam.service.RoomService;
import ua.aleh1s.hotelepam.utils.Utils;

public class DeleteRoomCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        RoomService roomService = AppContext.getInstance().getRoomService();
        ResourcesManager resourcesManager = ResourcesManager.getInstance();

        Integer number = Utils.getIntValue(request, "number");

        try {
            roomService.deleteByNumber(number);
        } catch (ServiceException e) {
            e.setPath(resourcesManager.getValue("path.command.get.rooms"));
            throw e;
        }

        return redirect(resourcesManager.getValue("path.command.get.rooms"), response);
    }
}

package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.exception.ApplicationException;
import ua.aleh1s.hotelepam.exception.ServiceException;
import ua.aleh1s.hotelepam.mapper.dtomapper.requesttodto.HttpRequestRoomDtoMapper;
import ua.aleh1s.hotelepam.model.dto.RoomDto;
import ua.aleh1s.hotelepam.service.RoomService;
import ua.aleh1s.hotelepam.service.exception.ValidationException;

public class UpdateRoomCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        RoomService roomService = AppContext.getInstance().getRoomService();
        ResourcesManager resourcesManager = ResourcesManager.getInstance();

        HttpRequestRoomDtoMapper mapper = new HttpRequestRoomDtoMapper();
        RoomDto roomDto = mapper.map(request);
        request.setAttribute("roomDto", roomDto);

        String path = resourcesManager.getValue("path.page.room.editor");
        if (mapper.hasErrors())
            throw new ValidationException(mapper.getMessagesByRejectedValue(), path);

        try {
            roomService.update(roomDto);
        } catch (ServiceException e) {
            e.setPath(path);
            throw e;
        }

        return redirect(resourcesManager.getValue("path.command.get.rooms"), response);
    }
}

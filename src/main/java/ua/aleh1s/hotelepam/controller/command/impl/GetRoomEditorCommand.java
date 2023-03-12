package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.model.dto.RoomDto;
import ua.aleh1s.hotelepam.model.dtomapper.RoomDtoMapper;
import ua.aleh1s.hotelepam.model.entity.RoomEntity;
import ua.aleh1s.hotelepam.service.RoomService;
import ua.aleh1s.hotelepam.utils.Utils;

public class GetRoomEditorCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        RoomService roomService = AppContext.getInstance().getRoomService();
        RoomDtoMapper roomDtoMapper = AppContext.getInstance().getRoomDtoMapper();

        Integer number = Utils.getIntValue(request, "number");

        RoomEntity room = roomService.getByRoomNumber(number);
        RoomDto roomDto = roomDtoMapper.apply(room);

        request.setAttribute("roomDto", roomDto);
        return ResourcesManager.getInstance().getValue("path.page.room.editor");
    }
}
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
import ua.aleh1s.hotelepam.utils.Page;
import ua.aleh1s.hotelepam.utils.PageRequest;
import ua.aleh1s.hotelepam.utils.Utils;

import java.util.List;

public class GetRoomsCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        RoomService roomService = AppContext.getInstance().getRoomService();
        RoomDtoMapper roomDtoMapper = AppContext.getInstance().getRoomDtoMapper();

        Integer pageNumber = Utils.getIntValueOrDefault(request, "pageNumber", 1);

        Page<RoomEntity> roomPage = roomService.getRooms(PageRequest.of(pageNumber, 9));

        List<RoomDto> roomDtos = roomPage.result().stream()
                .map(roomDtoMapper)
                .toList();

        Page<RoomDto> roomDtoPage = Page.of(roomDtos, roomPage.count());

        request.setAttribute("currPage", pageNumber);
        request.setAttribute("pagesNumber", Utils.getNumberOfPages(roomDtoPage.count(), 9));
        request.setAttribute("roomDtoPage", roomDtoPage);

        return ResourcesManager.getInstance().getValue("path.page.room.list");
    }

}

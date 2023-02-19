package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.controller.command.CommandException;
import ua.aleh1s.hotelepam.controller.dto.RoomDto;
import ua.aleh1s.hotelepam.controller.dtomapper.RoomDtoMapper;
import ua.aleh1s.hotelepam.model.entity.RoomEntity;
import ua.aleh1s.hotelepam.model.repository.RoomRepository;
import ua.aleh1s.hotelepam.utils.Utils;

import java.util.Objects;
import java.util.Optional;

import static ua.aleh1s.hotelepam.utils.Utils.*;

public class ViewRoomCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        RoomRepository roomRepository = AppContext.getInstance().getRoomRepository();
        RoomDtoMapper roomDtoMapper = AppContext.getInstance().getRoomDtoMapper();

        Integer roomNumber = getIntValue(request, "roomNumber");

        HttpSession session = request.getSession();
        session.setAttribute("roomNumber", roomNumber);

        RoomEntity room = roomRepository.getByRoomNumber(roomNumber)
                .orElseThrow(CommandException::new);

        RoomDto roomDto = roomDtoMapper.apply(room);

        request.setAttribute("roomDto", roomDto);
        return ResourcesManager.getInstance().getValue("path.page.room");
    }
}

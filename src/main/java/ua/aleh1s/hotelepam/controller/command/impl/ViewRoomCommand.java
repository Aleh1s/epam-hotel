package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.model.dto.RoomDto;
import ua.aleh1s.hotelepam.model.dtomapper.RoomDtoMapper;
import ua.aleh1s.hotelepam.model.entity.RoomEntity;
import ua.aleh1s.hotelepam.service.RoomService;
import ua.aleh1s.hotelepam.utils.Period;

import java.time.LocalDate;
import java.util.Objects;

import static ua.aleh1s.hotelepam.utils.Utils.*;

public class ViewRoomCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        RoomService roomService = AppContext.getInstance().getRoomService();
        RoomDtoMapper roomDtoMapper = AppContext.getInstance().getRoomDtoMapper();

        Integer roomNumber = getIntValue(request, "roomNumber");

        HttpSession session = request.getSession();
        LocalDate checkIn = (LocalDate) session.getAttribute("requestedCheckIn");
        LocalDate checkOut = (LocalDate) session.getAttribute("requestedCheckOut");
        session.setAttribute("roomNumber", roomNumber);

        RoomEntity room = roomService.getByRoomNumber(roomNumber);

        if (Objects.nonNull(checkIn) && Objects.nonNull(checkOut))
            room.setPrice(roomService.getTotalPrice(room, Period.between(checkIn, checkOut)));

        RoomDto roomDto = roomDtoMapper.apply(room);

        request.setAttribute("roomDto", roomDto);
        return ResourcesManager.getInstance().getValue("path.page.room");
    }
}

package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.ApplicationException;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.controller.dto.RoomDto;
import ua.aleh1s.hotelepam.controller.dtomapper.RoomDtoMapper;
import ua.aleh1s.hotelepam.model.entity.RoomEntity;
import ua.aleh1s.hotelepam.service.RoomService;
import ua.aleh1s.hotelepam.utils.Period;

import java.time.LocalDate;
import java.util.List;

import static ua.aleh1s.hotelepam.utils.Utils.*;

public class ChooseRoomsCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ResourcesManager resourcesManager = ResourcesManager.getInstance();
        RoomDtoMapper roomDtoMapper = AppContext.getInstance().getRoomDtoMapper();
        RoomService roomService = AppContext.getInstance().getRoomService();

        HttpSession session = request.getSession();

        Integer guests = getIntValue(request, "guests");
        LocalDate checkIn = getLocalDateValue(request, "checkIn");
        LocalDate checkOut = getLocalDateValue(request, "checkOut");

        String path = resourcesManager.getValue("path.page.home");

        Period requestedPeriod = Period.range(checkIn, checkOut);
        if (!isReservationPeriodValid(requestedPeriod))
            throw new ApplicationException("Invalid range of date.", path);

        List<RoomEntity> availableRooms =
                roomService.getAvailableRooms(guests, requestedPeriod);

        if (availableRooms.isEmpty())
            throw new ApplicationException("There are no available rooms. Try to pick another date.", path);

        availableRooms.forEach(room ->
                room.setPrice(roomService.getTotalPrice(room, requestedPeriod)));

        List<RoomDto> roomDtoList = availableRooms.stream()
                .map(roomDtoMapper)
                .toList();

        session.setAttribute("requestedCheckIn", checkIn);
        session.setAttribute("requestedCheckOut", checkOut);

        request.setAttribute("availableRooms", roomDtoList);
        return resourcesManager.getValue("path.page.available.rooms");
    }
}

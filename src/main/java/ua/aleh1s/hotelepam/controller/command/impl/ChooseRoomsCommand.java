package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.controller.command.CommandException;
import ua.aleh1s.hotelepam.controller.dto.RoomCardDto;
import ua.aleh1s.hotelepam.controller.dtomapper.RoomCardDtoMapper;
import ua.aleh1s.hotelepam.model.entity.RoomEntity;
import ua.aleh1s.hotelepam.service.RoomService;
import ua.aleh1s.hotelepam.utils.Period;

import java.time.LocalDate;
import java.util.List;

import static ua.aleh1s.hotelepam.utils.Utils.*;

public class ChooseRoomsCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        RoomCardDtoMapper roomCardDtoMapper = AppContext.getInstance().getRoomCardDtoMapper();
        RoomService roomService = AppContext.getInstance().getRoomService();
        ResourcesManager resourcesManager = ResourcesManager.getInstance();

        Integer guests = getIntValue(request, "guests");
        LocalDate checkIn = getLocalDateValue(request, "checkIn");
        LocalDate checkOut = getLocalDateValue(request, "checkOut");

        Period requestedPeriod = Period.range(checkIn, checkOut);
        if (!isReservationPeriodValid(requestedPeriod))
            throw new CommandException("Invalid range of date", resourcesManager.getValue("path.page.home"));

        List<RoomEntity> availableRooms =
                roomService.getAvailableRooms(guests, requestedPeriod);

        List<RoomCardDto> roomCardList = availableRooms.stream()
                .map(roomCardDtoMapper)
                .toList();

        HttpSession session = request.getSession();
        session.setAttribute("requestedCheckIn", checkIn);
        session.setAttribute("requestedCheckOut", checkOut);

        request.setAttribute("rooms", roomCardList);
        return resourcesManager.getValue("path.page.available.rooms");
    }
}

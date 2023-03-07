package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.ApplicationException;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.model.dto.ReservationDto;
import ua.aleh1s.hotelepam.model.dto.RoomDto;
import ua.aleh1s.hotelepam.model.dtomapper.ReservationDtoMapper;
import ua.aleh1s.hotelepam.model.dtomapper.RoomDtoMapper;
import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.model.entity.RoomEntity;
import ua.aleh1s.hotelepam.service.ReservationService;
import ua.aleh1s.hotelepam.service.RoomService;

import java.time.LocalDateTime;

import static ua.aleh1s.hotelepam.utils.Utils.getLongValue;

public class PaymentPageCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ResourcesManager resourcesManager = ResourcesManager.getInstance();
        ReservationService reservationService = AppContext.getInstance().getReservationService();
        RoomService roomService = AppContext.getInstance().getRoomService();
        ReservationDtoMapper reservationDtoMapper = AppContext.getInstance().getReservationDtoMapper();
        RoomDtoMapper roomDtoMapper = AppContext.getInstance().getRoomDtoMapper();

        Long reservationId = getLongValue(request, "reservationId");

        HttpSession session = request.getSession(false);
        session.setAttribute("reservationId", reservationId);

        ReservationEntity reservation = reservationService.getById(reservationId);

        String path = resourcesManager.getValue("path.command.profile");
        if (reservation.getExpiredAt().isBefore(LocalDateTime.now()))
            throw new ApplicationException("Reservation was canceled", path);

        ReservationDto reservationDto = reservationDtoMapper.apply(reservation);

        RoomEntity room = roomService.getByRoomNumber(reservation.getRoomNumber());
        RoomDto roomDto = roomDtoMapper.apply(room);

        request.setAttribute("reservationDto", reservationDto);
        request.setAttribute("roomDto", roomDto);

        return resourcesManager.getValue("path.page.confirm.payment");
    }
}

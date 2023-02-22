package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.ApplicationException;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.controller.dto.ReservationDto;
import ua.aleh1s.hotelepam.controller.dto.RoomDto;
import ua.aleh1s.hotelepam.controller.dtomapper.ReservationDtoMapper;
import ua.aleh1s.hotelepam.controller.dtomapper.RoomDtoMapper;
import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.model.entity.ReservationStatus;
import ua.aleh1s.hotelepam.model.entity.RoomEntity;
import ua.aleh1s.hotelepam.model.repository.ReservationRepository;
import ua.aleh1s.hotelepam.model.repository.RoomRepository;
import ua.aleh1s.hotelepam.service.ReservationService;
import ua.aleh1s.hotelepam.service.RoomService;

import java.util.Optional;

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

        String path = resourcesManager.getValue("path.command.my.bookings");
        if (!reservation.getStatus().equals(ReservationStatus.PENDING_PAYMENT))
            throw new ApplicationException("You cannot pay this reservation, because it's not confirmed", path);

        ReservationDto reservationDto = reservationDtoMapper.apply(reservation);

        RoomEntity room = roomService.getByRoomNumber(reservation.getRoomNumber());
        RoomDto roomDto = roomDtoMapper.apply(room);

        request.setAttribute("reservationDto", reservationDto);
        request.setAttribute("roomDto", roomDto);

        return resourcesManager.getValue("path.page.confirm.payment");
    }
}

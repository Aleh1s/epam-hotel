package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.aleh1s.hotelepam.AppContext;
import ua.aleh1s.hotelepam.ResourcesManager;
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

import java.util.Optional;

import static ua.aleh1s.hotelepam.Utils.getLongValue;

public class PaymentPageCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Long reservationId = getLongValue(request, "reservationId");

        HttpSession session = request.getSession(false);
        session.setAttribute("reservationId", reservationId);

        ReservationRepository reservationRepository = AppContext.getInstance().getReservationRepository();
        Optional<ReservationEntity> reservationOptional = reservationRepository.getById(reservationId);

        String errorMessage, path = "/controller?command=myBookings";
        if (reservationOptional.isEmpty()) {
            errorMessage = "There is no reservation with such id";
            request.setAttribute("errorMessage", errorMessage);
            return path;
        }

        ReservationEntity reservation = reservationOptional.get();
        if (!reservation.getStatus().equals(ReservationStatus.CONFIRMED)) {
            errorMessage = "You cannot pay this reservation, because it's not confirmed";
            request.setAttribute("errorMessage", errorMessage);
            return path;
        }

        RoomRepository roomRepository = AppContext.getInstance().getRoomRepository();
        Optional<RoomEntity> roomOptional = roomRepository.getByRoomNumber(reservation.getRoomNumber());

        if (roomOptional.isEmpty()) {
            errorMessage = "There is no room with such number";
            request.setAttribute("errorMessage", errorMessage);
            return path;
        }
        RoomEntity room = roomOptional.get();

        ReservationDtoMapper reservationDtoMapper = AppContext.getInstance().getReservationDtoMapper();
        ReservationDto reservationDto = reservationDtoMapper.apply(reservation);

        RoomDtoMapper roomDtoMapper = AppContext.getInstance().getRoomDtoMapper();
        RoomDto roomDto = roomDtoMapper.apply(room);

        request.setAttribute("reservationDto", reservationDto);
        request.setAttribute("roomDto", roomDto);

        return ResourcesManager.getInstance().getValue("path.page.confirm.payment");
    }
}

package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.exception.ApplicationException;
import ua.aleh1s.hotelepam.exception.ServiceException;
import ua.aleh1s.hotelepam.model.entity.RequestEntity;
import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.model.entity.RoomEntity;
import ua.aleh1s.hotelepam.service.BookingService;
import ua.aleh1s.hotelepam.service.RequestService;
import ua.aleh1s.hotelepam.service.RoomService;
import ua.aleh1s.hotelepam.utils.Period;

import java.io.IOException;
import java.util.Objects;

import static ua.aleh1s.hotelepam.model.entity.RequestStatus.CONFIRMED;
import static ua.aleh1s.hotelepam.utils.Utils.getLongValue;
import static ua.aleh1s.hotelepam.utils.Utils.toDate;

public class ConfirmRequestCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        ResourcesManager resourcesManager = ResourcesManager.getInstance();

        RequestService requestService = AppContext.getInstance().getRequestService();
        BookingService bookingService = AppContext.getInstance().getBookingService();
        RoomService roomService = AppContext.getInstance().getRoomService();

        Long requestId = getLongValue(request, "requestId");

        HttpSession session = request.getSession(false);
        Long userId = (Long) session.getAttribute("id");

        RequestEntity requestEntity = requestService.getById(requestId);
        RoomEntity room = roomService.getByRoomNumber(requestEntity.getRoomNumber());

        if (room.getIsUnavailable())
            throw new ApplicationException("Room is unavailable now. Try to pick another room.",
                    ResourcesManager.getInstance().getValue("path.command.profile"));

        String path = resourcesManager.getValue("path.command.profile");
        if (!Objects.equals(requestEntity.getCustomerId(), userId))
            throw new ApplicationException("You cannot change status", path);

        try {
            requestService.changeStatus(requestEntity, CONFIRMED);
        } catch (ServiceException e) {
            e.setPath(resourcesManager.getValue("path.command.profile"));
            throw e;
        }

        ReservationEntity reservation = bookingService.bookRoom(
                requestEntity.getRoomNumber(),
                requestEntity.getCustomerId(),
                Period.between(
                        requestEntity.getCheckIn(),
                        requestEntity.getCheckOut()
                )
        );

        session.setAttribute("reservationTotalAmount", reservation.getTotalAmount());
        session.setAttribute("reservationCheckIn", toDate(requestEntity.getCheckIn()));
        session.setAttribute("reservationCheckOut", toDate(requestEntity.getCheckOut()));

        return redirect(resourcesManager.getValue("path.page.success.booking"), response);
    }
}

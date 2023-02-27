package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.controller.dto.ReservationDto;
import ua.aleh1s.hotelepam.controller.dtomapper.ReservationDtoMapper;
import ua.aleh1s.hotelepam.utils.Page;
import ua.aleh1s.hotelepam.utils.PageRequest;
import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.model.entity.ReservationStatus;
import ua.aleh1s.hotelepam.service.ReservationService;

import java.util.List;

import static java.util.Objects.*;
import static ua.aleh1s.hotelepam.utils.Utils.getIntValueOrDefault;
import static ua.aleh1s.hotelepam.utils.Utils.getNumberOfPages;

public class MyBookingsCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ReservationService reservationService = AppContext.getInstance().getReservationService();
        ReservationDtoMapper reservationDtoMapper = AppContext.getInstance().getReservationDtoMapper();

        Integer pageNumber = getIntValueOrDefault(request, "pageNumber", 1);
        Integer pageSize = getIntValueOrDefault(request, "pageSize", 10);
        Integer statusIndex = getIntValueOrDefault(request, "status", 0);

        HttpSession session = request.getSession(false);
        Long userId = (Long) session.getAttribute("id");

        if (nonNull(request.getParameter("default")))
            session.setAttribute("reservationStatus", null);

        ReservationStatus status;
        if (statusIndex == 0)
            status = (ReservationStatus) session.getAttribute("reservationStatus");
        else
            status = ReservationStatus.atIndex(statusIndex);

        session.setAttribute("reservationStatus", status);

        Page<ReservationEntity> reservationEntityPage;

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        if (nonNull(status))
            reservationEntityPage = reservationService.getAllByUserIdAndStatusOrderByCreatedAtDesc(userId, status, pageRequest);
        else
            reservationEntityPage = reservationService.getAllByUserIdOrderByCreatedAtDesc(userId, pageRequest);

        List<ReservationDto> reservationDtoList = reservationEntityPage.getResult().stream()
                .map(reservationDtoMapper)
                .toList();

        Page<ReservationDto> reservationDtoPage = Page.of(reservationDtoList, reservationEntityPage.getCount());
        Integer pagesNumber = getNumberOfPages(reservationDtoPage.getCount(), pageSize);

        request.setAttribute("currPage", pageNumber);
        request.setAttribute("pagesNumber", pagesNumber);
        request.setAttribute("reservationPage", reservationDtoPage);

        return ResourcesManager.getInstance().getValue("path.page.my.bookings");
    }
}

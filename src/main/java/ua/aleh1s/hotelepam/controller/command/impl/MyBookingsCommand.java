package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.controller.dto.ReservationDto;
import ua.aleh1s.hotelepam.controller.dtomapper.ReservationDtoMapper;
import ua.aleh1s.hotelepam.model.pagination.Page;
import ua.aleh1s.hotelepam.model.pagination.PageRequest;
import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.model.entity.ReservationStatus;
import ua.aleh1s.hotelepam.model.repository.ReservationRepository;
import ua.aleh1s.hotelepam.service.ReservationService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.*;
import static ua.aleh1s.hotelepam.utils.Utils.getIntValueOrDefault;
import static ua.aleh1s.hotelepam.utils.Utils.getNumberOfPages;
import static ua.aleh1s.hotelepam.model.entity.ReservationStatus.REMOVED;

public class MyBookingsCommand implements Command {

    @Override
    // todo: get page of reservations from database
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

        List<ReservationEntity> reservationList = reservationService.getAllByCustomerId(userId);

        if (nonNull(status))
            reservationList = reservationList.stream()
                    .filter(reservation -> reservation.getStatus().equals(status))
                    .collect(Collectors.toList());

        reservationList = reservationList.stream()
                .filter(reservation -> !reservation.getStatus().equals(REMOVED))
                .collect(Collectors.toList());
        reservationList.sort(Comparator.comparing(ReservationEntity::getCreatedAt).reversed());

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        List<ReservationDto> reservationDtoList = reservationList.stream()
                .skip(pageRequest.getOffset())
                .limit(pageRequest.getLimit())
                .map(reservationDtoMapper)
                .toList();

        Page<ReservationDto> reservationDtoPage = Page.of(reservationDtoList, reservationList.size());
        Integer pagesNumber = getNumberOfPages(reservationDtoPage.getCount(), pageSize);

        request.setAttribute("currPage", pageNumber);
        request.setAttribute("pagesNumber", pagesNumber);
        request.setAttribute("reservationPage", reservationDtoPage);

        return ResourcesManager.getInstance().getValue("path.page.my.bookings");
    }
}

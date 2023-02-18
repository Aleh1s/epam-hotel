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

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.*;
import static ua.aleh1s.hotelepam.appcontext.Utils.getIntValueOrDefault;
import static ua.aleh1s.hotelepam.appcontext.Utils.getNumberOfPages;
import static ua.aleh1s.hotelepam.model.entity.ReservationStatus.REMOVED;

public class MyBookingsCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        final int PAGE_SIZE = 10;

        HttpSession session = request.getSession(false);
        Long userId = (Long) session.getAttribute("id");

        if (nonNull(request.getParameter("default")))
            session.setAttribute("reservationStatus", null);

        Integer pageNumber = getIntValueOrDefault(request, "pageNumber", 1);

        ReservationRepository reservationRepository = AppContext.getInstance().getReservationRepository();
        List<ReservationEntity> reservationList = reservationRepository.getAllByCustomerId(userId);

        Integer statusIndex = getIntValueOrDefault(request, "status", 0);

        ReservationStatus status;
        if (statusIndex == 0) {
            status = (ReservationStatus) session.getAttribute("reservationStatus");
        } else {
            status = ReservationStatus.atIndex(statusIndex);
        }

        if (nonNull(status))
            reservationList = reservationList.stream()
                    .filter(reservation -> reservation.getStatus().equals(status))
                    .collect(Collectors.toList());

        session.setAttribute("reservationStatus", status);

        reservationList = reservationList.stream()
                .filter(reservation -> !reservation.getStatus().equals(REMOVED))
                .collect(Collectors.toList());
        reservationList.sort(Comparator.comparing(ReservationEntity::getCreatedAt).reversed());


        PageRequest pageRequest = PageRequest.of(pageNumber, PAGE_SIZE);
        ReservationDtoMapper reservationDtoMapper = AppContext.getInstance().getReservationDtoMapper();

        List<ReservationDto> reservationDtoList = reservationList.stream()
                .skip(pageRequest.getOffset())
                .limit(pageRequest.getLimit())
                .map(reservationDtoMapper)
                .toList();

        Page<ReservationDto> reservationDtoPage = Page.of(reservationDtoList, reservationList.size());
        Integer pagesNumber = getNumberOfPages(reservationDtoPage.getCount(), PAGE_SIZE);

        request.setAttribute("currPage", pageNumber);
        request.setAttribute("pagesNumber", pagesNumber);
        request.setAttribute("reservationPage", reservationDtoPage);

        return ResourcesManager.getInstance().getValue("path.page.my.bookings");
    }
}

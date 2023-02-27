package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.service.ReservationService;
import ua.aleh1s.hotelepam.utils.Utils;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.controller.dto.ReservationDto;
import ua.aleh1s.hotelepam.controller.dtomapper.ReservationDtoMapper;
import ua.aleh1s.hotelepam.utils.Page;
import ua.aleh1s.hotelepam.utils.PageRequest;
import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.model.entity.ReservationStatus;

import java.util.List;

import static java.util.Objects.*;
import static ua.aleh1s.hotelepam.utils.Utils.*;

public class ReservationListCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ReservationService reservationService = AppContext.getInstance().getReservationService();
        ReservationDtoMapper reservationDtoMapper = AppContext.getInstance().getReservationDtoMapper();

        Integer pageSize = getIntValueOrDefault(request, "pageSize", 10);
        Integer pageNumber = getIntValueOrDefault(request, "pageNumber", 1);

        HttpSession session = request.getSession(false);
        if (nonNull(request.getParameter("default")))
            session.setAttribute("reservationStatus", null);

        Integer statusIndex = getIntValueOrDefault(request, "status", 0);

        ReservationStatus status;
        if (statusIndex == 0)
            status = (ReservationStatus) session.getAttribute("reservationStatus");
        else
            status = ReservationStatus.atIndex(statusIndex);

        session.setAttribute("reservationStatus", status);

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        Page<ReservationEntity> reservationPage;
        if (nonNull(status))
            reservationPage = reservationService.getAllByStatus(status, pageRequest);
        else
            reservationPage = reservationService.getAll(pageRequest);

        List<ReservationDto> reservationDtoList = reservationPage.getResult().stream()
                .map(reservationDtoMapper)
                .toList();

        Page<ReservationDto> reservationDtoPage = Page.of(reservationDtoList, reservationPage.getCount());
        Integer pagesNumber = Utils.getNumberOfPages(reservationDtoPage.getCount(), pageSize);

        request.setAttribute("reservationPage", reservationDtoPage);
        request.setAttribute("pagesNumber", pagesNumber);
        request.setAttribute("currPage", pageNumber);

        return ResourcesManager.getInstance().getValue("path.page.reservation.list");
    }

}

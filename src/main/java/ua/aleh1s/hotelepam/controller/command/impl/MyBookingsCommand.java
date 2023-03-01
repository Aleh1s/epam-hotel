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

        HttpSession session = request.getSession(false);
        Long userId = (Long) session.getAttribute("id");
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        Page<ReservationEntity> reservationByUserId = reservationService.getAllReservationsByUserId(userId, pageRequest);

        List<ReservationDto> reservationDtoList = reservationByUserId.getResult().stream()
                .map(reservationDtoMapper)
                .toList();

        Page<ReservationDto> reservationDtoPage = Page.of(reservationDtoList, reservationByUserId.getCount());
        Integer pagesNumber = getNumberOfPages(reservationDtoPage.getCount(), pageSize);

        request.setAttribute("currPage", pageNumber);
        request.setAttribute("pagesNumber", pagesNumber);
        request.setAttribute("reservationPage", reservationDtoPage);

        return ResourcesManager.getInstance().getValue("path.page.my.bookings");
    }
}

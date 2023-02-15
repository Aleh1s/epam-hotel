package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.aleh1s.hotelepam.AppContext;
import ua.aleh1s.hotelepam.ResourcesManager;
import ua.aleh1s.hotelepam.Utils;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.controller.dto.ReservationDto;
import ua.aleh1s.hotelepam.controller.mapper.ReservationDtoMapper;
import ua.aleh1s.hotelepam.controller.page.Page;
import ua.aleh1s.hotelepam.controller.page.PageRequest;
import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.model.repository.ReservationRepository;

import java.util.List;

import static ua.aleh1s.hotelepam.Utils.getNumberOfPages;

public class MyBookingsCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        final int PAGE_SIZE = 10;

        HttpSession session = request.getSession(false);
        Long userId = (Long) session.getAttribute("id");

        Integer pageNumber = Utils.getIntValueOrDefault(request, "pageNumber", 1);

        ReservationRepository reservationRepository = AppContext.getInstance().getReservationRepository();
        Page<ReservationEntity> reservationPage = reservationRepository.getAllByCustomerId(userId, PageRequest.of(pageNumber, PAGE_SIZE));

        ReservationDtoMapper reservationDtoMapper = AppContext.getInstance().getReservationDtoMapper();
        List<ReservationDto> reservationDtoList = reservationPage.getResult().stream()
                .map(reservationDtoMapper)
                .toList();

        Page<ReservationDto> reservationDtoPage = Page.of(reservationDtoList, reservationPage.getCount());

        Integer pagesNumber = getNumberOfPages(reservationDtoPage.getCount(), PAGE_SIZE);

        request.setAttribute("currPage", pageNumber);
        request.setAttribute("pagesNumber", pagesNumber);
        request.setAttribute("reservationPage", reservationDtoPage);

        return ResourcesManager.getInstance().getValue("path.page.my.bookings");
    }
}

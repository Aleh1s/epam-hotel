package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.service.ReservationService;
import ua.aleh1s.hotelepam.utils.Utils;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.model.dto.ReservationDto;
import ua.aleh1s.hotelepam.model.dtomapper.ReservationDtoMapper;
import ua.aleh1s.hotelepam.utils.Page;
import ua.aleh1s.hotelepam.utils.PageRequest;
import ua.aleh1s.hotelepam.model.entity.ReservationEntity;

import java.util.List;

import static ua.aleh1s.hotelepam.utils.Utils.*;

public class ReservationListCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ReservationService reservationService = AppContext.getInstance().getReservationService();
        ReservationDtoMapper reservationDtoMapper = AppContext.getInstance().getReservationDtoMapper();

        Integer pageSize = getIntValueOrDefault(request, "pageSize", 10);
        Integer pageNumber = getIntValueOrDefault(request, "pageNumber", 1);

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        Page<ReservationEntity> reservationPage =
                reservationService.getAllActualPayedReservations(pageRequest);

        List<ReservationDto> reservationDtoList = reservationPage.result().stream()
                .map(reservationDtoMapper)
                .toList();

        Page<ReservationDto> reservationDtoPage = Page.of(reservationDtoList, reservationPage.count());
        Integer pagesNumber = Utils.getNumberOfPages(reservationDtoPage.count(), pageSize);

        request.setAttribute("reservationPage", reservationDtoPage);
        request.setAttribute("pagesNumber", pagesNumber);
        request.setAttribute("currPage", pageNumber);

        return ResourcesManager.getInstance().getValue("path.page.reservation.list");
    }

}

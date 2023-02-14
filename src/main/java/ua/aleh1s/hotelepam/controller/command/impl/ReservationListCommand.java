package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.aleh1s.hotelepam.AppContext;
import ua.aleh1s.hotelepam.ResourcesManager;
import ua.aleh1s.hotelepam.Utils;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.controller.dto.ReservationDto;
import ua.aleh1s.hotelepam.controller.mapper.ReservationDtoMapper;
import ua.aleh1s.hotelepam.model.criteria.impl.ReservationCriteria;
import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.model.pagination.impl.ReservationListPagination;
import ua.aleh1s.hotelepam.model.repository.ReservationRepository;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import static ua.aleh1s.hotelepam.Utils.getIntContextParamValue;

public class ReservationListCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Integer pageNumber = Utils.getIntValueOrDefault(request, "pageNumber", 1);
        System.out.println(pageNumber);

        ReservationCriteria criteria = ReservationCriteria.valueOf(request);
        ReservationListPagination pagination = ReservationListPagination.valueOf(request);
        ReservationRepository reservationRepository = AppContext.getInstance().getReservationRepository();
        List<ReservationEntity> reservationEntitieList = reservationRepository.getAll(criteria, pagination);
        Integer count = reservationRepository.count(criteria);
        System.out.println(count);

        ReservationDtoMapper reservationDtoMapper = AppContext.getInstance().getReservationDtoMapper();
        List<ReservationDto> reservationDtoList = reservationEntitieList.stream()
                .map(reservationDtoMapper)
                .toList();

        Integer reservationsPerPage = getIntContextParamValue(request, "reservationsPerPage");
        Integer pagesNumber = (int) Math.ceil(count / (double) reservationsPerPage);
        System.out.println(pagesNumber);
        Map<String, Boolean> params = criteria.getParams();

        request.setAttribute("pagesNumber", pagesNumber);
        request.setAttribute("currPage", pageNumber);
        request.setAttribute("reservationList", reservationDtoList);
        request.setAttribute("params", params);
        request.setAttribute("paramString", getParamString(params));

        return ResourcesManager.getInstance().getValue("path.page.reservation.list");
    }

    private String getParamString(Map<String, Boolean> params) {
        StringJoiner joiner = new StringJoiner("&");
        params.forEach((key, value) -> {
            if (value) {
                joiner.add(key + "=on");
            }
        });
        return joiner.toString();
    }
}

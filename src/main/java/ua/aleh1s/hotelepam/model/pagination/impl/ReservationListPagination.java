package ua.aleh1s.hotelepam.model.pagination.impl;

import jakarta.servlet.http.HttpServletRequest;
import ua.aleh1s.hotelepam.Utils;
import ua.aleh1s.hotelepam.model.pagination.Pagination;

import static ua.aleh1s.hotelepam.Utils.getIntContextParamValue;
import static ua.aleh1s.hotelepam.Utils.getIntValueOrDefault;

public class ReservationListPagination implements Pagination {

    private final HttpServletRequest request;

    private ReservationListPagination(HttpServletRequest request) {
        this.request = request;
    }

    public static ReservationListPagination valueOf(HttpServletRequest request) {
        return new ReservationListPagination(request);
    }

    @Override
    public String build() {
        Integer pageNumber = getIntValueOrDefault(request, "pageNumber", 1);
        Integer reservationsPerPage = getIntContextParamValue(request, "reservationsPerPage");
        return "offset " + (pageNumber - 1) * reservationsPerPage +
                " limit " + reservationsPerPage;
    }
}

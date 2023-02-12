package ua.aleh1s.hotelepam.model.pagination.impl;

import jakarta.servlet.http.HttpServletRequest;
import ua.aleh1s.hotelepam.Utils;
import ua.aleh1s.hotelepam.model.constant.PaginationParam;
import ua.aleh1s.hotelepam.model.pagination.Pagination;

import static ua.aleh1s.hotelepam.Utils.*;

public class RoomListPagination implements Pagination {

    private final HttpServletRequest request;

    private RoomListPagination(HttpServletRequest request) {
        this.request = request;
    }

    public static RoomListPagination valueOf(HttpServletRequest request) {
        return new RoomListPagination(request);
    }

    @Override
    public String build() {
        Integer roomCardsPerPage = Utils.getIntContextParamValue(request, "roomCardsPerPage");
        Integer pageNumber = getIntValueOrDefault(request, PaginationParam.PAGE_NUMBER.getValue(), 1);
        return "offset " + (pageNumber - 1) * roomCardsPerPage +
                " limit " + roomCardsPerPage;
    }
}

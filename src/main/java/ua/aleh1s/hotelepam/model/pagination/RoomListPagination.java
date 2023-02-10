package ua.aleh1s.hotelepam.model.pagination;

import jakarta.servlet.http.HttpServletRequest;
import ua.aleh1s.hotelepam.controller.constant.Constant;
import ua.aleh1s.hotelepam.model.constant.PaginationParam;

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
        Integer pageNumber = getIntValueOrDefault(request, PaginationParam.PAGE_NUMBER.getValue(), 1);
        return "offset " + (pageNumber - 1) * Constant.ELEMENTS_PER_PAGE +
                " limit " + Constant.ELEMENTS_PER_PAGE;
    }
}

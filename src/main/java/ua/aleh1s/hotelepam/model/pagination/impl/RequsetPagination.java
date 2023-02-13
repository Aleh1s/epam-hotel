package ua.aleh1s.hotelepam.model.pagination.impl;

import jakarta.servlet.http.HttpServletRequest;
import ua.aleh1s.hotelepam.model.pagination.Pagination;

import static ua.aleh1s.hotelepam.Utils.getIntContextParamValue;
import static ua.aleh1s.hotelepam.Utils.getIntValueOrDefault;

public class RequsetPagination implements Pagination {

    private final HttpServletRequest request;
    public RequsetPagination(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String build() {
        Integer pageNumber = getIntValueOrDefault(request, "pageNumber", 1);
        Integer requestsPerPage = getIntContextParamValue(request, "requestsPerPage");
        return "offset " + (pageNumber - 1) * requestsPerPage + " limit " + requestsPerPage;
    }
}

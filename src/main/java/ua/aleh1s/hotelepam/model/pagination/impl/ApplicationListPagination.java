package ua.aleh1s.hotelepam.model.pagination.impl;

import jakarta.servlet.http.HttpServletRequest;
import ua.aleh1s.hotelepam.Utils;
import ua.aleh1s.hotelepam.model.pagination.Pagination;

public class ApplicationListPagination implements Pagination {

    private HttpServletRequest request;

    public ApplicationListPagination(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String build() {
        Integer applicationsPerPage = Utils.getIntContextParamValue(request, "applicationsPerPage");
        Integer pageNumber = Utils.getIntValueOrDefault(request, "pageNumber", 1);
        return "offset " + (pageNumber - 1) * applicationsPerPage +
                " limit " + applicationsPerPage;
    }
}

package ua.aleh1s.hotelepam.model.criteria.impl;

import jakarta.servlet.http.HttpServletRequest;
import ua.aleh1s.hotelepam.model.criteria.Criteria;

import static ua.aleh1s.hotelepam.Utils.*;

public class ApplicationListCriteria implements Criteria {

    private final HttpServletRequest request;

    public ApplicationListCriteria(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String build() {
        return "where status = " + getIntValueOrDefault(request, "status", 1);
    }
}

package ua.aleh1s.hotelepam.model.criteria.impl;

import ua.aleh1s.hotelepam.model.criteria.Criteria;
import ua.aleh1s.hotelepam.model.entity.UserEntity;

public class RequestCriteria implements Criteria {

    private final UserEntity user;
    public RequestCriteria(UserEntity user) {
        this.user = user;
    }

    @Override
    public String build() {
        return "where customer_id = " + user.getId() + " and status = 1";
    }
}

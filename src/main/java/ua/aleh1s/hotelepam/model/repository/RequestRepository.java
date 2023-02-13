package ua.aleh1s.hotelepam.model.repository;

import ua.aleh1s.hotelepam.model.criteria.Criteria;
import ua.aleh1s.hotelepam.model.entity.RequestEntity;
import ua.aleh1s.hotelepam.model.pagination.Pagination;

import java.util.List;

public interface RequestRepository {

    void create(RequestEntity request);

    List<RequestEntity> getAll(Criteria criteria, Pagination pagination);

    Integer count(Criteria criteria);
}

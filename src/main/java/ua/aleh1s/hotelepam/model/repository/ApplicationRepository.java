package ua.aleh1s.hotelepam.model.repository;

import ua.aleh1s.hotelepam.model.criteria.Criteria;
import ua.aleh1s.hotelepam.model.criteria.impl.ApplicationListCriteria;
import ua.aleh1s.hotelepam.model.entity.ApplicationEntity;
import ua.aleh1s.hotelepam.model.pagination.Pagination;
import ua.aleh1s.hotelepam.model.pagination.impl.ApplicationListPagination;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository {

    void create(ApplicationEntity applicationEntity);

    List<ApplicationEntity> getAll(Criteria criteria, Pagination pagination);

    Integer count(Criteria criteria);

    Optional<ApplicationEntity> getById(Long id);

    void update(ApplicationEntity application);
}

package ua.aleh1s.hotelepam.model.repository;

import ua.aleh1s.hotelepam.controller.page.Page;
import ua.aleh1s.hotelepam.controller.page.PageRequest;
import ua.aleh1s.hotelepam.model.criteria.Criteria;
import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.model.pagination.Pagination;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository {

    void create(ReservationEntity entity);


    List<ReservationEntity> getAll(Criteria criteria, Pagination pagination);

    Integer count(Criteria criteria);

    Optional<ReservationEntity> getById(Long reservationId);

    void update(ReservationEntity reservation);

    Page<ReservationEntity> getAllByCustomerId(Long userId, PageRequest pageRequest);
}

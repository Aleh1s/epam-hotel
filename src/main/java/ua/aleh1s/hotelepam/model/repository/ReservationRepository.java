package ua.aleh1s.hotelepam.model.repository;

import ua.aleh1s.hotelepam.model.criteria.Criteria;
import ua.aleh1s.hotelepam.model.criteria.impl.ReservationCriteria;
import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.model.entity.ReservationStatus;
import ua.aleh1s.hotelepam.model.pagination.Pagination;

import java.util.List;

public interface ReservationRepository {

    void create(ReservationEntity entity);

    List<ReservationEntity> getAllByRoomNumberAndStatus(Integer roomNumber, ReservationStatus status);

    List<ReservationEntity> getAll(Criteria criteria, Pagination pagination);

    Integer count(Criteria criteria);
}

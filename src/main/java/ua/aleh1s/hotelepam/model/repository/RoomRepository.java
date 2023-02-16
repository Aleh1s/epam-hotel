package ua.aleh1s.hotelepam.model.repository;

import ua.aleh1s.hotelepam.controller.page.Page;
import ua.aleh1s.hotelepam.controller.page.PageRequest;
import ua.aleh1s.hotelepam.model.criteria.Criteria;
import ua.aleh1s.hotelepam.model.criteria.impl.RoomListCriteria;
import ua.aleh1s.hotelepam.model.entity.RoomEntity;
import ua.aleh1s.hotelepam.model.pagination.Pagination;

import java.util.List;
import java.util.Optional;

public interface RoomRepository {

    Optional<RoomEntity> getByRoomNumber(Integer roomNumber);
    List<RoomEntity> getAll(Criteria criteria, Pagination pagination);
    Page<RoomEntity> getAll(PageRequest pageRequest);
    Integer count(RoomListCriteria criteria);
    void update(RoomEntity entity);

    List<RoomEntity> getAll();
}

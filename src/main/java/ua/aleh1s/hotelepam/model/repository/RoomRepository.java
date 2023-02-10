package ua.aleh1s.hotelepam.model.repository;

import ua.aleh1s.hotelepam.model.criteria.Criteria;
import ua.aleh1s.hotelepam.model.criteria.RoomListCriteria;
import ua.aleh1s.hotelepam.model.entity.RoomEntity;
import ua.aleh1s.hotelepam.model.pagination.Pagination;

import java.util.List;

public interface RoomRepository {

    List<RoomEntity> getAll(Criteria criteria, Pagination pagination);
    Integer count(RoomListCriteria criteria);

}

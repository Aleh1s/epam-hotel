package ua.aleh1s.hotelepam.model.repository;

import ua.aleh1s.hotelepam.model.criteria.RoomListCriteria;
import ua.aleh1s.hotelepam.model.entity.RoomEntity;

import java.util.List;

public interface RoomRepository {

    List<RoomEntity> getAll(RoomListCriteria criteria);
    Integer count(RoomListCriteria criteria);

}

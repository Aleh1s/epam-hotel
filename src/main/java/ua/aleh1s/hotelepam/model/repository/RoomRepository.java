package ua.aleh1s.hotelepam.model.repository;

import ua.aleh1s.hotelepam.model.entity.RoomEntity;
import ua.aleh1s.hotelepam.utils.Page;
import ua.aleh1s.hotelepam.utils.PageRequest;

import java.util.List;
import java.util.Optional;

public interface RoomRepository {

    Optional<RoomEntity> getByRoomNumber(Integer roomNumber);
    void update(RoomEntity entity);
    List<RoomEntity> getAll();
    Page<RoomEntity> getAll(PageRequest pageRequest);
}

package ua.aleh1s.hotelepam.repository;

import ua.aleh1s.hotelepam.model.entity.RoomEntity;
import ua.aleh1s.hotelepam.utils.Page;
import ua.aleh1s.hotelepam.utils.PageRequest;

import java.sql.Blob;
import java.util.List;
import java.util.Optional;

public interface RoomRepository {
    void save(RoomEntity room);
    Optional<RoomEntity> getByRoomNumber(Integer roomNumber);
    void update(RoomEntity entity);
    List<RoomEntity> getAvailableRooms();
    Page<RoomEntity> getAll(PageRequest pageRequest);
    void saveImage(Integer roomNumber, byte[] image);
    byte[] getImageByRoomNumber(Integer roomNumber);
    void deleteByNumber(Integer roomNumber);
}

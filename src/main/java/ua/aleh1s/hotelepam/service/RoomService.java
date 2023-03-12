package ua.aleh1s.hotelepam.service;

import ua.aleh1s.hotelepam.model.criteria.RoomCriteria;
import ua.aleh1s.hotelepam.model.entity.RoomEntity;
import ua.aleh1s.hotelepam.utils.Page;
import ua.aleh1s.hotelepam.utils.PageRequest;
import ua.aleh1s.hotelepam.utils.Period;

import java.math.BigDecimal;

public interface RoomService {

    void update(RoomEntity room);
    boolean isRoomAvailable(Integer number, Period period);
    Page<RoomEntity> getNotReservedRooms(RoomCriteria criteria, PageRequest pageRequest);
    Page<RoomEntity> getRooms(PageRequest pageRequest);
    BigDecimal getTotalPrice(Integer roomNumber, Period requestedPeriod);
    BigDecimal getTotalPrice(RoomEntity room, Period requestedPeriod);
    RoomEntity getByRoomNumber(Integer roomNumber);
}

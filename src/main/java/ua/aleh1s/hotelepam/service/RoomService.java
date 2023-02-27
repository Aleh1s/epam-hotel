package ua.aleh1s.hotelepam.service;

import ua.aleh1s.hotelepam.model.entity.RoomEntity;
import ua.aleh1s.hotelepam.utils.Period;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface RoomService {

    boolean isRoomAvailable(Integer number, Period period);
    List<RoomEntity> getAvailableRooms(Integer guests, Period requestedPeriod);
    BigDecimal getTotalPrice(Integer roomNumber, Period requestedPeriod);
    BigDecimal getTotalPrice(RoomEntity room, Period requestedPeriod);
    RoomEntity getByRoomNumber(Integer roomNumber);
    List<RoomEntity> getSortedRooms(Map<String, String> sortParamMap);
}

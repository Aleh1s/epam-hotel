package ua.aleh1s.hotelepam.service;

import ua.aleh1s.hotelepam.exception.ServiceException;
import ua.aleh1s.hotelepam.model.criteria.RoomCriteria;
import ua.aleh1s.hotelepam.model.entity.RoomEntity;
import ua.aleh1s.hotelepam.utils.Page;
import ua.aleh1s.hotelepam.utils.PageRequest;
import ua.aleh1s.hotelepam.utils.Period;

import java.math.BigDecimal;
import java.sql.Blob;

public interface RoomService {
    void create(RoomEntity room) throws ServiceException;
    void update(RoomEntity room) throws ServiceException;
    void updateImage(RoomEntity room) throws ServiceException;
    boolean existsByRoomNumber(Integer roomNumber) throws ServiceException;
    boolean isRoomAvailable(Integer number, Period period) throws ServiceException;
    Page<RoomEntity> getNotReservedRooms(RoomCriteria criteria, PageRequest pageRequest) throws ServiceException;
    Page<RoomEntity> getRooms(PageRequest pageRequest) throws ServiceException;
    BigDecimal getTotalPrice(Integer roomNumber, Period requestedPeriod) throws ServiceException;
    BigDecimal getTotalPrice(RoomEntity room, Period requestedPeriod) throws ServiceException;
    RoomEntity getByRoomNumber(Integer roomNumber) throws ServiceException;
    byte[] getImageByRoomNumber(Integer roomNumber) throws ServiceException;
}

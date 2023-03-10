package ua.aleh1s.hotelepam.model.repository;

import ua.aleh1s.hotelepam.utils.Page;
import ua.aleh1s.hotelepam.utils.PageRequest;
import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.model.entity.ReservationStatus;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository {

    Long create(ReservationEntity entity);
    Page<ReservationEntity> getAllActualReservationByStatus(ReservationStatus status, PageRequest pageRequest);
    Optional<ReservationEntity> findById(Long reservationId);
    void update(ReservationEntity reservation);
    List<ReservationEntity> getActualReservations();
    List<ReservationEntity> getActualReservationsByRoomNumber(Integer number);
    Page<ReservationEntity> getAllReservationsByUserId(Long userId, PageRequest pageRequest);
}

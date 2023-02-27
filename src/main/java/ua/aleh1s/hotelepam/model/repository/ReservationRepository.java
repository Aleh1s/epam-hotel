package ua.aleh1s.hotelepam.model.repository;

import ua.aleh1s.hotelepam.utils.Page;
import ua.aleh1s.hotelepam.utils.PageRequest;
import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.model.entity.ReservationStatus;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository {

    Long create(ReservationEntity entity);
    Page<ReservationEntity> getAllByStatus(ReservationStatus status, PageRequest pageRequest);
    Optional<ReservationEntity> getById(Long reservationId);
    void update(ReservationEntity reservation);
    Page<ReservationEntity> getAllByUserIdAndStatusOrderByCreatedAtDesc(Long userId, ReservationStatus status, PageRequest pageRequest);
    Page<ReservationEntity> getAll(PageRequest pageRequest);
    List<ReservationEntity> getActualReservations();
    List<ReservationEntity> getActualReservationsByRoomNumber(Integer number);
    void updateStatus(ReservationEntity reservation);

    Page<ReservationEntity> getAllByUserIdOrderByCreatedAtDesc(Long userId, PageRequest pageRequest);
}

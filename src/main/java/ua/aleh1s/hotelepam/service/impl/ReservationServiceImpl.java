package ua.aleh1s.hotelepam.service.impl;

import lombok.AllArgsConstructor;
import ua.aleh1s.hotelepam.exception.ServiceException;
import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.utils.Page;
import ua.aleh1s.hotelepam.utils.PageRequest;
import ua.aleh1s.hotelepam.repository.ReservationRepository;
import ua.aleh1s.hotelepam.service.ReservationService;

import static ua.aleh1s.hotelepam.model.entity.ReservationStatus.*;

@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    @Override
    public Long create(ReservationEntity entity) {
        return reservationRepository.create(entity);
    }

    @Override
    public ReservationEntity getById(Long id) throws ServiceException {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Reservation with id " + id + " does not exist"));
    }

    @Override
    public Page<ReservationEntity> getAllActualPayedReservations(PageRequest pageRequest) {
        return reservationRepository.getAllActualReservationByStatus(PAYED, pageRequest);
    }

    @Override
    public Page<ReservationEntity> getAllReservationsByUserId(Long userId, PageRequest pageRequest) {
        return reservationRepository.getAllReservationsByUserId(userId, pageRequest);
    }

    @Override
    public void cancelReservation(ReservationEntity reservation) {
        reservation.setStatus(CANCELED);
        update(reservation);
    }

    @Override
    public void update(ReservationEntity reservation) {
        reservationRepository.update(reservation);
    }

}

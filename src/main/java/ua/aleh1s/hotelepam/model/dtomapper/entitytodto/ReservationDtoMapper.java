package ua.aleh1s.hotelepam.model.dtomapper.entitytodto;

import ua.aleh1s.hotelepam.model.dto.ReservationDto;
import ua.aleh1s.hotelepam.model.entity.ReservationEntity;

import java.sql.Date;
import java.util.function.Function;

import static ua.aleh1s.hotelepam.utils.Utils.toDate;

public class ReservationDtoMapper implements Function<ReservationEntity, ReservationDto> {

    @Override
    public ReservationDto apply(ReservationEntity entity) {
        return new ReservationDto(
                entity.getId(),
                entity.getRoomNumber(),
                entity.getCustomerId(),
                Date.valueOf(entity.getCheckIn()),
                Date.valueOf(entity.getCheckOut()),
                toDate(entity.getCreatedAt()),
                toDate(entity.getExpiredAt()),
                toDate(entity.getPayedAt()),
                entity.getTotalAmount(),
                entity.getStatus()
        );
    }
}

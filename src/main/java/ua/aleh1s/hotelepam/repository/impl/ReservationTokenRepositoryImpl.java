package ua.aleh1s.hotelepam.repository.impl;

import ua.aleh1s.hotelepam.model.entity.ReservationTokenEntity;
import ua.aleh1s.hotelepam.database.querybuilder.EntityManager;
import ua.aleh1s.hotelepam.database.querybuilder.Root;
import ua.aleh1s.hotelepam.repository.ReservationTokenRepository;
import ua.aleh1s.hotelepam.mapper.sqlmapper.impl.SqlReservationTokenEntityMapper;

import java.sql.Timestamp;
import java.util.Optional;

public class ReservationTokenRepositoryImpl implements ReservationTokenRepository {

    private final SqlReservationTokenEntityMapper reservationTokenMapper;
    private final EntityManager entityManager;

    public ReservationTokenRepositoryImpl(
            SqlReservationTokenEntityMapper reservationTokenMapper,
            EntityManager entityManager) {
        this.reservationTokenMapper = reservationTokenMapper;
        this.entityManager = entityManager;
    }

    @Override
    public void create(ReservationTokenEntity reservationToken) {
        Root<ReservationTokenEntity> root = entityManager.valueOf(ReservationTokenEntity.class);

        root.insert().values(
                root.get("id").set(reservationToken.getId()),
                root.get("createdAt").set(Timestamp.valueOf(reservationToken.getCreatedAt())),
                root.get("expiredAt").set(Timestamp.valueOf(reservationToken.getExpiredAt())),
                root.get("reservationId").set(reservationToken.getReservationId())
        ).execute();
    }

    @Override
    public Optional<ReservationTokenEntity> findById(String tokenId) {
        Root<ReservationTokenEntity> root = entityManager.valueOf(ReservationTokenEntity.class);
        ReservationTokenEntity result = root.select()
                .where(root.get("id").equal(tokenId)).getResult(reservationTokenMapper);
        return Optional.ofNullable(result);
    }

    @Override
    public void update(ReservationTokenEntity entity) {
        Root<ReservationTokenEntity> root = entityManager.valueOf(ReservationTokenEntity.class);
        Timestamp confirmedAt = entity.getConfirmedAt() != null ? Timestamp.valueOf(entity.getConfirmedAt()) : null;
        root.update().set(
                root.get("createdAt").set(entity.getCreatedAt()),
                root.get("expiredAt").set(entity.getExpiredAt()),
                root.get("confirmedAt").set(confirmedAt),
                root.get("reservationId").set(entity.getReservationId())
        ).where(root.get("id").equal(entity.getId())).execute();
    }
}

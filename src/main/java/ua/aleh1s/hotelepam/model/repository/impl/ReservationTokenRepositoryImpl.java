package ua.aleh1s.hotelepam.model.repository.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.model.entity.ReservationTokenEntity;
import ua.aleh1s.hotelepam.model.repository.ReservationTokenRepository;
import ua.aleh1s.hotelepam.model.sqlmapper.impl.SqlReservationTokenEntityMapper;
import ua.aleh1s.hotelepam.model.querybuilder.Root;

import java.sql.Timestamp;
import java.util.Optional;

public class ReservationTokenRepositoryImpl implements ReservationTokenRepository {

    private static final Logger logger = LogManager.getLogger(ReservationTokenRepositoryImpl.class);
    private static final SqlReservationTokenEntityMapper reservationTokenMapper =
            AppContext.getInstance().getSqlReservationTokenEntityMapper();
    @Override
    public void create(ReservationTokenEntity reservationToken) {
        Root<ReservationTokenEntity> root = Root.valueOf(ReservationTokenEntity.class);

        root.insert().values(
                root.get("id").set(reservationToken.getId()),
                root.get("createdAt").set(Timestamp.valueOf(reservationToken.getCreatedAt())),
                root.get("expiredAt").set(Timestamp.valueOf(reservationToken.getExpiredAt())),
                root.get("reservationId").set(reservationToken.getReservationId())
        ).execute();
    }

    @Override
    public Optional<ReservationTokenEntity> findById(String tokenId) {
        Root<ReservationTokenEntity> root = Root.valueOf(ReservationTokenEntity.class);
        ReservationTokenEntity result = root.select()
                .where(root.get("id").equal(tokenId)).getResult(reservationTokenMapper);
        return Optional.ofNullable(result);
    }

    @Override
    public void update(ReservationTokenEntity entity) {
        Root<ReservationTokenEntity> root = Root.valueOf(ReservationTokenEntity.class);
        Timestamp confirmedAt = entity.getConfirmedAt() != null ? Timestamp.valueOf(entity.getConfirmedAt()) : null;
        root.update().set(
                root.get("createdAt").set(entity.getCreatedAt()),
                root.get("expiredAt").set(entity.getExpiredAt()),
                root.get("confirmedAt").set(confirmedAt),
                root.get("reservationId").set(entity.getReservationId())
        ).where(root.get("id").equal(entity.getId())).execute();
    }
}

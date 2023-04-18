package ua.aleh1s.hotelepam.repository.impl;

import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.model.entity.ReservationStatus;
import ua.aleh1s.hotelepam.database.querybuilder.EntityManager;
import ua.aleh1s.hotelepam.database.querybuilder.OrderUnit;
import ua.aleh1s.hotelepam.database.querybuilder.Root;
import ua.aleh1s.hotelepam.database.querybuilder.node.MultiplePredicateNode;
import ua.aleh1s.hotelepam.database.querybuilder.node.PredicateNode;
import ua.aleh1s.hotelepam.repository.ReservationRepository;
import ua.aleh1s.hotelepam.mapper.sqlmapper.impl.SqlReservationEntityMapper;
import ua.aleh1s.hotelepam.utils.Page;
import ua.aleh1s.hotelepam.utils.PageRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static ua.aleh1s.hotelepam.utils.Utils.toDate;
import static ua.aleh1s.hotelepam.utils.Utils.toTimestamp;

public class ReservationRepositoryImpl implements ReservationRepository {

    private final SqlReservationEntityMapper reservationEntityMapper;
    private final EntityManager entityManager;

    public ReservationRepositoryImpl(
            SqlReservationEntityMapper reservationEntityMapper,
            EntityManager entityManager) {
        this.reservationEntityMapper = reservationEntityMapper;
        this.entityManager = entityManager;
    }


    @Override
    public Long create(ReservationEntity entity) {
        Root<ReservationEntity> root = entityManager.valueOf(ReservationEntity.class);
        return root.insert().values(
                root.get("roomNumber").set(entity.getRoomNumber()),
                root.get("customerId").set(entity.getCustomerId()),
                root.get("checkIn").set(toDate(entity.getCheckIn())),
                root.get("checkOut").set(toDate(entity.getCheckOut())),
                root.get("createdAt").set(toTimestamp(entity.getCreatedAt())),
                root.get("expiredAt").set(toTimestamp(entity.getExpiredAt())),
                root.get("payedAt").set(toTimestamp(entity.getPayedAt())),
                root.get("totalAmount").set(entity.getTotalAmount().doubleValue()),
                root.get("status").set(entity.getStatus().getIndex())
        ).executeAndReturnPrimaryKey();
    }

    @Override
    public Page<ReservationEntity> getAllActualReservationByStatus(ReservationStatus status, PageRequest pageRequest) {
        Root<ReservationEntity> root = entityManager.valueOf(ReservationEntity.class);
        MultiplePredicateNode predicate = root.and(
                root.get("status").equal(status.getIndex()),
                root.get("checkOut").gte(toDate(LocalDate.now()))
        );

        return getAllReservations(predicate, pageRequest, root, root.get("createdAt", OrderUnit.Direction.DESC));
    }

    @Override
    public Optional<ReservationEntity> findById(Long reservationId) {
        Root<ReservationEntity> root = entityManager.valueOf(ReservationEntity.class);
        ReservationEntity reservation = root.select()
                .where(root.get("id").equal(reservationId))
                .getResult(reservationEntityMapper);
        return Optional.ofNullable(reservation);
    }

    @Override
    public void update(ReservationEntity entity) {
        Root<ReservationEntity> root = entityManager.valueOf(ReservationEntity.class);
        root.update().set(
                root.get("roomNumber").set(entity.getRoomNumber()),
                root.get("customerId").set(entity.getCustomerId()),
                root.get("checkIn").set(toDate(entity.getCheckIn())),
                root.get("checkOut").set(toDate(entity.getCheckOut())),
                root.get("createdAt").set(toTimestamp(entity.getCreatedAt())),
                root.get("expiredAt").set(toTimestamp(entity.getExpiredAt())),
                root.get("payedAt").set(toTimestamp(entity.getPayedAt())),
                root.get("totalAmount").set(entity.getTotalAmount().doubleValue()),
                root.get("status").set(entity.getStatus().getIndex())
        ).where(root.get("id").equal(entity.getId())).execute();
    }

    @Override
    public List<ReservationEntity> getActualReservations() {
        cancelAllExpiredReservations();

        Root<ReservationEntity> root = entityManager.valueOf(ReservationEntity.class);
        Date now = toDate(LocalDate.now());
        return root.select().where(
                root.and(
                        root.get("checkOut").gte(now),
                        root.or(
                                root.get("payedAt").isNotNull(),
                                root.get("expiredAt").gte(LocalDateTime.now())
                        )
                )
        ).getResultList(reservationEntityMapper);
    }

    @Override
    public List<ReservationEntity> getActualReservationsByRoomNumber(Integer number) {
        cancelAllExpiredReservationsByRoomNumber(number);

        Root<ReservationEntity> root = entityManager.valueOf(ReservationEntity.class);

        Date now = toDate(LocalDateTime.now());
        return root.select().where(
                root.and(
                        root.get("roomNumber").equal(number),
                        root.get("checkOut").gte(now),
                        root.or(
                                root.get("payedAt").isNotNull(),
                                root.get("expiredAt").gte(LocalDateTime.now())
                        )
                )
        ).getResultList(reservationEntityMapper);
    }

    private void cancelAllExpiredReservationsByRoomNumber(Integer number) {
        Root<ReservationEntity> root = entityManager.valueOf(ReservationEntity.class);
        root.update().set(
                root.get("status").set(ReservationStatus.CANCELED.getIndex())
        ).where(
                root.and(
                        root.get("roomNumber").equal(number),
                        root.get("expiredAt").lte(toTimestamp(LocalDateTime.now())),
                        root.get("payedAt").isNull()
                )
        ).execute();
    }

    private void cancelAllExpiredReservationsByUserId(Long userId) {
        Root<ReservationEntity> root = entityManager.valueOf(ReservationEntity.class);
        root.update().set(
                root.get("status").set(ReservationStatus.CANCELED.getIndex())
        ).where(
                root.and(
                        root.get("customerId").equal(userId),
                        root.get("expiredAt").lte(toTimestamp(LocalDateTime.now())),
                        root.get("payedAt").isNull()
                )
        ).execute();
    }

    private void cancelAllExpiredReservations() {
        Root<ReservationEntity> root = entityManager.valueOf(ReservationEntity.class);
        root.update().set(
                root.get("status").set(ReservationStatus.CANCELED.getIndex())
        ).where(
                root.and(
                        root.get("expiredAt").lte(toTimestamp(LocalDateTime.now())),
                        root.get("payedAt").isNull()
                )
        ).execute();
    }

    @Override
    public Page<ReservationEntity> getAllReservationsByUserId(Long userId, PageRequest pageRequest) {
        Root<ReservationEntity> root = entityManager.valueOf(ReservationEntity.class);
        PredicateNode userIdEqual = root.get("customerId").equal(userId);

        return getAllReservations(userIdEqual, pageRequest, root, root.get("createdAt", OrderUnit.Direction.DESC));
    }

    private Page<ReservationEntity> getAllReservations(PredicateNode predicate, PageRequest pageRequest, Root<ReservationEntity> root, OrderUnit... orderUnits) {
        cancelAllExpiredReservations();

        List<ReservationEntity> resultList = root.select().where(predicate)
                .order(orderUnits)
                .offset(pageRequest.getOffset())
                .limit(pageRequest.getLimit())
                .getResultList(reservationEntityMapper);

        Long count = root.select(root.countAll())
                .where(predicate)
                .execute(Long.class);

        return Page.of(resultList, count);
    }
}

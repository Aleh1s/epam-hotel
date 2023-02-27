package ua.aleh1s.hotelepam.model.repository.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.model.entity.ReservationStatus;
import ua.aleh1s.hotelepam.utils.Page;
import ua.aleh1s.hotelepam.utils.PageRequest;
import ua.aleh1s.hotelepam.model.repository.ReservationRepository;
import ua.aleh1s.hotelepam.model.sqlmapper.impl.SqlReservationEntityMapper;
import ua.aleh1s.hotelepam.model.querybuilder.OrderUnit;
import ua.aleh1s.hotelepam.model.querybuilder.Root;
import ua.aleh1s.hotelepam.model.querybuilder.node.MultiplePredicateNode;
import ua.aleh1s.hotelepam.model.querybuilder.node.PredicateNode;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ReservationRepositoryImpl implements ReservationRepository {

    private static final Logger logger = LogManager.getLogger(ReservationRepositoryImpl.class);
    private static final SqlReservationEntityMapper reservationEntityMapper
            = AppContext.getInstance().getSqlReservationEntityMapper();

    @Override
    public Long create(ReservationEntity entity) {
        Root<ReservationEntity> root = Root.valueOf(ReservationEntity.class);
        return root.insert().values(
                root.get("roomNumber").set(entity.getRoomNumber()),
                root.get("customerId").set(entity.getCustomerId()),
                root.get("entryDate").set(Date.valueOf(entity.getEntryDate())),
                root.get("leavingDate").set(Date.valueOf(entity.getLeavingDate())),
                root.get("createdAt").set(Timestamp.valueOf(entity.getCreatedAt())),
                root.get("expiredAt").set(Timestamp.valueOf(entity.getExpiredAt())),
                root.get("totalAmount").set(entity.getTotalAmount().doubleValue()),
                root.get("status").set(entity.getStatus().getIndex())
        ).executeAndReturnPrimaryKey();
    }

    @Override
    public Page<ReservationEntity> getAllByStatus(ReservationStatus status, PageRequest pageRequest) {
        Root<ReservationEntity> root = Root.valueOf(ReservationEntity.class);
        PredicateNode statusEqual = root.get("status").equal(status.getIndex());

        List<ReservationEntity> resultList = root.select()
                .where(statusEqual)
                .offset(pageRequest.getOffset())
                .limit(pageRequest.getLimit())
                .getResultList(reservationEntityMapper);

        Long count = root.select(root.countAll())
                .where(statusEqual)
                .execute(Long.class);

        return Page.of(resultList, count);
    }

    @Override
    public Optional<ReservationEntity> getById(Long reservationId) {
        Root<ReservationEntity> root = Root.valueOf(ReservationEntity.class);
        ReservationEntity reservation = root.select()
                .where(root.get("id").equal(reservationId))
                .getResult(reservationEntityMapper);
        return Optional.ofNullable(reservation);
    }

    @Override
    public void update(ReservationEntity entity) {
        Root<ReservationEntity> root = Root.valueOf(ReservationEntity.class);
        Timestamp payedAt = entity.getPayedAt() != null ? Timestamp.valueOf(entity.getPayedAt()) : null;
        root.update().set(
                root.get("roomNumber").set(entity.getRoomNumber()),
                root.get("customerId").set(entity.getCustomerId()),
                root.get("entryDate").set(Date.valueOf(entity.getEntryDate())),
                root.get("leavingDate").set(Date.valueOf(entity.getLeavingDate())),
                root.get("createdAt").set(Timestamp.valueOf(entity.getCreatedAt())),
                root.get("expiredAt").set(Timestamp.valueOf(entity.getExpiredAt())),
                root.get("payedAt").set(payedAt),
                root.get("totalAmount").set(entity.getTotalAmount().doubleValue()),
                root.get("status").set(entity.getStatus().getIndex())
        ).where(root.get("id").equal(entity.getId())).execute();
    }

    @Override
    public Page<ReservationEntity> getAllByUserIdAndStatusOrderByCreatedAtDesc(Long userId, ReservationStatus status, PageRequest pageRequest) {
        Root<ReservationEntity> root = Root.valueOf(ReservationEntity.class);

        MultiplePredicateNode predicate = root.and(
                root.get("customerId").equal(userId),
                root.get("status").equal(status.getIndex())
        );

        List<ReservationEntity> resultList = root.select()
                .where(predicate)
                .order(root.get("createdAt", OrderUnit.Direction.DESC))
                .offset(pageRequest.getOffset())
                .limit(pageRequest.getLimit())
                .getResultList(reservationEntityMapper);

        Long count = root.select(root.countAll()).where(predicate).execute(Long.class);

        return Page.of(resultList, count);
    }

    @Override
    public Page<ReservationEntity> getAll(PageRequest pageRequest) {
        Root<ReservationEntity> root = Root.valueOf(ReservationEntity.class);

        List<ReservationEntity> resultList = root.select()
                .offset(pageRequest.getOffset())
                .limit(pageRequest.getLimit())
                .getResultList(reservationEntityMapper);

        Long count = root.select(root.countAll()).execute(Long.class);
        return Page.of(resultList, count);
    }

    @Override
    public List<ReservationEntity> getActualReservations() {
        Root<ReservationEntity> root = Root.valueOf(ReservationEntity.class);
        return root.select().where(
                root.or(
                        root.get("entryDate").gte(Date.valueOf(LocalDate.now())),
                        root.get("leavingDate").gte(Date.valueOf(LocalDate.now()))
                )
        ).getResultList(reservationEntityMapper);
    }

    @Override
    public List<ReservationEntity> getActualReservationsByRoomNumber(Integer number) {
        Root<ReservationEntity> root = Root.valueOf(ReservationEntity.class);
        return root.select().where(
                root.and(
                        root.get("roomNumber").equal(number),
                        root.or(
                                root.get("entryDate").gte(Date.valueOf(LocalDate.now())),
                                root.get("leavingDate").gte(Date.valueOf(LocalDate.now()))
                        )
                )
        ).getResultList(reservationEntityMapper);
    }

    @Override
    public void updateStatus(ReservationEntity reservation) {
        Root<ReservationEntity> root = Root.valueOf(ReservationEntity.class);

        root.update()
                .set(root.get("status").set(reservation.getStatus().getIndex()))
                .where(root.get("id").equal(reservation.getId()))
                .execute();
    }

    @Override
    public Page<ReservationEntity> getAllByUserIdOrderByCreatedAtDesc(Long userId, PageRequest pageRequest) {
        Root<ReservationEntity> root = Root.valueOf(ReservationEntity.class);

        PredicateNode customerIdEqual = root.get("customerId").equal(userId);
        List<ReservationEntity> resultList = root.select()
                .where(customerIdEqual)
                .order(root.get("createdAt", OrderUnit.Direction.DESC))
                .offset(pageRequest.getOffset())
                .limit(pageRequest.getLimit())
                .getResultList(reservationEntityMapper);

        Long count = root.select(root.countAll())
                .where(customerIdEqual)
                .execute(Long.class);

        return Page.of(resultList, count);
    }
}

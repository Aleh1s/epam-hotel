package ua.aleh1s.hotelepam.model.repository.impl;

import ua.aleh1s.hotelepam.model.entity.RequestEntity;
import ua.aleh1s.hotelepam.model.entity.RequestStatus;
import ua.aleh1s.hotelepam.model.querybuilder.EntityManager;
import ua.aleh1s.hotelepam.model.querybuilder.OrderUnit;
import ua.aleh1s.hotelepam.utils.Page;
import ua.aleh1s.hotelepam.utils.PageRequest;
import ua.aleh1s.hotelepam.model.repository.RequestRepository;
import ua.aleh1s.hotelepam.model.sqlmapper.impl.SqlRequestEntityMapper;
import ua.aleh1s.hotelepam.model.querybuilder.Root;
import ua.aleh1s.hotelepam.model.querybuilder.node.MultiplePredicateNode;
import ua.aleh1s.hotelepam.utils.Utils;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class RequestRepositoryImpl implements RequestRepository {

    private final SqlRequestEntityMapper requestEntityMapper;
    private final EntityManager entityManager;

    public RequestRepositoryImpl(
            SqlRequestEntityMapper requestEntityMapper,
            EntityManager entityManager) {
        this.requestEntityMapper = requestEntityMapper;
        this.entityManager = entityManager;
    }

    @Override
    public void create(RequestEntity request) {
        Root<RequestEntity> root = entityManager.valueOf(RequestEntity.class);
        root.insert().values(
                root.get("roomNumber").set(request.getRoomNumber()),
                root.get("customerId").set(request.getCustomerId()),
                root.get("status").set(request.getStatus().getIndex()),
                root.get("checkIn").set(request.getCheckIn()),
                root.get("checkOut").set(request.getCheckOut()),
                root.get("totalAmount").set(request.getTotalAmount()),
                root.get("createdAt").set(Utils.toTimestamp(request.getCreatedAt()))
        ).execute();
    }

    @Override
    public Page<RequestEntity> getAllActiveByUserId(Long userId, PageRequest pageRequest) {
        Root<RequestEntity> root = entityManager.valueOf(RequestEntity.class);

        MultiplePredicateNode predicate = root.and(
                root.get("customerId").equal(userId),
                root.get("status").equal(RequestStatus.NEW.getIndex())
        );

        List<RequestEntity> resultList = root.select()
                .where(predicate)
                .order(root.get("createdAt", OrderUnit.Direction.DESC))
                .offset(pageRequest.getOffset())
                .limit(pageRequest.getLimit())
                .getResultList(requestEntityMapper);

        Long count = root.select(root.countAll())
                .where(predicate)
                .execute(Long.class);

        return Page.of(resultList, count);
    }

    @Override
    public Optional<RequestEntity> findById(Long requestId) {
        Root<RequestEntity> root = entityManager.valueOf(RequestEntity.class);
        RequestEntity request = root.select()
                .where(root.get("id").equal(requestId))
                .getResult(requestEntityMapper);
        return Optional.ofNullable(request);
    }

    @Override
    public void update(RequestEntity entity) {
        Root<RequestEntity> root = entityManager.valueOf(RequestEntity.class);
        root.update().set(
                root.get("roomNumber").set(entity.getRoomNumber()),
                root.get("customerId").set(entity.getCustomerId()),
                root.get("status").set(entity.getStatus().getIndex()),
                root.get("checkIn").set(Date.valueOf(entity.getCheckIn())),
                root.get("checkOut").set(Date.valueOf(entity.getCheckOut())),
                root.get("totalAmount").set(entity.getTotalAmount().doubleValue()),
                root.get("createdAt").set(Utils.toTimestamp(entity.getCreatedAt()))
        ).where(root.get("id").equal(entity.getId())).execute();
    }
}

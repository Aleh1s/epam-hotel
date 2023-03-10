package ua.aleh1s.hotelepam.model.repository.impl;

import ua.aleh1s.hotelepam.model.entity.ApplicationEntity;
import ua.aleh1s.hotelepam.model.entity.ApplicationStatus;
import ua.aleh1s.hotelepam.model.querybuilder.EntityManager;
import ua.aleh1s.hotelepam.model.querybuilder.OrderUnit;
import ua.aleh1s.hotelepam.model.querybuilder.Root;
import ua.aleh1s.hotelepam.model.querybuilder.node.PredicateNode;
import ua.aleh1s.hotelepam.model.repository.ApplicationRepository;
import ua.aleh1s.hotelepam.model.sqlmapper.impl.SqlApplicationEntityMapper;
import ua.aleh1s.hotelepam.utils.Page;
import ua.aleh1s.hotelepam.utils.PageRequest;

import java.util.List;
import java.util.Optional;

import static ua.aleh1s.hotelepam.utils.Utils.toDate;
import static ua.aleh1s.hotelepam.utils.Utils.toTimestamp;

public class ApplicationRepositoryImpl implements ApplicationRepository {

    private final SqlApplicationEntityMapper applicationEntityMapper;
    private final EntityManager entityManager;

    public ApplicationRepositoryImpl(
            SqlApplicationEntityMapper applicationEntityMapper,
            EntityManager entityManager) {
        this.applicationEntityMapper = applicationEntityMapper;
        this.entityManager = entityManager;
    }

    @Override
    public void create(ApplicationEntity application) {
        Root<ApplicationEntity> root = entityManager.valueOf(ApplicationEntity.class);
        root.insert().values(
                root.get("guests").set(application.getGuests()),
                root.get("roomClass").set(application.getClazz().getIndex()),
                root.get("checkIn").set(toDate(application.getCheckIn())),
                root.get("checkOut").set(toDate(application.getCheckOut())),
                root.get("status").set(application.getStatus().getIndex()),
                root.get("customerId").set(application.getCustomerId()),
                root.get("createdAt").set(toTimestamp(application.getCreatedAt()))
        ).execute();
    }

    @Override
    public Optional<ApplicationEntity> findById(Long id) {
        Root<ApplicationEntity> root = entityManager.valueOf(ApplicationEntity.class);
        ApplicationEntity application = root.select().where(root.get("id").equal(id)).getResult(applicationEntityMapper);
        return Optional.ofNullable(application);
    }

    @Override
    public void update(ApplicationEntity application) {
        Root<ApplicationEntity> root = entityManager.valueOf(ApplicationEntity.class);
        root.update().set(
                root.get("guests").set(application.getGuests()),
                root.get("roomClass").set(application.getClazz().getIndex()),
                root.get("checkIn").set(toDate(application.getCheckIn())),
                root.get("checkOut").set(toDate(application.getCheckOut())),
                root.get("status").set(application.getStatus().getIndex()),
                root.get("createdAt").set(toTimestamp(application.getCreatedAt()))
        ).where(root.get("id").equal(application.getId())).execute();
    }

    @Override
    public Page<ApplicationEntity> getAllByApplicationStatus(ApplicationStatus status, PageRequest pageRequest) {
        Root<ApplicationEntity> root = entityManager.valueOf(ApplicationEntity.class);
        PredicateNode statusEqual = root.get("status").equal(status.getIndex());

        List<ApplicationEntity> applicationList = root.select()
                .where(statusEqual)
                .order(root.get("createdAt", OrderUnit.Direction.DESC))
                .offset(pageRequest.getOffset())
                .limit(pageRequest.getLimit())
                .getResultList(applicationEntityMapper);

        Long count = root.select(root.countAll()).where(statusEqual).execute(Long.class);

        return Page.of(applicationList, count);
    }
}

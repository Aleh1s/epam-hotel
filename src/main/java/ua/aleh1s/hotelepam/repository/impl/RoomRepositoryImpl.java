package ua.aleh1s.hotelepam.repository.impl;

import ua.aleh1s.hotelepam.database.querybuilder.EntityManager;
import ua.aleh1s.hotelepam.database.querybuilder.OrderUnit;
import ua.aleh1s.hotelepam.database.querybuilder.Root;
import ua.aleh1s.hotelepam.mapper.sqlmapper.impl.SqlRoomEntityMapper;
import ua.aleh1s.hotelepam.model.entity.RoomEntity;
import ua.aleh1s.hotelepam.repository.RoomRepository;
import ua.aleh1s.hotelepam.utils.Page;
import ua.aleh1s.hotelepam.utils.PageRequest;

import java.util.List;
import java.util.Optional;

public class RoomRepositoryImpl implements RoomRepository {

    private final SqlRoomEntityMapper roomEntityMapper;
    private final EntityManager entityManager;

    public RoomRepositoryImpl(
            SqlRoomEntityMapper roomEntityMapper,
            EntityManager entityManager) {
        this.roomEntityMapper = roomEntityMapper;
        this.entityManager = entityManager;
    }

    @Override
    public void save(RoomEntity entity) {
        Root<RoomEntity> root = entityManager.valueOf(RoomEntity.class);
        root.insert().values(
                root.get("number").set(entity.getNumber()),
                root.get("class").set(entity.getClazz().getIndex()),
                root.get("title").set(entity.getTitle()),
                root.get("description").set(entity.getDescription()),
                root.get("attributes").set(String.join(",", entity.getAttributes())),
                root.get("beds").set(entity.getBeds()),
                root.get("guests").set(entity.getGuests()),
                root.get("price").set(entity.getPrice().doubleValue()),
                root.get("area").set(entity.getArea()),
                root.get("isUnavailable").set(entity.getIsUnavailable())
        ).execute();
    }

    @Override
    public Optional<RoomEntity> getByRoomNumber(Integer roomNumber) {
        Root<RoomEntity> root = entityManager.valueOf(RoomEntity.class);
        RoomEntity room = root.select().where(root.get("number").equal(roomNumber))
                .getResult(roomEntityMapper);
        return Optional.ofNullable(room);
    }

    @Override
    public void update(RoomEntity entity) {
        Root<RoomEntity> root = entityManager.valueOf(RoomEntity.class);
        root.update().set(
                root.get("class").set(entity.getClazz().getIndex()),
                root.get("title").set(entity.getTitle()),
                root.get("description").set(entity.getDescription()),
                root.get("attributes").set(String.join(",", entity.getAttributes())),
                root.get("beds").set(entity.getBeds()),
                root.get("guests").set(entity.getGuests()),
                root.get("price").set(entity.getPrice().doubleValue()),
                root.get("area").set(entity.getArea()),
                root.get("isUnavailable").set(entity.getIsUnavailable())
        ).where(root.get("number").equal(entity.getNumber())).execute();
    }

    @Override
    public List<RoomEntity> getAvailableRooms() {
        Root<RoomEntity> root = entityManager.valueOf(RoomEntity.class);
        return root.select().where(root.get("isUnavailable").equal(false))
                .getResultList(roomEntityMapper);
    }

    @Override
    public Page<RoomEntity> getAll(PageRequest pageRequest) {
        Root<RoomEntity> root = entityManager.valueOf(RoomEntity.class);
        List<RoomEntity> result = root.select().order(root.get("number", OrderUnit.Direction.ASC))
                .offset(pageRequest.getOffset())
                .limit(pageRequest.getLimit())
                .getResultList(roomEntityMapper);

        Long count = root.select(root.countAll()).execute(Long.class);

        return Page.of(result, count);
    }

    @Override
    public void saveImage(Integer roomNumber, byte[] image) {
        Root<RoomEntity> root = entityManager.valueOf(RoomEntity.class);
        root.update().set(
                root.get("image").set(image)
        ).where(root.get("number").equal(roomNumber)).execute();
    }

    @Override
    public byte[] getImageByRoomNumber(Integer roomNumber) {
        Root<RoomEntity> root = entityManager.valueOf(RoomEntity.class);
        return root.select(
                root.get("image")
        ).where(root.get("number").equal(roomNumber)).getBytes();
    }
}

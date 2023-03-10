package ua.aleh1s.hotelepam.model.repository.impl;

import ua.aleh1s.hotelepam.model.entity.RoomEntity;
import ua.aleh1s.hotelepam.model.querybuilder.EntityManager;
import ua.aleh1s.hotelepam.model.repository.RoomRepository;
import ua.aleh1s.hotelepam.model.sqlmapper.impl.SqlRoomEntityMapper;
import ua.aleh1s.hotelepam.model.querybuilder.Root;

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
    public Optional<RoomEntity> getByRoomNumber(Integer roomNumber) {
        Root<RoomEntity> root = entityManager.valueOf(RoomEntity.class);
        RoomEntity room = root.select().where(root.get("roomNumber").equal(roomNumber))
                .getResult(roomEntityMapper);
        return Optional.ofNullable(room);
    }

    @Override
    public void update(RoomEntity entity) {
        Root<RoomEntity> root = entityManager.valueOf(RoomEntity.class);
        root.update().set(
                root.get("roomClass").set(entity.getRoomClass().getIndex()),
                root.get("status").set(entity.getStatus().getIndex()),
                root.get("description").set(entity.getDescription()),
                root.get("price").set(entity.getPrice().doubleValue()),
                root.get("name").set(entity.getName()),
                root.get("attributes").set(String.join(",", entity.getAttributes())),
                root.get("bedsNumber").set(entity.getBedsNumber()),
                root.get("personsNumber").set(entity.getPersonsNumber()),
                root.get("area").set(entity.getArea())
        ).where(root.get("roomNumber").equal(entity.getRoomNumber())).execute();
    }

    @Override
    public List<RoomEntity> getAll() {
        Root<RoomEntity> root = entityManager.valueOf(RoomEntity.class);
        return root.select().getResultList(roomEntityMapper);
    }

    @Override
    public List<RoomEntity> getAllByGuests(Integer guests) {
        Root<RoomEntity> root = entityManager.valueOf(RoomEntity.class);
        return root.select().where(
                root.get("personsNumber").equal(guests)
        ).getResultList(roomEntityMapper);
    }
}

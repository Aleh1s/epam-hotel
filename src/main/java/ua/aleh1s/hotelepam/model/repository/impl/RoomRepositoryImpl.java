package ua.aleh1s.hotelepam.model.repository.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.model.entity.RoomEntity;
import ua.aleh1s.hotelepam.model.repository.RoomRepository;
import ua.aleh1s.hotelepam.model.sqlmapper.impl.SqlRoomEntityMapper;
import ua.aleh1s.hotelepam.model.querybuilder.Root;

import java.util.List;
import java.util.Optional;

public class RoomRepositoryImpl implements RoomRepository {

    private static final Logger logger = LogManager.getLogger(RoomRepositoryImpl.class);
    private static final SqlRoomEntityMapper roomEntityMapper = AppContext.getInstance().getSqlRoomEntityMapper();

    @Override
    public Optional<RoomEntity> getByRoomNumber(Integer roomNumber) {
        Root<RoomEntity> root = Root.valueOf(RoomEntity.class);
        RoomEntity room = root.select().where(root.get("roomNumber").equal(roomNumber))
                .getResult(roomEntityMapper);
        return Optional.ofNullable(room);
    }

    @Override
    public void update(RoomEntity entity) {
        Root<RoomEntity> root = Root.valueOf(RoomEntity.class);
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
        Root<RoomEntity> root = Root.valueOf(RoomEntity.class);
        return root.select().getResultList(roomEntityMapper);
    }
}

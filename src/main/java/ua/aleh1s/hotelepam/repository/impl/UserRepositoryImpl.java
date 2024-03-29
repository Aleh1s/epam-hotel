package ua.aleh1s.hotelepam.repository.impl;

import ua.aleh1s.hotelepam.model.entity.UserEntity;
import ua.aleh1s.hotelepam.database.querybuilder.EntityManager;
import ua.aleh1s.hotelepam.repository.UserRepository;
import ua.aleh1s.hotelepam.mapper.sqlmapper.impl.SqlUserEntityMapper;
import ua.aleh1s.hotelepam.database.querybuilder.Root;

import java.util.Optional;


public class UserRepositoryImpl implements UserRepository {

    private final SqlUserEntityMapper userEntityMapper;
    private final EntityManager entityManager;

    public UserRepositoryImpl(
            SqlUserEntityMapper userEntityMapper,
            EntityManager entityManager) {
        this.userEntityMapper = userEntityMapper;
        this.entityManager = entityManager;
    }

    @Override
    public void create(UserEntity userEntity) {
        Root<UserEntity> root = entityManager.valueOf(UserEntity.class);
        root.insert().values(
                root.get("email").set(userEntity.getEmail()),
                root.get("firstName").set(userEntity.getFirstName()),
                root.get("lastName").set(userEntity.getLastName()),
                root.get("phoneNumber").set(userEntity.getPhoneNumber()),
                root.get("password").set(userEntity.getPassword()),
                root.get("locale").set(userEntity.getLocale().getLanguage()),
                root.get("role").set(userEntity.getRole().name()),
                root.get("account").set(userEntity.getAccount().doubleValue())
        ).execute();
    }

    @Override
    public void update(UserEntity entity) {
        Root<UserEntity> root = entityManager.valueOf(UserEntity.class);
        root.update().set(
                root.get("email").set(entity.getEmail()),
                root.get("firstName").set(entity.getFirstName()),
                root.get("lastName").set(entity.getLastName()),
                root.get("phoneNumber").set(entity.getPhoneNumber()),
                root.get("password").set(entity.getPassword()),
                root.get("locale").set(entity.getLocale()),
                root.get("role").set(entity.getRole().name()),
                root.get("account").set(entity.getAccount().doubleValue())
        ).where(root.get("id").equal(entity.getId())).execute();
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        Root<UserEntity> root = entityManager.valueOf(UserEntity.class);
        UserEntity userEntity = root.select().where(root.get("id").equal(id)).getResult(userEntityMapper);
        return Optional.ofNullable(userEntity);
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        Root<UserEntity> root = entityManager.valueOf(UserEntity.class);
        UserEntity userEntity = root.select().where(root.get("email").equal(email)).getResult(userEntityMapper);
        return Optional.ofNullable(userEntity);
    }

    @Override
    public Optional<UserEntity> findByPhoneNumber(String phoneNumber) {
        Root<UserEntity> root = entityManager.valueOf(UserEntity.class);
        UserEntity userEntity = root.select().where(root.get("phoneNumber").equal(phoneNumber)).getResult(userEntityMapper);
        return Optional.ofNullable(userEntity);
    }
}

package ua.aleh1s.hotelepam.model.dao.impl;

import jdbc.Utils;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import ua.aleh1s.hotelepam.jdbc.DatabaseManager;
import ua.aleh1s.hotelepam.jdbc.DatabaseManagerFactory;
import ua.aleh1s.hotelepam.jdbc.exception.JdbcException;
import ua.aleh1s.hotelepam.model.dao.aggregator.UserEntityAggregator;
import ua.aleh1s.hotelepam.model.entity.UserEntity;
import ua.aleh1s.hotelepam.model.entity.role.UserRole;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class UserSimpleDaoTest {

    private static UserSimpleDao userSimpleDao;
    private static Connection connection;

    @BeforeAll
    static void init() {
        DatabaseManager databaseManager = DatabaseManagerFactory.INSTANCE.getDatabaseManager();
        userSimpleDao = new UserSimpleDao();
        try {
            connection = databaseManager.getConnection();
            userSimpleDao.setConnection(connection);
            Utils.createTables(connection);
        } catch (JdbcException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    static void destroy() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
        try {
            Utils.clearDatabase(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    @ParameterizedTest
    @CsvSource("1,test_login,test_password,UTC,en,CUSTOMER")
    void create(@AggregateWith(UserEntityAggregator.class) UserEntity expected) {
        userSimpleDao.create(expected);
        UserEntity actual = userSimpleDao.findBy(expected.getLogin()).orElseThrow();
        assertEquals(expected, actual);
    }

    @SneakyThrows
    @ParameterizedTest
    @CsvSource("1,test_login,test_password,UTC,en,CUSTOMER")
    void findBy(@AggregateWith(UserEntityAggregator.class) UserEntity expected) {
        userSimpleDao.create(expected);
        UserEntity actual = userSimpleDao.findBy(expected.getLogin()).orElseThrow();
        assertEquals(expected, actual);
    }


    @SneakyThrows
    @ParameterizedTest
    @MethodSource("provideUserEntities")
    void getAll(List<UserEntity> expectedEntities) {
        for (UserEntity expected : expectedEntities)
            userSimpleDao.create(expected);

        List<UserEntity> actualEntities = new ArrayList<>();
        for (UserEntity expected : expectedEntities) {
            UserEntity actual = userSimpleDao.findBy(expected.getLogin())
                    .orElseThrow();
            actualEntities.add(actual);
        }

        for (int i = 0; i < expectedEntities.size(); i++)
            assertEquals(expectedEntities.get(i), actualEntities.get(i));
    }

    @SneakyThrows
    @ParameterizedTest
    @CsvSource("1,test_login,test_password,UTC,en,CUSTOMER")
    void delete(@AggregateWith(UserEntityAggregator.class) UserEntity userEntity) {
        userSimpleDao.create(userEntity);
        userSimpleDao.delete(userEntity);
        boolean isDeleted = userSimpleDao.findBy(userEntity.getLogin()).isEmpty();
        assertTrue(isDeleted);
    }

    @SneakyThrows
    @ParameterizedTest
    @CsvSource("1,test_login,test_password,UTC,en,CUSTOMER")
    void update(@AggregateWith(UserEntityAggregator.class) UserEntity expected) {
        userSimpleDao.create(expected);
        expected.setPassword("new_password");
        userSimpleDao.update(expected);
        UserEntity actual = userSimpleDao.findBy(expected.getLogin()).orElseThrow();
        assertEquals(expected, actual);
    }

    @SneakyThrows
    private static Stream<List<UserEntity>> provideUserEntities() {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Path.of("src/test/resources/users.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(line.split(","));
            }
        }

        UserEntity[] userEntities = new UserEntity[data.size()];
        for (int i = 0; i < data.size(); i++)
            userEntities[i] = map(data.get(i));

        return Stream.of(List.of(userEntities));
    }

    private static UserEntity map(String[] strArr) {
        return UserEntity.Builder.newBuilder()
                .id(Long.parseLong(strArr[0]))
                .login(strArr[1])
                .password(strArr[2])
                .timezone(ZoneId.of(strArr[3]))
                .locale(new Locale(strArr[4]))
                .role(UserRole.valueOf(strArr[5]))
                .build();
    }
}
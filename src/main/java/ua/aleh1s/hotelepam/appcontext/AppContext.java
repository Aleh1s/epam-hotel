package ua.aleh1s.hotelepam.appcontext;

import ua.aleh1s.hotelepam.controller.dtomapper.*;
import ua.aleh1s.hotelepam.model.repository.*;
import ua.aleh1s.hotelepam.model.repository.impl.*;
import ua.aleh1s.hotelepam.model.sqlmapper.*;

import java.util.Objects;

public class AppContext {

    private static AppContext INSTANCE;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final ApplicationRepository applicationRepository;
    private final ReservationRepository reservationRepository;
    private final RequestRepository requestRepository;
    private final RoomDtoMapper roomDtoMapper;
    private final RoomCardDtoMapper roomCardDtoMapper;
    private final ApplicationDtoMapper applicationDtoMapper;
    private final RequestDtoMapper requestDtoMapper;
    private final UserDtoMapper userDtoMapper;
    private final ReservationDtoMapper reservationDtoMapper;
    private final SqlApplicationEntityMapper sqlApplicationEntityMapper;
    private final SqlRequestEntityMapper sqlRequestEntityMapper;
    private final SqlReservationEntityMapper sqlReservationEntityMapper;
    private final SqlRoomEntityMapper sqlRoomEntityMapper;
    private final SqlUserEntityMapper sqlUserEntityMapper;

    {
        this.userRepository = new UserRepositoryImpl();
        this.applicationRepository = new ApplicationRepositoryImpl();
        this.roomRepository = new RoomRepositoryImpl();
        this.roomCardDtoMapper = new RoomCardDtoMapper();
        this.roomDtoMapper = new RoomDtoMapper();
        this.reservationRepository = new ReservationRepositoryImpl();
        this.applicationDtoMapper = new ApplicationDtoMapper();
        this.requestRepository = new RequestRepositoryImpl();
        this.requestDtoMapper = new RequestDtoMapper();
        this.userDtoMapper = new UserDtoMapper();
        this.reservationDtoMapper = new ReservationDtoMapper();
        this.sqlApplicationEntityMapper = new SqlApplicationEntityMapper();
        this.sqlRequestEntityMapper = new SqlRequestEntityMapper();
        this.sqlReservationEntityMapper = new SqlReservationEntityMapper();
        this.sqlRoomEntityMapper = new SqlRoomEntityMapper();
        this.sqlUserEntityMapper = new SqlUserEntityMapper();
    }

    public static synchronized AppContext getInstance() {
        if (Objects.isNull(INSTANCE))
            INSTANCE = new AppContext();
        return INSTANCE;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public ApplicationRepository getApplicationRepository() {
        return applicationRepository;
    }

    public RoomRepository getRoomRepository() {
        return roomRepository;
    }

    public RoomCardDtoMapper getRoomCardDtoMapper() {
        return roomCardDtoMapper;
    }

    public RoomDtoMapper getRoomDtoMapper() {
        return roomDtoMapper;
    }

    public ReservationRepository getReservationRepository() {
        return reservationRepository;
    }

    public ApplicationDtoMapper getApplicationDtoMapper() {
        return applicationDtoMapper;
    }

    public RequestRepository getRequestRepository() {
        return requestRepository;
    }

    public RequestDtoMapper getRequestDtoMapper() {
        return requestDtoMapper;
    }

    public UserDtoMapper getUserDtoMapper() {
        return userDtoMapper;
    }

    public ReservationDtoMapper getReservationDtoMapper() {
        return reservationDtoMapper;
    }

    public SqlApplicationEntityMapper getSqlApplicationEntityMapper() {
        return sqlApplicationEntityMapper;
    }

    public SqlRequestEntityMapper getSqlRequestEntityMapper() {
        return sqlRequestEntityMapper;
    }

    public SqlReservationEntityMapper getSqlReservationEntityMapper() {
        return sqlReservationEntityMapper;
    }

    public SqlRoomEntityMapper getSqlRoomEntityMapper() {
        return sqlRoomEntityMapper;
    }

    public SqlUserEntityMapper getSqlUserEntityMapper() {
        return sqlUserEntityMapper;
    }
}

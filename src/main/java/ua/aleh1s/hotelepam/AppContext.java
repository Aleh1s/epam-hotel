package ua.aleh1s.hotelepam;

import ua.aleh1s.hotelepam.controller.mapper.ApplicationDtoMapper;
import ua.aleh1s.hotelepam.controller.mapper.RoomCardDtoMapper;
import ua.aleh1s.hotelepam.controller.mapper.RoomDtoMapper;
import ua.aleh1s.hotelepam.model.repository.ApplicationRepository;
import ua.aleh1s.hotelepam.model.repository.ReservationRepository;
import ua.aleh1s.hotelepam.model.repository.RoomRepository;
import ua.aleh1s.hotelepam.model.repository.UserRepository;
import ua.aleh1s.hotelepam.model.repository.impl.ApplicationRepositoryImpl;
import ua.aleh1s.hotelepam.model.repository.impl.ReservationRepositoryImpl;
import ua.aleh1s.hotelepam.model.repository.impl.RoomRepositoryImpl;
import ua.aleh1s.hotelepam.model.repository.impl.UserRepositoryImpl;

import java.util.Objects;

public class AppContext {

    private static AppContext INSTANCE;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final ApplicationRepository applicationRepository;
    private final ReservationRepository reservationRepository;
    private final RoomCardDtoMapper roomCardDtoMapper;
    private final RoomDtoMapper roomDtoMapper;
    private final ApplicationDtoMapper applicationDtoMapper;

    {
        this.userRepository = new UserRepositoryImpl();
        this.applicationRepository = new ApplicationRepositoryImpl();
        this.roomRepository = new RoomRepositoryImpl();
        this.roomCardDtoMapper = new RoomCardDtoMapper();
        this.roomDtoMapper = new RoomDtoMapper();
        this.reservationRepository = new ReservationRepositoryImpl();
        this.applicationDtoMapper = new ApplicationDtoMapper();
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
    public RoomRepository getRoomRepository() {return roomRepository;}
    public RoomCardDtoMapper getRoomCardDtoMapper() {return roomCardDtoMapper;}

    public RoomDtoMapper getRoomDtoMapper() {
        return roomDtoMapper;
    }

    public ReservationRepository getReservationRepository() {return reservationRepository;}
    public ApplicationDtoMapper getApplicationDtoMapper() {return applicationDtoMapper;}
}

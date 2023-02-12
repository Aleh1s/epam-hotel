package ua.aleh1s.hotelepam;

import ua.aleh1s.hotelepam.controller.mapper.ApplicationDtoMapper;
import ua.aleh1s.hotelepam.controller.mapper.RoomCardDtoMapper;
import ua.aleh1s.hotelepam.controller.mapper.RoomDtoMapper;
import ua.aleh1s.hotelepam.model.repository.*;
import ua.aleh1s.hotelepam.model.repository.impl.*;

import java.util.Objects;

public class AppContext {

    private static AppContext INSTANCE;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final ApplicationRepository applicationRepository;
    private final ReservationRepository reservationRepository;
    private final RequestRepository requestRepository;
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
        this.requestRepository = new RequestRepositoryImpl();
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

    public RequestRepository getRequestRepository() {return requestRepository;}
}

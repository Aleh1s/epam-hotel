package ua.aleh1s.hotelepam.appcontext;

import ua.aleh1s.hotelepam.controller.dtomapper.*;
import ua.aleh1s.hotelepam.mail.MailService;
import ua.aleh1s.hotelepam.mail.MailServiceImpl;
import ua.aleh1s.hotelepam.model.repository.*;
import ua.aleh1s.hotelepam.model.repository.impl.*;
import ua.aleh1s.hotelepam.model.sqlmapper.*;
import ua.aleh1s.hotelepam.service.*;

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
    private final RoomService roomService;
    private final ReservationService reservationService;
    private final MailService mailService;
    private ReservationTokenRepository reservationTokenRepository;
    private ReservationTokenService reservationTokenService;
    private UserService userService;
    private SqlReservationTokenEntityMapper sqlReservationTokenEntityMapper;
    private ApplicationService applicationService;
    private RegistrationService registrationService;
    private RequestService requestService;
    private PaymentService paymentService;
    private BookingService bookingService;

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
        this.roomService = new RoomService();
        this.reservationService = new ReservationService();
        this.mailService = new MailServiceImpl();
        this.reservationTokenRepository = new ReservationTokenRepositoryImpl();
        this.reservationTokenService = new ReservationTokenService();
        this.userService = new UserService();
        this.sqlReservationTokenEntityMapper = new SqlReservationTokenEntityMapper();
        this.applicationService = new ApplicationService();
        this.registrationService = new RegistrationService();
        this.requestService = new RequestService();
        this.paymentService = new PaymentService();
        this.bookingService = new BookingService();
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

    public RoomService getRoomService() {return roomService;}

    public ReservationService getReservationService() {
        return reservationService;
    }

    public MailService getMailService() {
        return mailService;
    }

    public ReservationTokenRepository getReservationTokenRepository() {
        return reservationTokenRepository;
    }

    public ReservationTokenService getReservationTokenService() {
        return reservationTokenService;
    }

    public UserService getUserService() {
        return userService;
    }

    public SqlReservationTokenEntityMapper getSqlReservationTokenEntityMapper() {return sqlReservationTokenEntityMapper;}

    public ApplicationService getApplicationService() {
        return applicationService;
    }

    public RegistrationService getRegistrationService() {
        return registrationService;
    }
    public RequestService getRequestService() {
        return requestService;
    }

    public PaymentService getPaymentService() {
        return paymentService;
    }

    public BookingService getBookingService() {
        return bookingService;
    }
}

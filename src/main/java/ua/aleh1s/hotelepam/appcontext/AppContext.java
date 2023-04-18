package ua.aleh1s.hotelepam.appcontext;

import lombok.Getter;
import ua.aleh1s.hotelepam.controller.security.SecurityManager;
import ua.aleh1s.hotelepam.controller.security.SecurityManagerImpl;
import ua.aleh1s.hotelepam.mapper.dtomapper.entitytodto.*;
import ua.aleh1s.hotelepam.mapper.dtomapper.requesttodto.*;
import ua.aleh1s.hotelepam.mapper.sqlmapper.impl.*;
import ua.aleh1s.hotelepam.database.querybuilder.EntityManager;
import ua.aleh1s.hotelepam.repository.*;
import ua.aleh1s.hotelepam.repository.impl.*;
import ua.aleh1s.hotelepam.service.MailService;
import ua.aleh1s.hotelepam.service.impl.MailServiceImpl;
import ua.aleh1s.hotelepam.service.*;
import ua.aleh1s.hotelepam.service.impl.*;

import java.util.Objects;

import static lombok.AccessLevel.*;

@Getter
public class AppContext {

    @Getter(PRIVATE)
    private static AppContext INSTANCE;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final ApplicationRepository applicationRepository;
    private final ReservationRepository reservationRepository;
    private final RequestRepository requestRepository;
    private final RoomDtoMapper roomDtoMapper;
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
    private final MailService mailService;
    private final ReservationTokenRepository reservationTokenRepository;
    private final ReservationService reservationService;
    private final ReservationTokenService reservationTokenService;
    private final UserService userService;
    private final SqlReservationTokenEntityMapper sqlReservationTokenEntityMapper;
    private final ApplicationService applicationService;
    private final AuthService authService;
    private final RequestService requestService;
    private final PaymentService paymentService;
    private final BookingService bookingService;
    private final PdfBuilderService pdfBuilderService;
    private final SecurityManager securityManager;
    private final EntityManager entityManager;
    private final SqlUserEntityMapper userEntityMapper;
    private final SqlRoomEntityMapper roomEntityMapper;
    private final SqlReservationTokenEntityMapper reservationTokenEntityMapper;
    private final SqlReservationEntityMapper reservationEntityMapper;
    private final SqlRequestEntityMapper requestEntityMapper;
    private final SqlApplicationEntityMapper applicationEntityMapper;
    {
        // Sql Entity Mappers
        this.userEntityMapper = new SqlUserEntityMapper();
        this.roomEntityMapper = new SqlRoomEntityMapper();
        this.reservationTokenEntityMapper = new SqlReservationTokenEntityMapper();
        this.reservationEntityMapper = new SqlReservationEntityMapper();
        this.requestEntityMapper = new SqlRequestEntityMapper();
        this.applicationEntityMapper = new SqlApplicationEntityMapper();
        // Entity Manager
        this.entityManager = new EntityManager();
        // Dto entity mappers
        this.roomDtoMapper = new RoomDtoMapper();
        this.applicationDtoMapper = new ApplicationDtoMapper();
        this.requestDtoMapper = new RequestDtoMapper();
        this.userDtoMapper = new UserDtoMapper();
        this.reservationDtoMapper = new ReservationDtoMapper();
        // Repositories
        this.userRepository = new UserRepositoryImpl(userEntityMapper, entityManager);
        this.applicationRepository = new ApplicationRepositoryImpl(applicationEntityMapper, entityManager);
        this.roomRepository = new RoomRepositoryImpl(roomEntityMapper, entityManager);
        this.reservationRepository = new ReservationRepositoryImpl(reservationEntityMapper, entityManager);
        this.requestRepository = new RequestRepositoryImpl(requestEntityMapper, entityManager);
        this.reservationTokenRepository = new ReservationTokenRepositoryImpl(reservationTokenEntityMapper, entityManager);
        // Services
        this.mailService = new MailServiceImpl();
        this.userService = new UserServiceImpl(userRepository);
        this.roomService = new RoomServiceImpl(reservationRepository, roomRepository);
        this.reservationTokenService = new ReservationTokenServiceImpl(reservationTokenRepository);
        this.reservationService = new ReservationServiceImpl(reservationRepository);
        this.applicationService = new ApplicationServiceImpl(applicationRepository);
        this.requestService = new RequestServiceImpl(requestRepository, applicationService, roomService);
        this.authService = new AuthServiceImpl(userService, userDtoMapper);
        this.paymentService = new PaymentServiceImpl(reservationService, userService, roomService);
        this.bookingService = new BookingServiceImpl(roomService, reservationService, reservationTokenService, mailService, userService);
        this.pdfBuilderService = new PdfBuilderServiceImpl(reservationService, roomService, userService);

        //Sql entity mappers
        this.sqlApplicationEntityMapper = new SqlApplicationEntityMapper();
        this.sqlRequestEntityMapper = new SqlRequestEntityMapper();
        this.sqlReservationEntityMapper = new SqlReservationEntityMapper();
        this.sqlRoomEntityMapper = new SqlRoomEntityMapper();
        this.sqlUserEntityMapper = new SqlUserEntityMapper();
        this.sqlReservationTokenEntityMapper = new SqlReservationTokenEntityMapper();

        this.securityManager = new SecurityManagerImpl();
    }

    public static synchronized AppContext getInstance() {
        if (Objects.isNull(INSTANCE))
            INSTANCE = new AppContext();
        return INSTANCE;
    }

    public HttpRequestRoomDtoMapper getHttpRequestRoomDtoMapper() {
        return null;
    }
}

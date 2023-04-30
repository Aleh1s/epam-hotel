package ua.aleh1s.hotelepam.controller.command;

import ua.aleh1s.hotelepam.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.util.Objects.isNull;

public final class CommandFactory {
    private static CommandFactory instance;
    private final Map<String, Command> commandMap;

    private CommandFactory() {}

    public synchronized static CommandFactory getInstance() {
        if (isNull(instance))
            instance = new CommandFactory();
        return instance;
    }

    public Optional<Command> getCommand(String commandStr) {
        return Optional.ofNullable(commandMap.get(commandStr));
    }

    {
        commandMap = new HashMap<>();
        commandMap.put("login", new LoginCommand());
        commandMap.put("signup", new SignupCommand());
        commandMap.put("i18n", new I18NCommand());
        commandMap.put("application", new ApplicationCommand());
        commandMap.put("redirectToErrorPage", new UnknownCommand());
        commandMap.put("viewRoom", new ViewRoomCommand());
        commandMap.put("book", new BookCommand());
        commandMap.put("applicationList", new ApplicationListCommand());
        commandMap.put("viewApplicationDetails", new ViewApplicationDetailsCommand());
        commandMap.put("makeRequest", new MakeRequestCommand());
        commandMap.put("profile", new ProfileCommand());
        commandMap.put("confirmBooking", new ConfirmBookingCommand());
        commandMap.put("reservationList", new ReservationListCommand());
        commandMap.put("myBookings", new MyBookingsCommand());
        commandMap.put("payReservation", new PayReservationCommand());
        commandMap.put("topUpAccount", new TopUpAccountCommand());
        commandMap.put("logOut", new LogOutCommand());
        commandMap.put("getAvailableRooms", new GetAvailableRoomsCommand());
        commandMap.put("rejectRequest", new RejectRequestCommand());
        commandMap.put("confirmRequest", new ConfirmRequestCommand());
        commandMap.put("downloadReservationPdf", new DownloadReservationPdfCommand());
        commandMap.put("getRooms", new GetRoomsCommand());
        commandMap.put("getRoomEditor", new GetRoomEditorCommand());
        commandMap.put("updateRoom", new UpdateRoomCommand());
        commandMap.put("changeRoomAvailability", new ChangeRoomAvailabilityCommand());
        commandMap.put("createManager", new CreateManagerCommand());
        commandMap.put("createRoom", new CreateRoomCommand());
        commandMap.put("getRoomImage", new GetRoomImageCommand());
    }
}

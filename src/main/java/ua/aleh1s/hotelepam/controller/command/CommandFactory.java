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
        commandMap.put("redirectToErrorPage", new RedirectToErrorPage());
        commandMap.put("roomList", new RoomListCommand());
        commandMap.put("viewRoom", new ViewRoomCommand());
        commandMap.put("book", new BookCommand());
        commandMap.put("bookPage", new BookPageCommand());
        commandMap.put("applicationList", new ApplicationListCommand());
        commandMap.put("takeApplication", new TakeApplicationCommand());
        commandMap.put("makeRequest", new MakeRequestCommand());
        commandMap.put("profile", new ProfileCommand());
        commandMap.put("confirmBooking", new ConfirmBookingCommand());
        commandMap.put("reservationList", new ReservationListCommand());
        commandMap.put("changeReservationStatus", new ChangeReservationStatusCommand());
        commandMap.put("myBookings", new MyBookingsCommand());
    }
}

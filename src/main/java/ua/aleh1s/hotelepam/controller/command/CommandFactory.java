package ua.aleh1s.hotelepam.controller.command;

import ua.aleh1s.hotelepam.controller.command.impl.RedirectToErrorPage;
import ua.aleh1s.hotelepam.controller.command.impl.SignupCommand;

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

        commandMap.put("registerCustomer", new SignupCommand());
        commandMap.put("redirectToErrorPage", new RedirectToErrorPage());
    }
}

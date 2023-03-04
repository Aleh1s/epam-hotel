package ua.aleh1s.hotelepam.controller.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import ua.aleh1s.hotelepam.model.entity.UserRole;

import java.io.IOException;
import java.util.*;

import static java.util.Objects.isNull;

public class SecurityManagerImpl implements SecurityManager {

    private Map<UserRole, Set<String>> accessMap;
    private Set<String> commons;
    private Set<String> outOfControl;

    @Override
    public void init() {
        accessMap = new HashMap<>();
        commons = new HashSet<>();
        outOfControl = new HashSet<>();

        Properties props = new Properties();
        try {
            props.load(SecurityManagerImpl.class.getClassLoader().getResourceAsStream("security.properties"));

            Set<String> managerAccessSet = toSet(props.getProperty("MANAGER"));
            Set<String> customerAccessSet = toSet(props.getProperty("CUSTOMER"));
            Set<String> commonsAccessSet = toSet(props.getProperty("COMMONS"));
            Set<String> outOfControlAccessSet = toSet(props.getProperty("OUT_OF_CONTROL"));

            accessMap.put(UserRole.MANAGER, managerAccessSet);
            accessMap.put(UserRole.CUSTOMER, customerAccessSet);
            commons = commonsAccessSet;
            outOfControl = outOfControlAccessSet;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isUserAuthorized(HttpServletRequest request) {
        Optional<String> commandOptional =
                Optional.ofNullable(request.getParameter("command"));

        if (commandOptional.isEmpty())
            return false;

        String command = commandOptional.get();

        if (command.isBlank())
            return false;

        if (outOfControl.contains(command))
            return true;

        HttpSession session = request.getSession(false);
        if (isNull(session))
            return false;

        UserRole role = (UserRole) session.getAttribute("role");
        if (isNull(role))
            return false;

        return accessMap.get(role).contains(command) || commons.contains(command);
    }

    private Set<String> toSet(String commands) {
        String[] split = commands.split(",");
        return new HashSet<>(List.of(split));
    }
}

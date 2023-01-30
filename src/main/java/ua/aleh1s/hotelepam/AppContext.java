package ua.aleh1s.hotelepam;

import ua.aleh1s.hotelepam.model.repository.UserRepository;
import ua.aleh1s.hotelepam.model.repository.impl.UserRepositoryImpl;

import java.util.Objects;

public class AppContext {

    private static AppContext INSTANCE;
    private final UserRepository userRepository;

    {
        this.userRepository = new UserRepositoryImpl();
    }

    public static synchronized AppContext getInstance() {
        if (Objects.isNull(INSTANCE))
            INSTANCE = new AppContext();
        return INSTANCE;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

}

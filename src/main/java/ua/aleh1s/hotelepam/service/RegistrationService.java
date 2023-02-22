package ua.aleh1s.hotelepam.service;

import org.mindrot.jbcrypt.BCrypt;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.ApplicationException;
import ua.aleh1s.hotelepam.model.entity.UserEntity;

public class RegistrationService {

    public void register(UserEntity user) {
        UserService userService
                = AppContext.getInstance().getUserService();

        String path = ResourcesManager.getInstance().getValue("path.page.signup");
        if (userService.existsByEmail(user.getEmail()))
            throw new ApplicationException(String.format("User with email %s already exists", user.getEmail()), path);

        if (userService.existsByPhoneNumber(user.getPhoneNumber()))
            throw new ApplicationException(String.format("User with phone number %s already exists", user.getPhoneNumber()), path);

        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

        user.setPassword(hashedPassword);

        userService.create(user);
    }
}

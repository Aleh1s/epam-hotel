package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.appcontext.Utils;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.controller.dtomapper.ApplicationDtoMapper;
import ua.aleh1s.hotelepam.controller.dtomapper.UserDtoMapper;
import ua.aleh1s.hotelepam.model.entity.ApplicationEntity;
import ua.aleh1s.hotelepam.model.entity.UserEntity;
import ua.aleh1s.hotelepam.model.repository.ApplicationRepository;
import ua.aleh1s.hotelepam.model.repository.UserRepository;

import java.util.Optional;

public class ViewApplicationDetailsCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Long applicationId = Utils.getLongValue(request, "applicationId");

        ApplicationRepository applicationRepository = AppContext.getInstance().getApplicationRepository();
        Optional<ApplicationEntity> applicationOptional = applicationRepository.getById(applicationId);

        String path = ResourcesManager.getInstance().getValue("path.page.error");
        if (applicationOptional.isEmpty()) {
            //todo: handle
            return path;
        }

        ApplicationEntity application = applicationOptional.get();

        UserRepository userRepository = AppContext.getInstance().getUserRepository();
        Optional<UserEntity> userOptional = userRepository.findById(application.getCustomerId());

        if (userOptional.isEmpty()) {
            //todo: handle
            return path;
        }
        UserEntity user = userOptional.get();

        UserDtoMapper userDtoMapper = AppContext.getInstance().getUserDtoMapper();
        ApplicationDtoMapper applicationDtoMapper = AppContext.getInstance().getApplicationDtoMapper();

        request.setAttribute("application", applicationDtoMapper.apply(application));
        request.setAttribute("user", userDtoMapper.apply(user));

        request.getSession(false).setAttribute("applicationId", applicationId);
        return ResourcesManager.getInstance().getValue("path.page.request");
    }
}

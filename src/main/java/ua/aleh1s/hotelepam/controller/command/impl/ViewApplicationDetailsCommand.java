package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.exception.ApplicationException;
import ua.aleh1s.hotelepam.service.ApplicationService;
import ua.aleh1s.hotelepam.service.UserService;
import ua.aleh1s.hotelepam.utils.Utils;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.model.dtomapper.entitytodto.ApplicationDtoMapper;
import ua.aleh1s.hotelepam.model.dtomapper.entitytodto.UserDtoMapper;
import ua.aleh1s.hotelepam.model.entity.ApplicationEntity;
import ua.aleh1s.hotelepam.model.entity.UserEntity;

public class ViewApplicationDetailsCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        Long applicationId = Utils.getLongValue(request, "applicationId");

        ApplicationDtoMapper applicationDtoMapper = AppContext.getInstance().getApplicationDtoMapper();
        UserDtoMapper userDtoMapper = AppContext.getInstance().getUserDtoMapper();
        ApplicationService applicationService = AppContext.getInstance().getApplicationService();
        UserService userService = AppContext.getInstance().getUserService();

        ApplicationEntity application = applicationService.getApplicationById(applicationId);
        UserEntity user = userService.getById(application.getCustomerId());

        request.setAttribute("application", applicationDtoMapper.apply(application));
        request.setAttribute("user", userDtoMapper.apply(user));

        request.getSession(false).setAttribute("applicationId", applicationId);
        return ResourcesManager.getInstance().getValue("path.page.application.details");
    }
}

package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.exception.ApplicationException;
import ua.aleh1s.hotelepam.mapper.dtomapper.requesttodto.HttpRequestApplicationDtoMapper;
import ua.aleh1s.hotelepam.mapper.dtomapper.requesttodto.HttpRequestRequestDtoMapper;
import ua.aleh1s.hotelepam.model.dto.ApplicationDto;
import ua.aleh1s.hotelepam.service.ApplicationService;
import ua.aleh1s.hotelepam.service.exception.ValidationException;
import ua.aleh1s.hotelepam.utils.Utils;

public class ApplicationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        ResourcesManager resourcesManager = ResourcesManager.getInstance();
        ApplicationService applicationService = AppContext.getInstance().getApplicationService();

        HttpSession session = request.getSession();
        Long id = (Long) session.getAttribute("id");

        HttpRequestApplicationDtoMapper mapper = new HttpRequestApplicationDtoMapper();
        ApplicationDto applicationDto = mapper.map(request);
        request.setAttribute("credentials", applicationDto);

        String path = resourcesManager.getValue("path.page.application");
        if (mapper.hasErrors())
            throw new ValidationException(mapper.getMessagesByRejectedValue(), path);

        try {
            applicationService.create(applicationDto, id);
        } catch (ApplicationException e) {
            e.setPath(path);
            throw e;
        }

        session.setAttribute("applicationDto", applicationDto);
        return redirect(resourcesManager.getValue("path.page.success.application"), response);
    }
}

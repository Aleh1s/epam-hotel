package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.model.entity.ApplicationStatus;
import ua.aleh1s.hotelepam.service.ApplicationService;
import ua.aleh1s.hotelepam.utils.Utils;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.controller.dto.ApplicationDto;
import ua.aleh1s.hotelepam.controller.dtomapper.ApplicationDtoMapper;
import ua.aleh1s.hotelepam.model.pagination.Page;
import ua.aleh1s.hotelepam.model.pagination.PageRequest;
import ua.aleh1s.hotelepam.model.entity.ApplicationEntity;

import java.util.List;

import static ua.aleh1s.hotelepam.utils.Utils.*;

public class ApplicationListCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ApplicationService applicationService = AppContext.getInstance().getApplicationService();
        ApplicationDtoMapper applicationDtoMapper = AppContext.getInstance().getApplicationDtoMapper();

        Integer pageNumber = getIntValueOrDefault(request, "pageNumber", 1);
        Integer pageSize = getIntValueOrDefault(request, "pageSize", 10);

//        WhereSpecification whereSpecification = WhereSpecification.newSpecification();
//        WhereCriteria statusWhereCriteria = WhereCriteria.of(SqlColumn.ApplicationTable.STATUS, ApplicationStatus.NEW.getIndex(), SearchOperation.EQUAL);
//        whereSpecification.addCriteria(statusWhereCriteria);

        Page<ApplicationEntity> applicationPage = applicationService.getAllByApplicationStatus(ApplicationStatus.NEW, PageRequest.of(pageNumber, pageSize));

        List<ApplicationDto> applicationDtoList = applicationPage.getResult().stream()
                .map(applicationDtoMapper)
                .toList();

        Page<ApplicationDto> applicationDtoPage = Page.of(applicationDtoList, applicationPage.getCount());
        Integer pagesNumber = Utils.getNumberOfPages(applicationDtoPage.getCount(), pageSize);

        request.setAttribute("applicationPage", applicationDtoPage);
        request.setAttribute("pagesNumber", pagesNumber);
        request.setAttribute("currPage", pageNumber);

        return ResourcesManager.getInstance().getValue("path.page.application.list");
    }
}

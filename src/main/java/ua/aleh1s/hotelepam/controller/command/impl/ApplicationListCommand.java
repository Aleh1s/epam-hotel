package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.aleh1s.hotelepam.AppContext;
import ua.aleh1s.hotelepam.ResourcesManager;
import ua.aleh1s.hotelepam.Utils;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.controller.dto.ApplicationDto;
import ua.aleh1s.hotelepam.controller.mapper.ApplicationDtoMapper;
import ua.aleh1s.hotelepam.model.criteria.impl.ApplicationListCriteria;
import ua.aleh1s.hotelepam.model.entity.ApplicationEntity;
import ua.aleh1s.hotelepam.model.pagination.impl.ApplicationListPagination;
import ua.aleh1s.hotelepam.model.repository.ApplicationRepository;

import java.util.List;

import static ua.aleh1s.hotelepam.Utils.*;

public class ApplicationListCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ApplicationListCriteria criteria = new ApplicationListCriteria(request);
        ApplicationListPagination pagination = new ApplicationListPagination(request);

        ApplicationRepository applicationRepository = AppContext.getInstance().getApplicationRepository();
        List<ApplicationEntity> applicationList = applicationRepository.getAll(criteria, pagination);
        Integer count = applicationRepository.count(criteria);

        ApplicationDtoMapper applicationDtoMapper = AppContext.getInstance().getApplicationDtoMapper();
        List<ApplicationDto> applicationDtoList = applicationList.stream()
                .map(applicationDtoMapper)
                .toList();

        Integer applicationsPerPage = getIntContextParamValue(request, "applicationsPerPage");
        Integer pagesNumber = (int) Math.ceil(count / (double) applicationsPerPage);
        Integer currPage = getIntValueOrDefault(request, "pageNumber", 1);

        request.setAttribute("pagesNumber", pagesNumber);
        request.setAttribute("applicationList", applicationDtoList);
        request.setAttribute("currPage", currPage);

        return ResourcesManager.getInstance().getValue("path.page.application.list");
    }
}

package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.aleh1s.hotelepam.AppContext;
import ua.aleh1s.hotelepam.ResourcesManager;
import ua.aleh1s.hotelepam.Utils;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.controller.dto.ApplicationDto;
import ua.aleh1s.hotelepam.controller.mapper.ApplicationDtoMapper;
import ua.aleh1s.hotelepam.controller.page.Page;
import ua.aleh1s.hotelepam.controller.page.PageRequest;
import ua.aleh1s.hotelepam.model.entity.ApplicationEntity;
import ua.aleh1s.hotelepam.model.entity.ApplicationStatus;
import ua.aleh1s.hotelepam.model.repository.ApplicationRepository;

import java.util.List;

import static ua.aleh1s.hotelepam.Utils.*;

public class ApplicationListCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        final int PAGE_SIZE = 10;

        Integer pageNumber = getIntValueOrDefault(request, "pageNumber", 1);

        ApplicationRepository applicationRepository = AppContext.getInstance().getApplicationRepository();
        Page<ApplicationEntity> applicationPage = applicationRepository.getAllByStatus(ApplicationStatus.NEW, PageRequest.of(pageNumber, PAGE_SIZE));

        ApplicationDtoMapper applicationDtoMapper = AppContext.getInstance().getApplicationDtoMapper();
        List<ApplicationDto> applicationDtoList = applicationPage.getResult().stream()
                .map(applicationDtoMapper)
                .toList();

        Page<ApplicationDto> applicationDtoPage = Page.of(applicationDtoList, applicationPage.getCount());
        Integer pagesNumber = Utils.getNumberOfPages(applicationDtoPage.getCount(), PAGE_SIZE);

        request.setAttribute("applicationPage", applicationDtoPage);
        request.setAttribute("pagesNumber", pagesNumber);
        request.setAttribute("currPage", pageNumber);

        return ResourcesManager.getInstance().getValue("path.page.application.list");
    }
}

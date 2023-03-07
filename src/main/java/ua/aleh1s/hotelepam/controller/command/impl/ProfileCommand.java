package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.model.dto.RequestDto;
import ua.aleh1s.hotelepam.model.dto.UserDto;
import ua.aleh1s.hotelepam.model.dtomapper.RequestDtoMapper;
import ua.aleh1s.hotelepam.model.dtomapper.UserDtoMapper;
import ua.aleh1s.hotelepam.model.entity.RequestEntity;
import ua.aleh1s.hotelepam.model.entity.UserEntity;
import ua.aleh1s.hotelepam.utils.Page;
import ua.aleh1s.hotelepam.utils.PageRequest;
import ua.aleh1s.hotelepam.service.RequestService;
import ua.aleh1s.hotelepam.service.UserService;
import ua.aleh1s.hotelepam.utils.Utils;

import java.util.List;

import static ua.aleh1s.hotelepam.utils.Utils.getIntValueOrDefault;

public class ProfileCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        UserService userService = AppContext.getInstance().getUserService();
        RequestService requestService = AppContext.getInstance().getRequestService();
        RequestDtoMapper requestDtoMapper = AppContext.getInstance().getRequestDtoMapper();
        UserDtoMapper userDtoMapper = AppContext.getInstance().getUserDtoMapper();

        Integer pageNumber = getIntValueOrDefault(request, "pageNumber", 1);

        HttpSession session = request.getSession(false);
        Long userId = (Long) session.getAttribute("id");

        UserEntity user = userService.getById(userId);

        Page<RequestEntity> requestEntityPage = requestService.getAllActiveRequestsByUserId(userId, PageRequest.of(pageNumber, 5));

        List<RequestDto> requestDtoList = requestEntityPage.result().stream()
                .map(requestDtoMapper)
                .toList();

        Page<RequestDto> requestDtoPage = Page.of(requestDtoList, requestEntityPage.count());

        UserDto userDto = userDtoMapper.apply(user);

        Integer pagesNumber = Utils.getNumberOfPages(requestDtoPage.count(), 5);

        request.setAttribute("userDto", userDto);
        request.setAttribute("requestDtoPage", requestDtoPage);
        request.setAttribute("currPage", pageNumber);
        request.setAttribute("pagesNumber", pagesNumber);

        return ResourcesManager.getInstance().getValue("path.page.profile");
    }
}

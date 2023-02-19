package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.controller.dto.RequestDto;
import ua.aleh1s.hotelepam.controller.dto.UserDto;
import ua.aleh1s.hotelepam.controller.dtomapper.RequestDtoMapper;
import ua.aleh1s.hotelepam.controller.dtomapper.UserDtoMapper;
import ua.aleh1s.hotelepam.model.criteria.Criteria;
import ua.aleh1s.hotelepam.model.criteria.impl.RequestCriteria;
import ua.aleh1s.hotelepam.model.entity.RequestEntity;
import ua.aleh1s.hotelepam.model.entity.UserEntity;
import ua.aleh1s.hotelepam.model.pagination.Pagination;
import ua.aleh1s.hotelepam.model.pagination.impl.RequsetPagination;
import ua.aleh1s.hotelepam.model.repository.RequestRepository;
import ua.aleh1s.hotelepam.model.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static ua.aleh1s.hotelepam.utils.Utils.getIntContextParamValue;
import static ua.aleh1s.hotelepam.utils.Utils.getIntValueOrDefault;

public class ProfileCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);

        Long userId = (Long) session.getAttribute("id");
        UserRepository userRepository = AppContext.getInstance().getUserRepository();

        Optional<UserEntity> userOptional = userRepository.findById(userId);

        String path = ResourcesManager.getInstance().getValue("path.page.error");
        if (userOptional.isEmpty()) {
            //todo: handle
            return path;
        }

        UserEntity user = userOptional.get();

        RequestRepository requestRepository = AppContext.getInstance().getRequestRepository();
        Criteria criteria = new RequestCriteria(user);
        Pagination pagination = new RequsetPagination(request);

        List<RequestEntity> requestEntityList = requestRepository.getAll(criteria, pagination); //todo: remove criteria and pagination
        Integer requestsCount = requestRepository.count(criteria);

        RequestDtoMapper requestDtoMapper = AppContext.getInstance().getRequestDtoMapper();
        List<RequestDto> requestDtoList = requestEntityList.stream()
                .map(requestDtoMapper)
                .toList();

        UserDtoMapper userDtoMapper = AppContext.getInstance().getUserDtoMapper();
        UserDto userDto = userDtoMapper.apply(user);

        Integer pageNumber = getIntValueOrDefault(request, "pageNumber", 1);
        Integer requestsPerPage = getIntContextParamValue(request, "requestsPerPage");
        Integer pagesNumber = (int) Math.ceil(requestsCount / (double) requestsPerPage);

        request.setAttribute("userDto", userDto);
        request.setAttribute("requestDtoList", requestDtoList);
        request.setAttribute("currPage", pageNumber);
        request.setAttribute("pagesNumber", pagesNumber);

        return ResourcesManager.getInstance().getValue("path.page.profile");
    }
}

package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.controller.dto.RoomCardDto;
import ua.aleh1s.hotelepam.controller.dtomapper.RoomCardDtoMapper;
import ua.aleh1s.hotelepam.model.entity.RoomEntity;
import ua.aleh1s.hotelepam.utils.Page;
import ua.aleh1s.hotelepam.utils.PageRequest;
import ua.aleh1s.hotelepam.service.RoomService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toMap;
import static ua.aleh1s.hotelepam.utils.Utils.getIntValueOrDefault;
import static ua.aleh1s.hotelepam.utils.Utils.getNumberOfPages;

public class RoomListCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        RoomService roomService = AppContext.getInstance().getRoomService();
        RoomCardDtoMapper roomCardDtoMapper = AppContext.getInstance().getRoomCardDtoMapper();

        Integer pageSize = getIntValueOrDefault(request, "pageSize", 9);
        Integer pageNumber = getIntValueOrDefault(request, "pageNumber", 1);

        HttpSession session = request.getSession();
        String[] sortValues = request.getParameterValues("sort");
        Map<String, String> sortParamMap;

        if (nonNull(request.getParameter("default")))
            session.setAttribute("roomListSortParamMap", null);

        if (nonNull(sortValues))
            sortParamMap = Arrays.stream(sortValues)
                    .map(valueStr -> valueStr.split(","))
                    .collect(toMap(valueArr -> valueArr[0], paramValueArr -> paramValueArr[1]));
        else
            sortParamMap = (Map<String, String>) session.getAttribute("roomListSortParamMap");

        if (isNull(sortParamMap))
            sortParamMap = new HashMap<>();

        session.setAttribute("roomListSortParamMap", sortParamMap);

        List<RoomEntity> roomList = roomService.getSortedRooms(sortParamMap);

        int totalCount = roomList.size();
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        List<RoomCardDto> roomCardDtoList = roomList.stream()
                .skip(pageRequest.getOffset())
                .limit(pageRequest.getLimit())
                .map(roomCardDtoMapper)
                .toList();

        Page<RoomCardDto> roomCardDtoPage = Page.of(roomCardDtoList, totalCount);
        Integer pagesNumber = getNumberOfPages(totalCount, pageSize);

        request.setAttribute("roomPage", roomCardDtoPage);
        request.setAttribute("currPage", pageNumber);
        request.setAttribute("pagesNumber", pagesNumber);

        return ResourcesManager.getInstance().getValue("path.page.room.list");
    }
}

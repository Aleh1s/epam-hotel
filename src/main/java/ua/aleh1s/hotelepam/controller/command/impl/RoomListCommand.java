package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.aleh1s.hotelepam.AppContext;
import ua.aleh1s.hotelepam.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.controller.dto.RoomCardDto;
import ua.aleh1s.hotelepam.controller.mapper.RoomCardDtoMapper;
import ua.aleh1s.hotelepam.controller.page.Page;
import ua.aleh1s.hotelepam.controller.page.PageRequest;
import ua.aleh1s.hotelepam.model.entity.RoomEntity;
import ua.aleh1s.hotelepam.model.repository.RoomRepository;

import java.util.*;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toMap;
import static ua.aleh1s.hotelepam.Utils.getIntValueOrDefault;
import static ua.aleh1s.hotelepam.Utils.getNumberOfPages;

public class RoomListCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        final int PAGE_SIZE = 9;

        Integer pageNumber = getIntValueOrDefault(request, "pageNumber", 1);

        RoomRepository roomRepository = AppContext.getInstance().getRoomRepository();
        RoomCardDtoMapper roomCardDtoMapper = AppContext.getInstance().getRoomCardDtoMapper();

        List<RoomEntity> roomList = roomRepository.getAll();

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

        if (sortParamMap.containsKey("price")) {
            String direction = sortParamMap.get("price");
            Comparator<RoomEntity> comparator = Comparator.comparing(RoomEntity::getPrice);
            if (direction.equals("desc"))
                comparator = comparator.reversed();
            roomList.sort(comparator);
        }

        if (sortParamMap.containsKey("guests")) {
            String direction = sortParamMap.get("guests");
            Comparator<RoomEntity> comparator = Comparator.comparingInt(RoomEntity::getPersonsNumber);
            if (direction.equals("desc"))
                comparator = comparator.reversed();
            roomList.sort(comparator);
        }

        if (sortParamMap.containsKey("class")) {
            String direction = sortParamMap.get("class");
            Comparator<RoomEntity> comparator = Comparator.comparingInt(room -> room.getRoomClass().getIndex());
            if (direction.equals("desc"))
                comparator = comparator.reversed();
            roomList.sort(comparator);
        }

        if (sortParamMap.containsKey("status")) {
            String direction = sortParamMap.get("status");
            Comparator<RoomEntity> comparator = Comparator.comparingInt(room -> room.getStatus().getIndex());
            if (direction.equals("desc"))
                comparator = comparator.reversed();
            roomList.sort(comparator);
        }

        Integer totalCount = roomList.size();
        PageRequest pageRequest = PageRequest.of(pageNumber, PAGE_SIZE);
        List<RoomCardDto> roomCardDtoList = roomList.stream()
                .skip(pageRequest.getOffset())
                .limit(pageRequest.getLimit())
                .map(roomCardDtoMapper)
                .toList();

        Page<RoomCardDto> roomCardDtoPage = Page.of(roomCardDtoList, totalCount);
        Integer pagesNumber = getNumberOfPages(totalCount, PAGE_SIZE);

        request.setAttribute("roomPage", roomCardDtoPage);
        request.setAttribute("currPage", pageNumber);
        request.setAttribute("pagesNumber", pagesNumber);

        return ResourcesManager.getInstance().getValue("path.page.room.list");
    }
}

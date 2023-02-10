package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.aleh1s.hotelepam.AppContext;
import ua.aleh1s.hotelepam.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.controller.dto.RoomCardDto;
import ua.aleh1s.hotelepam.controller.mapper.RoomCardDtoMapper;
import ua.aleh1s.hotelepam.model.criteria.RoomListCriteria;
import ua.aleh1s.hotelepam.model.entity.RoomEntity;
import ua.aleh1s.hotelepam.model.pagination.RoomListPagination;
import ua.aleh1s.hotelepam.model.repository.RoomRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import static ua.aleh1s.hotelepam.Utils.getIntContextParamValue;
import static ua.aleh1s.hotelepam.Utils.getIntValueOrDefault;

public class RoomListCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        RoomRepository roomRepository = AppContext.getInstance().getRoomRepository();
        RoomCardDtoMapper roomCardDtoMapper = AppContext.getInstance().getRoomCardDtoMapper();

        RoomListCriteria criteria = RoomListCriteria.valueOf(request);
        RoomListPagination pagination = RoomListPagination.valueOf(request);

        List<RoomEntity> roomList = roomRepository.getAll(criteria, pagination);
        Integer count = roomRepository.count(criteria);

        Integer roomCardsPerPage = getIntContextParamValue(request, "roomCardsPerPage");
        Integer currPage = getIntValueOrDefault(request, "pageNumber", 1);
        Integer pagesNumber = (int) Math.ceil(count / (double) roomCardsPerPage);

        Map<String, Object> params = new HashMap<>();
        params.putAll(criteria.getIntParams());
        params.putAll(criteria.getBoolParams());

        List<RoomCardDto> roomCardDtoList = roomList.stream()
                .map(roomCardDtoMapper).toList();

        request.setAttribute("roomList", roomCardDtoList);
        request.setAttribute("pagesNumber", pagesNumber);
        request.setAttribute("currPage", currPage);
        request.setAttribute("params", params);
        request.setAttribute("paramString", getParamString(params));

        return ResourcesManager.getInstance().getValue("path.page.room.list");
    }

    private String getParamString(Map<String, Object> params) {
        StringJoiner joiner = new StringJoiner("&");
        params.forEach((key, value) -> {
            if (value instanceof Integer i) {
                joiner.add(key + "=" + i);
            } else if (value instanceof Boolean isOn) {
                if (isOn) {
                    joiner.add(key + "=on");
                }
            }
        });
        return joiner.toString();
    }
}

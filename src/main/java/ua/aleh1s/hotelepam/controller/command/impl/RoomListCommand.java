package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.aleh1s.hotelepam.AppContext;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.controller.command.Result;
import ua.aleh1s.hotelepam.controller.dto.RoomCardDto;
import ua.aleh1s.hotelepam.model.criteria.RoomListCriteria;
import ua.aleh1s.hotelepam.model.entity.RoomEntity;
import ua.aleh1s.hotelepam.model.repository.RoomRepository;

import java.util.List;
import java.util.Locale;

public class RoomListCommand implements Command {

    private static final float ROOMS_PER_PAGE = 9;

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) {
        RoomListCriteria criteria = new RoomListCriteria(request);
        RoomRepository roomRepository = AppContext.getInstance().getRoomRepository();
        List<RoomEntity> roomList = roomRepository.getAll(criteria);
        Integer count = roomRepository.count(criteria);

        List<RoomCardDto> roomCardDtoList = roomList.stream().map(room ->
                RoomCardDto.Builder.newBuilder()
                        .roomStatus(room.getStatus().name().toLowerCase(Locale.ROOT))
                        .roomClass(room.getRoomClass().name().toLowerCase(Locale.ROOT))
                        .personsNumber(room.getPersonsNumber())
                        .bedsNumber(room.getBedsNumber())
                        .area(room.getArea())
                        .price(room.getPrice().doubleValue())
                        .build()).toList();

        Integer pagesNumber = (int) Math.ceil(count / ROOMS_PER_PAGE);
        request.setAttribute("roomList", roomCardDtoList);
        request.setAttribute("pagesNumber", pagesNumber);
        return Result.of("roomList.jsp", false);
    }
}

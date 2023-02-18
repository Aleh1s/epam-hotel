package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.aleh1s.hotelepam.AppContext;
import ua.aleh1s.hotelepam.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.controller.dto.RoomDto;
import ua.aleh1s.hotelepam.controller.dtomapper.RoomDtoMapper;
import ua.aleh1s.hotelepam.model.entity.RoomEntity;
import ua.aleh1s.hotelepam.model.repository.RoomRepository;

import java.util.Optional;

import static ua.aleh1s.hotelepam.Utils.*;

public class ViewRoomCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Integer roomNumber = getIntValue(request, "roomNumber");
        RoomRepository roomRepository = AppContext.getInstance().getRoomRepository();
        RoomDtoMapper roomDtoMapper = AppContext.getInstance().getRoomDtoMapper();

        Optional<RoomEntity> roomEntityOptional = roomRepository.getByRoomNumber(roomNumber);

        if (roomEntityOptional.isEmpty()) {
            //todo: handle exception
            return ResourcesManager.getInstance().getValue("path.page.error");
        }

        RoomEntity roomEntity = roomEntityOptional.get();
        RoomDto roomDto = roomDtoMapper.apply(roomEntity);

        request.setAttribute("roomDto", roomDto);

        return ResourcesManager.getInstance().getValue("path.page.room");
    }
}

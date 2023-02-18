package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.controller.dto.BookInfoDto;
import ua.aleh1s.hotelepam.model.entity.RoomEntity;
import ua.aleh1s.hotelepam.model.repository.RoomRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static java.lang.Math.*;
import static ua.aleh1s.hotelepam.appcontext.Utils.getIntValue;
import static ua.aleh1s.hotelepam.appcontext.Utils.getLocalDateValue;

public class BookCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Integer roomNumber = getIntValue(request, "roomNumber");
        LocalDate entryDate = getLocalDateValue(request, "entryDate");
        LocalDate leavingDate = getLocalDateValue(request, "leavingDate");

        String errorMessage, path = ResourcesManager.getInstance().getValue("path.command.view.room");
        if (entryDate.isBefore(LocalDate.now())) {
            errorMessage = "Entry date cannot be before now";
            request.setAttribute("errorMessage", errorMessage);
            return path;
        }

        if (leavingDate.isBefore(entryDate) || leavingDate.isEqual(entryDate)) {
            errorMessage = "Entry date must be before leaving date";
            request.setAttribute("errorMessage", errorMessage);
            return path;
        }

        RoomRepository roomRepository = AppContext.getInstance().getRoomRepository();
        Optional<RoomEntity> roomOptional = roomRepository.getByRoomNumber(roomNumber);

        if (roomOptional.isEmpty()) {
            errorMessage = "There is no room with such number";
            request.setAttribute("errorMessage", errorMessage);
            return path;
        }

        RoomEntity room = roomOptional.get();
        BigDecimal roomPrice = room.getPrice();
        Integer nights = toIntExact(ChronoUnit.DAYS.between(entryDate, leavingDate));
        BigDecimal totalAmount = roomPrice.multiply(BigDecimal.valueOf(nights));

        BookInfoDto bookInfoDto = BookInfoDto.Builder.newBuilder()
                .roomNumber(roomNumber)
                .roomName(room.getName())
                .roomClass(room.getRoomClass())
                .entryDate(entryDate)
                .leavingDate(leavingDate)
                .bedsNumber(room.getBedsNumber())
                .personsNumber(room.getPersonsNumber())
                .totalAmount(totalAmount)
                .build();

        request.getSession(false).setAttribute("bookInfo", bookInfoDto);
        return ResourcesManager.getInstance().getValue("path.page.confirm.booking");
    }
}
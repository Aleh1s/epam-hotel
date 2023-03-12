package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.ApplicationException;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.model.criteria.RoomCriteria;
import ua.aleh1s.hotelepam.model.dto.RoomDto;
import ua.aleh1s.hotelepam.model.dtomapper.RoomDtoMapper;
import ua.aleh1s.hotelepam.model.entity.RoomEntity;
import ua.aleh1s.hotelepam.service.RoomService;
import ua.aleh1s.hotelepam.utils.Page;
import ua.aleh1s.hotelepam.utils.PageRequest;
import ua.aleh1s.hotelepam.utils.Period;

import java.util.List;
import java.util.Objects;

import static ua.aleh1s.hotelepam.utils.Utils.*;

public class GetAvailableRoomsCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ResourcesManager resourcesManager = ResourcesManager.getInstance();
        RoomDtoMapper roomDtoMapper = AppContext.getInstance().getRoomDtoMapper();
        RoomService roomService = AppContext.getInstance().getRoomService();

        HttpSession session;
        String page = request.getParameter("page");
        RoomCriteria criteria;
        int pageNumber;
        Period requestedPeriod;

        String path = resourcesManager.getValue("path.page.home");
        if (Objects.nonNull(page) && page.equals("home")) {
            session = request.getSession();
            pageNumber = 1;
            requestedPeriod = Period.between(
                    getLocalDateValue(request, "checkIn"),
                    getLocalDateValue(request, "checkOut")
            );

            if (!isReservationPeriodValid(requestedPeriod))
                throw new ApplicationException("Invalid range of date.", path);

            criteria = new RoomCriteria();
            criteria.setSort("price,asc");
            criteria.setPeriod(requestedPeriod);
            session.setAttribute("roomCriteria", criteria);
        } else {
            session = request.getSession(false);
            criteria = (RoomCriteria) session.getAttribute("roomCriteria");
            requestedPeriod = criteria.getPeriod();
            String sort = request.getParameter("sort");
            if (Objects.nonNull(sort))
                criteria.setSort(sort);
            pageNumber = getIntValueOrDefault(request, "pageNumber", 1);
        }

        Page<RoomEntity> availableRoomPage =
                roomService.getNotReservedRooms(criteria, PageRequest.of(pageNumber, 9));

        if (availableRoomPage.result().isEmpty())
            throw new ApplicationException("There are no available rooms. Try to pick another date.", path);

        availableRoomPage.result().forEach(room ->
                room.setPrice(roomService.getTotalPrice(room, requestedPeriod)));

        List<RoomDto> roomDtoList = availableRoomPage.result().stream()
                .map(roomDtoMapper)
                .toList();

        Page<RoomDto> roomDtoPage = Page.of(roomDtoList, availableRoomPage.count());
        Integer pagesNumber = getNumberOfPages(roomDtoPage.count(), 9);

        session.setAttribute("requestedCheckIn", requestedPeriod.start());
        session.setAttribute("requestedCheckOut", requestedPeriod.end());

        request.setAttribute("availableRooms", roomDtoPage);
        request.setAttribute("currPage", pageNumber);
        request.setAttribute("pagesNumber", pagesNumber);

        return resourcesManager.getValue("path.page.available.rooms");
    }
}

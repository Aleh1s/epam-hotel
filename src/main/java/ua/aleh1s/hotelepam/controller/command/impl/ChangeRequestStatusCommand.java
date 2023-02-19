package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.utils.Utils;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.controller.dto.BookInfoDto;
import ua.aleh1s.hotelepam.model.entity.RequestEntity;
import ua.aleh1s.hotelepam.model.entity.RequestStatus;
import ua.aleh1s.hotelepam.model.repository.RequestRepository;

import java.io.IOException;
import java.util.Optional;

import static ua.aleh1s.hotelepam.model.entity.RequestStatus.*;

public class ChangeRequestStatusCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Long requestId = Utils.getLongValue(request, "requestId");
        Integer statusIndex = Utils.getIntValue(request, "status");
        RequestStatus requestStatus = RequestStatus.atIndex(statusIndex);

        RequestRepository requestRepository = AppContext.getInstance().getRequestRepository();
        Optional<RequestEntity> requestOptional = requestRepository.getById(requestId);

        String errorMessage, path = ResourcesManager.getInstance().getValue("path.page.error");
        if (requestOptional.isEmpty()) {
            //todo: handle
            return path;
        }

        RequestEntity requestEntity = requestOptional.get();
        if (!requestEntity.getStatus().equals(NEW)) {
            //todo: handle
            return path;
        }

        requestEntity.setStatus(requestStatus);
        requestRepository.update(requestEntity);

        if (requestStatus.equals(CONFIRMED)){
            BookInfoDto bookInfoDto = BookInfoDto.Builder.newBuilder()
                    .roomNumber(requestEntity.getRoomNumber())
                    .entryDate(requestEntity.getEntryDate())
                    .leavingDate(requestEntity.getLeavingDate())
                    .totalAmount(requestEntity.getTotalAmount())
                    .build();

            HttpSession session = request.getSession(false);
            session.setAttribute("bookInfo", bookInfoDto);

            path = ResourcesManager.getInstance().getValue("path.command.confirm.booking");
        } else {
            path = ResourcesManager.getInstance().getValue("path.command.profile");
        }

        try {
            response.sendRedirect(path);
            path = "redirect";
        } catch (IOException e) {
            path = ResourcesManager.getInstance().getValue("path.page.error");
        }

        return path;
    }
}

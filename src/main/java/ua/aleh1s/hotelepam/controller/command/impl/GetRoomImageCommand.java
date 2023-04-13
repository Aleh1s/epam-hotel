package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.exception.ApplicationException;
import ua.aleh1s.hotelepam.service.RoomService;
import ua.aleh1s.hotelepam.utils.Utils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class GetRoomImageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        RoomService roomService = AppContext.getInstance().getRoomService();

        Integer roomNumber = Utils.getIntValue(request, "roomNumber");
        byte[] imageBytes = roomService.getImageByRoomNumber(roomNumber);

        if (imageBytes.length == 0)
            return "redirect";

        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBytes);
             BufferedOutputStream outputStream = new BufferedOutputStream(response.getOutputStream())) {

            int ch;
            while ((ch = inputStream.read()) != -1) {
                outputStream.write(ch);
            }
        } catch (IOException e) {
            throw new ApplicationException();
        }

        return "redirect";
    }
}

package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.utils.Utils;
import ua.aleh1s.hotelepam.controller.command.Command;

public class BookPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Integer pageNumber = Utils.getIntValue(request, "roomNumber");
        HttpSession session = request.getSession(false);
        session.setAttribute("roomNumber", pageNumber);
        return ResourcesManager.getInstance().getValue("path.page.book");
    }
}

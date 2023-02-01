package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.controller.command.Result;

import java.util.Optional;

public class I18NCommand implements Command {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) {
        Optional<String> langOptional = Optional.ofNullable(request.getParameter("lang"));

        if (langOptional.isPresent()) {
            String lang = langOptional.get();
            HttpSession session = request.getSession();
            if (lang.equals("en"))
                session.setAttribute("lang", "");
            else if (lang.equals("ua"))
                session.setAttribute("lang", lang);
        }

        return Result.of("roomList.jsp", false);
    }
}

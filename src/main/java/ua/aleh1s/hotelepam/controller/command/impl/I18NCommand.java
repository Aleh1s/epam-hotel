package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.exception.ApplicationException;
import ua.aleh1s.hotelepam.model.entity.UserEntity;
import ua.aleh1s.hotelepam.service.UserService;

import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

public class I18NCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        Optional<String> langOptional = Optional.ofNullable(request.getParameter("lang"));
        if (langOptional.isPresent())
            setLang(request, langOptional.get());

        String path;
        HttpSession session = request.getSession(false);

        if (Objects.isNull(session.getAttribute("role"))) {
            path = ResourcesManager.getInstance().getValue("path.page.home");
        } else {
            path = ResourcesManager.getInstance().getValue("path.command.profile");
        }

        return path;
    }

    private void setLang(HttpServletRequest request, String lang) throws ApplicationException {
        Optional<HttpSession> sessionOptional = Optional.ofNullable(request.getSession(false));
        if (sessionOptional.isPresent())
            setLangToExistingSession(sessionOptional.get(), lang);
        else
            createSessionAndSetLang(request, lang);
    }

    private void setLangToExistingSession(HttpSession session, String lang) throws ApplicationException {
        Optional<Long> userIdOptional = Optional.ofNullable((Long) session.getAttribute("id"));
        if (userIdOptional.isPresent())
            updateUserEntityLang(lang, userIdOptional.get());
        setLangAttribute(session, lang);
    }

    private void setLangAttribute(HttpSession session, String lang) {
        if (lang.equals("en"))
            session.setAttribute("lang", "");
        else
            session.setAttribute("lang", lang);
    }

    private void createSessionAndSetLang(HttpServletRequest request, String lang) {
        HttpSession session = request.getSession();
        setLangAttribute(session, lang);
    }

    private void updateUserEntityLang(String lang, Long userId) throws ApplicationException {
        UserService userService = AppContext.getInstance().getUserService();
        UserEntity user = userService.getById(userId);

        if (lang.equals("en"))
            lang = "";

        user.setLocale(new Locale(lang));
        userService.update(user);
    }
}

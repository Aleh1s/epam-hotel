package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.aleh1s.hotelepam.AppContext;
import ua.aleh1s.hotelepam.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.model.entity.UserEntity;
import ua.aleh1s.hotelepam.model.repository.UserRepository;

import java.io.IOException;
import java.util.Locale;
import java.util.Optional;

public class I18NCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Optional<String> langOptional = Optional.ofNullable(request.getParameter("lang"));
        langOptional.ifPresent(s -> setLang(request, s));

        String path = "redirect";
        try {
            response.sendRedirect("/controller?command=profile");
        } catch (IOException e) {
            path = ResourcesManager.getInstance().getValue("path.page.error");
        }

        return path;
    }

    private void setLang(HttpServletRequest request, String lang) {
        Optional<HttpSession> sessionOptional = Optional.ofNullable(request.getSession(false));
        if (sessionOptional.isPresent())
            setLangToExistingSession(sessionOptional.get(), lang);
        else
            createSessionAndSetLang(request, lang);
    }

    private void setLangToExistingSession(HttpSession session, String lang) {
        Optional<Long> userIdOptional = Optional.ofNullable((Long) session.getAttribute("id"));
        userIdOptional.ifPresent(aLong -> updateUserEntityLang(lang, aLong));
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

    private void updateUserEntityLang(String lang, Long userId) {
        UserRepository userRepository = AppContext.getInstance().getUserRepository();
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();
            if (lang.equals("en")) lang = "";
            userEntity.setLocale(new Locale(lang));
            userRepository.update(userEntity);
        }
    }
}

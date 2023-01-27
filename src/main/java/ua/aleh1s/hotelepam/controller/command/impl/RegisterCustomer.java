package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.aleh1s.hotelepam.AppContext;
import ua.aleh1s.hotelepam.controller.Path;
import ua.aleh1s.hotelepam.controller.dto.CustomerDto;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.controller.command.Result;
import ua.aleh1s.hotelepam.model.entity.CustomerEntity;
import ua.aleh1s.hotelepam.model.entity.UserEntity;
import ua.aleh1s.hotelepam.model.entity.UserRole;
import ua.aleh1s.hotelepam.model.repository.CustomerRepository;
import ua.aleh1s.hotelepam.model.repository.UserRepository;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Locale;
import java.util.Optional;

public class RegisterCustomer implements Command {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String zoneOffsetStr = request.getParameter("timezoneOffset");
        ZoneId timezone = ZoneId.ofOffset("UTC", ZoneOffset.of(zoneOffsetStr));

        UserRepository userRepository = AppContext.getInstance().getUserRepository();
        CustomerRepository customerRepository = AppContext.getInstance().getCustomerRepository();

        String errorMessage;
        String page = Path.REGISTRATION_PAGE.getPath();

        if (userRepository.findByEmail(email).isPresent()) {
            errorMessage = String.format("User with email %s already exists", email);
            request.setAttribute("errorMessage", errorMessage);
            return Result.of(page, false);
        }

        Locale defaultLocale = Locale.ENGLISH;
        UserRole userRole = UserRole.CUSTOMER;

        UserEntity user = UserEntity.Builder.newBuilder()
                .email(email)
                .password(password)
                .timezone(timezone)
                .locale(defaultLocale)
                .role(userRole)
                .build();

        Optional<UserEntity> userEntityOptional = userRepository.create(user);

        if (userEntityOptional.isEmpty())
            return handleServerError(request);

        user = userEntityOptional.get();

        Long userId = user.getId();
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phoneNumber = request.getParameter("phoneNumber");

        CustomerEntity customer = CustomerEntity.Builder.newBuilder()
                .firstName(firstName)
                .lastName(lastName)
                .phoneNumber(phoneNumber)
                .userId(userId)
                .build();

        Optional<CustomerEntity> customerEntityOptional = customerRepository.create(customer);
        if (customerEntityOptional.isEmpty())
            return handleServerError(request);

        CustomerDto customerDto = CustomerDto.Builder.newBuilder()
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .timezone(timezone)
                .phoneNumber(phoneNumber)
                .locale(defaultLocale)
                .role(userRole)
                .build();

        HttpSession session = request.getSession();
        session.setAttribute("userDto", customerDto);

        boolean isRedirect;
        try {
            response.sendRedirect("/hello.jsp");
            isRedirect = true;
        } catch (IOException e) {
            page = Path.ERROR_PAGE.name();
            isRedirect = false;
        }

        return Result.of(page, isRedirect);
    }

    private Result handleServerError(HttpServletRequest request) {
        String errorMessage = "Something went wrong on server side";
        request.setAttribute("errorMessage", errorMessage);
        return Result.of(Path.REGISTRATION_PAGE.getPath(), false);
    }
}

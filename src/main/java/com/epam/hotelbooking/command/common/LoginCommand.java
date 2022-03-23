package com.epam.hotelbooking.command.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.hotelbooking.command.util.Command;
import com.epam.hotelbooking.command.util.CommandResult;
import com.epam.hotelbooking.entity.User;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.exception.ServiceException;
import com.epam.hotelbooking.service.UserServiceImpl;
import com.epam.hotelbooking.validation.UserValidator;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LoginCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);
    private static final String MAIN_PAGE = "/index.jsp";
    private final UserServiceImpl userService;
    private final UserValidator userValidator;

    public LoginCommand(UserServiceImpl userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp)
            throws ServiceException, DaoException {
        @SuppressWarnings("unchecked")
        User userForLogging = convertToUser(req.getParameterMap());
        if (!userValidator.isDataForLoginValid(userForLogging)) {
            throw new ServiceException("Data for logging in is invalid");
        }
        Optional<User> optionalUser = userService.login(userForLogging.getLogin(), userForLogging.getPassword());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getIsBlocked()) {
                req.setAttribute("errorMessage", "errorMessage.blocked");
                return CommandResult.forward(MAIN_PAGE);
            }
            setAttributesOnRequest(req, user);
            boolean isAdmin = user.isAdmin();
            return isAdmin
                    ? CommandResult.redirect(req.getContextPath() + "/controller?command=requestsPage&page=1")
                    : CommandResult.redirect(req.getContextPath() + MAIN_PAGE);
        } else {
            req.setAttribute("errorMessage", "errorMessage.login");
            return CommandResult.forward(MAIN_PAGE);
        }
    }

    // TODO should I declare this method as default method in Command interface but
    // with empty body?
    private void setAttributesOnRequest(HttpServletRequest req, User user) {
        req.getSession()
                .setAttribute("userId", user.getId());
        req.getSession()
                .setAttribute("isLoggedIn", true);
        req.getSession()
                .setAttribute("isAdmin", user.isAdmin());
    }

    // TODO should I declare this method as static method in Command?
    private User convertToUser(Map<String, String[]> parameterMap) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> params = new HashMap<>();
        Map<String, String[]> notConvertedMap = parameterMap;
        notConvertedMap.forEach((key, value) -> params.put(key, value[0]));
        LOGGER.debug(params);
        return mapper.convertValue(params, User.class);
    }
}

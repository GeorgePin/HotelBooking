package com.epam.hotelbooking.command.common;

import java.util.HashMap;
import java.util.Map;

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

public class RegistrationCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(RegistrationCommand.class);
    private final UserServiceImpl userService;
    private final UserValidator userValidator;

    public RegistrationCommand(UserServiceImpl userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp)
            throws ServiceException, DaoException {
        @SuppressWarnings("unchecked")
        User user = convertToUser(req.getParameterMap());
        if (!userValidator.isDataForRegistrationValid(user)) {
            throw new ServiceException("Data for registration is invalid");
        }
        if (userService.createUser(user)) {
            return CommandResult.redirect(req.getContextPath() + "/index.jsp");
        } else {
            req.setAttribute("errorMessage", "errorMessage.registration");
            return CommandResult.forward("/registrationPage.jsp");
        }
    }

    private User convertToUser(Map<String, String[]> parameterMap) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> params = new HashMap<>();
        Map<String, String[]> notConvertedMap = parameterMap;
        notConvertedMap.forEach((key, value) -> params.put(key, value[0]));
        LOGGER.debug(params);
        return mapper.convertValue(params, User.class);
    }
}

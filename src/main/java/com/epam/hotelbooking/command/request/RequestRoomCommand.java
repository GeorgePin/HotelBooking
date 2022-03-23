package com.epam.hotelbooking.command.request;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.hotelbooking.command.util.Command;
import com.epam.hotelbooking.command.util.CommandResult;
import com.epam.hotelbooking.entity.Request;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.exception.ServiceException;
import com.epam.hotelbooking.service.RequestServiceImpl;
import com.epam.hotelbooking.validation.RequestValidator;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RequestRoomCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(RequestRoomCommand.class);
    private final RequestServiceImpl requestService;
    private final RequestValidator requestValidator;

    public RequestRoomCommand(RequestServiceImpl requestService, RequestValidator requestValidator) {
        this.requestService = requestService;
        this.requestValidator = requestValidator;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp)
            throws ServiceException, DaoException {
        @SuppressWarnings("unchecked")
        Request request = convertToRequest(req.getParameterMap());
        if (!requestValidator.isDataForRoomRequestingValid(request)) {
            throw new ServiceException("Request data is invalid.");
        }
        requestService.createRoomRequest(request);
        return CommandResult.redirect(req.getContextPath() + "/controller?command=requestsPage&page=1");
    }

    private Request convertToRequest(Map<String, String[]> parameterMap) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> params = new HashMap<>();
        Map<String, String[]> notConvertedMap = parameterMap;
        notConvertedMap.forEach((key, value) -> params.put(key, value[0]));
        LOGGER.debug(params);
        return mapper.convertValue(params, Request.class);
    }
}

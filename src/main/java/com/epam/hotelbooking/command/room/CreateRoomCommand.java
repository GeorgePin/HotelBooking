package com.epam.hotelbooking.command.room;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.hotelbooking.command.util.Command;
import com.epam.hotelbooking.command.util.CommandResult;
import com.epam.hotelbooking.entity.Room;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.exception.ServiceException;
import com.epam.hotelbooking.service.RoomServiceImpl;
import com.epam.hotelbooking.validation.RoomValidator;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CreateRoomCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(CreateRoomCommand.class);
    private final RoomServiceImpl roomService;
    private final RoomValidator roomValidator;

    public CreateRoomCommand(RoomServiceImpl roomService, RoomValidator roomValidator) {
        this.roomService = roomService;
        this.roomValidator = roomValidator;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp)
            throws DaoException, ServiceException {
        @SuppressWarnings("unchecked")
        Room room = convertToRoom(req.getParameterMap());
        if (!roomValidator.isDataForRoomValid(room)) {
            throw new ServiceException("Data for room creating is invalid");
        }
        if (roomService.createRoom(room)) {
            return CommandResult.redirect(req.getContextPath() + "/controller?command=roomsPage&page=1");
        } else {
            req.setAttribute("errorMessage", "errorMessage.room-number");
            return CommandResult.forward("/controller?command=createRoomPage");
        }
    }

    private Room convertToRoom(Map<String, String[]> parameterMap) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> params = new HashMap<>();
        Map<String, String[]> notConvertedMap = parameterMap;
        notConvertedMap.forEach((key, value) -> params.put(key, value[0]));
        LOGGER.debug(params);
        return mapper.convertValue(params, Room.class);
    }
}

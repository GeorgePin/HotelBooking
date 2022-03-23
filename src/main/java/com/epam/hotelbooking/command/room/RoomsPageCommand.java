package com.epam.hotelbooking.command.room;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.hotelbooking.command.util.Command;
import com.epam.hotelbooking.command.util.CommandResult;
import com.epam.hotelbooking.entity.ItemsDto;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.exception.ServiceException;
import com.epam.hotelbooking.service.RoomServiceImpl;

public class RoomsPageCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(RoomsPageCommand.class);
    private final RoomServiceImpl roomService;

    public RoomsPageCommand(RoomServiceImpl roomService) {
        this.roomService = roomService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp)
            throws ServiceException, DaoException {
        int page = Integer.parseInt(req.getParameter("page"));
        ItemsDto transferObject = roomService.getRoomsForSinglePage(page, false);
        LOGGER.debug(transferObject.getAmountOfPages());
        setAttributesOnRequest(req, transferObject);
        return CommandResult.forward("/WEB-INF/view/admin-pages/rooms.jsp");
    }

    private void setAttributesOnRequest(HttpServletRequest req, ItemsDto transferObject) {
        req.setAttribute("roomsList", transferObject.getItems());
        req.setAttribute("numberOfPages", transferObject.getAmountOfPages());
    }
}

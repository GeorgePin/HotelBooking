package com.epam.hotelbooking.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.hotelbooking.entity.ItemsTransferObject;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.exception.ServiceException;
import com.epam.hotelbooking.service.RoomServiceImpl;

public class RoomsPageCommand implements Command {
    private final RoomServiceImpl roomService;

    public RoomsPageCommand(RoomServiceImpl roomService) {
        this.roomService = roomService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp)
            throws ServiceException, DaoException {
        int page = Integer.parseInt(req.getParameter("page"));
        ItemsTransferObject transferObject = roomService.getRoomsForSinglePage(page, false);
        req.setAttribute("roomsList", transferObject.getItems());
        req.setAttribute("numberOfPages", transferObject.getAmountOfPages());
        return CommandResult.forward("/pages/admin-pages/rooms.jsp");
    }
}

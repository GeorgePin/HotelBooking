package com.epam.hotelbooking.command.room;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.hotelbooking.command.Command;
import com.epam.hotelbooking.command.CommandResult;
import com.epam.hotelbooking.entity.RoomPrice;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.exception.ServiceException;
import com.epam.hotelbooking.service.RoomPriceServiceImpl;

public class CreateRoomPageCommand implements Command {
    private final RoomPriceServiceImpl roomPriceService;

    public CreateRoomPageCommand(RoomPriceServiceImpl roomPriceService) {
        this.roomPriceService = roomPriceService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp)
            throws DaoException, ServiceException {
        List<RoomPrice> listOfPrices = roomPriceService.getRoomPrices();
        req.setAttribute("listOfPrices", listOfPrices);
        return CommandResult.forward("/WEB-INF/view/admin-pages/createRoom.jsp");
    }
}
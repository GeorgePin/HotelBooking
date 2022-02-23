package com.epam.hotelbooking.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.hotelbooking.entity.RoomPrice;
import com.epam.hotelbooking.service.RoomPriceServiceImpl;

public class CreateRoomPageCommand implements Command {
    private RoomPriceServiceImpl roomPriceService;

    public CreateRoomPageCommand(RoomPriceServiceImpl roomPriceService) {
        this.roomPriceService = roomPriceService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        List<RoomPrice> listOfPrices = roomPriceService.getRoomPrices();
        req.setAttribute("listOfPrices", listOfPrices);
        return new CommandResult("/createRoom.jsp", false);
    }
}
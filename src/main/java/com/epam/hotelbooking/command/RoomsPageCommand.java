package com.epam.hotelbooking.command;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.hotelbooking.entity.Room;
import com.epam.hotelbooking.entity.RoomsAmount;
import com.epam.hotelbooking.service.AmountOfRoomsServiceImpl;
import com.epam.hotelbooking.service.RoomsPageServiceImpl;

public class RoomsPageCommand implements Command {
    private static final int RECORDS_PER_PAGE = 5;
    private final RoomsPageServiceImpl roomsPageService;
    private final AmountOfRoomsServiceImpl amountOfRoomsService;

    public RoomsPageCommand(RoomsPageServiceImpl roomsPageService, AmountOfRoomsServiceImpl amountOfRoomsService) {
        this.roomsPageService = roomsPageService;
        this.amountOfRoomsService = amountOfRoomsService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int page = Integer.parseInt(req.getParameter("page"));
        List<Room> rooms = roomsPageService.getRooms((page - 1) * RECORDS_PER_PAGE);
        Optional<RoomsAmount> numberOfRooms = amountOfRoomsService.getNumberOfRooms();
        int numberOfPages = (int) Math.ceil(numberOfRooms.get()
                .getAmountOfRooms() * 1.0 / RECORDS_PER_PAGE);
        req.setAttribute("roomsList", rooms);
        req.setAttribute("numberOfPages", numberOfPages);
        return new CommandResult("/rooms.jsp", false);
    }
}

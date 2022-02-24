package com.epam.hotelbooking.command;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.hotelbooking.entity.Request;
import com.epam.hotelbooking.entity.Room;
import com.epam.hotelbooking.entity.RoomPrice;
import com.epam.hotelbooking.service.RequestServiceImpl;
import com.epam.hotelbooking.service.RoomPriceServiceImpl;
import com.epam.hotelbooking.service.RoomServiceImpl;

public class RequestHandlingPageCommand implements Command {

    private RequestServiceImpl requestService;
    private RoomServiceImpl roomService;
    private RoomPriceServiceImpl roomPriceService;

    public RequestHandlingPageCommand(RequestServiceImpl requestService, RoomServiceImpl roomService,
            RoomPriceServiceImpl roomPriceService) {
        this.requestService = requestService;
        this.roomService = roomService;
        this.roomPriceService = roomPriceService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Long requestId = Long.parseLong(req.getParameter("requestId"));
        Optional<Request> requestForHandling = requestService.getRequest(requestId);
        System.out.println(requestForHandling.get()
                .toString());
        List<Room> listOfFreeRooms = roomService.getAllFreeRooms();
        System.out.println(listOfFreeRooms);
        List<RoomPrice> listOfPrices = roomPriceService.getRoomPrices();
        System.out.println(listOfPrices);
        req.setAttribute("request", requestForHandling);
        req.setAttribute("listOfRooms", listOfFreeRooms);
        req.setAttribute("listOfPrices", listOfPrices);
        return new CommandResult("/requestHandling.jsp", false);
    }
}

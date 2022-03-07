package com.epam.hotelbooking.command;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.hotelbooking.entity.ItemsTransferObject;
import com.epam.hotelbooking.entity.Request;
import com.epam.hotelbooking.entity.RoomPrice;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.exception.ServiceException;
import com.epam.hotelbooking.service.RequestServiceImpl;
import com.epam.hotelbooking.service.RoomPriceServiceImpl;
import com.epam.hotelbooking.service.RoomServiceImpl;

public class RequestHandlingPageCommand implements Command {

    private final RequestServiceImpl requestService;
    private final RoomServiceImpl roomService;
    private final RoomPriceServiceImpl roomPriceService;

    public RequestHandlingPageCommand(RequestServiceImpl requestService, RoomServiceImpl roomService,
            RoomPriceServiceImpl roomPriceService) {
        this.requestService = requestService;
        this.roomService = roomService;
        this.roomPriceService = roomPriceService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp)
            throws ServiceException, DaoException {
        Long requestId = Long.parseLong(req.getParameter("requestId"));
        int pageNumber = Integer.parseInt(req.getParameter("page"));
        Optional<Request> requestForHandling = requestService.getRequest(requestId);
        ItemsTransferObject transferObject = roomService.getRoomsForSinglePage(pageNumber, true);
        List<RoomPrice> listOfPrices = roomPriceService.getRoomPrices();
        req.setAttribute("request", requestForHandling);
        req.setAttribute("listOfRooms", transferObject.getItems());
        req.setAttribute("numberOfPages", transferObject.getAmountOfPages());
        req.setAttribute("listOfPrices", listOfPrices);
        return CommandResult.forward("/pages/admin-pages/requestHandling.jsp");
    }
}

package com.epam.hotelbooking.command.request;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.hotelbooking.command.Command;
import com.epam.hotelbooking.command.CommandResult;
import com.epam.hotelbooking.entity.ItemsTransferObject;
import com.epam.hotelbooking.entity.Request;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.exception.ServiceException;
import com.epam.hotelbooking.service.RequestServiceImpl;
import com.epam.hotelbooking.service.RoomServiceImpl;

public class RequestHandlingPageCommand implements Command {

    private final RequestServiceImpl requestService;
    private final RoomServiceImpl roomService;

    public RequestHandlingPageCommand(RequestServiceImpl requestService, RoomServiceImpl roomService) {
        this.requestService = requestService;
        this.roomService = roomService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp)
            throws ServiceException, DaoException {
        Long requestId = Long.parseLong(req.getParameter("requestId"));
        int pageNumber = Integer.parseInt(req.getParameter("page"));
        Optional<Request> requestForHandling = requestService.getRequest(requestId);
        ItemsTransferObject transferObject = roomService.getRoomsForSinglePage(pageNumber, true);
        req.setAttribute("request", requestForHandling.isPresent() ? requestForHandling.get() : Optional.empty());
        req.setAttribute("listOfRooms", transferObject.getItems());
        req.setAttribute("numberOfPages", transferObject.getAmountOfPages());
        return CommandResult.forward("/WEB-INF/view/admin-pages/requestHandling.jsp");
    }
}

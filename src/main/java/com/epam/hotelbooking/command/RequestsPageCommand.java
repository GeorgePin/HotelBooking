package com.epam.hotelbooking.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.hotelbooking.entity.ItemsTransferObject;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.exception.ServiceException;
import com.epam.hotelbooking.service.RequestServiceImpl;

public class RequestsPageCommand implements Command {
    private final RequestServiceImpl requestService;

    public RequestsPageCommand(RequestServiceImpl requestService) {
        this.requestService = requestService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp)
            throws ServiceException, DaoException {
        int pageInt = Integer.parseInt(req.getParameter("page"));
        boolean isAdmin = (Boolean) req.getSession()
                .getAttribute("isAdmin");
        ItemsTransferObject transferObject = requestService.getRequestsForUser(pageInt, isAdmin);
        req.setAttribute("requestsList", transferObject.getItems());
        req.setAttribute("numberOfPages", transferObject.getAmountOfPages());
        return CommandResult.forward("/page/common-pages/requests.jsp");
    }
}

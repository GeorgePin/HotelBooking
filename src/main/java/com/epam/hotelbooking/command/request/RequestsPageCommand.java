package com.epam.hotelbooking.command.request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.hotelbooking.command.util.Command;
import com.epam.hotelbooking.command.util.CommandResult;
import com.epam.hotelbooking.entity.ItemsDto;
import com.epam.hotelbooking.entity.Request;
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
        Long userId = (Long) req.getSession()
                .getAttribute("userId");
        ItemsDto<Request> transferObject = requestService.getRequestsForUser(pageInt, userId, isAdmin);
        req.setAttribute("requestsList", transferObject.getItems());
        req.setAttribute("numberOfPages", transferObject.getAmountOfPages());
        return CommandResult.forward("/WEB-INF/view/common-pages/requests.jsp");
    }

}

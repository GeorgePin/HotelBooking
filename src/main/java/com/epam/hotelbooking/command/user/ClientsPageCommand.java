package com.epam.hotelbooking.command.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.hotelbooking.command.Command;
import com.epam.hotelbooking.command.CommandResult;
import com.epam.hotelbooking.entity.ItemsTransferObject;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.exception.ServiceException;
import com.epam.hotelbooking.service.UserServiceImpl;

public class ClientsPageCommand implements Command {
    private final UserServiceImpl userService;

    public ClientsPageCommand(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp)
            throws ServiceException, DaoException {
        Integer currentPage = Integer.parseInt(req.getParameter("page"));
        ItemsTransferObject transferObject = userService.getAllClients(currentPage);
        req.setAttribute("listOfClients", transferObject.getItems());
        req.setAttribute("numberOfPages", transferObject.getAmountOfPages());
        return CommandResult.forward("/pages/admin-pages/clients.jsp");
    }
}

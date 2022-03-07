package com.epam.hotelbooking.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.hotelbooking.entity.User;
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
        List<User> listOfClients = userService.getAllClients();
        req.setAttribute("listOfClients", listOfClients);
        return CommandResult.forward("/pages/admin-pages/clients.jsp");
    }
}

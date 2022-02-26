package com.epam.hotelbooking.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.hotelbooking.entity.User;
import com.epam.hotelbooking.service.ClientServiceImpl;

public class ClientsPageCommand implements Command {
    private ClientServiceImpl clientService;

    public ClientsPageCommand(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        List<User> listOfClients = clientService.getAllClients();
        req.setAttribute("listOfClients", listOfClients);
        return new CommandResult("/clients.jsp", false);
    }
}

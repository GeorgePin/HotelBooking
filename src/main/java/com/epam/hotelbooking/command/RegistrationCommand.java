package com.epam.hotelbooking.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.hotelbooking.service.CreateUserServiceImpl;

public class RegistrationCommand implements Command {
    private CreateUserServiceImpl сreateUserService;

    public RegistrationCommand(CreateUserServiceImpl сreateUserService) {
        this.сreateUserService = сreateUserService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String name = req.getParameter("userName");
        String surname = req.getParameter("userSurname");
        String login = req.getParameter("userLogin");
        String password = req.getParameter("userPassword");
        System.out.println(login);
        boolean isRequestHandled = сreateUserService.createUser(name, surname, login, password);
        return new CommandResult("/index.jsp", false);
    }
}

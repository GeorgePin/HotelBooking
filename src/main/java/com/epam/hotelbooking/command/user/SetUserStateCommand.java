package com.epam.hotelbooking.command.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.hotelbooking.command.util.Command;
import com.epam.hotelbooking.command.util.CommandResult;
import com.epam.hotelbooking.exception.ServiceException;
import com.epam.hotelbooking.service.UserServiceImpl;

public class SetUserStateCommand implements Command {

    private final UserServiceImpl userService;

    public SetUserStateCommand(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        Long userId = Long.parseLong(req.getParameter("userId"));
        boolean userState = Integer.parseInt(req.getParameter("state")) == 1;
        userService.setUserState(userId, userState);
        return CommandResult.redirect(req.getContextPath() + "/controller?command=clientsPage&page=1");
    }
}
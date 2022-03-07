package com.epam.hotelbooking.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowPageCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String pageName = req.getParameter("page");
        return CommandResult.forward("/" + pageName + ".jsp");
    }
}

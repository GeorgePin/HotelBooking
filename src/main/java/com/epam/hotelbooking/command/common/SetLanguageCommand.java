package com.epam.hotelbooking.command.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.hotelbooking.command.util.Command;
import com.epam.hotelbooking.command.util.CommandResult;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.exception.ServiceException;

public class SetLanguageCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp)
            throws ServiceException, DaoException {
        HttpSession session = req.getSession();
        session.setAttribute("lang", req.getParameter("sessionLocale"));
        return CommandResult.forward(session.getAttribute("currentPageCommand")
                .toString());
    }
}

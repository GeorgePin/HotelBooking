package com.epam.hotelbooking.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.exception.ServiceException;

public class SetLanguageCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp)
            throws ServiceException, DaoException {
        req.getSession()
                .setAttribute("lang", req.getParameter("sessionLocale"));
        return CommandResult.forward(req.getSession()
                .getAttribute("currentPageCommand")
                .toString());
    }
}

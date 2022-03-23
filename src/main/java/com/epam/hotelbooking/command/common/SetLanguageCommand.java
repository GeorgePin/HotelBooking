package com.epam.hotelbooking.command.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.hotelbooking.command.util.Command;
import com.epam.hotelbooking.command.util.CommandResult;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.exception.ServiceException;

public class SetLanguageCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp)
            throws ServiceException, DaoException {
        setAttributesOnRequest(req);
        return CommandResult.forward(req.getSession()
                .getAttribute("currentPageCommand")
                .toString());
    }

    private void setAttributesOnRequest(HttpServletRequest req) {
        req.getSession()
                .setAttribute("lang", req.getParameter("sessionLocale"));
    }
}

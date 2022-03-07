package com.epam.hotelbooking.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.exception.ServiceException;

public interface Command {
    CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws DaoException, ServiceException;
}

package com.epam.hotelbooking.command.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.exception.ServiceException;

/**
 * This is an interface for basic request and response processing.
 * 
 * @author George Papkevich
 * @version 1.0
 * @since 1.0
 */
public interface Command {

    /**
     * This method is responsible for request and response processing. It takes
     * {@code HttpServletRequest} and {@code HttpServletResponse} as parameters and
     * returns {@code CommandResult}.
     * 
     * @param req  request which is given for processing.
     * @param resp response which is given for processing.
     * @return CommandResult object that declares page to which user will be
     *         redirected or forwarded.
     * 
     */
    CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws DaoException, ServiceException;
}

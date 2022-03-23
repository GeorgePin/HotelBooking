package com.epam.hotelbooking;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.hotelbooking.command.util.Command;
import com.epam.hotelbooking.command.util.CommandFactory;
import com.epam.hotelbooking.command.util.CommandResult;

public class Controller extends HttpServlet {
    private static final long serialVersionUID = 4688296068345966226L;
    private static final Logger LOGGER = LogManager.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.debug("Processing started");
        String commandName = req.getParameter("command");
        CommandFactory commandFactory = new CommandFactory();
        Command action = commandFactory.createCommand(commandName);
        try {
            CommandResult result = action.execute(req, resp);
            dispatch(req, resp, result);
        } catch (Exception exception) {
            List<StackTraceElement> stacktraceList = Arrays.asList(exception.getStackTrace());
            List<String> stacktraceStringList = stacktraceList.stream()
                    .map(x -> x.toString() + "\n")
                    .collect(Collectors.toList());
            LOGGER.debug(stacktraceStringList);
            req.setAttribute("errorMessage", exception.getMessage());
            dispatch(req, resp, CommandResult.forward("/errorPage.jsp"));
        }
    }

    private void dispatch(HttpServletRequest req, HttpServletResponse resp, CommandResult result)
            throws ServletException, IOException {
        String page = result.getPage();
        if (!result.isRedirect()) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(req, resp);
        } else {
            resp.sendRedirect(page);
        }
    }
}
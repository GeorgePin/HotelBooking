package com.epam.hotelbooking;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.hotelbooking.command.Command;
import com.epam.hotelbooking.command.CommandFactory;
import com.epam.hotelbooking.command.CommandResult;

public class Controller extends HttpServlet {
    private static final long serialVersionUID = 4688296068345966226L;

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
        String commandName = req.getParameter("command");
        CommandFactory commandFactory = new CommandFactory();
        Command action = commandFactory.createCommand(commandName);
        try {
            CommandResult result = action.execute(req, resp);
            dispatch(req, resp, result);
        } catch (Exception exception) {
//            exception.printStackTrace();
            req.setAttribute("errorMessage", exception.getMessage());
            dispatch(req, resp, CommandResult.forward("/pages/common-pages/errorPage.jsp"));
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
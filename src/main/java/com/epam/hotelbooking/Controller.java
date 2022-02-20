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

//TODO exceptions
//TODO Overall refactoring
//TODO crud
//TODO jsp include for repeating parts
//TODO if admin then only requests, clients and rooms and log out
//TODO multiple pages if a lot of content
//TODO id is not seening to anybody (соответсвенно если нужно передать id то нужно сделать это так
//чтобы его не было видно в браузерной строке
//TODO sure you want to delete data? (warning)
//TODO new user (item) = put (post?) zapros ->post redirect get  (F5 protection)
//TODO row mapper -> constructor
//TODO update methods do in abstractDao
//TODO Сделать фильтр авторизации. Обычный пользователь не может заходить на страницы для админа
//TODO 9 march sobes -_- :'0
public class Controller extends HttpServlet {

    private static final long serialVersionUID = 4688296068345966226L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            process(req, resp);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            process(req, resp);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String commandName = req.getParameter("command");
        CommandFactory commandFactory = new CommandFactory();
        Command action = commandFactory.createCommand(commandName);
        try {
            CommandResult result = action.execute(req, resp);
            dispatch(req, resp, result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
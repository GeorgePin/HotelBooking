package com.epam.hotelbooking.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AdminFilter implements Filter {
    private static final String CLIENTS_PAGE = "/pages/admin-pages/clients\\.jsp";
    private static final String BAN_USER_COMMAND = "/controller\\?command=banUser";
    private static final String UNBLOCK_ROOM_COMMAND = "/controller\\?command=unblockRoom";
    private static final String ROOMS_PAGE_COMMAND = "/controller\\?command=roomsPage";
    private static final String DELETE_ROOM_COMMAND = "/controller\\?command=deleteRoom";
    private static final String CREATE_ROOM_PAGE_COMMAND = "/controller\\?command=createRoomPage";
    private static final String CREATE_ROOM_COMMAND = "/controller\\?command=createRoom";
    private static final String REQUEST_HANDLING_PAGE_COMMAND = "/controller\\?command=requestHandlingPage";
    private static final String REQUEST_HANDLING_COMMAND = "/controller\\?command=requestHandling";
    private static final String ADMIN_PRIVILEGES_NEDDED_PATTERN = "(.+)(" + CLIENTS_PAGE + "|" + BAN_USER_COMMAND + "|"
            + UNBLOCK_ROOM_COMMAND + "|" + ROOMS_PAGE_COMMAND + "|" + DELETE_ROOM_COMMAND + "|"
            + CREATE_ROOM_PAGE_COMMAND + "|" + CREATE_ROOM_COMMAND + "|" + REQUEST_HANDLING_PAGE_COMMAND + "|"
            + REQUEST_HANDLING_COMMAND + ")(.+)";

    public void init(FilterConfig config) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(false);
        if (httpRequest.getRequestURI()
                .endsWith(".css")
                || httpRequest.getRequestURI()
                        .endsWith(".png")
                || httpRequest.getRequestURI()
                        .endsWith(".js")) {
            chain.doFilter(request, response);
            return;
        }
        if (isAdminRightsRequiredForPage(httpRequest) && !((boolean) session.getAttribute("isAdmin"))) {
            httpRequest.setAttribute("errorMessage", "notAdminMsg");
            RequestDispatcher dispatcher = httpRequest.getRequestDispatcher("/pages/common-pages/errorPage.jsp");
            dispatcher.forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    private boolean isAdminRightsRequiredForPage(HttpServletRequest request) {
        String requestUri = request.getQueryString() == null ? request.getRequestURI()
                : request.getRequestURI() + "?" + request.getQueryString();
        return requestUri.matches(ADMIN_PRIVILEGES_NEDDED_PATTERN);
    }

    public void destroy() {
    }
}
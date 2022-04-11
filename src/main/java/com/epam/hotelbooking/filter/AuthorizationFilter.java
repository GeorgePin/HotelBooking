package com.epam.hotelbooking.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AuthorizationFilter implements Filter {

    private static final Logger LOGGER = LogManager.getLogger(AuthorizationFilter.class);
    private static final String IS_ADMIN = "isAdmin";

    private static final List<String> ADMIN_PAGES = Collections
            .unmodifiableList(Arrays.asList("clientsPage", "editRoom", "banUser", "setRoomState", "roomsPage",
                    "deleteRoom", "createRoom", "requestHandling", "WEB-INF/view/admin-pages", "adminHeader"));

    private static final List<String> COMMON_PAGES = Collections.unmodifiableList(
            Arrays.asList("registrationPage", "index.jsp", "register", "login", "logout", "setLanguage&sessionLocale"));

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(true);
        String requestUri = httpRequest.getRequestURI();
        if (session.getAttribute(IS_ADMIN) == null) {
            session.setAttribute(IS_ADMIN, false);
        }
        if (requestUri.endsWith(".css") || requestUri.endsWith(".png") || requestUri.endsWith(".js")) {
            LOGGER.debug("Css, image or js file is passing");
            chain.doFilter(request, response);
            return;
        }
        if (!areSpecificRightsRequiredForPage(httpRequest, COMMON_PAGES)
                && session.getAttribute("userId") == null
                && !(requestUri).matches(httpRequest.getContextPath() + "/")) {
            LOGGER.debug("User is not logged in. Forwarding to the error page");
            httpRequest.setAttribute("errorMessage", "notLoggedInMsg");
            RequestDispatcher dispatcher = httpRequest.getRequestDispatcher("/errorPage.jsp");
            dispatcher.forward(request, response);
        }
        if (areSpecificRightsRequiredForPage(httpRequest, ADMIN_PAGES) && !((boolean) session.getAttribute(IS_ADMIN))) {
            LOGGER.debug("User is not an admin. Forwarding to the error page");
            httpRequest.setAttribute("errorMessage", "notAdminMsg");
            RequestDispatcher dispatcher = httpRequest.getRequestDispatcher("/errorPage.jsp");
            dispatcher.forward(request, response);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

    private boolean areSpecificRightsRequiredForPage(HttpServletRequest request, List<String> pagesList) {
        String requestUri = request.getQueryString() == null
                ? request.getRequestURI()
                : request.getRequestURI() + "?" + request.getQueryString();
        for (String page : pagesList) {
            if (requestUri.contains(page)) {
                return true;
            }
        }
        return false;
    }
}

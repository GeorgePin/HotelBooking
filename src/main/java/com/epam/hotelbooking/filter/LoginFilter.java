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

public class LoginFilter implements Filter {
    private static final String REGISTRATION_PAGE = "/controller\\?command=showPage&page=registrationPage";
    private static final String ALTERNATIVE_LOGIN_PAGE = "/";
    private static final String LOGIN_PAGE = "/index.jsp";
    private static final String REGISTER_COMMAND = "/controller\\?command=register";
    private static final String LOGIN_COMMAND = "/controller\\?command=login";
    private static final String LOGOUT_COMMAND = "/controller\\?command=logout";
    private static final String SET_LOCALE_COMMAND = "/controller\\?command=setLanguage&sessionLocale=";
    private static final String USER_NOT_LOGGED_IN_PAGES_AVAILABLE = "(.+)(" + REGISTRATION_PAGE + "|"
            + ALTERNATIVE_LOGIN_PAGE + "|" + LOGIN_PAGE + "|" + REGISTER_COMMAND + "|" + LOGIN_COMMAND + "|"
            + LOGOUT_COMMAND + "|" + SET_LOCALE_COMMAND + "\\w{2,3}" + ")";

    public void init(FilterConfig config) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(true);
        if (httpRequest.getRequestURI()
                .endsWith(".css")
                || httpRequest.getRequestURI()
                        .endsWith(".png")
                || httpRequest.getRequestURI()
                        .endsWith(".js")) {
            chain.doFilter(request, response);
            return;
        }
        if (session.getAttribute("isAdmin") == null) {
            session.setAttribute("isAdmin", false);
        }
        if (isLoginRequiredForPage(httpRequest) && !((boolean) session.getAttribute("isLoggedIn"))) {
            httpRequest.setAttribute("errorMessage", "notLoggedInMsg");
            RequestDispatcher dispatcher = httpRequest.getRequestDispatcher("/errorPage.jsp");
            dispatcher.forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    private boolean isLoginRequiredForPage(HttpServletRequest request) {
        String requestUri = request.getQueryString() == null ? request.getRequestURI()
                : request.getRequestURI() + "?" + request.getQueryString();
        return !(requestUri.matches(USER_NOT_LOGGED_IN_PAGES_AVAILABLE));
    }

    public void destroy() {
    }
}
package com.epam.hotelbooking.command;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.hotelbooking.entity.Request;
import com.epam.hotelbooking.entity.RequestsAmount;
import com.epam.hotelbooking.service.AmountOfRequestsServiceImpl;
import com.epam.hotelbooking.service.RequestsPageServiceImpl;

public class RequestsPageCommand implements Command {
    private static final int RECORDS_PER_PAGE = 5;
    private final RequestsPageServiceImpl requestsPageService;
    private final AmountOfRequestsServiceImpl amountOfRequestsService;

    public RequestsPageCommand(RequestsPageServiceImpl requestsPageService,
            AmountOfRequestsServiceImpl amountOfRequestsService) {
        this.requestsPageService = requestsPageService;
        this.amountOfRequestsService = amountOfRequestsService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int pageInt = Integer.parseInt(req.getParameter("page"));
        List<Request> requests = requestsPageService.getRequests((pageInt - 1) * RECORDS_PER_PAGE);
        System.out.println(requests);
        Optional<RequestsAmount> numberOfRequests = amountOfRequestsService.getNumberOfRequests();
        int numberOfPages = (int) Math.ceil(numberOfRequests.get()
                .getAmountOfRequests() * 1.0 / RECORDS_PER_PAGE);
        req.setAttribute("requestsList", requests);
        req.setAttribute("numberOfPages", numberOfPages);
        return new CommandResult("/requests.jsp", false);
    }
}

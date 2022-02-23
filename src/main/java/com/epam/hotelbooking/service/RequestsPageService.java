package com.epam.hotelbooking.service;

import java.util.List;

import com.epam.hotelbooking.entity.Request;

public interface RequestsPageService {
    List<Request> getRequests(int startElement) throws Exception;
}

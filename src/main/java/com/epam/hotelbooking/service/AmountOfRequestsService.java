package com.epam.hotelbooking.service;

import java.util.Optional;

import com.epam.hotelbooking.entity.RequestsAmount;

public interface AmountOfRequestsService {
    Optional<RequestsAmount> getNumberOfRequests() throws Exception;
}

package com.epam.hotelbooking.dao;

import java.util.Optional;

import com.epam.hotelbooking.entity.RequestsAmount;

public interface AmountOfRequestsDao {
    Optional<RequestsAmount> getNumberOfRequests() throws Exception;
}

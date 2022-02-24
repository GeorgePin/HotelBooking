package com.epam.hotelbooking.service;

import java.util.Optional;

import com.epam.hotelbooking.entity.Request;

public interface RequestService {
    Optional<Request> getRequest(Long requestId) throws Exception;
}

package com.epam.hotelbooking.dao;

import java.util.Optional;

import com.epam.hotelbooking.entity.Request;

public interface RequestDao {
    Optional<Request> getRequest(Long requestId) throws Exception;
}

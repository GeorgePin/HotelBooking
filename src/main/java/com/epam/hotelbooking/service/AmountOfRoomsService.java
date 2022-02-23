package com.epam.hotelbooking.service;

import java.util.Optional;

import com.epam.hotelbooking.entity.RoomsAmount;

public interface AmountOfRoomsService {
    Optional<RoomsAmount> getNumberOfRooms() throws Exception;
}

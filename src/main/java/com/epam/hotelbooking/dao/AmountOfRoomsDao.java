package com.epam.hotelbooking.dao;

import java.util.Optional;

import com.epam.hotelbooking.entity.RoomsAmount;

public interface AmountOfRoomsDao {
    Optional<RoomsAmount> getNumberOfRooms() throws Exception;
}

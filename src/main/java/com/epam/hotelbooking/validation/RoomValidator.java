package com.epam.hotelbooking.validation;

import com.epam.hotelbooking.entity.Room;

public class RoomValidator {

    private static final String CAPACITY_PATTERN = "[1-9]{1}";
    private static final String ROOM_NUMBER_PATTERN = "\\d{1,3}";
    private static final String ROOM_CLASS_PATTERN = "[a-zA-Z]{3,10}";
    private static final String ROOM_PRICE_ID_PATTERN = "\\d{1,10}";

    public boolean isDataForRoomValid(Room room) {
        Integer roomCapacity = room.getCapacity();
        Integer roomNumber = room.getNumber();
        String roomClass = room.getRoomClass();
        Long roomPriceId = room.getRoomPriceId();
        return roomCapacity != null
                && roomNumber != null
                && roomClass != null
                && roomPriceId != null
                && roomCapacity.toString()
                        .matches(CAPACITY_PATTERN)
                && roomNumber.toString()
                        .matches(ROOM_NUMBER_PATTERN)
                && roomClass.matches(ROOM_CLASS_PATTERN)
                && roomPriceId.toString()
                        .matches(ROOM_PRICE_ID_PATTERN);
    }
}

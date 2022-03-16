package com.epam.hotelbooking.validation;

public class RoomValidator {

    private static final String FROM_ONE_TO_THREE_DIGITS_PATTERN = "\\d{1,3}";

    public boolean isDataForRoomCreatingValid(String roomNumber) {
        return roomNumber.matches(FROM_ONE_TO_THREE_DIGITS_PATTERN);
    }
}

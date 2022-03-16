package com.epam.hotelbooking.validation;

import org.junit.Assert;
import org.junit.Test;

public class RoomValidationTest {
    @Test
    public void testIsDataForRoomCreatingValidShouldReturnTrueWhenRoomNumberDoesMatchPattern() {
        // given
        RoomValidator roomValidator = new RoomValidator();
        String roomNumber = "123";
        // when
        boolean actual = roomValidator.isDataForRoomCreatingValid(roomNumber);
        // then
        Assert.assertTrue(actual);
    }

    @Test
    public void testIsDataForRoomCreatingValidShouldReturnFalseWhenRoomNumberDoesntMatchPattern() {
        // given
        RoomValidator roomValidator = new RoomValidator();
        String roomNumber = "1234";
        // when
        boolean actual = roomValidator.isDataForRoomCreatingValid(roomNumber);
        // then
        Assert.assertFalse(actual);
    }
}

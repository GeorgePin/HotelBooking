package com.epam.hotelbooking.validation;

import org.junit.Assert;
import org.junit.Test;

import com.epam.hotelbooking.entity.Room;
import com.epam.hotelbooking.entity.RoomClass;

public class RoomValidationTest {
    @Test
    public void testIsDataForRoomCreatingValidShouldReturnTrueWhenRoomNumberDoesMatchPattern() {
        // given
        RoomValidator roomValidator = new RoomValidator();
        Integer roomNumber = 124;
        Integer roomCapacity = 4;
        RoomClass roomClass = RoomClass.DELUXE;
        Long roomPriceId = 5L;
        Room room = new Room();
        room.setNumber(roomNumber);
        room.setCapacity(roomCapacity);
        room.setRoomClass(roomClass);
        room.setRoomPriceId(roomPriceId);
        // when
        boolean actual = roomValidator.isDataForRoomValid(room);
        // then
        Assert.assertTrue(actual);
    }

//    @Test
//    public void testIsDataForRoomCreatingValidShouldReturnFalseWhenRoomNumberDoesntMatchPattern() {
//        // given
//        RoomValidator roomValidator = new RoomValidator();
//        String roomNumber = "1234";
//        // when
//        boolean actual = roomValidator.isDataForRoomCreatingValid(roomNumber);
//        // then
//        Assert.assertFalse(actual);
//    }
}

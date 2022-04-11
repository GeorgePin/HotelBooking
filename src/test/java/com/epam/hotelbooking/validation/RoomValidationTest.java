package com.epam.hotelbooking.validation;

import org.junit.Assert;
import org.junit.Test;

import com.epam.hotelbooking.entity.Room;

public class RoomValidationTest {

    private static final Integer VALID_ROOM_CAPACITY = 3;
    private static final Integer VALID_ROOM_NUMBER = 123;
    private static final String VALID_ROOM_CLASS = "standart";
    private static final Long VALID_ROOM_PRICE_ID = 13L;

    private static final Integer INVALID_ROOM_CAPACITY = 33;
    private static final Integer INVALID_ROOM_NUMBER = 2132;
    private static final String INVALID_ROOM_CLASS = "DELUXE";
    private static final Long INVALID_ROOM_PRICE_ID = 133111333333L;

    private static final String VALID_ROOM_NUMBER_TEXT = "321";
    private static final String INVALID_ROOM_NUMBER_TEXT = "032";

    @Test
    public void testIsDataForRoomValidShouldReturnTrueWhenAllDataIsValid() {
        // given
        RoomValidator roomValidator = new RoomValidator();
        Room room = new Room.RoomBuilder().withCapacity(VALID_ROOM_CAPACITY)
                .withNumber(VALID_ROOM_NUMBER)
                .withRoomClass(VALID_ROOM_CLASS)
                .withRoomPriceId(VALID_ROOM_PRICE_ID)
                .build();
        // when
        boolean actual = roomValidator.isDataForRoomValid(room);
        // then
        Assert.assertTrue(actual);
    }

    @Test
    public void testIsDataForRoomValidShouldReturnFalseWhenCapacityIsNull() {
        // given
        RoomValidator roomValidator = new RoomValidator();
        Room room = new Room.RoomBuilder().withCapacity(null)
                .withNumber(VALID_ROOM_NUMBER)
                .withRoomClass(VALID_ROOM_CLASS)
                .withRoomPriceId(VALID_ROOM_PRICE_ID)
                .build();
        // when
        boolean actual = roomValidator.isDataForRoomValid(room);
        // then
        Assert.assertFalse(actual);
    }

    @Test
    public void testIsDataForRoomValidShouldReturnFalseWhenRoomNumberIsNull() {
        // given
        RoomValidator roomValidator = new RoomValidator();
        Room room = new Room.RoomBuilder().withCapacity(VALID_ROOM_CAPACITY)
                .withNumber(null)
                .withRoomClass(VALID_ROOM_CLASS)
                .withRoomPriceId(VALID_ROOM_PRICE_ID)
                .build();
        // when
        boolean actual = roomValidator.isDataForRoomValid(room);
        // then
        Assert.assertFalse(actual);
    }

    @Test
    public void testIsDataForRoomValidShouldReturnFalseWhenRoomClassIsNull() {
        // given
        RoomValidator roomValidator = new RoomValidator();
        Room room = new Room.RoomBuilder().withCapacity(VALID_ROOM_CAPACITY)
                .withNumber(VALID_ROOM_NUMBER)
                .withRoomClass(null)
                .withRoomPriceId(VALID_ROOM_PRICE_ID)
                .build();
        // when
        boolean actual = roomValidator.isDataForRoomValid(room);
        // then
        Assert.assertFalse(actual);
    }

    @Test
    public void testIsDataForRoomValidShouldReturnFalseWhenRoomPriceIdIsNull() {
        // given
        RoomValidator roomValidator = new RoomValidator();
        Room room = new Room.RoomBuilder().withCapacity(VALID_ROOM_CAPACITY)
                .withNumber(VALID_ROOM_NUMBER)
                .withRoomClass(VALID_ROOM_CLASS)
                .withRoomPriceId(null)
                .build();
        // when
        boolean actual = roomValidator.isDataForRoomValid(room);
        // then
        Assert.assertFalse(actual);
    }

    @Test
    public void testIsDataForRoomValidShouldReturnFalseWhenCapacityDoesntMatchPattern() {
        // given
        RoomValidator roomValidator = new RoomValidator();
        Room room = new Room.RoomBuilder().withCapacity(INVALID_ROOM_CAPACITY)
                .withNumber(VALID_ROOM_NUMBER)
                .withRoomClass(VALID_ROOM_CLASS)
                .withRoomPriceId(VALID_ROOM_PRICE_ID)
                .build();
        // when
        boolean actual = roomValidator.isDataForRoomValid(room);
        // then
        Assert.assertFalse(actual);
    }

    @Test
    public void testIsDataForRoomValidShouldReturnFalseWhenNumberDoesntMatchPattern() {
        // given
        RoomValidator roomValidator = new RoomValidator();
        Room room = new Room.RoomBuilder().withCapacity(VALID_ROOM_CAPACITY)
                .withNumber(INVALID_ROOM_NUMBER)
                .withRoomClass(VALID_ROOM_CLASS)
                .withRoomPriceId(VALID_ROOM_PRICE_ID)
                .build();
        // when
        boolean actual = roomValidator.isDataForRoomValid(room);
        // then
        Assert.assertFalse(actual);
    }

    @Test
    public void testIsDataForRoomValidShouldReturnFalseWhenRoomClassDoesntMatchPattern() {
        // given
        RoomValidator roomValidator = new RoomValidator();
        Room room = new Room.RoomBuilder().withCapacity(VALID_ROOM_CAPACITY)
                .withNumber(VALID_ROOM_NUMBER)
                .withRoomClass(INVALID_ROOM_CLASS)
                .withRoomPriceId(VALID_ROOM_PRICE_ID)
                .build();
        // when
        boolean actual = roomValidator.isDataForRoomValid(room);
        // then
        Assert.assertFalse(actual);
    }

    @Test
    public void testIsDataForRoomValidShouldReturnFalseWhenRoomPriceIdDoesntMatchPattern() {
        // given
        RoomValidator roomValidator = new RoomValidator();
        Room room = new Room.RoomBuilder().withCapacity(VALID_ROOM_CAPACITY)
                .withNumber(VALID_ROOM_NUMBER)
                .withRoomClass(VALID_ROOM_CLASS)
                .withRoomPriceId(INVALID_ROOM_PRICE_ID)
                .build();
        // when
        boolean actual = roomValidator.isDataForRoomValid(room);
        // then
        Assert.assertFalse(actual);
    }

    @Test
    public void testIsNumberOfRoomStartsWithZeroShouldReturnFalseWhenRoomNumberIsValid() {
        // given
        RoomValidator roomValidator = new RoomValidator();
        // when
        boolean actual = roomValidator.isNumberOfRoomStartsWithZero(VALID_ROOM_NUMBER_TEXT);
        // then
        Assert.assertFalse(actual);
    }

    @Test
    public void testIsNumberOfRoomStartsWithZeroShouldReturnTrueWhenRoomNumberIsInvalid() {
        // given
        RoomValidator roomValidator = new RoomValidator();
        // when
        boolean actual = roomValidator.isNumberOfRoomStartsWithZero(INVALID_ROOM_NUMBER_TEXT);
        // then
        Assert.assertTrue(actual);
    }
}

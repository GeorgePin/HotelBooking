package com.epam.hotelbooking.validation;

import java.sql.Date;

import org.junit.Assert;
import org.junit.Test;

import com.epam.hotelbooking.entity.Request;

public class RequestValidatorTest {

    private static final Integer VALID_ROOM_CAPACITY = 1;
    private static final String VALID_ROOM_CLASS = "premium";
    private static final Date NOW_DATE = new Date(System.currentTimeMillis());
    private static final Date VALID_START_DATE = new Date(NOW_DATE.getTime() + 392222991L);
    private static final Date VALID_END_DATE = new Date(VALID_START_DATE.getTime() + 492222991L);

    private static final Integer INVALID_ROOM_CAPACITY = 13;
    private static final String INVALID_ROOM_CLASS = "pre?/mium";
    private static final Date INVALID_START_DATE = new Date(NOW_DATE.getTime() - 392222991L);
    private static final Date INVALID_END_DATE = new Date(VALID_START_DATE.getTime() - 392222991L);

    @Test
    public void testIsDataForRoomRequestingValidShouldReturnFalseWhenCapacityIsNull() {
        // given
        RequestValidator requestValidator = new RequestValidator();
        Request request = new Request.RequestBuilder().withRoomCapacity(null)
                .withRoomClass(VALID_ROOM_CLASS)
                .withStartDate(VALID_START_DATE)
                .withEndDate(VALID_END_DATE)
                .build();
        // when
        boolean actual = requestValidator.isDataForRoomRequestingValid(request);
        // then
        Assert.assertFalse(actual);
    }

    @Test
    public void testIsDataForRoomRequestingValidShouldReturnFalseWhenRoomClassIsNull() {
        // given
        RequestValidator requestValidator = new RequestValidator();
        Request request = new Request.RequestBuilder().withRoomCapacity(VALID_ROOM_CAPACITY)
                .withRoomClass(null)
                .withStartDate(VALID_START_DATE)
                .withEndDate(VALID_END_DATE)
                .build();
        // when
        boolean actual = requestValidator.isDataForRoomRequestingValid(request);
        // then
        Assert.assertFalse(actual);
    }

    @Test
    public void testIsDataForRoomRequestingValidShouldReturnFalseWhenStartDateIsNull() {
        // given
        RequestValidator requestValidator = new RequestValidator();
        Request request = new Request.RequestBuilder().withRoomCapacity(VALID_ROOM_CAPACITY)
                .withRoomClass(VALID_ROOM_CLASS)
                .withStartDate(null)
                .withEndDate(VALID_END_DATE)
                .build();
        // when
        boolean actual = requestValidator.isDataForRoomRequestingValid(request);
        // then
        Assert.assertFalse(actual);
    }

    @Test
    public void testIsDataForRoomRequestingValidShouldReturnFalseWhenRoomCapacityIsInvalid() {
        // given
        RequestValidator requestValidator = new RequestValidator();
        Request request = new Request.RequestBuilder().withRoomCapacity(INVALID_ROOM_CAPACITY)
                .withRoomClass(VALID_ROOM_CLASS)
                .withStartDate(VALID_START_DATE)
                .withEndDate(VALID_END_DATE)
                .build();
        // when
        boolean actual = requestValidator.isDataForRoomRequestingValid(request);
        // then
        Assert.assertFalse(actual);
    }

    @Test
    public void testIsDataForRoomRequestingValidShouldReturnFalseWhenRoomClassIsInvalid() {
        // given
        RequestValidator requestValidator = new RequestValidator();
        Request request = new Request.RequestBuilder().withRoomCapacity(VALID_ROOM_CAPACITY)
                .withRoomClass(INVALID_ROOM_CLASS)
                .withStartDate(VALID_START_DATE)
                .withEndDate(VALID_END_DATE)
                .build();
        // when
        boolean actual = requestValidator.isDataForRoomRequestingValid(request);
        // then
        Assert.assertFalse(actual);
    }

    @Test
    public void testIsDataForRoomRequestingValidShouldReturnFalseWhenStartDateIsNotAfterNowDate() {
        // given
        RequestValidator requestValidator = new RequestValidator();
        Request request = new Request.RequestBuilder().withRoomCapacity(VALID_ROOM_CAPACITY)
                .withRoomClass(VALID_ROOM_CLASS)
                .withStartDate(INVALID_START_DATE)
                .withEndDate(VALID_END_DATE)
                .build();
        // when
        boolean actual = requestValidator.isDataForRoomRequestingValid(request);
        // then
        Assert.assertFalse(actual);
    }

    @Test
    public void testIsDataForRoomRequestingValidShouldReturnTrueWhenEndDateIsNull() {
        // given
        RequestValidator requestValidator = new RequestValidator();
        Request request = new Request.RequestBuilder().withRoomCapacity(VALID_ROOM_CAPACITY)
                .withRoomClass(VALID_ROOM_CLASS)
                .withStartDate(VALID_START_DATE)
                .withEndDate(null)
                .build();
        // when
        boolean actual = requestValidator.isDataForRoomRequestingValid(request);
        // then
        Assert.assertTrue(actual);
    }

    @Test
    public void testIsDataForRoomRequestingValidShouldReturnFalseWhenEndDateIsAfterStartDate() {
        // given
        RequestValidator requestValidator = new RequestValidator();
        Request request = new Request.RequestBuilder().withRoomCapacity(VALID_ROOM_CAPACITY)
                .withRoomClass(VALID_ROOM_CLASS)
                .withStartDate(VALID_START_DATE)
                .withEndDate(INVALID_END_DATE)
                .build();
        // when
        boolean actual = requestValidator.isDataForRoomRequestingValid(request);
        // then
        Assert.assertFalse(actual);
    }

    @Test
    public void testIsDataForRoomRequestingValidShouldReturnTrueWhenAllDataIsValid() {
        // given
        RequestValidator requestValidator = new RequestValidator();
        Request request = new Request.RequestBuilder().withRoomCapacity(VALID_ROOM_CAPACITY)
                .withRoomClass(VALID_ROOM_CLASS)
                .withStartDate(VALID_START_DATE)
                .withEndDate(VALID_END_DATE)
                .build();
        // when
        boolean actual = requestValidator.isDataForRoomRequestingValid(request);
        // then
        Assert.assertTrue(actual);
    }
}

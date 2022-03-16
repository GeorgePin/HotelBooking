package com.epam.hotelbooking.validation;

import org.junit.Assert;
import org.junit.Test;

public class RequestValidatorTest {

    @Test
    public void testIsDataForRoomRequestingValidShouldReturnTrueWhenStartDateBeforeEndDate() {
        // given
        RequestValidator requestValidator = new RequestValidator();
        String startDate = "2022-03-11";
        String endDate = "2022-03-17";
        // when
        boolean actual = requestValidator.isDataForRoomRequestingValid(startDate, endDate);
        // then
        Assert.assertTrue(actual);
    }

    @Test
    public void testIsDataForRoomRequestingValidShouldReturnFalseWhenStartDateAfterEndDate() {
        // given
        RequestValidator requestValidator = new RequestValidator();
        String startDate = "2022-03-18";
        String endDate = "2022-03-17";
        // when
        boolean actual = requestValidator.isDataForRoomRequestingValid(startDate, endDate);
        // then
        Assert.assertFalse(actual);
    }

    @Test
    public void testIsDataForRoomRequestingValidShouldReturnTrueWhenDatesAreEqual() {
        // given
        RequestValidator requestValidator = new RequestValidator();
        String startDate = "2022-03-11";
        String endDate = "2022-03-11";
        // when
        boolean actual = requestValidator.isDataForRoomRequestingValid(startDate, endDate);
        // then
        Assert.assertTrue(actual);
    }

    @Test
    public void testIsDataForRoomRequestingValidShouldReturnTrueWhenEndDateIsNull() {
        // given
        RequestValidator requestValidator = new RequestValidator();
        String startDate = "2022-03-15";
        String endDate = "";
        // when
        boolean actual = requestValidator.isDataForRoomRequestingValid(startDate, endDate);
        // then
        Assert.assertTrue(actual);
    }

    @Test
    public void testIsDataForRoomRequestingValidShouldReturnFalseWhenStartDateDoesntMatchPattern() {
        // given
        RequestValidator requestValidator = new RequestValidator();
        String startDate = "2022-d03-31";
        String endDate = "2022-03-05";
        // when
        boolean actual = requestValidator.isDataForRoomRequestingValid(startDate, endDate);
        // then
        Assert.assertFalse(actual);
    }

    @Test
    public void testIsDataForRoomRequestingValidShouldReturnFalseWhenEndDateDoesntMatchPattern() {
        // given
        RequestValidator requestValidator = new RequestValidator();
        String startDate = "2022-03-05";
        String endDate = "2022-03-31c";
        // when
        boolean actual = requestValidator.isDataForRoomRequestingValid(startDate, endDate);
        // then
        Assert.assertFalse(actual);
    }
}

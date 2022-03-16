package com.epam.hotelbooking.validation;

import java.sql.Date;

public class RequestValidator {

    private static final String DATE_PATTERN = "[0-9]{4}-[0-9]{2}-[0-9]{2}";

    public boolean isDataForRoomRequestingValid(String startDateText, String endDateText) {
        if (!startDateText.matches(DATE_PATTERN)) {
            return false;
        }
        if (!endDateText.isEmpty()) {
            if (!endDateText.matches(DATE_PATTERN)) {
                return false;
            }
        }
        Date startDate = Date.valueOf(startDateText);
        Date endDate = endDateText.isEmpty() ? null : Date.valueOf(endDateText);
        return endDate == null || startDate.before(endDate) || startDate.equals(endDate);
    }
}

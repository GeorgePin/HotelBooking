package com.epam.hotelbooking.validation;

import java.sql.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.hotelbooking.entity.Request;

public class RequestValidator {

    private static final Logger LOGGER = LogManager.getLogger(RequestValidator.class);
    private static final String DATE_PATTERN = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
    private static final String CAPACITY_PATTERN = "[1-9]{1}";
    private static final String ROOM_CLASS_PATTERN = "[a-z]{3,10}";

    public boolean isDataForRoomRequestingValid(Request request) {
        LOGGER.debug(request);
        Date nowDate = new Date(System.currentTimeMillis());
        Integer capacity = request.getRoomCapacity();
        String roomClass = request.getRoomClass();
        Date startDate = request.getStartDate();
        Date endDate = request.getEndDate();
        if (capacity == null || roomClass == null || startDate == null) {
            return false;
        }
        if (capacity.toString()
                .matches(CAPACITY_PATTERN)
                && roomClass.matches(ROOM_CLASS_PATTERN)
                && startDate.toString()
                        .matches(DATE_PATTERN)
                && startDate.after(nowDate)) {
            if (endDate == null
                    || endDate.toString()
                            .isEmpty()) {
                return true;
            } else {
                return endDate.after(startDate);
            }
        } else {
            return false;
        }
    }
}

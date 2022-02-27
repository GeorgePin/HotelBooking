package com.epam.hotelbooking.entity;

import java.io.Serializable;
import java.sql.Date;

public class Request implements Identifable, Serializable {

    private static final long serialVersionUID = 7123168168412409612L;
    private final Long id;
    private Long roomId;
    private final Long userId;
    private final Date startDate;
    private final Date endDate;
    private final int roomCapacity;
    private final RoomClass roomClass;
    private boolean isApproved;

    public Request(Long id, Long roomId, Long userId, Date startDate, Date endDate, int roomCapacity,
            RoomClass roomClass, boolean isApproved) {
        this.id = id;
        this.roomId = roomId;
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.roomCapacity = roomCapacity;
        this.roomClass = roomClass;
        this.isApproved = isApproved;
    }

    public Long getRoomId() {
        return roomId;
    }

    public Long getUserId() {
        return userId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getRoomCapacity() {
        return roomCapacity;
    }

    public RoomClass getRoomClass() {
        return roomClass;
    }

    public boolean getisApproved() {
        return isApproved;
    }

    @Override
    public Long getId() {
        return id;
    }

}

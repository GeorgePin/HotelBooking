package com.epam.hotelbooking.entity;

import java.sql.Date;

public class Request implements Identifable {
    private Long id;
    private Long roomId;
    private Long userId;
    private Date startDate;
    private Date endDate;
    private int roomCapacity;
    private RoomClass roomClass;
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

    public String getRoomClass() {
        return roomClass.toString();
    }

    public boolean getisApproved() {
        return isApproved;
    }

    @Override
    public Long getId() {
        return id;
    }

}

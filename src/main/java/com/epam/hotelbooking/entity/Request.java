package com.epam.hotelbooking.entity;

import java.math.BigDecimal;
import java.sql.Date;

public class Request extends Entity {

    private static final long serialVersionUID = 7123168168412409612L;
    private Long id;
    private Long roomId;
    private Long userId;
    private Date startDate;
    private Date endDate;
    private int roomCapacity;
    private RoomClass roomClass;
    private boolean isApproved;
    private BigDecimal price;

    public Request(Long id, Long roomId, Long userId, Date startDate, Date endDate, int roomCapacity,
            RoomClass roomClass, boolean isApproved, BigDecimal price) {
        this.id = id;
        this.roomId = roomId;
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.roomCapacity = roomCapacity;
        this.roomClass = roomClass;
        this.isApproved = isApproved;
        this.price = price;
    }

    public Request(Long userId, Date startDate, Date endDate, int roomCapacity, RoomClass roomClass) {
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.roomCapacity = roomCapacity;
        this.roomClass = roomClass;
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

    public BigDecimal getPrice() {
        return price;
    }

}

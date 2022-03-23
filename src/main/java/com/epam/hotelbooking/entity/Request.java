package com.epam.hotelbooking.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Request extends Entity {

    private static final long serialVersionUID = 7123168168412409612L;
    private Long id;
    private Long roomId;
    private Long userId;
    private Date startDate;
    private Date endDate;
    private Integer roomCapacity;
    private RoomClass roomClass;
    private boolean isApproved;
    private BigDecimal price;

    public Request() {
    }

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

    public Request(Long id, Long userId, Date startDate, Date endDate, int roomCapacity, RoomClass roomClass) {
        this.id = id;
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.roomCapacity = roomCapacity;
        this.roomClass = roomClass;
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

    public Integer getRoomCapacity() {
        return roomCapacity;
    }

    public String getRoomClass() {
        return roomClass.toString()
                .toLowerCase();
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

    @Override
    public int hashCode() {
        return Objects.hash(endDate, id, isApproved, price, roomCapacity, roomClass, roomId, startDate, userId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Request other = (Request) obj;
        return Objects.equals(endDate, other.endDate)
                && Objects.equals(id, other.id)
                && isApproved == other.isApproved
                && Objects.equals(price, other.price)
                && roomCapacity == other.roomCapacity
                && roomClass == other.roomClass
                && Objects.equals(roomId, other.roomId)
                && Objects.equals(startDate, other.startDate)
                && Objects.equals(userId, other.userId);
    }

    @Override
    public String toString() {
        return "Request [id=" + id + ", roomId=" + roomId + ", userId=" + userId + ", startDate=" + startDate
                + ", endDate=" + endDate + ", roomCapacity=" + roomCapacity + ", roomClass=" + roomClass
                + ", isApproved=" + isApproved + ", price=" + price + "]";
    }

}

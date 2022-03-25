package com.epam.hotelbooking.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

public class Request extends Entity {

    public static final String TABLE_NAME = "reservation";
    private static final long serialVersionUID = 7123168168412409612L;
    private final Long id;
    private final Long roomId;
    private final Long userId;
    private final Date startDate;
    private final Date endDate;
    private final Integer roomCapacity;
    private final String roomClass;
    private final Boolean isApproved;
    private final BigDecimal price;

    public Request(RequestBuilder requestBuilder) {
        this.id = requestBuilder.id;
        this.roomId = requestBuilder.roomId;
        this.userId = requestBuilder.userId;
        this.startDate = requestBuilder.startDate;
        this.endDate = requestBuilder.endDate;
        this.roomCapacity = requestBuilder.roomCapacity;
        this.roomClass = requestBuilder.roomClass;
        this.isApproved = requestBuilder.isApproved;
        this.price = requestBuilder.price;
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
        return roomClass;
    }

    public Boolean getIsApproved() {
        return isApproved;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(endDate, id, isApproved, price, roomCapacity, roomClass, roomId, startDate, userId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
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

    public static class RequestBuilder implements Builder<Request> {
        private Long id;
        private Long roomId;
        private Long userId;
        private Date startDate;
        private Date endDate;
        private Integer roomCapacity;
        private String roomClass;
        private Boolean isApproved;
        private BigDecimal price;

        public RequestBuilder withId(final Long id) {
            this.id = id;
            return this;
        }

        public RequestBuilder withRoomId(final Long roomId) {
            this.roomId = roomId;
            return this;
        }

        public RequestBuilder withUserId(final Long userId) {
            this.userId = userId;
            return this;
        }

        public RequestBuilder withStartDate(final Date startDate) {
            this.startDate = startDate;
            return this;
        }
        public RequestBuilder withEndDate(final Date endDate) {
            this.endDate = endDate;
            return this;
        }

        public RequestBuilder withRoomCapacity(final Integer roomCapacity) {
            this.roomCapacity = roomCapacity;
            return this;
        }

        public RequestBuilder withRoomClass(final String roomClass) {
            this.roomClass = roomClass;
            return this;
        }

        public RequestBuilder withIsApproved(final Boolean isApproved) {
            this.isApproved = isApproved;
            return this;
        }

        public RequestBuilder withPrice(final BigDecimal price) {
            this.price = price;
            return this;
        }

        @Override
        public Request build() {
            Request request = new Request(this);
            reset();
            return request;
        }

        @Override
        public void reset() {
            this.id = null;
            this.roomId = null;
            this.userId = null;
            this.startDate = null;
            this.endDate = null;
            this.roomCapacity = null;
            this.roomClass = null;
            this.isApproved = null;
            this.price = null;
        }
    }
}

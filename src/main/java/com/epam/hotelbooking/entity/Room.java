package com.epam.hotelbooking.entity;

import java.util.Objects;

public class Room extends Entity {

    public static final String TABLE_NAME = "room";
    private static final long serialVersionUID = -1343084847929251069L;
    private final Long id;
    private final Integer capacity;
    private final String roomClass;
    private final Integer number;
    private final Boolean isBlocked;
    private final RoomPrice roomPrice;
    private final Long roomPriceId;

    public Room(RoomBuilder roomBuilder) {
        this.id = roomBuilder.id;
        this.capacity = roomBuilder.capacity;
        this.roomClass = roomBuilder.roomClass;
        this.number = roomBuilder.number;
        this.isBlocked = roomBuilder.isBlocked;
        this.roomPrice = roomBuilder.roomPrice;
        this.roomPriceId = roomBuilder.roomPriceId;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public String getRoomClass() {
        return roomClass;
    }

    public Integer getNumber() {
        return number;
    }

    public Boolean getIsBlocked() {
        return isBlocked;
    }

    public RoomPrice getRoomPrice() {
        return roomPrice;
    }

    public Long getRoomPriceId() {
        return roomPriceId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(capacity, id, isBlocked, number, roomPrice, roomPriceId, roomClass);
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
        Room other = (Room) obj;
        return capacity == other.capacity
                && Objects.equals(id, other.id)
                && isBlocked == other.isBlocked
                && number == other.number
                && Objects.equals(roomPrice, other.roomPrice)
                && Objects.equals(roomPriceId, other.roomPriceId)
                && roomClass == other.roomClass;
    }

    @Override
    public String toString() {
        return "Room [id=" + id + ", capacity=" + capacity + ", roomClass=" + roomClass + ", number=" + number
                + ", isBlocked=" + isBlocked + ", roomPrice=" + roomPrice + ", roomPriceId=" + roomPriceId + "]";
    }

    public static class RoomBuilder implements Builder<Room> {
        private Long id;
        private Integer capacity;
        private String roomClass;
        private Integer number;
        private Boolean isBlocked;
        private RoomPrice roomPrice;
        private Long roomPriceId;

        public RoomBuilder withId(final Long id) {
            this.id = id;
            return this;
        }

        public RoomBuilder withCapacity(final Integer capacity) {
            this.capacity = capacity;
            return this;
        }

        public RoomBuilder withRoomClass(final String roomClass) {
            this.roomClass = roomClass;
            return this;
        }

        public RoomBuilder withNumber(final Integer number) {
            this.number = number;
            return this;
        }

        public RoomBuilder withIsBlocked(final Boolean isBlocked) {
            this.isBlocked = isBlocked;
            return this;
        }

        public RoomBuilder withRoomPrice(final RoomPrice roomPrice) {
            this.roomPrice = roomPrice;
            return this;
        }

        public RoomBuilder withRoomPriceId(final Long roomPriceId) {
            this.roomPriceId = roomPriceId;
            return this;
        }

        public Room build() {
            Room room = new Room(this);
            reset();
            return room;
        }

        public void reset() {
            this.id = null;
            this.capacity = null;
            this.roomClass = null;
            this.number = null;
            this.isBlocked = null;
            this.roomPrice = null;
            this.roomPriceId = null;
        }
    }
}

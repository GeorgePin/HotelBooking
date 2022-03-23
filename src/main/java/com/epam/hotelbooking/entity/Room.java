package com.epam.hotelbooking.entity;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Room extends Entity {

    private static final long serialVersionUID = -1343084847929251069L;
    private Long id;
    private Integer capacity;
    private RoomClass roomClass;
    private int number;
    private boolean isBlocked;
    private RoomPrice roomPrice;
    private Long roomPriceId;

    public Room() {
    }

    public Room(Long id, int capacity, RoomClass roomClass, int number, boolean isBlocked, RoomPrice roomPrice) {
        this.id = id;
        this.capacity = capacity;
        this.roomClass = roomClass;
        this.number = number;
        this.isBlocked = isBlocked;
        this.roomPrice = roomPrice;
    }

    public Room(int capacity, RoomClass roomClass, int number, boolean isBlocked, RoomPrice roomPrice) {
        this.capacity = capacity;
        this.roomClass = roomClass;
        this.number = number;
        this.isBlocked = isBlocked;
        this.roomPrice = roomPrice;
    }

    public Room(int capacity, RoomClass roomClass, int number, Long roomPriceId) {
        this.capacity = capacity;
        this.roomClass = roomClass;
        this.number = number;
        this.roomPriceId = roomPriceId;
    }

    public Room(Long id, int capacity, RoomClass roomClass, int number, Long roomPriceId) {
        this.id = id;
        this.capacity = capacity;
        this.roomClass = roomClass;
        this.number = number;
        this.roomPriceId = roomPriceId;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public String getRoomClass() {
        return roomClass.toString()
                .toLowerCase();
    }

    public int getNumber() {
        return number;
    }

    public boolean getIsBlocked() {
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
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
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

}

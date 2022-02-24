package com.epam.hotelbooking.entity;

public class Room implements Identifable {
    private Long id;
    private int capacity;
    private RoomClass type;
    private int number;
    private boolean isBlocked;
    private Long roomPriceId;

    public Room(Long id, int capacity, RoomClass type, int number, boolean isBlocked, Long roomPriceId) {
        this.id = id;
        this.capacity = capacity;
        this.type = type;
        this.number = number;
        this.isBlocked = isBlocked;
        this.roomPriceId = roomPriceId;
    }

    public Room(int capacity, RoomClass type, int number, boolean isBlocked, Long roomPriceId) {
        this.capacity = capacity;
        this.type = type;
        this.number = number;
        this.isBlocked = isBlocked;
        this.roomPriceId = roomPriceId;
    }

    @Override
    public Long getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public RoomClass getType() {
        return type;
    }

    public int getNumber() {
        return number;
    }

    public boolean getIsBlocked() {
        return isBlocked;
    }

    public Long getRoomPriceId() {
        return roomPriceId;
    }

    @Override
    public String toString() {
        return "Room [roomPriceId=" + roomPriceId + "]";
    }

}

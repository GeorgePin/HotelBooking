package com.epam.hotelbooking.entity;

import java.io.Serializable;

public class Room implements Identifable, Serializable {
    private static final long serialVersionUID = -1343084847929251069L;
    private Long id;
    private int capacity;
    private RoomClass type;
    private int number;
    private boolean isBlocked;
    private RoomPrice roomPrice;

    public Room(Long id, int capacity, RoomClass type, int number, boolean isBlocked, RoomPrice roomPrice) {
        this.id = id;
        this.capacity = capacity;
        this.type = type;
        this.number = number;
        this.isBlocked = isBlocked;
        this.roomPrice = roomPrice;
    }

    public Room(int capacity, RoomClass type, int number, boolean isBlocked, RoomPrice roomPrice) {
        this.capacity = capacity;
        this.type = type;
        this.number = number;
        this.isBlocked = isBlocked;
        this.roomPrice = roomPrice;
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

    public RoomPrice getRoomPrice() {
        return roomPrice;
    }

}

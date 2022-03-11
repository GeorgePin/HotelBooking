package com.epam.hotelbooking.entity;

public class Room extends Entity {

    private static final long serialVersionUID = -1343084847929251069L;
    private Long id;
    private int capacity;
    private RoomClass type;
    private int number;
    private boolean isBlocked;
    private RoomPrice roomPrice;
    private Long roomPriceId;

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

    public Room(int capacity, RoomClass type, int number, Long roomPriceId) {
        this.capacity = capacity;
        this.type = type;
        this.number = number;
        this.roomPriceId = roomPriceId;
    }

    @Override
    public Long getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getRoomClass() {
        return type.toString()
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

}

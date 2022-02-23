package com.epam.hotelbooking.entity;

public class RoomsAmount implements Identifable {
    private Long id;
    private Integer amountOfRooms;

    public RoomsAmount(Integer amountOfRooms) {
        this.amountOfRooms = amountOfRooms;
    }

    public Integer getAmountOfRooms() {
        return amountOfRooms;
    }

    @Override
    public Long getId() {
        return id;
    }

}

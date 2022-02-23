package com.epam.hotelbooking.entity;

import java.math.BigDecimal;

public class RoomPrice implements Identifable {
    private Long id;
    private BigDecimal price;

    public RoomPrice(Long id, BigDecimal price) {
        this.id = id;
        this.price = price;
    }

    @Override
    public Long getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price;
    }

}

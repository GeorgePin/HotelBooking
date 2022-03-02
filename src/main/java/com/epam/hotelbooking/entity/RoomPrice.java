package com.epam.hotelbooking.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

public class RoomPrice implements Identifable, Serializable {
    private static final long serialVersionUID = -1127474130423104866L;
    private Long id;
    private BigDecimal price;
    private Date validFrom;

    public RoomPrice(Long id, BigDecimal price, Date validFrom) {
        this.id = id;
        this.price = price;
        this.validFrom = validFrom;
    }

    @Override
    public Long getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "RoomPrice [id=" + id + ", price=" + price + ", validFrom=" + validFrom + "]";
    }

}

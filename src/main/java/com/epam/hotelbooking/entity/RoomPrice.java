package com.epam.hotelbooking.entity;

import java.math.BigDecimal;
import java.sql.Date;

public class RoomPrice extends Entity {
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

    public Date getValidFrom() {
        return validFrom;
    }

}

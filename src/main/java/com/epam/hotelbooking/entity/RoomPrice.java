package com.epam.hotelbooking.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

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

    @Override
    public int hashCode() {
        return Objects.hash(id, price, validFrom);
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
        RoomPrice other = (RoomPrice) obj;
        return Objects.equals(id, other.id)
                && Objects.equals(price, other.price)
                && Objects.equals(validFrom, other.validFrom);
    }
}

package com.epam.hotelbooking.entity;

import java.io.Serializable;

public class EntityAmount implements Identifable, Serializable {

    private Long id;
    private static final long serialVersionUID = 8115082589401813219L;
    private int amountOfEntities;

    public EntityAmount(int amountOfEntities) {
        this.amountOfEntities = amountOfEntities;
    }

    @Override
    public Long getId() {
        return id;
    }

    public int getAmountOfEntities() {
        return amountOfEntities;
    }

}

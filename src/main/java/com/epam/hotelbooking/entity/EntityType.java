package com.epam.hotelbooking.entity;

import java.io.Serializable;

public enum EntityType implements Identifable, Serializable {
    ROOM("room"), USER("user"), REQUEST("reservation");

    private final String entityType;

    EntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getEntityType() {
        return entityType;
    }

    @Override
    public Long getId() {
        return (long) this.ordinal();
    }

}

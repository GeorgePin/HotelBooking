package com.epam.hotelbooking.entity;

public class Request implements Identifable {
    private Long id;

    public Request(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Request [id=" + id + "]";
    }
}

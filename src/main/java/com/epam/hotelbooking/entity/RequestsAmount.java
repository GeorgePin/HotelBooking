package com.epam.hotelbooking.entity;

public class RequestsAmount implements Identifable {
    private Long id;
    private Integer amountOfRequests;

    public RequestsAmount(Integer amountOfRequests) {
        this.amountOfRequests = amountOfRequests;
    }

    public Integer getAmountOfRequests() {
        return amountOfRequests;
    }

    @Override
    public Long getId() {
        return id;
    }

}

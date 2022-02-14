package com.epam.hotelbooking.entity;

import java.io.Serializable;

public class User implements Identifable, Serializable {

    private static final long serialVersionUID = 1372779825189737930L;
    private final long id;
    private final String login;

    public User(long id, String login) {
        this.id = id;
        this.login = login;
    }

    @Override
    public Long getId() {
        return id;
    }
}

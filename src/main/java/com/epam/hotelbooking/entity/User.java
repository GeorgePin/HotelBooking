package com.epam.hotelbooking.entity;

import java.io.Serializable;

public class User implements Identifable, Serializable {

    private static final long serialVersionUID = 1372779825189737930L;
    private final long id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private boolean isBlocked;

    public User(long id, String login) {
        this.id = id;
        this.login = login;
    }

    public User(long id, String name, String surname, String login, String password, boolean isBlocked) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.isBlocked = isBlocked;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public boolean getIsBlocked() {
        return isBlocked;
    }
}

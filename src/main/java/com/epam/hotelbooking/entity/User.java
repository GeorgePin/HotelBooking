package com.epam.hotelbooking.entity;

public class User extends Entity {

    private static final long serialVersionUID = 1372779825189737930L;
    private long id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private boolean isBlocked;
    private boolean isAdmin;

    public User(long id, boolean isAdmin) {
        this.id = id;
        this.isAdmin = isAdmin;
    }

    public User(String name, String surname, String login, String password) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
    }

    public User(Long id, String name, String surname, String login, boolean isBlocked) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
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

    public boolean isAdmin() {
        return isAdmin;
    }
}

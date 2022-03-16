package com.epam.hotelbooking.entity;

import java.util.Objects;

public class User extends Entity {

    private static final long serialVersionUID = 1372779825189737930L;
    private long id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private boolean isBlocked;
    private boolean isAdmin;

    public User(long id, boolean isAdmin, boolean isBlocked) {
        this.id = id;
        this.isAdmin = isAdmin;
        this.isBlocked = isBlocked;
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

    @Override
    public int hashCode() {
        return Objects.hash(id, isAdmin, isBlocked, login, name, password, surname);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        return id == other.id && isAdmin == other.isAdmin && isBlocked == other.isBlocked
                && Objects.equals(login, other.login) && Objects.equals(name, other.name)
                && Objects.equals(password, other.password) && Objects.equals(surname, other.surname);
    }

}

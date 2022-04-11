package com.epam.hotelbooking.entity;

import java.util.Objects;

public class User extends Entity {

    public static final String TABLE_NAME = "user";
    private static final long serialVersionUID = 1372779825189737930L;
    private final Long id;
    private final String name;
    private final String surname;
    private final String login;
    private final String password;
    private final Boolean isBlocked;
    private final Boolean isAdmin;

    public User(UserBuilder userBuilder) {
        this.id = userBuilder.id;
        this.name = userBuilder.name;
        this.surname = userBuilder.surname;
        this.login = userBuilder.login;
        this.password = userBuilder.password;
        this.isBlocked = userBuilder.isBlocked;
        this.isAdmin = userBuilder.isAdmin;
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

    public Boolean getIsBlocked() {
        return isBlocked;
    }

    public Boolean isAdmin() {
        return isAdmin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isAdmin, isBlocked, login, name, password, surname);
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
        User other = (User) obj;
        return id == other.id
                && isAdmin == other.isAdmin
                && isBlocked == other.isBlocked
                && Objects.equals(login, other.login)
                && Objects.equals(name, other.name)
                && Objects.equals(password, other.password)
                && Objects.equals(surname, other.surname);
    }

    public static class UserBuilder implements Builder<User> {
        private Long id;
        private String name;
        private String surname;
        private String login;
        private String password;
        private Boolean isBlocked;
        private Boolean isAdmin;

        public UserBuilder withId(final Long id) {
            this.id = id;
            return this;
        }

        public UserBuilder withName(final String name) {
            this.name = name;
            return this;
        }

        public UserBuilder withSurname(final String surname) {
            this.surname = surname;
            return this;
        }

        public UserBuilder withLogin(final String login) {
            this.login = login;
            return this;
        }

        public UserBuilder withPassword(final String password) {
            this.password = password;
            return this;
        }

        public UserBuilder withIsBlocked(final Boolean isBlocked) {
            this.isBlocked = isBlocked;
            return this;
        }

        public UserBuilder withIsAdmin(final Boolean isAdmin) {
            this.isAdmin = isAdmin;
            return this;
        }

        @Override
        public User build() {
            User user = new User(this);
            reset();
            return user;
        }

        @Override
        public void reset() {
            this.id = null;
            this.name = null;
            this.surname = null;
            this.login = null;
            this.password = null;
            this.isBlocked = null;
            this.isAdmin = null;
        }
    }
}

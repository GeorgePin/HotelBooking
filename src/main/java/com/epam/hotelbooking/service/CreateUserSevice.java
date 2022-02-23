package com.epam.hotelbooking.service;

public interface CreateUserSevice {
    boolean createUser(String name, String surname, String login, String password) throws Exception;
}

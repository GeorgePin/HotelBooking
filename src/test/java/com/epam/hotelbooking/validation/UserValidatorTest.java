package com.epam.hotelbooking.validation;

import org.junit.Assert;
import org.junit.Test;

import com.epam.hotelbooking.entity.User;

public class UserValidatorTest {

    @Test
    public void testIsDataForLoginValidShouldReturnTrueWhenLoginAndPasswordAreValid() {
        // given
        UserValidator userValidation = new UserValidator();
        String login = "asd1";
        String password = "qwerty123";
        // when
        boolean actual = userValidation.isDataForLoginValid(login, password);
        // then
        Assert.assertTrue(actual);
    }

    @Test
    public void testIsDataForLoginValidShouldReturnFalseWhenLoginAndPasswordAreInvalid() {
        // given
        UserValidator userValidation = new UserValidator();
        String login = "asd/1";
        String password = "abc&&?";
        // when
        boolean actual = userValidation.isDataForLoginValid(login, password);
        // then
        Assert.assertFalse(actual);
    }

    @Test
    public void testIsDataForRegistrationValidShouldReturnTrueWhenLoginAndPasswordAndNameAndSurnameAreValid() {
        // given
        UserValidator userValidation = new UserValidator();
        String name = "Petya";
        String surname = "Sidorov";
        String login = "pet321";
        String password = "ABC__123QQ";
        User user = new User(name, surname, login, password);
        // when
        boolean actual = userValidation.isDataForRegistrationValid(user);
        // then
        Assert.assertTrue(actual);
    }

    @Test
    public void testIsDataForRegistrationValidShouldReturnFalseWhenLoginAndPasswordAndNameAndSurnameAreInvalid() {
        // given
        UserValidator userValidation = new UserValidator();
        String name = "123";
        String surname = "465\\";
        String login = "asd/1";
        String password = "ABC/_s_123QQ";
        User user = new User(name, surname, login, password);
        // when
        boolean actual = userValidation.isDataForRegistrationValid(user);
        // then
        Assert.assertFalse(actual);
    }
}

package com.epam.hotelbooking.validation;

import org.junit.Assert;
import org.junit.Test;

import com.epam.hotelbooking.entity.User;

public class UserValidatorTest {

    private static final String VALID_LOGIN = "m1ndgamer";
    private static final String VALID_PASSWORD = "qwerty123";

    private static final String INVALID_LOGIN = "WhyMe??";
    private static final String INVALID_PASSWORD = "Good security";

    private static final String VALID_NAME = "Stas";
    private static final String VALID_SURNAME = "Petrov";

    private static final String INVALID_NAME = "Petr1";
    private static final String INVALID_SURNAME = "Vu";

    @Test
    public void testIsDataForLoginValidShouldReturnTrueWhenLoginAndPasswordNotNullAndValid() {
        // given
        UserValidator userValidation = new UserValidator();
        User user = new User.UserBuilder().withLogin(VALID_LOGIN)
                .withPassword(VALID_PASSWORD)
                .build();
        // when
        boolean actual = userValidation.isDataForLoginValid(user);
        // then
        Assert.assertTrue(actual);
    }

    @Test
    public void testIsDataForLoginValidShouldFalseTrueWhenLoginIsNull() {
        // given
        UserValidator userValidation = new UserValidator();
        User user = new User.UserBuilder().withLogin(null)
                .withPassword(VALID_PASSWORD)
                .build();
        // when
        boolean actual = userValidation.isDataForLoginValid(user);
        // then
        Assert.assertFalse(actual);
    }

    @Test
    public void testIsDataForLoginValidShouldFalseTrueWhenPasswordIsNull() {
        // given
        UserValidator userValidation = new UserValidator();
        User user = new User.UserBuilder().withLogin(VALID_LOGIN)
                .withPassword(null)
                .build();
        // when
        boolean actual = userValidation.isDataForLoginValid(user);
        // then
        Assert.assertFalse(actual);
    }

    @Test
    public void testIsDataForLoginValidShouldFalseTrueWhenLoginAndPasswordAreInvalid() {
        // given
        UserValidator userValidation = new UserValidator();
        User user = new User.UserBuilder().withLogin(INVALID_LOGIN)
                .withPassword(INVALID_PASSWORD)
                .build();
        // when
        boolean actual = userValidation.isDataForLoginValid(user);
        // then
        Assert.assertFalse(actual);
    }

    @Test
    public void testIsDataForRegistrationValidShouldTrueTrueWhenNameAndSurnameAreNotNullAndValid() {
        // given
        UserValidator userValidation = new UserValidator();
        User user = new User.UserBuilder().withLogin(VALID_LOGIN)
                .withPassword(VALID_PASSWORD)
                .withName(VALID_NAME)
                .withSurname(VALID_SURNAME)
                .build();
        // when
        boolean actual = userValidation.isDataForRegistrationValid(user);
        // then
        Assert.assertTrue(actual);
    }

    @Test
    public void testIsDataForRegistrationValidShouldFalseTrueWhenNameIsNull() {
        // given
        UserValidator userValidation = new UserValidator();
        User user = new User.UserBuilder().withLogin(VALID_LOGIN)
                .withPassword(VALID_PASSWORD)
                .withName(null)
                .withSurname(VALID_SURNAME)
                .build();
        // when
        boolean actual = userValidation.isDataForRegistrationValid(user);
        // then
        Assert.assertFalse(actual);
    }

    @Test
    public void testIsDataForRegistrationValidShouldFalseTrueWhenSurnameIsNull() {
        // given
        UserValidator userValidation = new UserValidator();
        User user = new User.UserBuilder().withLogin(VALID_LOGIN)
                .withPassword(VALID_PASSWORD)
                .withName(VALID_NAME)
                .withSurname(null)
                .build();
        // when
        boolean actual = userValidation.isDataForRegistrationValid(user);
        // then
        Assert.assertFalse(actual);
    }

    @Test
    public void testIsDataForRegistrationValidShouldFalseTrueWhenNameAndSurnameAreInvalid() {
        // given
        UserValidator userValidation = new UserValidator();
        User user = new User.UserBuilder().withLogin(VALID_LOGIN)
                .withPassword(VALID_PASSWORD)
                .withName(INVALID_NAME)
                .withSurname(INVALID_SURNAME)
                .build();
        // when
        boolean actual = userValidation.isDataForRegistrationValid(user);
        // then
        Assert.assertFalse(actual);
    }

}

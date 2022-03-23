package com.epam.hotelbooking.validation;

import com.epam.hotelbooking.entity.User;

public class UserValidator {

    private static final String CONTAINS_ANY_WORD_CHARACHTER_PATTER = "\\w{3,20}";
    private static final String ANY_WORD_OR_DIGIT = "[\\d|\\w]{1,10}";

    public boolean isDataForLoginValid(User user) {
        return areLoginAndPassowrdValid(user);
    }

    public boolean isDataForRegistrationValid(User user) {
        String name = user.getName();
        String surname = user.getSurname();
        return name != null
                && surname != null
                && name.matches(CONTAINS_ANY_WORD_CHARACHTER_PATTER)
                && surname.matches(CONTAINS_ANY_WORD_CHARACHTER_PATTER)
                && areLoginAndPassowrdValid(user);
    }

    private boolean areLoginAndPassowrdValid(User user) {
        String login = user.getLogin();
        String password = user.getPassword();
        return login != null
                && password != null
                && login.matches(ANY_WORD_OR_DIGIT)
                && password.matches(ANY_WORD_OR_DIGIT);
    }
}

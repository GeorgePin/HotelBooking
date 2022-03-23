package com.epam.hotelbooking.command.common;

import static org.mockito.Mockito.mock;

import org.junit.Test;

import com.epam.hotelbooking.command.util.CommandResult;
import com.epam.hotelbooking.service.UserServiceImpl;
import com.epam.hotelbooking.validation.UserValidator;

public class LoginCommandTest {

    @Test
    public void testExecuteShouldReturnCommandResultWithIndexJspWhenUserIsClientAndLoginSuccessful() {
        // given
        UserServiceImpl userServiceImpl = mock(UserServiceImpl.class);
        UserValidator userValidator = mock(UserValidator.class);
        LoginCommand loginCommand = new LoginCommand(userServiceImpl, userValidator);
        // when
      CommandResult commandResult =  loginCommand.execute(, null);
        // then
    }
}

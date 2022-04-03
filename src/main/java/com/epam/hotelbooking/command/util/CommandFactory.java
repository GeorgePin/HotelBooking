package com.epam.hotelbooking.command.util;

public class CommandFactory {

    public Command createCommand(String command) throws IllegalArgumentException {
        return CommandEnum.valueOf(command.toUpperCase())
                .getCommand();
    }
}

package com.epam.hotelbooking.command.util;

public class CommandFactory {

    public Command createCommand(String command) {
        try {
            return CommandEnum.valueOf(command.toUpperCase())
                    .getCommand();
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("Unknown command = " + command);
        }
    }
}

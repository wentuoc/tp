package exmealplan.parser;

import exmealplan.command.ByeCommand;
import exmealplan.command.Command;
import exmealplan.command.UnknownCommand;

public class Parser {
    public static Command parse(String userInput) {
        userInput = userInput.trim();

        if(userInput.equals("bye")) {
            return new ByeCommand();
        }
        return new UnknownCommand();
    }
}

package seedu.parser;

import seedu.command.ByeCommand;
import seedu.command.Command;
import seedu.command.UnknownCommand;

public class Parser {
    public static Command parse(String userInput) {
        userInput = userInput.trim();

        if(userInput.equals("bye")) {
            return new ByeCommand();
        }
        return new UnknownCommand();
    }
}

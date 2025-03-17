package seedu.parser;

import seedu.command.ByeCommand;
import seedu.command.Command;
import seedu.command.CreateCommand;
import seedu.command.SelectCommand;
import seedu.command.UnknownCommand;

public class Parser {
    public static Command parse(String userInput) {
        String bye = "bye";
        String create = "create";
        String select = "select";
        userInput = userInput.toLowerCase().trim();
        if (userInput.contains(bye)) {
            return new ByeCommand();
        } else if (userInput.contains(create)) {
            return new CreateCommand(userInput);
        } else if (userInput.contains(select)) {
            return new SelectCommand(userInput);
        }
        return new UnknownCommand(userInput);
    }
}

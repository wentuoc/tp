package seedu.parser;

import seedu.command.ByeCommand;
import seedu.command.Command;
import seedu.command.CreateCommand;
import seedu.command.FilterCommand;
import seedu.command.SelectCommand;
import seedu.command.UnknownCommand;

public class Parser {
    static String bye = "bye";
    static String create = "create";
    static String filter = "filter";
    static String select = "select";

    public static Command parse(String userInput) {
        userInput = userInput.toLowerCase().trim();
        if (userInput.equalsIgnoreCase(bye)) {
            return new ByeCommand();
        } else if (userInput.contains(create)) {
            return new CreateCommand(userInput);
        } else if (userInput.contains(filter)) {
            return new FilterCommand(userInput);
        } else if (userInput.contains(select)) {
            return new SelectCommand(userInput);
        }
        return new UnknownCommand(userInput);
    }
}

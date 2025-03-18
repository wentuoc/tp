package seedu.parser;

import seedu.command.ByeCommand;
import seedu.command.Command;
import seedu.command.CreateCommand;
import seedu.command.ListCommand;
import seedu.command.MealCommand;
import seedu.command.UnknownCommand;

public class Parser {
    public static Command parse(String userInput) {
        String bye = "bye";
        String create = "create";
        userInput = userInput.toLowerCase().trim();
        if (userInput.contains(bye)) {
            return new ByeCommand();
        } else if (userInput.contains(create)) {
            return new CreateCommand(userInput);
        } else if (userInput.equals("list")) {
            return new ListCommand();
        } else if (userInput.equals("meal")) {
            return new MealCommand();
        }
        return new UnknownCommand(userInput);
    }
}

package seedu.parser;

import seedu.command.ByeCommand;
import seedu.command.Command;
import seedu.command.CreateCommand;
import seedu.command.HelpCommand;
import seedu.command.ListCommand;
import seedu.command.MealCommand;
import seedu.command.UnknownCommand;
import seedu.ui.UserInterface;

public class Parser {
    static UserInterface ui = new UserInterface();
    public static Command parse(String userInput) {
        String bye = "bye";
        String create = "create";
        userInput = userInput.toLowerCase().trim();
        if (userInput.startsWith(bye)) {
            return new ByeCommand();
        } else if (userInput.startsWith(create)) {
            return new CreateCommand(userInput);
        } else if (userInput.equals("list")) {
            return new ListCommand();
        } else if (userInput.equals("meal")) {
            return new MealCommand();
        } else if (userInput.startsWith("help")) {
            return new HelpCommand(userInput);
        }else {
            return new UnknownCommand(userInput);
        }
    }
}

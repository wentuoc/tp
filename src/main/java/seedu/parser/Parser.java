package seedu.parser;

import seedu.command.ByeCommand;
import seedu.command.Command;
import seedu.command.CreateCommand;
import seedu.command.FilterCommand;
import seedu.command.SelectCommand;
import seedu.command.ListCommand;
import seedu.command.MealCommand;
import seedu.command.UnknownCommand;

public class Parser {
    static String bye = "bye";
    static String create = "create";
    static String filter = "filter";
    static String select = "select";
    static String meal = "meal";
    static String list = "list";

    public static Command parse(String userInput) {
        userInput = userInput.toLowerCase().trim();

        if (userInput.startsWith(bye)) {
            return new ByeCommand();
        } else if (userInput.startsWith(create)) {
            return new CreateCommand(userInput);
        } else if (userInput.startsWith(filter)) {
            return new FilterCommand(userInput);
        } else if (userInput.startsWith(select)) {
            return new SelectCommand(userInput);
        } else if (userInput.startsWith(list)) {
            return new ListCommand();
        } else if (userInput.startsWith(meal)) {
            return new MealCommand();
        }
        return new UnknownCommand(userInput);
    }
}

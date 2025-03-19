package seedu.parser;

import seedu.command.ByeCommand;
import seedu.command.Command;
import seedu.command.CreateCommand;
import seedu.command.FilterCommand;
import seedu.command.ListCommand;
import seedu.command.MealCommand;
import seedu.command.SelectCommand;
import seedu.command.UnknownCommand;

public class Parser {
    static String bye = "bye";
    static String create = "create";
    static String filter = "filter";
    static String select = "select";
    static String list = "list";
    static String meal = "meal";

    public static Command parse(String userInput) {
        userInput = userInput.toLowerCase().trim();
        if (userInput.startsWith(bye)) {
            return new ByeCommand();
        } else if (userInput.startsWith(create)) {
            return new CreateCommand(userInput);
        } else if (userInput.contains(filter)) {
            return new FilterCommand(userInput);
        } else if (userInput.contains(select)) {
            return new SelectCommand(userInput);
        } else if (userInput.equals(list)) {
            return new ListCommand();
        } else if (userInput.equals(meal)) {
            return new MealCommand();
        }
        return new UnknownCommand(userInput);
    }
}

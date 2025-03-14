package seedu.command;

import seedu.logic.MealManager;
import seedu.ui.UserInterface;

public class UnknownCommand extends Command {

    @Override
    public void execute(MealManager mealManager, UserInterface ui) {
        ui.printUnknowCommand();
    }
}

package seedu.command;

import seedu.logic.MealManager;
import seedu.ui.UserInterface;

public abstract class Command {

    public boolean isExit() {
        return false;
    }

    public abstract void execute(MealManager mealManager, UserInterface ui);
}

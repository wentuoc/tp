package seedu.command;

import seedu.exceptions.EZMealPlanException;
import seedu.logic.MealManager;
import seedu.ui.UserInterface;

public abstract class Command {
    protected static String validUserInput;
    protected String lowerCaseInput;

    public boolean isExit() {
        return false;
    }

    public abstract void execute(MealManager mealManager, UserInterface ui) throws EZMealPlanException;
}

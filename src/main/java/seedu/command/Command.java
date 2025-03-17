package seedu.command;

import seedu.exceptions.EZMealPlanException;
import seedu.logic.MealManager;
import seedu.ui.UserInterface;

public abstract class Command {
    protected String validUserInput;

    public boolean isExit() {
        return false;
    }

    public void setValidUserInput(String validUserInput) {
        this.validUserInput = validUserInput;
    }

    public String getValidUserInput() {
        return validUserInput;
    }

    public abstract void execute(MealManager mealManager, UserInterface ui) throws EZMealPlanException;
}

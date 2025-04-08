package ezmealplan.command;

import ezmealplan.exceptions.EZMealPlanException;
import ezmealplan.logic.MealManager;
import ezmealplan.ui.UserInterface;

public abstract class Command {
    protected static String validUserInput;
    protected String lowerCaseInput;

    public boolean isExit() {
        return false;
    }

    public abstract void execute(MealManager mealManager, UserInterface ui) throws EZMealPlanException;
}

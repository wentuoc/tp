package exmealplan.command;

import exmealplan.logic.MealManager;
import exmealplan.ui.UserInterface;

public abstract class Command {

    public boolean isExit() {
        return false;
    }

    public abstract void execute(MealManager mealManager, UserInterface ui);
}

package exmealplan.command;

import exmealplan.logic.MealManager;
import exmealplan.ui.UserInterface;

public class ByeCommand extends Command {
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(MealManager mealManager, UserInterface ui) {
        ui.printGoodbye();
    }
}

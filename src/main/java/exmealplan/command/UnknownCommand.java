package exmealplan.command;

import exmealplan.logic.MealManager;
import exmealplan.ui.UserInterface;

public class UnknownCommand extends Command {

    @Override
    public void execute(MealManager mealManager, UserInterface ui) {
        ui.printUnknowCommand();
    }
}

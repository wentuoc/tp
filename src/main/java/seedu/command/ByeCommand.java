package seedu.command;

import seedu.logic.MealManager;
import seedu.ui.UserInterface;

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

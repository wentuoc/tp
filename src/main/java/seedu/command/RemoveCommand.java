package seedu.command;

import seedu.exceptions.EZMealPlanException;
import seedu.logic.MealManager;
import seedu.ui.UserInterface;

import java.util.logging.Logger;

public class RemoveCommand extends RemoveDeleteCommand {
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public RemoveCommand(String userInputText) {
        super(userInputText);
        this.removeOrDelete = remove;
        logger.fine("Received \"Remove\" command, user input: " + userInputText);
    }

    @Override
    public void execute(MealManager mealManager, UserInterface ui) throws EZMealPlanException {
        super.execute(mealManager, ui);
        logger.fine("Command finished executing: Removed \"" + removedOrDeletedMeal.getName() + "\" meal");
    }
}

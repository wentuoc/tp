package seedu.command;

import seedu.exceptions.EZMealPlanException;
import seedu.logic.MealManager;
import seedu.meallist.MealList;
import seedu.ui.UserInterface;

import java.util.logging.Logger;

public class DeleteCommand extends RemoveDeleteCommand {
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public DeleteCommand(String userInputText) {
        super(userInputText);
        this.removeOrDelete = delete;
        logger.fine("Received \"Delete\" command, user input: " + userInputText);
    }

    @Override
    public void execute(MealManager mealManager, UserInterface ui) throws EZMealPlanException {
        super.execute(mealManager, ui);
        MealList userList = mealManager.getUserList();
        if (userList.contains(removedOrDeletedMeal)) {
            int indexInUserList = userList.getIndex(removedOrDeletedMeal);
            userList.removeMeal(indexInUserList + 1);
            logger.fine("Command finished executing: Removed \"" + removedOrDeletedMeal.getName() + "\" meal " +
                    "from user list");
        }
        logger.fine("Command finished executing: Deleted \"" + removedOrDeletedMeal.getName() + "\" meal from " +
                "main list");
    }
}

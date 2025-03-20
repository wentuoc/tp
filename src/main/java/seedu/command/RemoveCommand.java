package seedu.command;

import seedu.checkers.RemoveChecker;
import seedu.exceptions.EZMealPlanException;
import seedu.food.Meal;
import seedu.logic.MealManager;
import seedu.meallist.MealList;
import seedu.ui.UserInterface;

import java.util.logging.Logger;

public class RemoveCommand extends Command {
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public RemoveCommand(String userInputText) {
        this.validUserInput = userInputText.trim();
        logger.fine("Received \"Remove\" command, user input: " + userInputText);
    }

    @Override
    public void execute(MealManager mealManager, UserInterface ui) throws EZMealPlanException {
        MealList userMealList = mealManager.getUserList();

        boolean isValidUserInput = checkValidUserInput();
        if (!isValidUserInput) {
            logger.severe("Huge issue detected! The user input format remains invalid despite " +
                    "passing all the checks for input formatting error.");
        }
        assert isValidUserInput;
        int index = Integer.parseInt(validUserInput.split("\\s+")[1]);
        Meal removedMeal = mealManager.removeMeal(index, userMealList);
        ui.printRemovedMessage(removedMeal, userMealList.size());
        logger.fine("Command finished executing: Removed \"" + removedMeal.getName() + "\" meal");
    }

    private boolean checkValidUserInput() throws EZMealPlanException {
        RemoveChecker checker = new RemoveChecker(validUserInput);
        checker.check();
        return checker.isPassed();
    }
}

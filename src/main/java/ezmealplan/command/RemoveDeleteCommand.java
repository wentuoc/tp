package ezmealplan.command;

import ezmealplan.command.checkers.RemoveDeleteChecker;
import ezmealplan.exceptions.EZMealPlanException;
import ezmealplan.food.Meal;
import ezmealplan.logic.MealManager;
import ezmealplan.food.list.MealList;
import ezmealplan.ui.UserInterface;

import java.util.logging.Logger;

public abstract class RemoveDeleteCommand extends Command {
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    protected String removeOrDelete;
    protected final String remove = "remove";
    protected final String delete = "delete";
    protected Meal removedOrDeletedMeal;

    public RemoveDeleteCommand(String userInputText) {
        validUserInput = userInputText.trim();
    }

    @Override
    public void execute(MealManager mealManager, UserInterface ui) throws EZMealPlanException {
        MealList wishList = mealManager.getWishList();
        MealList recipesList = mealManager.getRecipesList();
        int indexOfIndex = 1;

        boolean isValidUserInput = checkValidUserInput();
        if (!isValidUserInput) {
            logger.severe("Huge issue detected! The user input format remains invalid despite " +
                    "passing all the checks for input formatting error.");
        }
        assert isValidUserInput;
        String regexPattern = "\\s+";
        int indexAdjustment = 1;
        int index = Integer.parseInt(validUserInput.split(regexPattern)[indexOfIndex]) - indexAdjustment;
        if (removeOrDelete.equals(remove)) {
            removedOrDeletedMeal = mealManager.removeMeal(index, wishList);
            ui.printRemovedMessage(removedOrDeletedMeal, wishList.size());
        } else if (removeOrDelete.equals(delete)) {
            removedOrDeletedMeal = mealManager.removeMeal(index, recipesList);
            ui.printDeletedMessage(removedOrDeletedMeal, recipesList.size());
        }
    }

    private boolean checkValidUserInput() throws EZMealPlanException {
        RemoveDeleteChecker checker = new RemoveDeleteChecker(validUserInput);
        checker.check();
        return checker.isPassed();
    }
}

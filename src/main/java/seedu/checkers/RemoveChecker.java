package seedu.checkers;

import seedu.exceptions.EZMealPlanException;
import seedu.logic.MealManager;

import java.util.logging.Logger;

public class RemoveChecker extends Checker {
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final MealManager mealManager;

    public RemoveChecker(String userInputText, MealManager mealManager) {
        this.userInput = userInputText;
        this.mealManager = mealManager;
    }

    @Override
    public void check() throws EZMealPlanException {
        int index = parseIndex(userInput);
        checkIndexWithinRange(index);
        setPassed(true);
    }

    private int parseIndex(String userInput) throws EZMealPlanException {
        try {
            return Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            throw new EZMealPlanException(); //TODO: add the relevant exception and logger
        }
    }

    private void checkIndexWithinRange(int index) throws EZMealPlanException {
        if (index > mealManager.getUserMealList().size()) {
            throw new EZMealPlanException(); //TODO: add the relevant exception and logger
        }
    }
}

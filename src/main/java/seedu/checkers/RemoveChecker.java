package seedu.checkers;

import seedu.exceptions.EZMealPlanException;
import seedu.logic.MealManager;

import java.util.logging.Logger;

public class RemoveChecker extends Checker {
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public RemoveChecker(String userInputText) {
        this.userInput = userInputText;
    }

    @Override
    public void check() throws EZMealPlanException {
        int index = parseIndex(userInput);
        setPassed(true);
    }

    private int parseIndex(String userInput) throws EZMealPlanException {
        try {
            return Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            throw new EZMealPlanException(); //TODO: add the relevant exception and logger
        }
    }
}

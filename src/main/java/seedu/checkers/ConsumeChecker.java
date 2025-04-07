//@@author olsonwangyj
package seedu.checkers;

import seedu.exceptions.EZMealPlanException;
import seedu.exceptions.MissingIngKeywordException;
import seedu.exceptions.MissingIngredientException;

import java.util.logging.Logger;

public class ConsumeChecker extends Checker {
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static final String ING = "/ing";
    private static final String CONSUME = "consume";

    public ConsumeChecker(String userInputText) {
        this.userInput = userInputText.trim();
        this.lowerCaseInput = userInputText.toLowerCase();
    }

    @Override
    public void check() throws EZMealPlanException {
        logger.fine("Checking '" + userInput + "' for consume command errors.");
        checkIngExists();
        checkIngredientExists();
        setPassed(true);
    }

    /**
     * Checks whether the '/ing' keyword exists in the consume command input.
     * @throws MissingIngKeywordException if '/ing' is not found.
     */
    private void checkIngExists() throws MissingIngKeywordException {
        if (!lowerCaseInput.contains(ING)) {
            String message = "Triggers MissingIngKeywordException()!";
            logger.warning(message);
            throw new MissingIngKeywordException("CONSUME");
        }
    }

    /**
     * Checks that there is non-empty ingredient information provided after the '/ing' keyword.
     * @throws MissingIngredientException if no ingredient details are found.
     */
    private void checkIngredientExists() throws MissingIngredientException {
        int afterIngIndex = lowerCaseInput.indexOf(ING) + ING.length();
        String ingredients = userInput.substring(afterIngIndex).trim();
        if (ingredients.isEmpty()) {
            String message = "Triggers MissingIngredientException()!";
            logger.warning(message);
            throw new MissingIngredientException(CONSUME);
        }
    }
}

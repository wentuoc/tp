package seedu.checkers;

import seedu.exceptions.EZMealPlanException;
import seedu.exceptions.RemoveFormatException;

import java.util.logging.Logger;

public class RemoveChecker extends Checker {
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public RemoveChecker(String userInputText) {
        this.userInput = userInputText;
    }

    @Override
    public void check() throws EZMealPlanException {
        String indexString = extractIndex(userInput);
        parseIndex(indexString);
        setPassed(true);
    }

    private void parseIndex(String input) throws EZMealPlanException {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new RemoveFormatException(userInput);
        }
    }

    private String extractIndex(String input) throws EZMealPlanException {
        try {
            return input.split("\\s+")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new RemoveFormatException(userInput);
        }
    }
}

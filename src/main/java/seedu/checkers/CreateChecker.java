package seedu.checkers;

import seedu.exceptions.EZMealPlanException;
import seedu.exceptions.InvalidCreateIndexException;
import seedu.exceptions.InvalidIngMnameException;
import seedu.exceptions.InvalidIngredientFormatException;
import seedu.exceptions.MissingIngKeywordException;
import seedu.exceptions.MissingIngredientException;
import seedu.exceptions.MissingMealNameException;
import seedu.exceptions.MissingMnameKeywordException;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateChecker extends Checker {
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    String ing = "/ing";
    String mname = "/mname";
    String create = "create";

    public CreateChecker(String userInputText) {
        this.userInput = userInputText.trim();
        this.lowerCaseInput = userInputText.toLowerCase();
    }

    @Override
    public void check() throws EZMealPlanException {
        logger.fine("Checking '" + userInput + "' for errors.");
        checkMnameExists();
        checkIngExists();
        checkMnameIngIndexes();
        checkMealNameExists();
        checkIngredientExists();
        checkIngredientFormat();
        setPassed(true);
    }

    private void checkMnameIngIndexes() throws EZMealPlanException {
        int mnameIndex = lowerCaseInput.indexOf(mname);
        int createIndex = lowerCaseInput.indexOf(create);
        int ingIndex = lowerCaseInput.indexOf(ing);
        boolean isValidCreateIndex = createIndex < mnameIndex && createIndex < ingIndex;
        if (!isValidCreateIndex) {
            String message = "Triggers InvalidCreateIndexException()!";
            logger.warning(message);
            throw new InvalidCreateIndexException();
        }
        boolean isValidSeqIndexes = mnameIndex < ingIndex;
        if (!isValidSeqIndexes) {
            String message = "Triggers InvalidIngMnameException()!";
            logger.warning(message);
            throw new InvalidIngMnameException();
        }
    }

    private void checkIngExists() throws MissingIngKeywordException {
        boolean isIngPresent = lowerCaseInput.contains(ing);
        if (!isIngPresent) {
            String message = "Triggers MissingIngKeywordException()!";
            logger.warning(message);
            throw new MissingIngKeywordException();
        }
    }

    private void checkMnameExists() throws MissingMnameKeywordException {
        boolean isMnamePresent = lowerCaseInput.contains(mname);
        if (!isMnamePresent) {
            String message = "Triggers MissingMnameKeywordException()!";
            logger.warning(message);
            throw new MissingMnameKeywordException();
        }
    }

    private void checkMealNameExists() throws MissingMealNameException {
        int afterMnameIndex = lowerCaseInput.indexOf(mname) + mname.length();
        int ingIndex = lowerCaseInput.indexOf(ing);
        String mealName = userInput.substring(afterMnameIndex, ingIndex).trim();
        if (mealName.isEmpty()) {
            String message = "Triggers MissingMealNameException()!";
            logger.warning(message);
            throw new MissingMealNameException(create);
        }
    }

    private void checkIngredientExists() throws MissingIngredientException {
        int afterIngIndex = lowerCaseInput.indexOf(ing) + ing.length();
        String ingredients = userInput.substring(afterIngIndex).trim();
        if (ingredients.isEmpty()) {
            String message = "Triggers MissingIngredientException()!";
            logger.warning(message);
            throw new MissingIngredientException(create);
        }
    }

    private void checkIngredientFormat() throws InvalidIngredientFormatException {
        int afterIngIndex = lowerCaseInput.indexOf(ing) + ing.length();
        String ingredients = userInput.substring(afterIngIndex).trim();
        String splitRegex = "\\s*,\\s*";
        String[] ingredientArray = ingredients.split(splitRegex);
        String matchingRegex = "^([\\w\\s]+)\\s*\\(-?[0-9]{1,13}(\\.[0-9]*)?\\)$";
        Pattern ingredientPattern = Pattern.compile(matchingRegex);
        for (String ingredient : ingredientArray) {
            Matcher ingredientMatcher = ingredientPattern.matcher(ingredient);
            if (!ingredientMatcher.matches()) {
                String message = "Triggers InvalidIngredientFormatException()!";
                logger.warning(message);
                throw new InvalidIngredientFormatException();
            }
        }
    }
}

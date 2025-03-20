package seedu.checkers;

import seedu.exceptions.EZMealPlanException;
import seedu.exceptions.InvalidIngIndexException;
import seedu.exceptions.InvalidMcostIndexException;
import seedu.exceptions.InvalidMnameIndexException;
import seedu.exceptions.MissingIngredientException;
import seedu.exceptions.MissingMealCostException;
import seedu.exceptions.MissingMealNameException;

import java.util.logging.Logger;

public abstract class FilterSelectChecker extends Checker {
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    String ing = "/ing";
    String mname = "/mname";
    String mcost = "/mcost";
    String filterOrSelect;
    String filterMethod;
    final String byIng = "byIng";
    final String byMname = "byMname";
    final String byMcost = "byMcost";

    @Override
    public void check() throws EZMealPlanException {
        logger.fine("Checking '" + userInput + "' for errors.");
        checkFilterMethodFormat();
        setPassed(true);
    }

    private void checkFilterMethodFormat() throws EZMealPlanException {
        switch (filterMethod) {
        case byIng:
            checkIngIndex();
            checkIngFormat();
            break;
        case byMname:
            checkMnameIndex();
            checkMnameFormat();
            break;
        case byMcost:
            checkMcostIndex();
            checkMcostFormat();
            break;
        default:
            break;
        }
    }

    private void checkMnameIndex() throws EZMealPlanException {
        int commandIndex = lowerCaseInput.indexOf(filterOrSelect);
        int mnameIndex = lowerCaseInput.indexOf(mname);
        if (commandIndex >= mnameIndex) {
            throw new InvalidMnameIndexException(filterOrSelect);
        }
    }

    private void checkIngIndex() throws EZMealPlanException {
        int commandIndex = lowerCaseInput.indexOf(filterOrSelect);
        int ingIndex = lowerCaseInput.indexOf(ing);
        if (commandIndex >= ingIndex) {
            throw new InvalidIngIndexException(filterOrSelect);
        }
    }

    private void checkMcostIndex() throws EZMealPlanException {
        int commandIndex = lowerCaseInput.indexOf(filterOrSelect);
        int mcostIndex = lowerCaseInput.indexOf(mcost);
        if (commandIndex >= mcostIndex) {
            throw new InvalidMcostIndexException(filterOrSelect);
        }
    }

    private void checkMcostFormat() throws EZMealPlanException {
        int afterMcostIndex = lowerCaseInput.indexOf(mcost) + mcost.length();
        String afterMcost = this.userInput.substring(afterMcostIndex);
        if (afterMcost.isEmpty()) {
            throw new MissingMealCostException(filterOrSelect);
        }
    }

    private void checkMnameFormat() throws EZMealPlanException {
        int afterMnameIndex = lowerCaseInput.indexOf(mname) + mname.length();
        String afterMname = this.userInput.substring(afterMnameIndex);
        if (afterMname.isEmpty()) {
            throw new MissingMealNameException(filterOrSelect);
        }
    }

    private void checkIngFormat() throws EZMealPlanException {
        int afterIngIndex = lowerCaseInput.indexOf(ing) + ing.length();
        String afterIng = this.userInput.substring(afterIngIndex);
        if (afterIng.isEmpty()) {
            throw new MissingIngredientException(filterOrSelect);
        }
    }
}


package seedu.checkers;

import seedu.exceptions.EZMealPlanException;
import seedu.exceptions.InvalidFilterMethodException;
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

    @Override
    public void check() throws EZMealPlanException {
        logger.fine("Checking '" + userInput + "' for errors.");
        checkFilterMethod();
        setPassed(true);
    }

    private void checkFilterMethod() throws EZMealPlanException {
        boolean isContainIng = this.userInput.contains(ing);
        boolean isContainMname = this.userInput.contains(mname);
        boolean isContainMcost = this.userInput.contains(mcost);
        if (isContainIng && !isContainMname && !isContainMcost) {
            checkIngIndex();
            checkIngFormat();
            return;
        } else if (!isContainIng && isContainMname && !isContainMcost) {
            checkMnameIndex();
            checkMnameFormat();
            return;
        } else if (!isContainIng && !isContainMname && isContainMcost) {
            checkMcostIndex();
            checkMcostFormat();
            return;
        }
        String select = "select";
        boolean isContainSelect = userInput.contains(select);
        if (isContainSelect && !(isContainMcost && isContainIng && isContainMname)) {
            return;
        }
        throw new InvalidFilterMethodException();
    }

    private void checkMnameIndex() throws EZMealPlanException {
        int commandIndex = this.userInput.indexOf(filterOrSelect);
        int mnameIndex = this.userInput.indexOf(mname);
        if (commandIndex >= mnameIndex) {
            throw new InvalidMnameIndexException(filterOrSelect);
        }
    }

    private void checkIngIndex() throws EZMealPlanException {
        int commandIndex = this.userInput.indexOf(filterOrSelect);
        int ingIndex = this.userInput.indexOf(ing);
        if (commandIndex >= ingIndex) {
            throw new InvalidIngIndexException(filterOrSelect);
        }
    }

    private void checkMcostIndex() throws EZMealPlanException {
        int commandIndex = this.userInput.indexOf(filterOrSelect);
        int mcostIndex = this.userInput.indexOf(mcost);
        if (commandIndex >= mcostIndex) {
            throw new InvalidMcostIndexException(filterOrSelect);
        }
    }

    private void checkMcostFormat() throws EZMealPlanException {
        int afterMcostIndex = this.userInput.indexOf(mcost) + mcost.length();
        String afterMcost = this.userInput.substring(afterMcostIndex);
        if (afterMcost.isEmpty()) {
            throw new MissingMealCostException(filterOrSelect);
        }
    }

    private void checkMnameFormat() throws EZMealPlanException {
        int afterMnameIndex = this.userInput.indexOf(mname) + mname.length();
        String afterMname = this.userInput.substring(afterMnameIndex);
        if (afterMname.isEmpty()) {
            throw new MissingMealNameException(filterOrSelect);
        }
    }

    private void checkIngFormat() throws EZMealPlanException {
        int afterIngIndex = this.userInput.indexOf(ing) + ing.length();
        String afterIng = this.userInput.substring(afterIngIndex);
        if (afterIng.isEmpty()) {
            throw new MissingIngredientException(filterOrSelect);
        }
    }
}


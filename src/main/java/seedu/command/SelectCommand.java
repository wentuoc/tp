package seedu.command;

import seedu.exceptions.EZMealPlanException;
import seedu.exceptions.InvalidSelectIndexException;
import seedu.food.Meal;
import seedu.logic.MealManager;
import seedu.ui.UserInterface;

import java.util.List;
import java.util.logging.Logger;

public class SelectCommand extends FilterSelectCommand {
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public SelectCommand(String userInput) {
        this.validUserInput = userInput.trim();
        this.filterOrSelect = "select";
    }

    @Override
    public void execute(MealManager mealManager, UserInterface ui) throws EZMealPlanException {
        boolean isValidUserInput = checkValidUserInput(filterOrSelect);
        if (!isValidUserInput) {
            logger.severe("Huge issue detected! The user input format remains invalid despite " +
                    "passing all the checks for input formatting error.");
        }
        assert isValidUserInput;
        List<Meal> filteredMealList = getFilteredMealList(filterOrSelect, mealManager);
        if (filteredMealList.isEmpty()) {
            System.out.println("The filtered meal list is empty.");
            return;
        }
        String indexSubstring = getIndexSubstring();
        int inputIndex = checkValidParse(indexSubstring);
        Meal selectedMeal = checkValidInputIndex(inputIndex, filteredMealList);
        List<Meal> userMealList = mealManager.getUserMealList();
        mealManager.add(selectedMeal, userMealList);
        String mealListName = "user meal list";
        ui.printAddMealMessage(selectedMeal, userMealList, mealListName);
    }

    private String getIndexSubstring() {
        int afterSelectIndex = this.validUserInput.indexOf(filterOrSelect) + filterOrSelect.length();
        boolean isContainsIng = this.validUserInput.contains(ing);
        boolean isContainsMcost = this.validUserInput.contains(mcost);
        boolean isContainsMname = this.validUserInput.contains(mname);
        String indexSubstring;
        if (isContainsIng) {
            int ingIndex = this.validUserInput.indexOf(ing);
            indexSubstring = this.validUserInput.substring(afterSelectIndex, ingIndex).trim();
        } else if (isContainsMcost) {
            int mcostIndex = this.validUserInput.indexOf(mcost);
            indexSubstring = this.validUserInput.substring(afterSelectIndex, mcostIndex).trim();
        } else if (isContainsMname) {
            int mnameIndex = this.validUserInput.indexOf(mname);
            indexSubstring = this.validUserInput.substring(afterSelectIndex, mnameIndex).trim();
        } else {
            indexSubstring = this.validUserInput.substring(afterSelectIndex).trim();
        }
        return indexSubstring;
    }

    private Meal checkValidInputIndex(int inputIndex, List<Meal> mealList) throws EZMealPlanException {
        Meal selectedMeal;
        int indexAdjustment = 1;
        int actualIndex = inputIndex - indexAdjustment;
        try {
            selectedMeal = mealList.get(actualIndex);
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            throw new InvalidSelectIndexException();
        }
        return selectedMeal;
    }

    private int checkValidParse(String indexSubstring) throws EZMealPlanException {
        int inputIndex;
        try {
            inputIndex = Integer.parseInt(indexSubstring);
        } catch (NumberFormatException numberFormatException) {
            throw new InvalidSelectIndexException();
        }
        return inputIndex;
    }
}

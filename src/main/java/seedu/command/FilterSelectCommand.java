package seedu.command;

import seedu.checkers.FilterChecker;
import seedu.checkers.FilterSelectChecker;
import seedu.checkers.SelectChecker;
import seedu.exceptions.EZMealPlanException;
import seedu.exceptions.InvalidMcostException;

import seedu.food.Meal;
import seedu.logic.MealManager;
import seedu.ui.UserInterface;

import java.util.List;

public abstract class FilterSelectCommand extends Command {
    String filterOrSelect;
    String ing = "/ing";
    String mname = "/mname";
    String mcost = "/mcost";

    protected boolean checkValidUserInput(String filterOrSelect) throws EZMealPlanException {
        FilterSelectChecker checker = getFilterSelectChecker(filterOrSelect);
        assert checker != null;
        checker.check();
        return checker.isPassed();
    }

    private FilterSelectChecker getFilterSelectChecker(String filterOrSelect) {
        FilterSelectChecker checker = null;
        String filter = "filter";
        String select = "select";
        if (filterOrSelect.equals(filter)) {
            checker = new FilterChecker(validUserInput, filter);
        } else if (filterOrSelect.equals(select)) {
            checker = new SelectChecker(validUserInput, select);
        }
        return checker;
    }

    protected List<Meal> getFilteredMealList(String filterOrSelect, MealManager mealManager) throws EZMealPlanException {
        boolean isContainIng = this.validUserInput.contains(ing);
        boolean isContainMname = this.validUserInput.contains(mname);
        boolean isContainMcost = this.validUserInput.contains(mcost);
        List<Meal> mealList;
        if (isContainIng) {
            mealList = filterByIngList(mealManager);
        } else if (isContainMname) {
            mealList = filterByMnameList(mealManager);
        } else if (isContainMcost) {
            mealList = filterByMcostList(mealManager);
        } else {
            mealList = mealManager.getMainMealList();
        }
        return mealList;
    }

    private List<Meal> filterByMcostList(MealManager mealManager) throws EZMealPlanException {
        int afterMcostIndex = this.validUserInput.indexOf(mcost) + mcost.length();
        String mcostInput = this.validUserInput.substring(afterMcostIndex).trim();
        double mcostDouble = checkValidMcostPrice(mcostInput);
        return mealManager.filteringByMcost(mcostDouble);
    }

    private double checkValidMcostPrice(String mcostInput) throws EZMealPlanException {
        double mcostDouble;
        try {
            mcostDouble = Double.parseDouble(mcostInput);
            checkMcostPositive(mcostDouble);
        } catch (NumberFormatException numberFormatException) {
            throw new InvalidMcostException();
        }
        return mcostDouble;
    }

    private static void checkMcostPositive(double mcostDouble) {
        int zero = 0;
        if (mcostDouble <= zero) {
            throw new NumberFormatException();
        }
    }

    private List<Meal> filterByMnameList(MealManager mealManager) {
        int afterMnameIndex = this.validUserInput.indexOf(mname) + mname.length();
        String mnameInput = this.validUserInput.substring(afterMnameIndex).trim();
        String splitRegex = "\\s*,\\s*";
        String[] mealNameArray = mnameInput.split(splitRegex);
        return mealManager.filteringByMname(mealNameArray);
    }

    private List<Meal> filterByIngList(MealManager mealManager) {
        int afterIngIndex = this.validUserInput.indexOf(ing) + ing.length();
        String ingInput = this.validUserInput.substring(afterIngIndex).trim();
        String splitRegex = "\\s*,\\s*";
        String[] ingredientsArray = ingInput.split(splitRegex);
        return mealManager.filteringByIng(ingredientsArray);
    }

    public void printFilteredMealList(List<Meal> mealList, UserInterface ui) {
        String mealCost = "meal cost";
        String mealName = "meal name";
        String ingredients = "ingredient(s)";
        if (mealList.isEmpty()) {
            System.out.println("The filtered meal list is empty.");
            return;
        }
        if (this.validUserInput.contains(mcost)) {
            String mcostMessage = "the meal list filtered by " + mealCost;
            ui.printMealList(mealList, mcostMessage);
        } else if (this.validUserInput.contains(ing)) {
            String ingMessage = "the meal list filtered by " + ingredients;
            ui.printMealList(mealList, ingMessage);
        } else if (this.validUserInput.contains(mname)) {
            String mnameMessage = "the meal list filtered by " + mealName;
            ui.printMealList(mealList, mnameMessage);
        }
    }
}

package seedu.exceptions;

import seedu.meallist.RecipesList;
import seedu.meallist.MealList;

public class ViewIndexOutOfRangeException extends EZMealPlanException {
    int inputIndex;
    int listSize;
    String listName;
    String mainMealListName = "main meal list";
    String userMealListName = "user meal list";

    public ViewIndexOutOfRangeException(int inputIndex, MealList mealList) {
        this.inputIndex = inputIndex;
        this.listSize = mealList.size();
        this.listName = mealList instanceof RecipesList ? mainMealListName : userMealListName;
    }

    @Override
    public String getMessage() {
        return "The index provided for the " + listName + " (" + inputIndex + ") is out of range.\n" +
                "It must be between 1 and " + listSize + ".\n";
    }
}

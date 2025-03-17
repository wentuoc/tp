package seedu.exceptions;

public class DuplicateMealException extends EZMealPlanException {
    String mealName;
    String listName;

    public DuplicateMealException(String mealName, String listName) {
        this.mealName = mealName;
        this.listName = listName;
    }

    @Override
    public String getMessage() {
        return "This meal: " + mealName + " already exists in the " + listName + ".";
    }
}

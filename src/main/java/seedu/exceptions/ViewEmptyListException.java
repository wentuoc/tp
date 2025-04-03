package seedu.exceptions;

public class ViewEmptyListException extends EZMealPlanException {

    @Override
    public String getMessage() {
        return "The meal list is empty.\n";
    }
}

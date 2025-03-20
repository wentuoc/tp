package seedu.exceptions;

public class InvalidViewKeywordException extends EZMealPlanException {
    @Override
    public String getMessage() {
        return "Only 1 of the keywords '/m' (main meal list) or '/u' (user meal list) is allowed" +
                " and must be present in the 'view' command.\n";
    }
}

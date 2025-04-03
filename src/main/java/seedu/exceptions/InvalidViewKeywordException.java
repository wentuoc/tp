package seedu.exceptions;

public class InvalidViewKeywordException extends EZMealPlanException {
    @Override
    public String getMessage() {
        return "Only 1 of the keywords '/r' (recipes list) or '/w' (wishlist) is allowed" +
                " and must be present in the 'view' command.\n";
    }
}

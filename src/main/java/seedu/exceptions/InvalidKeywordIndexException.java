package seedu.exceptions;

public class InvalidKeywordIndexException extends EZMealPlanException {
    String mainOrUser;

    public InvalidKeywordIndexException(String mainOrUser) {
        this.mainOrUser = mainOrUser;
    }

    @Override
    public String getMessage() {
        return "'" + mainOrUser + "' must be present after the 'view' keyword in the 'view' command.\n";
    }
}

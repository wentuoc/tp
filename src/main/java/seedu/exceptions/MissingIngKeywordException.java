package seedu.exceptions;

public class MissingIngKeywordException extends EZMealPlanException {
    private String command;
    public MissingIngKeywordException(String message) {
        command = message;
    }

    @Override
    public String getMessage() {
        return String.format("""
            "/ing" keyword cannot be missing from the %s command.
            """, command);
    }
}

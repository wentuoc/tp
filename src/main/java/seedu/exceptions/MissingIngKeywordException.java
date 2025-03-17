package seedu.exceptions;

public class MissingIngKeywordException extends EZMealPlanException {
    @Override
    public String getMessage() {
        return """
                "/ing" keyword cannot be missing from the "create" command.
                """;
    }
}

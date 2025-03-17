package seedu.exceptions;

public class MissingMealNameException extends EZMealPlanException {
    public String getMessage() {
        return """
                The meal name cannot be missing from the "create" command.
                It must be present after the "/mname" keyword and before "/ing" keyword.
                """;
    }
}

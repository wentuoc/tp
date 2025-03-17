package seedu.exceptions;

public class MissingIngredientException extends EZMealPlanException {
    @Override
    public String getMessage() {
        return """
                The ingredient(s) cannot be missing from the "create" command.
                It must be present after the "/ing" keyword.
                """;
    }
}

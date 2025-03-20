package seedu.exceptions;

public class RemoveFormatException extends EZMealPlanException {
    String command;

    public RemoveFormatException(String command) {
        this.command = command;
    }

    @Override
    public String getMessage() {
        return "The index of meal to be removed must appear in your command: " + command;
    }
}

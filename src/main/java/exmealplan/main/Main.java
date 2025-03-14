package exmealplan.main;

import exmealplan.command.Command;
import exmealplan.logic.MealManager;
import exmealplan.ui.UserInterface;
import exmealplan.parser.Parser;

public class Main {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        MealManager mealManager = new MealManager();
        ui.printGreetingMessage();
        String userInput;
        while (true) {

            userInput = ui.readInput();
            // extracts out the command from the user input
            Command command = Parser.parse(userInput);
            // Executes the command parsed out
            command.execute(mealManager, ui);

            if (command.isExit()) {
                break;
            }
        }
    }
}

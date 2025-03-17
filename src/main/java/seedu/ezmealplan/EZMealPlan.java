package seedu.ezmealplan;

import seedu.command.Command;
import seedu.exceptions.EZMealPlanException;
import seedu.logic.MealManager;
import seedu.ui.UserInterface;
import seedu.parser.Parser;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class EZMealPlan {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static void main(String[] args) {
        String fileName = "EZMealPlan.log";
        setupLogger(fileName);
        UserInterface ui = new UserInterface();
        MealManager mealManager = new MealManager();
        ui.printGreetingMessage();
        String userInput;
        while (true) {
            ui.prompt();
            userInput = ui.readInput();
            // extracts out the command from the user input
            Command command = Parser.parse(userInput);
            executeCommand(command, mealManager, ui);
            if (command.isExit()) {
                break;
            }
        }
    }

    private static void executeCommand(Command command, MealManager mealManager, UserInterface ui) {
        try {
            // Executes the command parsed out
            command.execute(mealManager, ui);
        } catch (EZMealPlanException ezMealPlanException) {
            ui.printErrorMessage(ezMealPlanException);
        }
    }

    private static void setupLogger(String fileName) {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.ALL);
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.WARNING);
        logger.addHandler(consoleHandler);
        createLogFile(fileName);
    }

    private static void createLogFile(String fileName) {
        try {
            FileHandler fileHandler = new FileHandler(fileName, true);
            fileHandler.setLevel(Level.FINE);
            logger.addHandler(fileHandler);
        } catch (IOException ioException) {
            logger.log(Level.WARNING, "File logger is not working.", ioException);
        }
    }
}

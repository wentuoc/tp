package seedu.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.exceptions.EZMealPlanException;
import seedu.food.Meal;
import seedu.logic.MealManager;
import seedu.meallist.Meals;
import seedu.ui.UserInterface;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ClearCommandTest {

    private static final Logger logger = Logger.getLogger(ClearCommandTest.class.getName());
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private UserInterface ui;
    private MealManager mealManager;

    // Use the system line separator in expected outputs
    private final String ls = System.lineSeparator();

    public ClearCommandTest() {
        String fileName = "ClearCommandTest.log";
        setupLogger(fileName);
    }

    private static void setupLogger(String fileName) {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.ALL);

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.INFO);
        logger.addHandler(consoleHandler);

        createLogFile(fileName);
    }

    private static void createLogFile(String fileName) {
        try {
            FileHandler fileHandler = new FileHandler(fileName, true);
            fileHandler.setLevel(Level.FINE);
            logger.addHandler(fileHandler);
        } catch (IOException ioException) {
            logger.log(Level.SEVERE, "File logger is not working.", ioException);
        }
    }

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent)); // Redirect System.out to capture output
        ui = new UserInterface();
        mealManager = new MealManager();
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut); // Restore original System.out
        outContent.reset();         // Reset captured output
    }

    /**
     * Tests that the ClearCommand clears the user meal list and prints the expected message.
     */
    @Test
    void clearsUserMeals_noInputs_printsMessage() throws EZMealPlanException {
        logger.fine("running execute_clearsUserMeals_printsMessage()");

        // Prepare userMeals with some meals
        Meals userMeals = mealManager.getUserMeals();
        userMeals.addMeal(new Meal("Chicken Rice"));
        userMeals.addMeal(new Meal("Pasta"));

        // Ensure userMealList is not empty before clearing
        assertEquals(2, userMeals.getList().size(), "User meal list should contain 2 meals before clearing.");

        // Execute ClearCommand
        ClearCommand clearCommand = new ClearCommand();
        clearCommand.execute(mealManager, ui);

        // Verify userMeals is now empty
        assertTrue(userMeals.getList().isEmpty(), "User meal list should be empty after ClearCommand.");

        // Verify output
        String expectedOutput = "All meals cleared from your meal list!" + ls;
        assertEquals(expectedOutput, outContent.toString(), "Command output does not match expected message.");

        logger.info("execute_clearsUserMeals_printsMessage() passed");
    }
}

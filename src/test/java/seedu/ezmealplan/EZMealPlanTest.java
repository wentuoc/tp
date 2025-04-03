//@@author olsonwangyj
package seedu.ezmealplan;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

public class EZMealPlanTest {
    private static final Logger logger = Logger.getLogger(EZMealPlanTest.class.getName());

    // Constructor: Set up the logger and log file for this test class.
    public EZMealPlanTest() {
        String fileName = "EZMealPlanTest.log";
        setupLogger(fileName);
    }

    // Setup logger: Reset log manager, configure console handler and add file handler.
    private static void setupLogger(String fileName) {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.ALL);
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.INFO);
        logger.addHandler(consoleHandler);
        createLogFile(fileName);
    }

    // Create file handler for logging.
    private static void createLogFile(String fileName) {
        try {
            FileHandler fileHandler = new FileHandler(fileName, true);
            fileHandler.setLevel(Level.FINE);
            logger.addHandler(fileHandler);
        } catch (Exception e) {
            logger.severe("File logger is not working: " + e.getMessage());
        }
    }

    /**
     * Test the main integration flow of the application.
     * <p>
     * The test simulates user inputs for all major commands:
     * 1. list                   - Display global recipes (Recipe List)
     * 2. meal                   - Display user's wish list (User Meal List)
     * 3. create ...             - Create a new recipe
     * 4. filter /mname testmeal - Filter recipes by name ("testMeal")
     * 5. select 1               - Select the first filtered recipe into the wish list
     * 6. clear                  - Clear the wish list
     * 7. help list              - Display help for the list command
     * 8. remove 1               - Remove recipe at index 1 from the wish list
     * 9. view /m 1              - View details (ingredients) of recipe at index 1 from the global list
     * 10. delete 1              - Delete recipe at index 1 from the global list
     * 11. buy /ing ingredient1 (1.0)     - Buy ingredient1 with price 1.0 (update inventory)
     * 12. consume /ing ingredient2         - Consume ingredient2 (update inventory)
     * 13. recommend             - Recommend a recipe based on wish list & inventory
     * 14. bye                   - Exit the program
     * <p>
     * The test verifies that the output contains key messages indicating correct execution.
     */
    @Test
    public void testMainIntegrationAllCommands() {
        logger.fine("Starting testMainIntegration_AllCommands()");

        // Simulate user input covering all major commands.
        String simulatedInput = String.join(System.lineSeparator(),
                "list",
                "meal",
                "create /mname testMeal /ing ingredient1 (1.0), ingredient2 (2.0)",
                "filter /mname testmeal",
                "select 1",
                "clear",
                "help list",
                "remove 1",
                "view /m 1",
                "delete 1",
                "buy /ing ingredient1 (1.0)",
                "consume /ing ingredient2",
                "recommend",
                "bye"
        ) + System.lineSeparator();
        logger.fine("Simulated input: " + simulatedInput);

        // Backup original System.in and System.out.
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        // Redirect input and output streams.
        ByteArrayInputStream testIn = new ByteArrayInputStream(simulatedInput.getBytes());
        ByteArrayOutputStream testOut = new ByteArrayOutputStream();

        try {
            System.setIn(testIn);
            System.setOut(new PrintStream(testOut));

            // Execute main program.
            EZMealPlan.main(new String[0]);
        } finally {
            // Restore original streams.
            System.setIn(originalIn);
            System.setOut(originalOut);
        }

        // Convert captured output to lowercase for case-insensitive matching.
        String output = testOut.toString().toLowerCase();
        logger.fine("Captured output: " + output);

        // 1. Verify "list" command output.
        assertTrue(output.contains("main list")
                        || output.contains("no meals found in the main list"),
                "List command should display " +
                        "global recipes or indicate no recipes found.");

        // 2. Verify "meal" command output.
        assertTrue(output.contains("your meal list")
                        || output.contains("no meals found in your meal list"),
                "Meal command should display wish list or indicate it is empty.");

        // 3. Verify "create" command output: check that 'testmeal' is added.
        assertTrue(output.contains("testmeal") && output.contains("added"),
                "Create command should confirm " +
                        "that 'testmeal' was added to the global recipe list.");

        // 4. Verify "filter" command output: check
        // that filtered results include 'testmeal' or appropriate message.
        assertTrue(output.contains("testmeal")
                        || output.contains("cannot find any meal names with"),
                "Filter command should return filtered results or an appropriate message.");

        // 5. Verify "select" command output:
        // should contain confirmation message indicating recipe was added to the wish list.
        assertTrue(output.contains("successfully added a meal:")
                        && output.contains("user meal list"),
                "Select command should confirm " +
                        "that the recipe was added to the wish list.");

        // 6. Verify "clear" command output.
        assertTrue(output.contains("cleared"),
                "Clear command should indicate that the wish list has been cleared.");

        // 7. Verify "help list" command output.
        assertTrue(output.contains("sample input: list")
                        && output.contains("sample output:"),
                "Help command should display detailed help for the list command.");

        // 8. Verify "remove" command output.
        assertTrue(output.contains("removed"),
                "Remove command should confirm " +
                        "that a recipe was removed from the wish list.");

        // 9. Verify "view" command output: should display ingredient list heading.
        assertTrue(output.contains("here are the ingredients for"),
                "View command should display recipe details with ingredients.");

        // 10. Verify "delete" command output.
        assertTrue(output.contains("has been removed from the global meal list!"),
                "Delete command should indicate " +
                        "that a recipe was removed from the global recipe list.");

        // 11. Verify "buy" command output: should contain "ingredient1 bought".
        assertTrue(output.contains("ingredient1 bought"),
                "Buy command should confirm that the ingredient was added to inventory.");

        // 12. Verify "consume" command output: should contain "ingredient2 consumed".
        assertTrue(output.contains("ingredient2 consumed") || output.contains("not found"),
                "Consume command should confirm that the ingredient was consumed.");

        // 13. Verify "recommend" command output.
        assertTrue(output.contains("recommend") || output.contains("shortfall"),
                "Recommend command should output " +
                        "recommendations and indicate ingredient shortfall if any.");

        // 14. Verify "bye" command output.
        assertTrue(output.contains("bye") || output.contains("exited")
                        || output.contains("hope to see you again"),
                "Program should exit gracefully after 'bye' command.");

        logger.info("testMainIntegration_AllCommands() passed");
    }
}

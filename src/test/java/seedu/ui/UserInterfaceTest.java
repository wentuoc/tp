package seedu.ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.exceptions.EZMealPlanException;
import seedu.exceptions.InvalidPriceException;
import seedu.food.Ingredient;
import seedu.food.Meal;
import seedu.meallist.MainList;
import seedu.meallist.MealList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserInterfaceTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private UserInterface ui;

    @BeforeEach
    void setUp() {
        // Redirect System.out to our ByteArrayOutputStream so we can capture output
        System.setOut(new PrintStream(outContent));
        ui = new UserInterface();
    }

    @AfterEach
    void tearDown() {
        // Restore original System.out
        System.setOut(originalOut);
        outContent.reset();
    }

    @Test
    void testPrintGreetingMessage_displaysCorrectText() {
        ui.printGreetingMessage();
        String output = outContent.toString();
        assertTrue(output.contains("Hello! This is EzMealPlan"),
                "Should contain greeting text: 'Hello! This is EzMealPlan'");
        assertTrue(output.contains("Let me help you in planning your meals."),
                "Should contain help text: 'Let me help you in planning your meals.'");
    }

    @Test
    void testPrintGoodbye_displaysCorrectText() {
        ui.printGoodbye();
        String output = outContent.toString();
        assertTrue(output.contains("Bye. Hope to see you again soon!"),
                "Should contain goodbye text: 'Bye. Hope to see you again soon!'");
    }

    @Test
    void testPrintUnknownCommand_displaysErrorMessage() {
        ui.printUnknownCommand("unknown_cmd");
        String output = outContent.toString();
        assertTrue(output.contains("Invalid command: unknown_cmd"),
                "Should mention invalid command");
        assertTrue(output.contains("me no understand what you talking."),
                "Should show the custom error message");
    }

    @Test
    void testPrintErrorMessage_displaysExceptionMessage() {
        Exception e = new Exception("Test exception message");
        ui.printErrorMessage(e);
        String output = outContent.toString();
        assertTrue(output.contains("Test exception message"),
                "Should display the exception's message");
    }

    @Test
    void testPrintAddMealMessage_displaysExpectedOutput() throws EZMealPlanException {
        Meal mockMeal = new Meal("Chicken Rice");
        MealList mockMealList = new MainList();
        mockMealList.addMeal(mockMeal);

        ui.printAddMealMessage(mockMeal, mockMealList);
        String output = outContent.toString();
        // Updated expected price from ($3.00) to ($1.00)
        assertTrue(output.contains("You have successfully added a meal: Chicken Rice ($1.00) into main meal list."),
                "Should display success add meal message");
        assertTrue(output.contains("Here are the meals in main meal list:"),
                "Should list meals in main meal list");
    }

    @Test
    void testPrintIngredientList_displaysExpectedOutput() throws InvalidPriceException {
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Chicken Breast", 2.5));
        ingredients.add(new Ingredient("Rice", 1.0));

        ui.printIngredientList(ingredients);
        String output = outContent.toString();

        // Note that printIngredientList uses 'this.toString()' in the heading.
        assertTrue(output.contains("Here are the ingredients for"),
                "Should mention 'Here are the ingredients for'");
        assertTrue(output.contains("Chicken Breast ($2.50)"),
                "Should display first ingredient");
        assertTrue(output.contains("Rice ($1.00)"),
                "Should display second ingredient");
    }

    @Test
    void testPrintMealList_displaysExpectedOutput() throws InvalidPriceException {
        List<Meal> meals = List.of(
                new Meal("Chicken Rice"),
                new Meal("Fish Ball Noodles")
        );
        ui.printMealList(meals, "main meal list");
        String output = outContent.toString();

        assertTrue(output.contains("Here are the meals in main meal list:"),
                "Should display heading for meal list");
        // Updated expected price from ($3.00) to ($1.00)
        assertTrue(output.contains("1. Chicken Rice ($1.00)"),
                "Should list first meal");
        // For the second meal, assuming its default price is also $1.00,
        // update the expected string accordingly:
        assertTrue(output.contains("2. Fish Ball Noodles ($1.00)"),
                "Should list second meal");
    }

    @Test
    void testPrintRemovedMessage_displaysExpectedOutput() throws InvalidPriceException {
        Meal mockMeal = new Meal("Chicken Rice");
        ui.printRemovedMessage(mockMeal, 2);
        String output = outContent.toString();
        // Updated expected price from ($3.00) to ($1.00)
        assertTrue(output.contains("Chicken Rice ($1.00) has been removed from your meal list!"),
                "Should indicate which meal was removed");
        assertTrue(output.contains("You have 2 meals in your meal list."),
                "Should display updated meal list size");
    }

    @Test
    void testPrompt_displaysPromptMessage() {
        ui.prompt();
        String output = outContent.toString();
        assertTrue(output.contains("How may I help you?"),
                "Should prompt user for input");
    }

    @Test
    void testPrintByeCommandHelp_displaysExpectedOutput() {
        ui.printByeCommandHelp();
        String output = outContent.toString();
        assertTrue(output.contains("Entering the bye command will gracefully exits the software"),
                "Should mention the bye command usage");
        assertTrue(output.contains("Sample input: bye"),
                "Should show sample input");
        assertTrue(output.contains("Sample output: Bye. Hope to see you again soon!"),
                "Should show sample output");
    }

    @Test
    void testPrintGeneralHelp_displaysExpectedOutput() {
        ui.printGeneralHelp();
        String output = outContent.toString();
        assertTrue(output.contains("you have not entered any command line options"),
                "Should mention general help message");
    }

    @Test
    void testPrintMealCommandHelp_displaysExpectedOutput() {
        ui.printMealCommandHelp();
        String output = outContent.toString();
        assertTrue(output.contains("Entering the meal command will list out all the meals"),
                "Should mention meal command usage");
        assertTrue(output.contains("Sample input: meal"),
                "Should show sample input");
    }

    @Test
    void testPrintListCommandHelp_displaysExpectedOutput() {
        ui.printListCommandHelp();
        String output = outContent.toString();
        assertTrue(output.contains("Entering the list command will list out all the meals from the main list."),
                "Should mention list command usage");
        assertTrue(output.contains("Sample input: list"),
                "Should show sample input");
    }

    @Test
    void testPrintCreateCommandHelp_displaysExpectedOutput() {
        ui.printCreateCommandHelp();
        String output = outContent.toString();
        assertTrue(output.contains("Entering the create command will create a new meal"),
                "Should mention create command usage");
        assertTrue(output.contains("Sample input: create /mname Hokkien Mee"),
                "Should show sample input");
        assertTrue(output.contains("Sample output:"),
                "Should show sample output section");
    }

    @Test
    void testPrintRemoveCommandHelp_displaysExpectedOutput() {
        ui.printRemoveCommandHelp();
        String output = outContent.toString();
        assertTrue(output.contains("Entering the remove command will remove a meal"),
                "Should mention remove command usage");
        assertTrue(output.contains("Sample input: remove 1"),
                "Should show sample input");
    }

    @Test
    void testPrintSelectCommandHelp_displaysExpectedOutput() {
        ui.printSelectCommandHelp();
        String output = outContent.toString();
        assertTrue(output.contains("Entering the select command will add the selected meal"),
                "Should mention select command usage");
        assertTrue(output.contains("Sample input: select 1"),
                "Should show sample input");
    }

    @Test
    void testPrintFilterCommandHelp_displaysExpectedOutput() {
        ui.printFilterCommandHelp();
        String output = outContent.toString();
        assertTrue(output.contains("Entering the filter command will filter a meal in the main list"),
                "Should mention filter command usage");
        assertTrue(output.contains("Sample input: filter /mcost 5.50"),
                "Should show sample input");
        assertTrue(output.contains("Sample input: filter /ing  Fish Ball"),
                "Should show second sample input");
        assertTrue(output.contains("Sample input: filter /manme Hokkien Mee"),
                "Should show third sample input");
    }

    @Test
    void testPrintDeleteCommandHelp_displaysExpectedOutput() {
        ui.printDeleteCommandHelp();
        String output = outContent.toString();
        assertTrue(output.contains("Entering the delete command will delete a meal"),
                "Should mention delete command usage");
        assertTrue(output.contains("Sample input: delete 1"),
                "Should show sample input");
    }

    @Test
    void testPrintViewCommandHelp_displaysExpectedOutput() {
        ui.printViewCommandHelp();
        String output = outContent.toString();
        assertTrue(output.contains("Entering the view command will view a view all the ingredients"),
                "Should mention view command usage");
        assertTrue(output.contains("Sample input: view 1"),
                "Should show sample input");
    }

    @Test
    void testPrintClearCommandHelp_displaysExpectedOutput() {
        ui.printClearCommandHelp();
        String output = outContent.toString();
        assertTrue(output.contains("Entering the clear command will clear all the meals"),
                "Should mention clear command usage");
        assertTrue(output.contains("Sample input: clear"),
                "Should show sample input");
    }

    @Test
    void testPrintHelpCommandHelp_displaysExpectedOutput() {
        ui.printHelpCommandHelp();
        String output = outContent.toString();
        assertTrue(output.contains("Entering the help command followed by the command that requires help"),
                "Should mention help command usage");
        assertTrue(output.contains("Sample input: help bye"),
                "Should show sample input");
        assertTrue(output.contains("Sample output:"),
                "Should show sample output section");
    }
}

package seedu.ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.exceptions.EZMealPlanException;
import seedu.exceptions.InvalidPriceException;
import seedu.food.Ingredient;
import seedu.food.Meal;
import seedu.meallist.MainMeals;
import seedu.meallist.Meals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserInterfaceTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final String ls = System.lineSeparator();
    private UserInterface ui;

    @BeforeEach
    void setUp() {
        // Redirect System.out to capture printed output for testing.
        System.setOut(new PrintStream(outContent));
        ui = new UserInterface();
    }

    @AfterEach
    void tearDown() {
        // Restore original System.out after each test and reset captured output.
        System.setOut(originalOut);
        outContent.reset();
    }

    @Test
    void shouldDisplayGreetingMessage_whenPrintGreetingMessageIsCalled() {
        ui.printGreetingMessage();
        String expected = "Hello! This is EzMealPlan" + ls + "Let me help you in planning your meals." + ls;
        assertEquals(expected, outContent.toString(), "Greeting message output does not match.");
    }

    @Test
    void shouldDisplayGoodbyeMessage_whenPrintGoodbyeIsCalled() {
        ui.printGoodbye();
        // Note: printGoodbye() uses System.out.print so no extra newline is appended.
        String expected = "Bye. Hope to see you again soon!";
        assertEquals(expected, outContent.toString(), "Goodbye message output does not match.");
    }

    @Test
    void shouldDisplayUnknownCommandMessage_whenPrintUnknownCommandIsCalled() {
        ui.printUnknownCommand("unknown_cmd");
        String expected = "Invalid command: unknown_cmd" + ls + "me no understand what you talking." + ls;
        assertEquals(expected, outContent.toString(), "Unknown command message output does not match.");
    }

    @Test
    void shouldDisplayErrorMessage_whenPrintErrorMessageIsCalled() {
        Exception e = new Exception("Test exception message");
        ui.printErrorMessage(e);
        String expected = "Test exception message" + ls;
        assertEquals(expected, outContent.toString(), "Error message output does not match.");
    }

    @Test
    void shouldDisplayAddMealMessage_whenPrintAddMealMessageIsCalled() throws EZMealPlanException {
        Meal mockMeal = new Meal("Chicken Rice");
        Meals mockMealList = new MainMeals();
        mockMealList.addMeal(mockMeal);

        ui.printAddMealMessage(mockMeal, mockMealList);
        String expected = "You have successfully added a meal: Chicken Rice ($1.00) into main meal list." + ls +
                "Here are the meals in main meal list:" + ls +
                "    1. Chicken Rice ($1.00)" + ls +
                ls +
                "Currently, you have 1 meals in main meal list." + ls;
        assertEquals(expected, outContent.toString(), "Add meal message output does not match.");
    }

    @Test
    void shouldDisplayIngredientList_whenPrintIngredientListIsCalled() throws InvalidPriceException {
        Meal meal = new Meal("Test Meal");
        meal.getIngredientList().add(new Ingredient("Chicken Breast", 2.5));
        meal.getIngredientList().add(new Ingredient("Rice", 1.0));

        ui.printIngredientList(meal);
        String expected = "Here are the ingredients for " + meal + ":" + ls +
                "    1. Chicken Breast ($2.50)" + ls +
                "    2. Rice ($1.00)" + ls + ls;
        assertEquals(expected, outContent.toString(), "Ingredient list output does not match.");
    }

    @Test
    void shouldDisplayMealList_whenPrintMealListIsCalled() throws InvalidPriceException {
        List<Meal> meals = List.of(new Meal("Chicken Rice"), new Meal("Fish Ball Noodles"));
        ui.printMealList(meals, "main meal list");
        String expected = "Here are the meals in main meal list:" + ls +
                "    1. Chicken Rice ($1.00)" + ls +
                "    2. Fish Ball Noodles ($1.00)" + ls + ls;
        assertEquals(expected, outContent.toString(), "Meal list output does not match.");
    }

    @Test
    void shouldDisplayRemovedMessage_whenPrintRemovedMessageIsCalled() throws InvalidPriceException {
        Meal mockMeal = new Meal("Chicken Rice");
        ui.printRemovedMessage(mockMeal, 2);
        String expected = "Chicken Rice ($1.00) has been removed from your meal list!" + ls +
                "You have 2 meals in your meal list.";
        assertEquals(expected, outContent.toString(), "Removed message output does not match.");
    }

    @Test
    void shouldDisplayDeletedMessage_whenPrintDeletedMessageIsCalled() throws InvalidPriceException {
        Meal mockMeal = new Meal("Chicken Rice");
        ui.printDeletedMessage(mockMeal, 5);
        String expected = "Chicken Rice ($1.00) has been removed from the global meal list!" + ls +
                "There are now 5 meals in the global meal list.";
        assertEquals(expected, outContent.toString(), "Deleted message output does not match.");
    }

    @Test
    void shouldDisplayPromptMessage_whenPromptIsCalled() {
        ui.prompt();
        String expected = "How may I help you?" + ls;
        assertEquals(expected, outContent.toString(), "Prompt output does not match.");
    }

    @Test
    void shouldDisplayClearedListMessage_whenPrintClearedListIsCalled() {
        ui.printClearedList();
        String expected = "All meals cleared from your meal list!" + ls;
        assertEquals(expected, outContent.toString(), "Cleared list message output does not match.");
    }

    @Test
    void shouldDisplayByeCommandHelp_whenPrintByeCommandHelpIsCalled() {
        ui.printByeCommandHelp();
        String expected = "Entering the bye command will gracefully exits the software" + ls +
                "Sample input: bye" + ls +
                "Sample output: Bye. Hope to see you again soon!" + ls;
        assertEquals(expected, outContent.toString(), "Bye command help output does not match.");
    }

    @Test
    void shouldDisplayGeneralHelp_whenPrintGeneralHelpIsCalled() {
        ui.printGeneralHelp();
        String expected = "you have not entered any command line options" + ls;
        assertEquals(expected, outContent.toString(), "General help output does not match.");
    }

    @Test
    void shouldDisplayMealCommandHelp_whenPrintMealCommandHelpIsCalled() {
        ui.printMealCommandHelp();
        String expected = "Entering the meal command will list out all the meals you have selected from the" +
                " main list." + ls +
                "Sample input: meal" + ls +
                "Sample output:" + ls +
                "               1. Chicken Rice" + ls +
                "               2. Fish Ball Noodles" + ls;
        assertEquals(expected, outContent.toString(), "Meal command help output does not match.");
    }

    @Test
    void shouldDisplayListCommandHelp_whenPrintListCommandHelpIsCalled() {
        ui.printListCommandHelp();
        String expected = "Entering the list command will list out all the meals from the main list." + ls +
                "Sample input: list" + ls +
                "Sample output:" + ls +
                "               1. Chicken Rice" + ls +
                "               2. Fish Ball Noodles" + ls;
        assertEquals(expected, outContent.toString(), "List command help output does not match.");
    }

    @Test
    void shouldDisplayCreateCommandHelp_whenPrintCreateCommandHelpIsCalled() {
        ui.printCreateCommandHelp();
        String expected = "Entering the create command will create a new meal" + ls +
                "Sample input: create /mname Hokkien Mee /ing yellow noodle (1), thick bee hoon (1), prawn (1.2), " +
                "egg (0.5), pork lard (0.2), squid (1.5), lime (0.1)" + ls +
                "Sample output:" + ls +
                "               1. Chicken Rice ($3.00)" + ls +
                "               2. Fish Ball Noodles ($3.00)" + ls +
                "               3. Hokkien Mee ($5.50)" + ls;
        assertEquals(expected, outContent.toString(), "Create command help output does not match.");
    }

    @Test
    void shouldDisplayRemoveCommandHelp_whenPrintRemoveCommandHelpIsCalled() {
        ui.printRemoveCommandHelp();
        String expected = "Entering the remove command will remove a meal in the user's meal list" + ls +
                "Sample input: remove 1 " + ls +
                "Sample output:" + ls +
                "               OK.  Chicken Rice ($3.00) have been removed from the meal list." + ls;
        assertEquals(expected, outContent.toString(), "Remove command help output does not match.");
    }

    @Test
    void shouldDisplaySelectCommandHelp_whenPrintSelectCommandHelpIsCalled() {
        ui.printSelectCommandHelp();
        String expected = "Entering the select command will add the selected meal in the meal list" + ls +
                "Sample input: select 1 " + ls +
                "Sample output:" + ls +
                "               OK.  Chicken Rice ($3.00) have been added to the meal list." + ls;
        assertEquals(expected, outContent.toString(), "Select command help output does not match.");
    }

    @Test
    void shouldDisplayFilterCommandHelp_whenPrintFilterCommandHelpIsCalled() {
        ui.printFilterCommandHelp();
        String expected = "Entering the filter command will filter a meal in the main list" + ls +
                "There are three filter option by cost, by ingredient or by meal name" + ls +
                "Sample input: filter /mcost 5.50" + ls +
                "Sample output:" + ls +
                "               1. Chicken Rice ($5.50)" + ls +
                "Sample input: filter /ing  Fish Ball" + ls +
                "Sample output:" + ls +
                "               1. Chicken Rice ($5.50)" + ls +
                "Sample input: filter /manme Hokkien Mee" + ls +
                "Sample output:" + ls +
                "               1. Hokkien Mee ($5.50)" + ls;
        assertEquals(expected, outContent.toString(), "Filter command help output does not match.");
    }

    @Test
    void shouldDisplayDeleteCommandHelp_whenPrintDeleteCommandHelpIsCalled() {
        ui.printDeleteCommandHelp();
        String expected = "Entering the delete command will delete a meal in the main list" + ls +
                "Sample input: delete 1 " + ls +
                "Sample output:" + ls +
                "               OK.  Chicken Rice ($3.00) have been deleted from the main list." + ls;
        assertEquals(expected, outContent.toString(), "Delete command help output does not match.");
    }

    @Test
    void shouldDisplayViewCommandHelp_whenPrintViewCommandHelpIsCalled() {
        ui.printViewCommandHelp();
        String expected = "Entering the view command will view a view all the ingredients of the selected meal" + ls +
                "Sample input: view 1 " + ls +
                "Sample output:" + ls +
                "               1. yellow noodle" + ls +
                "               2. bee hoon" + ls +
                "               3. prawn" + ls +
                "               4. egg" + ls +
                "               5. pork lard" + ls +
                "               6. squid" + ls +
                "               7. lime" + ls;
        assertEquals(expected, outContent.toString(), "View command help output does not match.");
    }

    @Test
    void shouldDisplayClearCommandHelp_whenPrintClearCommandHelpIsCalled() {
        ui.printClearCommandHelp();
        String expected = "Entering the clear command will clear all the meals in the meal list" + ls +
                "Sample input: clear" + ls + "Sample output:" + ls +
                "               The meal list has been cleared." + ls;
        assertEquals(expected, outContent.toString(), "Clear command help output does not match.");
    }

    @Test
    void shouldDisplayHelpCommandHelp_whenPrintHelpCommandHelpIsCalled() {
        ui.printHelpCommandHelp();
        String expected = "Entering the help command followed by the command that requires help will give brief " +
                "explanation of the command" + ls +
                "Sample input: help bye" + ls +
                "Sample output:" + ls +
                "                Entering the bye command will gracefully exits the software" + ls +
                "                    Sample input: bye" + ls +
                "                    Sample output: Bye. Hope to see you again soon!" + ls;
        assertEquals(expected, outContent.toString(), "Help command help output does not match.");
    }
}

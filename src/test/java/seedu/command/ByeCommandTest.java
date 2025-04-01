package seedu.command;

import seedu.exceptions.EZMealPlanException;
import seedu.food.Meal;
import seedu.logic.MealManager;
import seedu.meallist.Meals;
import seedu.storage.Storage;
import seedu.ui.UserInterface;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ByeCommandTest {
    private static final Logger logger = Logger.getLogger(ByeCommandTest.class.getName());
    final MealManager mealManager = new MealManager();
    private UserInterface ui;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    public ByeCommandTest() {
        String fileName = "ByeCommandTest.log";
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
        // Call main(null) to set up logger.
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
    public void byeCommandTest_success() {
        logger.fine("running byeCommandTest_success()");
        try {
            List<Meal> mealsList = Storage.loadPresetMeals();
            List<Meal> expectedRecipesList = getExpectedRecipesList(mealsList);
            List<Meal> expectedWishList = getExpectedWishList(mealsList);
            ByeCommand byeCommand = new ByeCommand();
            assertTrue(byeCommand.isExit());
            byeCommand.execute(mealManager, ui);
            checkOutputString();
            Storage.createListFiles();
            checkRecipesLists(expectedRecipesList);
            checkWishLists(expectedWishList);
            logger.info("byeCommandTest_success() passed");
        } catch (Exception exception) {
            ui.printErrorMessage(exception);
            fail();
        }
    }

    private void checkOutputString() {
        String expected = "Bye. Hope to see you again soon!";
        assertEquals(expected, outContent.toString(), "Goodbye message output does not match.");
    }

    private static void checkWishLists(List<Meal> expectedWishList) throws IOException {
        File wishListFile = Storage.getUserListFile();
        List<Meal> wishListFromFile = Storage.loadExistingList(wishListFile);
        assertEquals(expectedWishList, wishListFromFile, "Wish list does not match.");
    }

    private static void checkRecipesLists(List<Meal> expectedRecipesList) throws IOException {
        File recipesFile = Storage.getMainListFile();
        List<Meal> recipesFromFile = Storage.loadExistingList(recipesFile);
        assertEquals(expectedRecipesList, recipesFromFile, "Recipes list does not match.");
    }

    private List<Meal> getExpectedWishList(List<Meal> mealsList) throws EZMealPlanException {
        int three = 3;
        Meals wishList = mealManager.getUserMeals();
        return addMealsIntoList(three, mealsList, wishList);
    }

    private List<Meal> getExpectedRecipesList(List<Meal> mealsList) throws EZMealPlanException {
        int five = 5;
        Meals recipes = mealManager.getMainMeals();
        return addMealsIntoList(five, mealsList, recipes);
    }


    private List<Meal> addMealsIntoList(int number, List<Meal> mealsList, Meals meals) throws EZMealPlanException {
        List<Meal> expectedList = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            assert mealsList.get(i) != null;
            mealManager.addMeal(mealsList.get(i), meals);
            expectedList.add(mealsList.get(i));
        }
        return expectedList;
    }
}

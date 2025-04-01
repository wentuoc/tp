package seedu.command;

import seedu.exceptions.EZMealPlanException;
import seedu.food.Meal;
import seedu.logic.MealManager;
import seedu.meallist.Meals;
import seedu.storage.Storage;
import seedu.ui.UserInterface;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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
            Storage.createListFiles();
            List<Meal> mealsList = Storage.loadPresetMeals();
            List<Meal> expectedRecipesList = getExpectedRecipesList(mealsList);
            List<Meal> expectedWishList = getExpectedWishList(mealsList);
            List<File> latestFiles = saveLatestLists();
            firstHalf_byeCommandTest_success();
            secondHalf_byeCommandTest_success(expectedRecipesList, expectedWishList, latestFiles);
            logger.info("byeCommandTest_success() passed");
        } catch (Exception exception) {
            ui.printErrorMessage(exception);
            logger.severe("byeCommandTest_success() should not fail");
            fail();
        }
    }

    private void secondHalf_byeCommandTest_success(List<Meal> expectedRecipesList, List<Meal> expectedWishList,
                                                   List<File> latestFiles) throws IOException {
        Storage.createListFiles();
        checkRecipesLists(expectedRecipesList);
        checkWishLists(expectedWishList);
        restoreLatestLists(latestFiles);
    }

    private void firstHalf_byeCommandTest_success() {
        ByeCommand byeCommand = new ByeCommand();
        assertTrue(byeCommand.isExit());
        byeCommand.execute(mealManager, ui);
        checkOutputString();
    }

    private void restoreLatestLists(List<File> latestFiles) throws IOException {
        int recipesFileIndex = 0;
        int wishListFileIndex = 1;
        restoreLatestRecipes(latestFiles.get(recipesFileIndex));
        restoreLatestWishList(latestFiles.get(wishListFileIndex));
    }

    private void restoreLatestWishList(File tempWishListFile) throws IOException {
        File wishListFile = Storage.getUserListFile();
        Scanner scanner = new Scanner(tempWishListFile);
        try (FileWriter fileCleaner = new FileWriter(wishListFile);
             FileWriter fileWriter = new FileWriter(wishListFile, true)) {
            while (scanner.hasNextLine()) {
                fileWriter.append(scanner.nextLine()).append(System.lineSeparator());
            }
            scanner.close();
        }
        tempWishListFile.delete();
    }

    private void restoreLatestRecipes(File tempRecipesFile) throws IOException {
        File recipesFile = Storage.getMainListFile();
        Scanner scanner = new Scanner(tempRecipesFile);
        try (FileWriter fileCleaner = new FileWriter(recipesFile);
             FileWriter fileWriter = new FileWriter(recipesFile, true)) {
            while (scanner.hasNextLine()) {
                fileWriter.append(scanner.nextLine()).append(System.lineSeparator());
            }
            scanner.close();
        }
        tempRecipesFile.delete();
    }

    private List<File> saveLatestLists() throws IOException {
        List<File> files = new ArrayList<>();
        files.add(saveLatestRecipes());
        files.add(saveLatestWishList());
        return files;
    }

    private File saveLatestWishList() throws IOException {
        String tempWishListPath = "data/tempWishList.txt";
        File tempWishListFile = new File(tempWishListPath);
        File wishListFile = Storage.getUserListFile();
        Scanner scanner = new Scanner(wishListFile);
        try (FileWriter fileWriter = new FileWriter(tempWishListFile, true)) {
            while (scanner.hasNextLine()) {
                fileWriter.append(scanner.nextLine()).append(System.lineSeparator());
            }
            scanner.close();
        }
        return tempWishListFile;
    }

    private File saveLatestRecipes() throws IOException {
        String tempRecipesPath = "data/tempRecipes.txt";
        File tempRecipesFile = new File(tempRecipesPath);
        File recipesFile = Storage.getMainListFile();
        Scanner scanner = new Scanner(recipesFile);
        try (FileWriter fileWriter = new FileWriter(tempRecipesFile, true)) {
            while (scanner.hasNextLine()) {
                fileWriter.append(scanner.nextLine()).append(System.lineSeparator());
            }
            scanner.close();
        }
        return tempRecipesFile;
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

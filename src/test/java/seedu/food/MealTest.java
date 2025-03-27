package seedu.food;

import org.junit.jupiter.api.Test;
import seedu.exceptions.InvalidPriceException;

import java.io.IOException;
import java.util.logging.*;

import static org.junit.jupiter.api.Assertions.*;

class MealTest {
    private static final Logger logger = Logger.getLogger(MealTest.class.getName());

    public static void main(String[] args) {
        String fileName = "MealTest.log";
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

    @Test
    void equals_sameNameSameIngredient_true() throws InvalidPriceException {
        Ingredient chicken = new Ingredient("Chicken", 1);
        Meal meal1 = new Meal("Chicken Rice");
        meal1.addIngredient(chicken);
        Meal meal2 = new Meal("Chicken Rice");
        meal2.addIngredient(chicken);
        assertEquals(meal1, meal2);
    }

    @Test
    void equals_sameNameDifferentIngredient_false() throws InvalidPriceException {
        Ingredient steamedChicken = new Ingredient("Steamed Chicken", 1);
        Ingredient roastedChicken = new Ingredient("Roasted Chicken", 1);
        Meal meal1 = new Meal("Chicken Rice");
        meal1.addIngredient(steamedChicken);
        Meal meal2 = new Meal("Chicken Rice");
        meal2.addIngredient(roastedChicken);
        assertNotEquals(meal1, meal2);
    }

    @Test
    void equals_differentNameSameIngredient_false() throws InvalidPriceException {
        Ingredient chicken = new Ingredient("Chicken", 1);
        Meal meal1 = new Meal("Chicken Rice");
        meal1.addIngredient(chicken);
        Meal meal2 = new Meal("Chicken Rice Upsized");
        meal2.addIngredient(chicken);
        assertNotEquals(meal1, meal2);
    }

    @Test
    void equals_differentNameDifferentIngredient_false() throws InvalidPriceException {
        Ingredient chicken = new Ingredient("Chicken", 1);
        Ingredient duck = new Ingredient("Duck", 1.2);
        Meal meal1 = new Meal("Chicken Rice");
        meal1.addIngredient(chicken);
        Meal meal2 = new Meal("Duck Rice");
        meal2.addIngredient(duck);
        assertNotEquals(meal1, meal2);
    }

    @Test
    void equals_DifferentCapitalisationSameIngredient_true() throws InvalidPriceException {
        Ingredient chicken = new Ingredient("Chicken", 1);
        Meal meal1 = new Meal("Chicken Rice");
        meal1.addIngredient(chicken);
        Meal meal2 = new Meal("chicken rice");
        meal2.addIngredient(chicken);
        assertEquals(meal1, meal2);
    }
}
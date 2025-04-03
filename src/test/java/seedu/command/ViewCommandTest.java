package seedu.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import seedu.exceptions.EZMealPlanException;
import seedu.exceptions.ViewEmptyListException;
import seedu.exceptions.ViewIndexOutOfRangeException;
import seedu.food.Ingredient;
import seedu.food.Meal;
import seedu.logic.MealManager;
import seedu.ui.UserInterface;

public class ViewCommandTest {

    private static final Logger logger = Logger.getLogger(ViewCommandTest.class.getName());

    @BeforeAll
    public static void setupLogger() {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.ALL);
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.INFO);
        logger.addHandler(consoleHandler);

        try {
            FileHandler fileHandler = new FileHandler("ViewCommandTest.log", true);
            fileHandler.setLevel(Level.FINE);
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "File logger setup failed", e);
        }
    }

    /**
     * Define a UserInterface class for testing, to capture the params of printMealList.
     */
    public static class TestUserInterface extends UserInterface {
        Meal capturedMeal;
        List<Ingredient> capturedIngredients;

        @Override
        public void printIngredientList(Meal meal) {
            this.capturedMeal = meal;
            this.capturedIngredients = meal.getIngredientList();
        }
    }

    @Test
    public void testExecute_viewMainMeal_success() throws EZMealPlanException {

        logger.fine("Running testExecute_viewMainMeal_success()");

        MealManager mealManager = new MealManager();
        Meal meal1 = new Meal("Main Meal 1");
        Ingredient ingredient1 = new Ingredient("egg", 0.50);
        Ingredient ingredient2 = new Ingredient("rice", 1.0);
        meal1.addIngredient(ingredient1);
        meal1.addIngredient(ingredient2);

        mealManager.getMainMeals().getList().add(meal1);

        ViewCommandTest.TestUserInterface testUI = new ViewCommandTest.TestUserInterface();
        ViewCommand ViewCommand = new ViewCommand("/m 1");
        ViewCommand.execute(mealManager, testUI);

        assertEquals("Main Meal 1 ($1.50)", testUI.capturedMeal.toString());
        List<Ingredient> expectedIngredients = new ArrayList<>();
        expectedIngredients.add(ingredient1);
        expectedIngredients.add(ingredient2);
        assertIterableEquals(expectedIngredients, testUI.capturedIngredients);

        logger.info("testExecute_viewMainMeal_success passed");
    }


    @Test
    public void testExecute_viewUserMeal_success() throws EZMealPlanException {
        logger.fine("Running testExecute_viewUserMeal_success()");
        MealManager mealManager = new MealManager();
        Meal meal = new Meal("User Meal 1");
        Ingredient i1 = new Ingredient("tofu", 1.20);
        Ingredient i2 = new Ingredient("noodles", 1.80);
        meal.addIngredient(i1);
        meal.addIngredient(i2);
        mealManager.getUserMeals().getList().add(meal);

        TestUserInterface testUI = new TestUserInterface();
        ViewCommand command = new ViewCommand("/u 1");
        command.execute(mealManager, testUI);

        assertEquals("User Meal 1 ($3.00)", testUI.capturedMeal.toString());
        assertIterableEquals(List.of(i1, i2), testUI.capturedIngredients);
        logger.info("testExecute_viewUserMeal_success passed");
    }

    @Test
    public void testExecute_viewMainMeal_emptyList() throws EZMealPlanException{
        logger.fine("Running testExecute_viewMainMeal_emptyList()");
        MealManager mealManager = new MealManager();
        TestUserInterface testUI = new TestUserInterface();
        ViewCommand command = new ViewCommand("/m 1");
        assertThrows(ViewEmptyListException.class, () -> command.execute(mealManager, testUI));

        logger.info("testExecute_viewMainMeal_emptyList passed");
    }

    @Test
    public void testExecute_viewUserMeal_emptyList() throws EZMealPlanException{
        logger.fine("Running testExecute_viewUserMeal_emptyList()");
        MealManager mealManager = new MealManager();
        TestUserInterface testUI = new TestUserInterface();
        ViewCommand command = new ViewCommand("/u 5");
        assertThrows(ViewEmptyListException.class, () -> command.execute(mealManager, testUI));

        logger.info("testExecute_viewUserMeal_emptyList passed");
    }

    @Test
    public void testExecute_viewMainMeal_outOfRange() throws EZMealPlanException {
        logger.fine("Running testExecute_viewMainMeal_outOfRange()");
        MealManager mealManager = new MealManager();
        Meal meal = new Meal("Main Meal 1");
        Ingredient i1 = new Ingredient("tofu", 1.20);
        Ingredient i2 = new Ingredient("noodles", 1.80);
        meal.addIngredient(i1);
        meal.addIngredient(i2);
        mealManager.getMainMeals().getList().add(meal);

        TestUserInterface testUI = new TestUserInterface();
        ViewCommand command = new ViewCommand("/m 2");

        assertThrows(ViewIndexOutOfRangeException.class, () -> command.execute(mealManager,testUI));

        logger.info("testExecute_viewMainMeal_outOfRange passed");
    }

    @Test
    public void testExecute_viewUserMeal_outOfRange() throws EZMealPlanException{
        logger.fine("Running testExecute_viewUserMeal_outOfRange()");
        MealManager mealManager = new MealManager();
        Meal meal = new Meal("User Meal 1");
        Ingredient i1 = new Ingredient("tofu", 1.20);
        Ingredient i2 = new Ingredient("noodles", 1.80);
        meal.addIngredient(i1);
        meal.addIngredient(i2);
        mealManager.getUserMeals().getList().add(meal);

        TestUserInterface testUI = new TestUserInterface();
        ViewCommand command = new ViewCommand("/u 2");

        assertThrows(ViewIndexOutOfRangeException.class, () -> command.execute(mealManager,testUI));

        logger.info("testExecute_viewUserMeal_outOfRange passed");
    }
}



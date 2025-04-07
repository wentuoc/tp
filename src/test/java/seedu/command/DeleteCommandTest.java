package seedu.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import seedu.exceptions.EZMealPlanException;
import seedu.exceptions.EmptyListException;
import seedu.exceptions.RemoveFormatException;
import seedu.exceptions.RemoveIndexOutOfRangeException;
import seedu.food.Meal;
import seedu.logic.MealManager;
import seedu.ui.UserInterface;

public class DeleteCommandTest {

    private static final Logger logger = Logger.getLogger(DeleteCommandTest.class.getName());

    @BeforeAll
    public static void setupLogger() {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.ALL);

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.INFO);
        logger.addHandler(consoleHandler);

        try {
            FileHandler fileHandler = new FileHandler("DeleteCommandTest.log", true);
            fileHandler.setLevel(Level.FINE);
            logger.addHandler(fileHandler);
        } catch (IOException ioException) {
            logger.log(Level.SEVERE, "Failed to set up file logger", ioException);
        }
    }

    /**
     * Minimal UserInterface stub to suppress delete output.
     */
    public static class TestUI extends UserInterface {
        @Override
        public void printDeletedMessage(Meal removedMeal, int newSize) {
            // Suppress output
        }
    }

    @Test
    public void deleteCommand_validRecipeIndex_success() throws EZMealPlanException {
        logger.fine("Running deleteCommand_validRecipeIndex_success()");
        MealManager mealManager = new MealManager();
        mealManager.getRecipesList().getList().clear();

        Meal testMeal = new Meal("Egg Fried Rice");
        mealManager.getRecipesList().getList().add(testMeal);

        List<Meal> recipes = mealManager.getRecipesList().getList();
        assertEquals(1, recipes.size());
        assertEquals("Egg Fried Rice", recipes.get(0).getName());

        DeleteCommand deleteCommand = new DeleteCommand("delete 1");
        deleteCommand.execute(mealManager, new TestUI());

        assertEquals(0, recipes.size());
        logger.info("deleteCommand_validRecipeIndex_success passed");
    }

    @Test
    public void deleteCommand_extraSpacingInput_success() throws EZMealPlanException {
        logger.fine("Running deleteCommand_extraSpacingInput_success()");
        MealManager mealManager = new MealManager();
        mealManager.getRecipesList().getList().clear();

        Meal testMeal = new Meal("Soup");
        mealManager.getRecipesList().getList().add(testMeal);

        List<Meal> recipes = mealManager.getRecipesList().getList();
        assertEquals(1, recipes.size());

        DeleteCommand deleteCommand = new DeleteCommand("   delete     1   ");
        deleteCommand.execute(mealManager, new TestUI());

        assertEquals(0, recipes.size());
        logger.info("deleteCommand_extraSpacingInput_success passed");
    }

    @Test
    public void deleteCommand_emptyList_throwsEmptyListException() {
        logger.fine("Running deleteCommand_emptyList_throwsRemoveFormatException()");
        DeleteCommand deleteCommand = new DeleteCommand("delete 1");
        assertThrows(EmptyListException.class,
                () -> deleteCommand.execute(new MealManager(), new TestUI()));
        logger.info("deleteCommand_emptyList_throwsEmptyListException passed");
    }

    @Test
    public void deleteCommand_missingIndex_throwsRemoveFormatException() {
        logger.fine("Running deleteCommand_missingIndex_throwsRemoveFormatException()");
        DeleteCommand deleteCommand = new DeleteCommand("delete ");
        assertThrows(RemoveFormatException.class,
                () -> deleteCommand.execute(new MealManager(), new TestUI()));
        logger.info("deleteCommand_missingIndex_throwsRemoveFormatException passed");
    }

    @Test
    public void deleteCommand_negativeIndex_throwsRemoveFormatException() throws EZMealPlanException {
        logger.fine("Running deleteCommand_negativeIndex_RemoveIndexOutOfRangeException()");
        MealManager mealManager = new MealManager();
        mealManager.getRecipesList().getList().clear();

        Meal testMeal = new Meal("Soup");
        mealManager.getRecipesList().getList().add(testMeal);

        List<Meal> recipes = mealManager.getRecipesList().getList();
        assertEquals(1, recipes.size());
        DeleteCommand deleteCommand = new DeleteCommand("delete -1");
        assertThrows(RemoveIndexOutOfRangeException.class,
                () -> deleteCommand.execute(mealManager, new TestUI()));
        logger.info("deleteCommand_negativeIndex_RemoveIndexOutOfRangeException passed");
    }

    @Test
    public void deleteCommand_nonIntegerIndex_throwsRemoveFormatException() {
        logger.fine("Running deleteCommand_nonIntegerIndex_throwsRemoveFormatException()");
        DeleteCommand deleteCommand = new DeleteCommand("delete abc");
        assertThrows(RemoveFormatException.class,
                () -> deleteCommand.execute(new MealManager(), new TestUI()));
        logger.info("deleteCommand_nonIntegerIndex_throwsRemoveFormatException passed");
    }
}

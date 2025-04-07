package seedu.command;

import seedu.exceptions.*;
import seedu.food.Inventory;
import seedu.logic.MealManager;
import seedu.ui.UserInterface;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BuyCommandTest {
    private final UserInterface ui = new UserInterface();

    @Test
    public void testExecute_validInputs_success() throws EZMealPlanException {
        MealManager mealManager = new MealManager();
        String userInput = "buy /ing Apple (1.00), Banana(3.00)";
        Command command = new BuyCommand(userInput);
        command.execute(mealManager, ui);

        Inventory inventory = mealManager.getInventory();
        String expectedOutput = """
                    1. Apple ($1.00): 1
                    2. Banana ($3.00): 1
                """;
        assertEquals(expectedOutput, inventory.toString());
    }

    @Test
    public void testExecute_repeatedIngredientsSamePrice_success() throws EZMealPlanException {
        MealManager mealManager = new MealManager();
        String userInput = "buy /ing Apple (1.00), Banana (3.00), Apple (1.00)";
        Command command = new BuyCommand(userInput);
        command.execute(mealManager, ui);

        Inventory inventory = mealManager.getInventory();
        String expectedOutput = """
                    1. Apple ($1.00): 2
                    2. Banana ($3.00): 1
                """;
        assertEquals(expectedOutput, inventory.toString());
    }

    @Test
    public void testExecute_repeatedIngredientsDifferentPrice_success() throws EZMealPlanException {
        MealManager mealManager = new MealManager();
        String userInput = "buy /ing Apple (1.00), Banana (3.00), Apple (2.00)";
        Command command = new BuyCommand(userInput);
        command.execute(mealManager, ui);

        Inventory inventory = mealManager.getInventory();
        String expectedOutput = """
                    1. Apple ($1.00): 1
                    2. Apple ($2.00): 1
                    3. Banana ($3.00): 1
                """;
        assertEquals(expectedOutput, inventory.toString());
    }

    @Test
    public void testExecute_missingIng_exceptionThrown() throws EZMealPlanException {
        MealManager mealManager = new MealManager();
        String userInput = "buy Apple (1.00), Banana (3.00), Apple (2.00)";
        Command command = new BuyCommand(userInput);
        assertThrows(MissingIngKeywordException.class, () -> command.execute(mealManager, ui));
    }

    @Test
    public void testExecute_missingIngredient_exceptionThrown() throws EZMealPlanException {
        MealManager mealManager = new MealManager();
        String userInput = "buy /ing";
        Command command = new BuyCommand(userInput);
        assertThrows(MissingIngredientException.class, () -> command.execute(mealManager, ui));
    }

    @Test
    public void testExecute_invalidIngredientFormat_exceptionThrown() throws EZMealPlanException {
        MealManager mealManager = new MealManager();
        String[] userInput = {"buy /ing ing1", "buy /ing ing1 ing2", "buy /ing ing1, ing2",
                "buy /ing ing1(1.00) ing(2.00)", "buy /ing ing1 ()", "buy /ing ing1 1.00", "buy /ing ing1 (abc)"};
        for (String invalidInput : userInput) {
            Command command = new BuyCommand(invalidInput);
            assertThrows(InvalidIngredientFormatException.class, () -> command.execute(mealManager, ui));
        }
    }
}
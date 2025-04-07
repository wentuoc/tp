package seedu.command;

import org.junit.jupiter.api.Test;
import seedu.exceptions.EZMealPlanException;
import seedu.exceptions.IngredientPriceFormatException;
import seedu.exceptions.InvalidPriceException;
import seedu.exceptions.InventoryMultipleIngredientsException;
import seedu.food.Ingredient;
import seedu.food.Inventory;
import seedu.logic.MealManager;
import seedu.ui.UserInterface;

import static org.junit.jupiter.api.Assertions.*;

class ConsumeCommandTest {
    UserInterface ui = new UserInterface();
    private final Ingredient ingredient1;
    private final Ingredient ingredient2;
    private final Ingredient ingredient3;
    private final Ingredient ingredient4;

    public ConsumeCommandTest() throws InvalidPriceException, IngredientPriceFormatException {
        ingredient1 = new Ingredient("Apple", "1.00");
        ingredient2 = new Ingredient("Apple", "2.00");
        ingredient3 = new Ingredient("Banana", "3.00");
        ingredient4 = new Ingredient("Chocolate", "4.00");
    }

    @Test
    public void testExecute_validInput_success() throws EZMealPlanException {
        MealManager mealManager = new MealManager();
        Inventory inventory = mealManager.getInventory();
        inventory.addIngredient(ingredient1);
        inventory.addIngredient(ingredient3);
        inventory.addIngredient(ingredient4);
        String userInput = "consume /ing apple";
        Command command = new ConsumeCommand(userInput);
        command.execute(mealManager, ui);

        String expectedOutput = """
                    1. Banana ($3.00): 1
                    2. Chocolate ($4.00): 1
                """;
        assertEquals(expectedOutput, inventory.toString());
    }

    @Test
    public void testExecute_validInputMultipleConsumed_success() throws EZMealPlanException {
        MealManager mealManager = new MealManager();
        Inventory inventory = mealManager.getInventory();
        inventory.addIngredient(ingredient1);
        inventory.addIngredient(ingredient3);
        inventory.addIngredient(ingredient4);
        String userInput = "consume /ing apple, Banana";
        Command command = new ConsumeCommand(userInput);
        command.execute(mealManager, ui);

        String expectedOutput = """
                    1. Chocolate ($4.00): 1
                """;
        assertEquals(expectedOutput, inventory.toString());
    }

    @Test
    public void testExecute_duplicateIngredientSamePrice_success() throws EZMealPlanException {
        MealManager mealManager = new MealManager();
        Inventory inventory = mealManager.getInventory();
        inventory.addIngredient(ingredient1);
        inventory.addIngredient(ingredient1);
        inventory.addIngredient(ingredient1);
        inventory.addIngredient(ingredient3);
        inventory.addIngredient(ingredient4);
        String userInput = "consume /ing apple";
        Command command = new ConsumeCommand(userInput);
        command.execute(mealManager, ui);

        String expectedOutput = """
                    1. Apple ($1.00): 2
                    2. Banana ($3.00): 1
                    3. Chocolate ($4.00): 1
                """;
        assertEquals(expectedOutput, inventory.toString());
    }

    @Test
    public void testExecute_duplicateIngredientDifferentPrice_exceptionThrown() throws EZMealPlanException {
        MealManager mealManager = new MealManager();
        Inventory inventory = mealManager.getInventory();
        inventory.addIngredient(ingredient1);
        inventory.addIngredient(ingredient1);
        inventory.addIngredient(ingredient2);
        inventory.addIngredient(ingredient3);
        inventory.addIngredient(ingredient4);
        String userInput1 = "consume /ing Banana";
        Command command1 = new ConsumeCommand(userInput1);
        command1.execute(mealManager, ui);

        String expectedOutput = """
                    1. Apple ($1.00): 2
                    2. Apple ($2.00): 1
                    3. Chocolate ($4.00): 1
                """;
        assertEquals(expectedOutput, inventory.toString());

        String userInput2 = "consume /ing apple";
        Command command2 = new ConsumeCommand(userInput2);

        assertThrows(InventoryMultipleIngredientsException.class, () -> command2.execute(mealManager, ui));
    }

    @Test
    public void testExecute_duplicateIngredientDifferentPriceMultipleConsumed_exceptionThrown() {
        MealManager mealManager = new MealManager();
        Inventory inventory = mealManager.getInventory();
        inventory.addIngredient(ingredient1);
        inventory.addIngredient(ingredient1);
        inventory.addIngredient(ingredient2);
        inventory.addIngredient(ingredient3);
        inventory.addIngredient(ingredient4);
        String userInput = "consume /ing apple, banana";
        Command command = new ConsumeCommand(userInput);

        assertThrows(InventoryMultipleIngredientsException.class, () -> command.execute(mealManager, ui));
    }
}
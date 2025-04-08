package seedu.food;

import org.junit.jupiter.api.Test;
import seedu.exceptions.EZMealPlanException;
import seedu.exceptions.IngredientPriceFormatException;
import seedu.exceptions.InvalidPriceException;
import seedu.exceptions.InventoryIngredientNotFound;
import seedu.exceptions.InventoryMultipleIngredientsException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class InventoryTest {
    private final Ingredient ingredient1;
    private final Ingredient ingredient2;
    private final Ingredient ingredient3;
    private final Ingredient ingredient4;
    private final Ingredient ingredient5;

    private final String ls = System.lineSeparator();

    public InventoryTest() throws InvalidPriceException, IngredientPriceFormatException {
        ingredient1 = new Ingredient("Apple", "1.00");
        ingredient2 = new Ingredient("Apple", "2.00");
        ingredient3 = new Ingredient("Banana", "3.00");
        ingredient4 = new Ingredient("Chocolate", "4.00");
        ingredient5 = new Ingredient("Apple", "1.00");
    }

    @Test
    void addIngredient_differentIngredients_success() {
        Inventory inventory = new Inventory();
        inventory.addIngredient(ingredient1);
        inventory.addIngredient(ingredient3);
        inventory.addIngredient(ingredient4);
        String expectedOutput = "    1. Apple ($1.00): 1" + ls + "    2. Banana ($3.00): 1" + ls +
                "    3. Chocolate ($4.00): 1" + ls;
        assertEquals(expectedOutput, inventory.toString());
    }

    @Test
    void addIngredient_repeatedIngredientsSamePrice_repetitionCaptured() {
        Inventory inventory = new Inventory();
        inventory.addIngredient(ingredient1);
        inventory.addIngredient(ingredient5);
        inventory.addIngredient(ingredient3);
        inventory.addIngredient(ingredient4);
        String expectedOutput = "    1. Apple ($1.00): 2" + ls + "    2. Banana ($3.00): 1" + ls +
                "    3. Chocolate ($4.00): 1" + ls;
        assertEquals(expectedOutput, inventory.toString());
    }

    @Test
    void addIngredient_repeatedIngredientsDifferentPrice_success() {
        Inventory inventory = new Inventory();
        inventory.addIngredient(ingredient1);
        inventory.addIngredient(ingredient2);
        inventory.addIngredient(ingredient3);
        inventory.addIngredient(ingredient4);
        String expectedOutput = "    1. Apple ($1.00): 1" + ls + "    2. Apple ($2.00): 1" + ls +
                "    3. Banana ($3.00): 1" + ls + "    4. Chocolate ($4.00): 1" + ls;
        assertEquals(expectedOutput, inventory.toString());
    }

    @Test
    void removeIngredient_uniqueIngredients_success() throws InventoryMultipleIngredientsException,
            InventoryIngredientNotFound {
        Inventory inventory = new Inventory();
        inventory.addIngredient(ingredient1);
        inventory.addIngredient(ingredient3);
        inventory.addIngredient(ingredient4);
        inventory.removeIngredient("Apple");
        String expectedOutput = "    1. Banana ($3.00): 1" + ls + "    2. Chocolate ($4.00): 1" + ls;
        assertEquals(expectedOutput, inventory.toString());
    }

    @Test
    void removeIngredient_repeatedIngredientSamePrice_success() throws InventoryMultipleIngredientsException,
            InventoryIngredientNotFound {
        Inventory inventory = new Inventory();
        inventory.addIngredient(ingredient1);
        inventory.addIngredient(ingredient5);
        inventory.addIngredient(ingredient3);
        inventory.addIngredient(ingredient4);
        inventory.removeIngredient("Apple");
        String expectedOutput = "    1. Apple ($1.00): 1" + ls + "    2. Banana ($3.00): 1" + ls +
                "    3. Chocolate ($4.00): 1" + ls;
        assertEquals(expectedOutput, inventory.toString());
    }

    @Test
    void removeIngredient_repeatedIngredientDifferentPrice_exceptionThrown() {
        Inventory inventory = new Inventory();
        inventory.addIngredient(ingredient1);
        inventory.addIngredient(ingredient2);
        inventory.addIngredient(ingredient3);
        inventory.addIngredient(ingredient4);
        try {
            inventory.removeIngredient("Apple");
            fail();
        } catch (EZMealPlanException ezMealPlanException) {
            String expectedMessage = "There are multiple ingredients with the same name present in the Inventory: \n" +
                    "Apple ($1.00)\n" + "Apple ($2.00)\n" + "Please refine your arguments.";
            assertEquals(expectedMessage, ezMealPlanException.getMessage());
        }
    }

    @Test
    void removeIngredient_ingredientDoesNotExist_exceptionThrown() throws
            InventoryMultipleIngredientsException {
        Inventory inventory = new Inventory();
        inventory.addIngredient(ingredient1);
        inventory.addIngredient(ingredient3);
        try {
            inventory.removeIngredient("Chocolate");
            fail();
        } catch (EZMealPlanException ezMealPlanException) {
            assertEquals("Chocolate not found in Inventory", ezMealPlanException.getMessage());
        }
    }
}

package seedu.food;


import seedu.exceptions.EZMealPlanException;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


import org.junit.jupiter.api.Test;

public class IngredientTest {

    @Test
    public void createIngredient_ingredient_success() throws EZMealPlanException {
        String ingredientName = "salt";
        double ingredientPrice = 2.5;
        Ingredient newIngredient = new Ingredient(ingredientName, ingredientPrice);
        String expectedOutput = "salt ($2.50)";
        assertEquals(expectedOutput, newIngredient.toString());
    }

    @Test
    public void equals_sameNameSamePrice_true() throws EZMealPlanException {
        String ingredient1Name = "salt";
        double ingredient1Price = 2.5;
        String ingredient2Name = "salt";
        double ingredient2Price = 2.5;
        Ingredient newIngredient1 = new Ingredient(ingredient1Name, ingredient1Price);
        Ingredient newIngredient2 = new Ingredient(ingredient2Name, ingredient2Price);
        assertEquals(newIngredient1, newIngredient2);
    }

    @Test
    public void equals_sameNameDifferentPrice_true() throws EZMealPlanException {
        String ingredient1Name = "salt";
        double ingredient1Price = 2.5;
        String ingredient2Name = "salt";
        double ingredient2Price = 2;
        Ingredient newIngredient1 = new Ingredient(ingredient1Name, ingredient1Price);
        Ingredient newIngredient2 = new Ingredient(ingredient2Name, ingredient2Price);
        assertEquals(newIngredient1, newIngredient2);
    }

    @Test
    public void equals_differentNameDifferentPrice_false() throws EZMealPlanException {
        String ingredient1Name = "salt";
        double ingredient1Price = 2.5;
        String ingredient2Name = "pepper";
        double ingredient2Price = 2;
        Ingredient newIngredient1 = new Ingredient(ingredient1Name, ingredient1Price);
        Ingredient newIngredient2 = new Ingredient(ingredient2Name, ingredient2Price);
        assertNotEquals(newIngredient1, newIngredient2);
    }

    @Test
    public void equals_differentNameSamePrice_false() throws EZMealPlanException {
        String ingredient1Name = "salt";
        double ingredient1Price = 2.5;
        String ingredient2Name = "pepper";
        double ingredient2Price = 2.5;
        Ingredient newIngredient1 = new Ingredient(ingredient1Name, ingredient1Price);
        Ingredient newIngredient2 = new Ingredient(ingredient2Name, ingredient2Price);
        assertNotEquals(newIngredient1, newIngredient2);
    }
}

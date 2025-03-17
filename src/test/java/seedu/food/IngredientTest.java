package seedu.food;


import seedu.exceptions.EZMealPlanException;


import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Test;

public class IngredientTest {

    @Test
    public void ingredient_success() throws EZMealPlanException {
        String ingredientName = "salt";
        double ingredientPrice = 2.5;
        Ingredient newIngredient = new Ingredient(ingredientName, ingredientPrice);
        String expectedOutput = "salt ($2.50)";
        assertEquals(expectedOutput, newIngredient.toString());
    }
}

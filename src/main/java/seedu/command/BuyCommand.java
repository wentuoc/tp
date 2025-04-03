package seedu.command;

import seedu.logic.MealManager;
import seedu.ui.UserInterface;
import seedu.food.Inventory;
import seedu.food.Ingredient;
import java.util.List;

public class BuyCommand extends Command {
    private final List<Ingredient> ingredients;

    /**
     * Constructs a BuyCommand with the specified list of ingredients.
     *
     * @param ingredients a list of ingredients (with name and price) to be bought.
     */
    public BuyCommand(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    /**
     * Executes the buy command by adding each specified ingredient into the inventory.
     *
     * @param mealManager the MealManager providing access to the inventory.
     * @param ui          the UserInterface for printing messages.
     */
    @Override
    public void execute(MealManager mealManager, UserInterface ui) {
        Inventory inventory = mealManager.getInventory();
        List<Ingredient> ingredientList = inventory.getIngredients();
        for (Ingredient ingredient : ingredients) {
            // Add the ingredient (with name and price) into the inventory list.
            ingredientList.add(ingredient);
            ui.printBought(ingredient.getName(), ingredient.getPrice());
        }
    }
}

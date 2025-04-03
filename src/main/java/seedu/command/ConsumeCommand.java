package seedu.command;

import seedu.logic.MealManager;
import seedu.ui.UserInterface;
import seedu.food.Inventory;
import seedu.food.Ingredient;
import java.util.List;
import java.util.Iterator;

public class ConsumeCommand extends Command {
    private List<String> ingredients;

    /**
     * Constructs a ConsumeCommand with the specified list of ingredient names.
     *
     * @param ingredients a list of ingredient names to be consumed.
     */
    public ConsumeCommand(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    /**
     * Executes the consume command by removing each specified ingredient from the inventory.
     *
     * @param mealManager the MealManager that provides access to the inventory.
     * @param ui          the UserInterface for printing messages.
     */
    @Override
    public void execute(MealManager mealManager, UserInterface ui) {
        // Retrieve the inventory from the MealManager.
        Inventory inventory = mealManager.getInventory();
        // Assuming Inventory has a method to get its ingredient list.
        List<Ingredient> ingredientList = inventory.getIngredients();

        // Process each ingredient name provided in the command.
        for (String ingredientName : ingredients) {
            boolean removed = false;
            // Use an iterator to safely remove items while iterating.
            Iterator<Ingredient> iterator = ingredientList.iterator();
            while (iterator.hasNext()) {
                Ingredient ingredient = iterator.next();
                if (ingredient.getName().equalsIgnoreCase(ingredientName)) {
                    iterator.remove();
                    ui.printConsumed(ingredientName);
                    removed = true;
                    break;
                }
            }
            if (!removed) {
                ui.printIngredientNotFound(ingredientName);
            }
        }
    }
}

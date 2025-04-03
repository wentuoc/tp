package seedu.command;

import seedu.food.Meal;
import seedu.food.Ingredient;
import seedu.storage.Storage;
import seedu.logic.MealManager;
import seedu.ui.UserInterface;

import java.io.IOException;
import java.util.List;

public class ByeCommand extends Command {
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(MealManager mealManager, UserInterface ui) {
        updateRecipesListFile(mealManager);
        updateWishListFile(mealManager);
        updateInventoryFile(mealManager);
        ui.printGoodbye();
    }

    private void updateWishListFile(MealManager mealManager) {
        List<Meal> wishList = mealManager.getWishList().getList();
        String wishListFilePath = Storage.getWishListFilePath();
        clearAndUpdateFile(wishList, wishListFilePath);
    }

    private void updateRecipesListFile(MealManager mealManager) {
        List<Meal> recipesList = mealManager.getRecipesList().getList();
        String recipesListFilePath = Storage.getRecipesListFilePath();
        clearAndUpdateFile(recipesList, recipesListFilePath);
    }


    private void clearAndUpdateFile(List<Meal> mealList, String filePath) {
        Storage.clearFile(filePath);
        writeMealsToFile(mealList, filePath);
    }

    private static void writeMealsToFile(List<Meal> mealList, String filePath) {
        for (Meal newMeal : mealList) {
            try {
                Storage.writeToFile(newMeal.toDataString(), filePath);
            } catch (IOException ioException) {
                UserInterface.printMessage("Error writing to file: " + ioException.getMessage());
            }
        }
    }

    private static void writeIngredientsToFile(List<Ingredient> ingredientList, String filePath) {
        for (Ingredient ingredient : ingredientList) {
            try {
                Storage.writeToFile(ingredient.toDataString(), filePath);
            } catch (IOException ioException) {
                UserInterface.printMessage("Error writing to file: " + ioException.getMessage());
            }
        }
    }

    private void clearAndUpdateFileForIngredients(List<Ingredient> ingredientList, String filePath) {
        Storage.clearFile(filePath);
        writeIngredientsToFile(ingredientList, filePath);
    }

    private void updateInventoryFile(MealManager mealManager) {
        // Retrieve the list of ingredients from the inventory.
        List<Ingredient> ingredientList = mealManager.getInventory().getIngredients();
        // Get the file path for the inventory list.
        String inventoryFilePath = Storage.getInventoryFilePath();
        // Clear the existing file and write the new list.
        clearAndUpdateFileForIngredients(ingredientList, inventoryFilePath);
    }
}

package seedu.command;

import seedu.logic.MealManager;
import seedu.ui.UserInterface;
import seedu.meallist.Meals;
import seedu.food.Meal;
import seedu.food.Ingredient;
import seedu.food.Inventory;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class RecommendCommand extends Command {
    private String ingredientKeyword;

    /**
     * Constructs a RecommendCommand.
     * Expected user input format: "recommend /ing Chicken"
     */
    public RecommendCommand(String userInput) {
        String[] parts = userInput.split("/ing");
        if (parts.length < 2) {
            ingredientKeyword = "";
        } else {
            ingredientKeyword = parts[1].trim();
        }
    }

    @Override
    public void execute(MealManager mealManager, UserInterface ui) {
        Inventory inventory = mealManager.getInventory();

        // First, filter the user meal list (wishlist) by the ingredient keyword.
        Meals userMeals = mealManager.getUserMeals();
        List<Meal> candidateMeals = filterMealsByIngredient(userMeals.getList(), ingredientKeyword);

        // If no matching meals in the user list, try the main meal list.
        if (candidateMeals.isEmpty()) {
            Meals mainMeals = mealManager.getMainMeals();
            candidateMeals = filterMealsByIngredient(mainMeals.getList(), ingredientKeyword);
        }

        if (candidateMeals.isEmpty()) {
            ui.printMessage("No meal found containing ingredient: " + ingredientKeyword);
            return;
        }

        // Randomly select a meal from the candidate meals.
        Random random = new Random();
        Meal selectedMeal = candidateMeals.get(random.nextInt(candidateMeals.size()));

        // Build the output message.
        StringBuilder sb = new StringBuilder();
        sb.append("Recommended Meal: ").append(selectedMeal.getName())
                .append(" (").append(selectedMeal).append(")")
                .append(System.lineSeparator());
        sb.append("Ingredients:").append(System.lineSeparator());

        List<Ingredient> mealIngredients = selectedMeal.getIngredientList();
        for (int i = 0; i < mealIngredients.size(); i++) {
            Ingredient ing = mealIngredients.get(i);
            sb.append("   ").append(i + 1).append(". ").append(ing.toString()).append(System.lineSeparator());
        }

        // Determine which ingredients are missing from the inventory.
        List<String> missingIngredients = new ArrayList<>();
        for (Ingredient ing : mealIngredients) {
            if (!inventory.hasIngredient(ing.getName())) {
                missingIngredients.add(ing.getName());
            }
        }
        if (missingIngredients.isEmpty()) {
            sb.append("You have all the necessary ingredients for this meal.");
        } else {
            sb.append("Missing Ingredients: ").append(String.join(", ", missingIngredients));
        }

        ui.printMessage(sb.toString());
    }

    /**
     * Filters a list of meals by checking if any ingredient name contains the given keyword.
     */
    private List<Meal> filterMealsByIngredient(List<Meal> meals, String keyword) {
        return meals.stream()
                .filter(meal -> meal.getIngredientList().stream()
                        .anyMatch(ing -> ing.getName().toLowerCase().contains(keyword.toLowerCase())))
                .collect(Collectors.toList());
    }
}

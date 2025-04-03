package seedu.food;


import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Ingredient> ingredients;

    public Inventory() {
        ingredients = new ArrayList<>();
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public boolean removeIngredient(String ingredientName) {
        return ingredients.removeIf(i -> i.getName().equalsIgnoreCase(ingredientName));
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public boolean hasIngredient(String ingredientName) {
        return ingredients.stream()
                .anyMatch(i -> i.getName().equalsIgnoreCase(ingredientName));
    }
}

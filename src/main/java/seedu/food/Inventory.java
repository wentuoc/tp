package seedu.food;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Inventory {
    private final List<Ingredient> ingredients;

    public Inventory() {
        ingredients = new ArrayList<>();
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
        ingredients.sort(Comparator.comparing(Ingredient::getName,
                String.CASE_INSENSITIVE_ORDER).thenComparing(Ingredient::getPrice));
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

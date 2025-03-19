package seedu.food;

import seedu.exceptions.InvalidPriceException;

import java.util.ArrayList;
import java.util.List;

public class Meal extends Product {
    private final List<Ingredient> ingredientList = new ArrayList<>();

    public Meal(String mealName) throws InvalidPriceException {
        double tempMealPrice = 1; // buffer value for the meal price
        setName(mealName);
        setPrice(tempMealPrice);
    }

    public void setPrice(double mealPrice) throws InvalidPriceException {
        super.setPrice(mealPrice);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    @Override
    public boolean equals(Object otherMeal) {
        if (otherMeal instanceof Meal) {
            Meal other = (Meal) otherMeal;
            return this.getName().equalsIgnoreCase(other.getName()) &&
                    this.ingredientList.equals(other.getIngredientList());
        }
        return false;
    }

    // If searching for an ingredient is a behavior of a single meal,
    // you can include a method to return matching ingredients.
    public List<Ingredient> findIngredient(String ingredientName) {
        String lcIngredientName = ingredientName.toLowerCase();
        List<Ingredient> matchingIngredList = new ArrayList<>();
        for (Ingredient ingredient : ingredientList) {
            if (ingredient.getName().toLowerCase().contains(lcIngredientName)) {
                matchingIngredList.add(ingredient);
            }
        }
        return matchingIngredList;
    }

    public String toDataString() {
        StringBuilder sb = new StringBuilder();
        // Append the meal name.
        sb.append(getName());
        // Append each ingredient in the required format: " | ingredientName (price)"
        for (Ingredient ingredient : ingredientList) {
            sb.append(" | ");
            sb.append(ingredient.getName());
            sb.append(" (");
            sb.append(String.format("%.2f", ingredient.getPrice()));
            sb.append(")");
        }
        return sb.toString();
    }


    public static Meal fromData(String data) throws InvalidPriceException {
        String[] parts = data.split("\\|");
        // Create a Meal with the first part as the name (and a temporary price)
        Meal meal = new Meal(parts[0]);
        // Set the correct price
        meal.setPrice(Double.parseDouble(parts[1]));
        // Assuming the remaining parts represent ingredients, parse each one.
        for (int i = 2; i < parts.length; i++) {
            Ingredient ingredient = Ingredient.fromData(parts[i]);
            meal.getIngredientList().add(ingredient);
        }
        return meal;
    }

}

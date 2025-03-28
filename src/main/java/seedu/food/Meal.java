package seedu.food;

import seedu.exceptions.DuplicateIngredientException;
import seedu.exceptions.EZMealPlanException;
import seedu.exceptions.IngredientPriceFormatException;
import seedu.exceptions.InvalidPriceException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

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
        if (otherMeal instanceof Meal other) {
            return this.getName().equalsIgnoreCase(other.getName()) &&
                   this.ingredientList.equals(other.getIngredientList());
        }
        return false;
    }

    public double computeMealPrice() {
        List<Ingredient> ingredientList = getIngredientList();
        double totalPrice = 0;
        for (Ingredient ingredient : ingredientList) {
            totalPrice += ingredient.getPrice();
        }
        return totalPrice;
    }

    public void addIngredient(String ingredientName, String ingredientPrice, Logger logger)
            throws EZMealPlanException {
        double ingredientPriceDouble = checkValidIngPrice(ingredientName, ingredientPrice, logger);
        Ingredient newIngredient = new Ingredient(ingredientName, ingredientPriceDouble);
        checkDuplicateIngredients(newIngredient, logger);
        List<Ingredient> ingredientList = getIngredientList();
        ingredientList.add(newIngredient);
    }

    private void checkDuplicateIngredients(Ingredient newIngredient, Logger logger) throws EZMealPlanException {
        String mealName = getName();
        List<Ingredient> ingredientList = getIngredientList();
        for (Ingredient ingredient : ingredientList) {
            if (newIngredient.equals(ingredient)) {
                String ingredientName = newIngredient.getName();
                String message = "Triggers DuplicateIngredientException()!";
                logger.warning(message);
                throw new DuplicateIngredientException(ingredientName, mealName);
            }
        }
    }

    private static double checkValidIngPrice(String ingredientName, String ingredientPrice, Logger logger)
            throws IngredientPriceFormatException {
        try {
            double hundred = 100.0;
            double ingredientPriceDouble = Double.parseDouble(ingredientPrice);
            return Math.round(ingredientPriceDouble * hundred) / hundred;
        } catch (NumberFormatException numberFormatException) {
            String message = "Triggers IngredientPriceFormatException()!";
            logger.warning(message);
            throw new IngredientPriceFormatException(ingredientName);
        }
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
        StringBuilder stringBuilder = new StringBuilder();
        // Append the meal name.
        stringBuilder.append(getName());
        // Append each ingredient in the required format: " | ingredientName (price)"
        for (Ingredient ingredient : ingredientList) {
            stringBuilder.append(" | ");
            stringBuilder.append(ingredient.getName());
            stringBuilder.append(" (");
            stringBuilder.append(String.format("%.2f", ingredient.getPrice()));
            stringBuilder.append(")");
        }
        return stringBuilder.toString();
    }
}

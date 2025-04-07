package seedu.food;


import seedu.exceptions.InventoryIngredientNotFound;
import seedu.exceptions.InventoryMultipleIngredientsException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Inventory {
    private final HashMap<Ingredient, Integer> ingredients;

    public Inventory() {
        ingredients = new HashMap<>();
    }

    public void addIngredient(Ingredient ingredient) {
        if (ingredients.containsKey(ingredient)) {
            ingredients.put(ingredient, ingredients.get(ingredient) + 1);
        } else {
            ingredients.put(ingredient, 1);
        }
    }

    public void removeIngredient(String ingredientNameToBeRemoved) throws InventoryMultipleIngredientsException,
            InventoryIngredientNotFound {
        ArrayList<Ingredient> ingredientsToRemove = findIngredientsFromString(ingredientNameToBeRemoved);
        if (hasMultipleIngredientsWithSameName(ingredientsToRemove)) {
            throw new InventoryMultipleIngredientsException(ingredientsToRemove);
        } else if (hasOnlyOneIngredient(ingredientsToRemove)) {
            Ingredient ingredientToBeRemoved = ingredientsToRemove.get(0);
            int ingredientQuantity = ingredients.get(ingredientToBeRemoved);
            if (ingredientQuantity > 1) {
                ingredients.put(ingredientToBeRemoved, ingredientQuantity - 1);
            } else {
                ingredients.remove(ingredientsToRemove.get(0));
            }
        } else if (hasNoIngredients(ingredientsToRemove)) {
            throw new InventoryIngredientNotFound(ingredientNameToBeRemoved);
        }
    }

    private ArrayList<Ingredient> findIngredientsFromString(String ingredientString) {
        ArrayList<Ingredient> ingredientsFound = new ArrayList<>();
        Set<Ingredient> ingredientSet = ingredients.keySet();
        for (Ingredient ingredientInSet : ingredientSet) {
            if (ingredientInSet.getName().equals(ingredientString)) {
                ingredientsFound.add(ingredientInSet);
            }
        }
        return ingredientsFound;
    }

    private boolean hasNoIngredients(ArrayList<Ingredient> ingredients) {
        return ingredients.isEmpty();
    }

    private boolean hasMultipleIngredientsWithSameName(ArrayList<Ingredient> ingredients) {
        return ingredients.size() > 1;
    }

    private boolean hasOnlyOneIngredient(ArrayList<Ingredient> ingredients) {
        return ingredients.size() == 1;
    }

    public String toString() {
        ArrayList<Ingredient> ingredientsArrayList = new ArrayList<>(ingredients.keySet());
        ingredientsArrayList.sort(Comparator.comparing(Ingredient::getName)); //TODO: might have issues with this
        int count = 0;
        StringBuilder outputString = new StringBuilder();
        for (Ingredient ingredient : ingredientsArrayList) {
            count++;
            outputString.append("    ");
            outputString.append(count);
            outputString.append(". ");
            outputString.append(ingredient);
            outputString.append(": ");
            outputString.append(ingredients.get(ingredient));
            outputString.append("\n");
        }
        return outputString.toString();
    }

    public ArrayList<String> toDataArray() {
        ArrayList<Ingredient> ingredientsArrayList = new ArrayList<>(ingredients.keySet());
        ingredientsArrayList.sort(Comparator.comparing(Ingredient::getName)); //TODO: might have issues with this
        ArrayList<String> outputDataArray = new ArrayList<>();
        for (Ingredient ingredient : ingredientsArrayList) {
            outputDataArray.add(ingredient + " | " + getIngredientAmount(ingredient));
        }
        return outputDataArray;
    }

    //Remove
    public List<Ingredient> getIngredients() {
        return new ArrayList<>(ingredients.keySet());
    }

    //    public HashMap<Ingredient, Integer> getIngredientsAsHashMap() {
    //        return ingredients;
    //    }

    //    //The following functions are only used in testing
    //    public int numberOfUniqueIngredients() {
    //        return ingredients.size();
    //    }

    public boolean hasIngredient(String ingredientName) {
        ArrayList<Ingredient> foundIngredients = findIngredientsFromString(ingredientName);
        return !foundIngredients.isEmpty();
    }

    private int getIngredientAmount(Ingredient ingredient) {
        return ingredients.getOrDefault(ingredient, 0);
    }
}

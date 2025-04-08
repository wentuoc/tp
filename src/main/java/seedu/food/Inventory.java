package seedu.food;


import seedu.exceptions.InventoryIngredientNotFound;
import seedu.exceptions.InventoryMultipleIngredientsException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Inventory {
    private final HashMap<Ingredient, Integer> ingredients;
    private final ArrayList<Ingredient> uniqueSortedIngredients;

    public Inventory() {
        ingredients = new HashMap<>();
        uniqueSortedIngredients = new ArrayList<>();
    }

    public void addIngredient(Ingredient ingredient) {
        if (isIngredientInInventory(ingredient)) {
            Ingredient ingredientInInventory = getIngredientInInventory(ingredient);
            ingredients.put(ingredientInInventory, ingredients.get(ingredientInInventory) + 1);
        } else {
            ingredients.put(ingredient, 1);
            uniqueSortedIngredients.add(ingredient);
            uniqueSortedIngredients.sort(Comparator.comparing(Ingredient::getName,String.CASE_INSENSITIVE_ORDER).
                    thenComparing(Ingredient::getPrice));
        }
    }

    public void addIngredient(Ingredient ingredient, int quantity) {
        ingredients.put(ingredient, quantity);
        uniqueSortedIngredients.add(ingredient);
        uniqueSortedIngredients.sort(Comparator.comparing(Ingredient::getName,String.CASE_INSENSITIVE_ORDER).
                thenComparing(Ingredient::getPrice));

    }

    private boolean isIngredientInInventory(Ingredient ingredient) {
        return uniqueSortedIngredients.contains(ingredient);
    }

    private Ingredient getIngredientInInventory(Ingredient ingredient) {
        int index = uniqueSortedIngredients.indexOf(ingredient);
        return uniqueSortedIngredients.get(index);
    }

    public Ingredient removeIngredient(String ingredientNameToBeRemoved) throws InventoryMultipleIngredientsException,
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
                ingredients.remove(ingredientToBeRemoved);
                uniqueSortedIngredients.remove(ingredientToBeRemoved);
            }
            return ingredientToBeRemoved;
        } else {
            throw new InventoryIngredientNotFound(ingredientNameToBeRemoved);
        }
    }

    public void removeIngredient(Ingredient ingredient) throws InventoryIngredientNotFound {
        if (uniqueSortedIngredients.contains(ingredient)) {
            int indexOfIngredient = uniqueSortedIngredients.indexOf(ingredient);
            Ingredient ingredientToBeRemoved = uniqueSortedIngredients.get(indexOfIngredient);
            int ingredientQuantity = ingredients.get(ingredientToBeRemoved);
            if (ingredientQuantity > 1) {
                ingredients.put(ingredientToBeRemoved, ingredientQuantity - 1);
            } else {
                ingredients.remove(ingredientToBeRemoved);
                uniqueSortedIngredients.remove(ingredientToBeRemoved);
            }
        } else {
            throw new InventoryIngredientNotFound(ingredient.toString());
        }
    }

    private ArrayList<Ingredient> findIngredientsFromString(String ingredientString) {
        ArrayList<Ingredient> ingredientsFound = new ArrayList<>();
        for (Ingredient uniqueIngredient : uniqueSortedIngredients) {
            if (uniqueIngredient.getName().equalsIgnoreCase(ingredientString)) {
                ingredientsFound.add(uniqueIngredient);
            }
        }
        return ingredientsFound;
    }

    private boolean hasMultipleIngredientsWithSameName(ArrayList<Ingredient> ingredients) {
        return ingredients.size() > 1;
    }

    private boolean hasOnlyOneIngredient(ArrayList<Ingredient> ingredients) {
        return ingredients.size() == 1;
    }

    public String toString() {
        int count = 0;
        StringBuilder outputString = new StringBuilder();
        for (Ingredient ingredient : uniqueSortedIngredients) {
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
        ArrayList<String> outputDataArray = new ArrayList<>();
        for (Ingredient ingredient : uniqueSortedIngredients) {
            outputDataArray.add(ingredient.toDataString() + " | " + getIngredientAmount(ingredient));
        }
        return outputDataArray;
    }

    public List<Ingredient> getIngredients() {
        return uniqueSortedIngredients;
    }

    public boolean hasIngredient(String ingredientName) {
        ArrayList<Ingredient> foundIngredients = findIngredientsFromString(ingredientName);
        return !foundIngredients.isEmpty();
    }

    private int getIngredientAmount(Ingredient ingredient) {
        return ingredients.getOrDefault(ingredient, 0);
    }
}

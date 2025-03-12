package seedu.food;

import seedu.exceptions.DuplicateIngredientException;
import seedu.exceptions.IngredientPriceFormatException;
import seedu.exceptions.InvalidPriceException;

import java.util.ArrayList;

public class Meal extends Product {
    private ArrayList<Ingredient> ingredientList = new ArrayList<>();

    public Meal(String mealName) throws InvalidPriceException {
        double tempMealPrice = 1; // buffer value for the meal price
        setName(mealName);
        setPrice(tempMealPrice);
    }

    @Override
    public void setPrice(double mealPrice) throws InvalidPriceException {
        super.setPrice(mealPrice);
    }

    @Override
    public String toString() {
        return getName() + " ($" + getPrice() + ")";
    }

    public void addIngredient(String ingredientName, String ingredientPrice)
            throws InvalidPriceException, IngredientPriceFormatException, DuplicateIngredientException {
        double ingredientPriceDouble = checkValidIngPrice(ingredientName, ingredientPrice);
        checkDuplicateIngredients(ingredientName);
        Ingredient newIngredient = new Ingredient(ingredientName, ingredientPriceDouble);
        ingredientList.add(newIngredient);
        double newMealPrice = computeMealPrice();
        setPrice(newMealPrice);
    }

    private void checkDuplicateIngredients(String ingredientName) throws DuplicateIngredientException {
        String mealName = getName();
        for (Ingredient ingredient : ingredientList) {
            String currentIngredientName = ingredient.getName();
            if (ingredientName.equals(currentIngredientName)) {
                throw new DuplicateIngredientException(ingredientName, mealName);
            }
        }
    }

    private static double checkValidIngPrice(String ingredientName, String ingredientPrice)
            throws IngredientPriceFormatException {
        try {
            return Double.parseDouble(ingredientPrice);
        } catch (NumberFormatException numberFormatException) {
            throw new IngredientPriceFormatException(ingredientName);
        }
    }

    public void removeIngredient(String ingredientName) throws InvalidPriceException {
        boolean isRemoved = ingredientList.removeIf(ingredient -> ingredient.getName().equals(ingredientName));
        if (!isRemoved) {
            System.out.println("The ingredient " + ingredientName + " is not present in the meal " + getName() + '.');
            return;
        }
        double newMealPrice = computeMealPrice();
        setPrice(newMealPrice);
    }

    public ArrayList<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(ArrayList<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public double computeMealPrice() {
        double totalPrice = 0;
        for (Ingredient ingredient : ingredientList) {
            totalPrice += ingredient.getPrice();
        }
        return totalPrice;
    }

    public void findIngredient(String ingredientName) {
        ArrayList<Ingredient> matchingIngredList = new ArrayList<>();
        for (Ingredient ingredient : ingredientList) {
            String currentIngName = ingredient.getName();
            if (currentIngName.contains(ingredientName)) {
                matchingIngredList.add(ingredient);
            }
        }
        printMatchingIngredList(matchingIngredList);
    }

    private static void printMatchingIngredList(ArrayList<Ingredient> matchingIngredList) {
        if (matchingIngredList.isEmpty()) {
            System.out.println("No matching ingredient found.");
            return;
        }
        System.out.println("Here are the matching ingredients: ");
        int count = 0;
        for (Ingredient ingredient : matchingIngredList) {
            count++;
            System.out.println("    " + count + ". " + ingredient);
        }
    }

    public void printIngredientList() {
        if (ingredientList.isEmpty()) {
            System.out.println("The ingredient list of "+ getName() +" is empty.");
            return;
        }
        System.out.println("Here are the ingredients for " + this + ": ");
        for (Ingredient ingredient : ingredientList) {
            System.out.println("    " + ingredient);
        }
    }
}

package seedu.food;
;
import seedu.exceptions.InvalidPriceException;

import java.util.ArrayList;

public class Meal extends Product {
    private final ArrayList<Ingredient> ingredientList = new ArrayList<>();

    public Meal(String mealName) throws InvalidPriceException {
        double tempMealPrice = 1; // buffer value for the meal price
        setName(mealName);
        setPrice(tempMealPrice);
    }

    public void setPrice(double mealPrice) throws InvalidPriceException {
        super.setPrice(mealPrice);
    }

    public String toString() {
        return super.toString();
    }


    public ArrayList<Ingredient> getIngredientList() {
        return ingredientList;
    }

    @Override
    public boolean equals(Object otherMeal) {
        if (otherMeal instanceof Meal) {
            String otherMealName = ((Meal) otherMeal).getName();
            String thisMealName = this.getName();
            ArrayList<Ingredient> otherIngredientList = ((Meal) otherMeal).getIngredientList();
            ArrayList<Ingredient> thisIngredientList = this.getIngredientList();
            return thisMealName.equalsIgnoreCase(otherMealName) && thisIngredientList.equals(otherIngredientList);
        }
        return false;
    }

    public void findIngredient(String ingredientName) {
        String lcIngredientName = ingredientName.toLowerCase();
        ArrayList<Ingredient> matchingIngredList = new ArrayList<>();
        for (Ingredient ingredient : ingredientList) {
            String currentIngName = ingredient.getName().toLowerCase();
            if (currentIngName.contains(lcIngredientName)) {
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
}

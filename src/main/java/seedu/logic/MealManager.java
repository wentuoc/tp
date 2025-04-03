package seedu.logic;

import seedu.exceptions.EZMealPlanException;
import seedu.food.Ingredient;
import seedu.food.Inventory;
import seedu.food.Meal;
import seedu.meallist.MealList;
import seedu.meallist.RecipesList;
import seedu.meallist.WishList;

import java.util.ArrayList;
import java.util.List;

public class MealManager {
    MealList chosenMealList;
    private final MealList wishList = new WishList();
    private final MealList recipesList = new RecipesList();
    private final Inventory inventory = new Inventory();


    public MealList getWishList() {
        return wishList;
    }

    public MealList getRecipesList() {
        return recipesList;
    }


    // Adds a new meal to the specified list after checking for duplicates
    public void addMeal(Meal newMeal, MealList mealsInput) throws EZMealPlanException {
        chosenMealList = mealsInput instanceof RecipesList ? getRecipesList() : getWishList();
        chosenMealList.addMeal(newMeal);
    }


    public List<Meal> filteringByMcost(double mcostDouble) {
        List<Meal> filteredMealList = new ArrayList<>();
        List<Meal> recipesList = getRecipesList().getList();
        for (Meal meal : recipesList) {
            if (meal.getPrice() == mcostDouble) {
                filteredMealList.add(meal);
            }
        }
        return filteredMealList;
    }

    public List<Meal> filteringByMname(String[] mealNameArray) {
        List<Meal> filteredMealList = new ArrayList<>();
        List<Meal> recipesList = getRecipesList().getList();
        for (Meal meal : recipesList) {
            boolean isMatchingMname = checkMname(mealNameArray, meal);
            if (isMatchingMname) {
                filteredMealList.add(meal);
            }
        }
        return filteredMealList;
    }

    private static boolean checkMname(String[] mealNameArray, Meal meal) {
        boolean isMealNameContains = true;
        String getMealName = meal.getName().toLowerCase();
        for (String mealName : mealNameArray) {
            mealName = mealName.toLowerCase();
            if (!getMealName.contains(mealName)) {
                isMealNameContains = false;
                break;
            }
        }
        return isMealNameContains;
    }

    public List<Meal> filteringByIng(String[] ingredientsArray) {
        List<Meal> filteredMealList = new ArrayList<>();
        List<Meal> mainMealList = getRecipesList().getList();
        for (Meal meal : mainMealList) {
            boolean isMealContainsIng = checkIngPerMeal(ingredientsArray, meal);
            if (isMealContainsIng) {
                filteredMealList.add(meal);
            }
        }
        return filteredMealList;
    }

    private static boolean checkIngPerMeal(String[] ingArray, Meal meal) {
        boolean isMealContainsIng = true;
        ArrayList<Ingredient> ingredientList = (ArrayList<Ingredient>) meal.getIngredientList();
        ArrayList<String> ingredientsNameList = new ArrayList<>();
        ingredientList.forEach(ingredient -> ingredientsNameList.add(ingredient.getName()));
        int overallCount = getOverallCount(ingArray, ingredientsNameList);
        if (overallCount < ingArray.length) {
            isMealContainsIng = false;
        }
        return isMealContainsIng;
    }

    private static int getOverallCount(String[] ingArray, ArrayList<String> ingredientsNameList) {
        int overallCount = 0;
        int zero = 0;
        for (String ingName : ingArray) {
            ingName = ingName.toLowerCase();
            int eachCount = getEachCount(ingName, ingredientsNameList);
            if (eachCount > zero) {
                overallCount++;
            }
        }
        return overallCount;
    }

    private static int getEachCount(String ingName, ArrayList<String> ingredientsNameList) {
        int eachCount = 0;
        for (String ingredientName : ingredientsNameList) {
            ingredientName = ingredientName.toLowerCase();
            if (ingredientName.contains(ingName)) {
                eachCount++;
            }
        }
        return eachCount;
    }

    public Meal removeMeal(int index, MealList mealListInput) throws EZMealPlanException {
        chosenMealList = mealListInput instanceof RecipesList ? getRecipesList() : getWishList();
        return chosenMealList.removeMeal(index);
    }

    public void compareLists() {
        List<Meal> recipesList = this.recipesList.getList();
        List<Meal> wishList = this.wishList.getList();
        for (Meal meal : wishList) {
            if (!recipesList.contains(meal)) {
                try {
                    addMeal(meal, this.recipesList);
                } catch (EZMealPlanException ezMealPlanException) {
                    ezMealPlanException.getMessage();
                }
            }
        }
    }

    public Inventory getInventory() {
        return inventory;
    }
}

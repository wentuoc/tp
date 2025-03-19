package seedu.logic;

import seedu.exceptions.DuplicateMealException;
import seedu.exceptions.EZMealPlanException;
import seedu.food.Ingredient;
import seedu.food.Meal;

import seedu.food.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MealManager {
    private final List<Meal> userMealList = new ArrayList<>();
    private final List<Meal> mainMealList = new ArrayList<>();

    public List<Meal> getMainMealList() {
        return mainMealList;
    }

    public List<Meal> getUserMealList() {
        return userMealList;
    }

    // Adds a new meal to the specified list after checking for duplicates
    public void addMeal(Meal newMeal, List<Meal> mealList) throws EZMealPlanException {
        checkDuplicateMeal(newMeal, mealList);
        mealList.add(newMeal);
        mealList.sort(Comparator.comparing(Product::getName));
    }

    // Checks whether the newMeal already exists in the given meal list
    private void checkDuplicateMeal(Meal newMeal, List<Meal> mealList) throws EZMealPlanException {
        String listName = mealList == mainMealList ? "main meal list" : "user meal list";
        for (Meal meal : mealList) {
            if (meal.equals(newMeal)) {
                throw new DuplicateMealException(newMeal.getName(), listName);
            }
        }
    }

    public List<Meal> filteringByMcost(double mcostDouble) {
        List<Meal> filteredMealList = new ArrayList<>();
        List<Meal> mainMealList = getMainMealList();
        for (Meal meal : mainMealList) {
            if (meal.getPrice() == mcostDouble) {
                filteredMealList.add(meal);
            }
        }
        return filteredMealList;
    }

    public List<Meal> filteringByMname(String[] mealNameArray) {
        List<Meal> filteredMealList = new ArrayList<>();
        List<Meal> mainMealList = getMainMealList();
        for (Meal meal : mainMealList) {
            boolean isMatchingMname = checkMname(mealNameArray, meal);
            if (isMatchingMname) {
                filteredMealList.add(meal);
            }
        }
        return filteredMealList;
    }

    private static boolean checkMname(String[] mealNameArray, Meal meal) {
        boolean isMealNameContains = true;
        String getMealName = meal.getName();
        for (String mealName : mealNameArray) {
            if (!getMealName.contains(mealName)) {
                isMealNameContains = false;
                break;
            }
        }
        return isMealNameContains;
    }

    public List<Meal> filteringByIng(String[] ingredientsArray) {
        List<Meal> filteredMealList = new ArrayList<>();
        List<Meal> mainMealList = getMainMealList();
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
        ArrayList<Ingredient> ingredientList = meal.getIngredientList();
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
            if (ingredientName.contains(ingName)) {
                eachCount++;
            }
        }
        return eachCount;
    }
}

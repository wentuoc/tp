package seedu.logic;

import seedu.exceptions.DuplicateMealException;
import seedu.exceptions.EZMealPlanException;
import seedu.food.Meal;
import java.util.ArrayList;
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
}

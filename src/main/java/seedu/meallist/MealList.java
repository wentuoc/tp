package seedu.meallist;

import seedu.exceptions.DuplicateMealException;
import seedu.exceptions.EZMealPlanException;
import seedu.food.Meal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class MealList {
    protected final List<Meal> mealList = new ArrayList<>();

    public List<Meal> getList() {
        return mealList;
    }

    // Adds a new meal to the specified list after checking for duplicates
    public void addMeal(Meal newMeal) throws EZMealPlanException {
        checkDuplicateMeal(newMeal);
        mealList.add(newMeal);
        mealList.sort(Comparator.comparing(meal -> meal.getName().toLowerCase()));
    }

    // Checks whether the newMeal already exists in the given meal list
    public void checkDuplicateMeal(Meal newMeal) throws EZMealPlanException {
        String mealListName = this instanceof MainList ? "main meal list" : "user meal list";
        for (Meal meal : mealList) {
            if (meal.equals(newMeal)) {
                throw new DuplicateMealException(newMeal.getName(), mealListName);
            }
        }
    }

}

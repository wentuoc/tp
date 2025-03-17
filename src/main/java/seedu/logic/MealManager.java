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

    public void add(Meal newMeal, List<Meal> mealList) throws EZMealPlanException {
        checkDuplicateMeal(newMeal, mealList);
        mealList.add(newMeal);
    }

    public void checkDuplicateMeal(Meal newMeal, List<Meal> mealList) throws EZMealPlanException {
        String listName;
        if (mealList.equals(mainMealList)) {
            listName = "main meal list";
        } else {
            listName = "user meal list";
        }
        for (Meal meal : mealList) {
            if (meal.equals(newMeal)) {
                String mealName = meal.getName();
                throw new DuplicateMealException(mealName, listName);
            }
        }
    }
}

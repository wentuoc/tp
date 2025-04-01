package seedu.meallist;

import seedu.exceptions.DuplicateMealException;
import seedu.exceptions.EZMealPlanException;
import seedu.exceptions.RemoveIndexOutOfRangeException;
import seedu.food.Meal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class MealList {
    protected final List<Meal> meals = new ArrayList<>();

    public List<Meal> getList() {
        return meals;
    }

    /**
     * Adds a new meal to the List after checking for duplicates.
     *
     * @throws DuplicateMealException if the same meal already exists in the List.
     */
    public void addMeal(Meal newMeal) throws DuplicateMealException {
        checkDuplicateMeal(newMeal);
        meals.add(newMeal);
        meals.sort(Comparator.comparing(meal -> meal.getName().toLowerCase()));
    }

    // Checks whether the newMeal already exists in the given meal list
    public void checkDuplicateMeal(Meal newMeal) throws DuplicateMealException {
        String mealListName = this instanceof RecipesList ? "main meal list" : "user meal list";
        for (Meal meal : meals) {
            if (meal.equals(newMeal)) {
                throw new DuplicateMealException(newMeal.getName(), mealListName);
            }
        }
    }

    /**
     * Removes the meal at a specified index and returns it.
     */
    public Meal removeMeal(int index) throws EZMealPlanException {
        try {
            return meals.remove(index - 1);
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            throw new RemoveIndexOutOfRangeException(index, meals.size());
        }
    }

    /**
     * Returns the size of the meal list.
     */
    public int size() {
        return meals.size();
    }

    /**
     * Returns the index of a specified meal object in the list.
     */
    public int getIndex(Meal meal) {
        return meals.indexOf(meal);
    }

    public boolean contains(Meal meal) {
        return meals.contains(meal);
    }
}

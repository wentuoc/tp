package seedu.meallist;

import seedu.exceptions.DuplicateMealException;
import seedu.exceptions.EZMealPlanException;
import seedu.exceptions.MealNotFoundException;
import seedu.exceptions.RemoveIndexOutOfRangeException;
import seedu.food.Meal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class MealList {
    protected final List<Meal> meals = new ArrayList<>();
    protected String mealListName;

    public List<Meal> getList() {
        return meals;
    }

    /**
     * Adds a new meal to the List after checking for duplicates.
     *
     * @throws DuplicateMealException if the same meal already exists in the List.
     */
    public void addMeal(Meal newMeal) throws EZMealPlanException {
        checkDuplicateMeal(newMeal);
        meals.add(newMeal);
        meals.sort(Comparator.comparing(meal -> meal.getName().toLowerCase()));
    }

    /**
     * Checks whether newMeal already exists in the mealList.
     */
    private void checkDuplicateMeal(Meal newMeal) throws EZMealPlanException {
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
     * Returns the index of a specified meal object in the list, starting from index 0.
     *
     * @throws MealNotFoundException if the specified meal cannot be found.
     */
    public int getIndex(Meal meal) throws MealNotFoundException {
        int index = meals.indexOf(meal);
        if (index == -1) {
            throw new MealNotFoundException(meal);
        } else {
            return index;
        }
    }

    public boolean contains(Meal meal) {
        return meals.contains(meal);
    }
}

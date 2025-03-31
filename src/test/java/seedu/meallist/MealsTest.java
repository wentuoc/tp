package seedu.meallist;

import org.junit.jupiter.api.Test;
import seedu.exceptions.*;
import seedu.food.Ingredient;
import seedu.food.Meal;

import static org.junit.jupiter.api.Assertions.*;

class MealsTest {

    Meal meal1;
    Meal meal2;
    Meal meal3;

    public MealsTest() throws InvalidPriceException, DuplicateIngredientException {
        meal1 = new Meal("Chicken Rice");
        meal1.addIngredient(new Ingredient("Chicken", 1));
        meal2 = new Meal("Apple Pie");
        meal2.addIngredient(new Ingredient("Apple", 0.5));
        meal3 = new Meal("French Fries");
        meal3.addIngredient(new Ingredient("Potato", 0.8));
    }

    @Test
    void addMeal_meals_sortedAlphabetically() throws EZMealPlanException {
        Meals mealList = new MainMeals();
        mealList.addMeal(meal1);
        mealList.addMeal(meal2);
        mealList.addMeal(meal3);
        assertEquals("[Apple Pie ($0.50), Chicken Rice ($1.00), French Fries ($0.80)]",
                mealList.getList().toString());
    }

    @Test
    void addMeal_duplicateMeal_exceptionThrown() throws EZMealPlanException {
        Meals mealList = new MainMeals();
        mealList.addMeal(meal1);
        mealList.addMeal(meal2);
        assertThrows(DuplicateMealException.class, () -> mealList.addMeal(meal1));
    }

    @Test
    void removeMeal_indexWithinRange_success() throws EZMealPlanException {
        Meals mealList = new MainMeals();
        mealList.addMeal(meal1);
        mealList.addMeal(meal2);
        mealList.addMeal(meal3);
        mealList.removeMeal(0);
        assertEquals("[Chicken Rice ($1.00), French Fries ($0.80)]", mealList.getList().toString());
    }

    @Test
    void removeMeal_indexOutOfRange_exceptionThrown() throws EZMealPlanException {
        Meals mealList = new MainMeals();
        mealList.addMeal(meal1);
        mealList.addMeal(meal2);
        mealList.addMeal(meal3);
        assertThrows(RemoveIndexOutOfRangeException.class, () -> mealList.removeMeal(-1));
        assertThrows(RemoveIndexOutOfRangeException.class, () -> mealList.removeMeal(3));
    }

    @Test
    void getIndex_mealExists_success() throws EZMealPlanException {
        Meals mealList = new MainMeals();
        mealList.addMeal(meal1);
        mealList.addMeal(meal2);
        mealList.addMeal(meal3);
        assertEquals(1, mealList.getIndex(meal1));
        assertEquals(0, mealList.getIndex(meal2));
        assertEquals(2, mealList.getIndex(meal3));
    }

    @Test
    void getIndex_mealDoesNotExist_exceptionThrown() throws EZMealPlanException {
        Meals mealList = new MainMeals();
        mealList.addMeal(meal1);
        mealList.addMeal(meal2);
        assertThrows(MealNotFoundException.class, () -> mealList.getIndex(meal3));
    }

    @Test
    void contains_mealExists_true() throws EZMealPlanException {
        Meals mealList = new MainMeals();
        mealList.addMeal(meal1);
        mealList.addMeal(meal2);
        assertTrue(mealList.contains(meal1));
    }

    @Test
    void contains_mealDoesNotExist_false() throws EZMealPlanException {
        Meals mealList = new MainMeals();
        mealList.addMeal(meal1);
        mealList.addMeal(meal2);
        assertFalse(mealList.contains(meal3));
    }
}
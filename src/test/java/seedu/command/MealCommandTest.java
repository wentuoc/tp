package seedu.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.exceptions.EZMealPlanException;
import seedu.food.Meal;
import seedu.logic.MealManager;
import seedu.ui.UserInterface;

public class MealCommandTest {

    /**
     * Define a UserInterface class for testing, to capture the params of printMealList.
     */
    public class TestUserInterface extends UserInterface {
        List<Meal> capturedMeals;
        String capturedListName;

        @Override
        public void printMealList(List<Meal> meals, String mealListName) {

            this.capturedMeals = new ArrayList<>(meals);
            this.capturedListName = mealListName;
        }
    }

    @Test
    public void testExecute_mealCommand_printsUserChosenMeals() throws EZMealPlanException {

        MealManager mealManager = new MealManager();
        Meal meal1 = new Meal("Meal A");
        Meal meal2 = new Meal("Meal B");
        mealManager.getUserMeals().getList().add(meal1);
        mealManager.getUserMeals().getList().add(meal2);

        TestUserInterface testUI = new TestUserInterface();
        MealCommand mealCommand = new MealCommand();
        mealCommand.execute(mealManager, testUI);

        assertEquals("user chosen meals", testUI.capturedListName);
        List<Meal> expectedMeals = new ArrayList<>();
        expectedMeals.add(meal1);
        expectedMeals.add(meal2);
        assertIterableEquals(expectedMeals, testUI.capturedMeals);
    }
}

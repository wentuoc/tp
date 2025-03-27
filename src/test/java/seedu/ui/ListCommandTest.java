package seedu.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import seedu.command.ListCommand;
import seedu.exceptions.EZMealPlanException;
import seedu.food.Meal;
import seedu.logic.MealManager;

public class ListCommandTest {

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
    public void testExecute_listCommand_printsMainList() throws EZMealPlanException {

        MealManager mealManager = new MealManager();
        Meal meal1 = new Meal("Main Meal 1");
        Meal meal2 = new Meal("Main Meal 2");
        mealManager.getMainMeals().getList().add(meal1);
        mealManager.getMainMeals().getList().add(meal2);

        TestUserInterface testUI = new TestUserInterface();
        ListCommand listCommand = new ListCommand();
        listCommand.execute(mealManager, testUI);

        assertEquals("main list", testUI.capturedListName);
        List<Meal> expectedMeals = new ArrayList<>();
        expectedMeals.add(meal1);
        expectedMeals.add(meal2);
        assertIterableEquals(expectedMeals, testUI.capturedMeals);
    }
}

package seedu.command;

import seedu.logic.MealManager;
import seedu.meallist.MealList;
import seedu.ui.UserInterface;
import seedu.food.Meal;
import java.util.List;


public class ClearCommand extends Command {

    @Override
    public void execute(MealManager mealManager, UserInterface ui) {
        MealList userMeals = mealManager.getUserMeals();
        List<Meal> userMealList = userMeals.getList();
        userMealList.clear();
        ui.printClearedList();
    }
}

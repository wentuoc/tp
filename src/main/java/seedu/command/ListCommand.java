package seedu.command;

import seedu.exceptions.EZMealPlanException;
import seedu.food.Meal;
import seedu.logic.MealManager;
import seedu.ui.UserInterface;

import java.util.List;

public class ListCommand extends Command {
    @Override
    public void execute(MealManager mealManager, UserInterface ui) throws EZMealPlanException {
        List<Meal> mainMealList = mealManager.getMainMealList();
        ui.printMealList(mainMealList, "main list");
    }
}

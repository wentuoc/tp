//@@author olsonwangyj
package seedu.command;

import seedu.exceptions.EZMealPlanException;
import seedu.food.Meal;
import seedu.logic.MealManager;
import seedu.ui.UserInterface;

import java.util.List;
import java.util.logging.Logger;

public class RecipesCommand extends Command {
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Override
    public void execute(MealManager mealManager, UserInterface ui) throws EZMealPlanException {
        assert mealManager != null : "MealManager cannot be null";
        logger.fine("Executing 'recipes' command");
        String recipesListName = mealManager.getRecipesList().getMealListName();
        List<Meal> recipesList = mealManager.getRecipesList().getList();
        ui.printMealList(recipesList, recipesListName);
    }
}

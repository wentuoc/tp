package seedu.command;

import seedu.logic.MealManager;
import seedu.ui.UserInterface;
import seedu.meallist.MealList;
import seedu.storage.Storage;
import seedu.food.Meal;
import java.util.List;


public class ClearCommand extends Command {

    @Override
    public void execute(MealManager mealManager, UserInterface ui) {
        MealList userMealList = mealManager.getUserList();
        List<Meal> userList = userMealList.getList();
        userList.clear();
        ui.printClearedList();
        Storage.clearUserList();
    }
}

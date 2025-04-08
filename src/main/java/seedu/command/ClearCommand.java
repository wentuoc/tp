//@@author ryanling169
package seedu.command;

import seedu.logic.MealManager;
import seedu.ui.UserInterface;
import seedu.food.Meal;
import java.util.List;


public class ClearCommand extends Command {

    @Override
    public void execute(MealManager mealManager, UserInterface ui) {
        List<Meal> wishList = mealManager.getWishList().getList();
        wishList.clear();
        ui.printClearedList();
    }
}

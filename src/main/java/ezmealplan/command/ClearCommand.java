//@@author ryanling169
package ezmealplan.command;

import ezmealplan.logic.MealManager;
import ezmealplan.ui.UserInterface;
import ezmealplan.food.Meal;
import java.util.List;


public class ClearCommand extends Command {

    @Override
    public void execute(MealManager mealManager, UserInterface ui) {
        List<Meal> wishList = mealManager.getWishList().getList();
        wishList.clear();
        ui.printClearedList();
    }
}

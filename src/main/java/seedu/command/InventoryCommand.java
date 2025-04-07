package seedu.command;

import seedu.exceptions.EZMealPlanException;
import seedu.food.Inventory;
import seedu.logic.MealManager;
import seedu.ui.UserInterface;

import java.util.logging.Logger;

public class InventoryCommand extends Command {
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Override
    public void execute(MealManager mealManager, UserInterface ui) throws EZMealPlanException {
        assert mealManager != null : "MealManager cannot be null";
        logger.fine("Executing 'inventory' command");
        Inventory inventory = mealManager.getInventory();
        ui.printInventory(inventory.toString());
    }
}

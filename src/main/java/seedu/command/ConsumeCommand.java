package seedu.command;

import seedu.checkers.ConsumeChecker;
import seedu.exceptions.EZMealPlanException;
import seedu.exceptions.IngredientPriceFormatException;
import seedu.exceptions.InvalidPriceException;
import seedu.food.Ingredient;
import seedu.logic.MealManager;
import seedu.ui.UserInterface;
import seedu.food.Inventory;


import java.util.ArrayList;

import java.util.List;
import java.util.logging.Logger;

public class ConsumeCommand extends Command {
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static final String CONSUME = "consume";
    private static final List<String> ingredientNames = new ArrayList<>();
    private static final List<Ingredient> ingredients = new ArrayList<>();

    public ConsumeCommand(String userInput) {
        validUserInput = userInput.trim();
    }

    /**
     * Executes the consume command by removing each specified ingredient from the inventory.
     *
     * @param mealManager the MealManager that provides access to the inventory.
     * @param ui          the UserInterface for printing messages.
     */
    @Override
    public void execute(MealManager mealManager, UserInterface ui) throws EZMealPlanException {
        ingredientNames.clear();
        ingredients.clear();

        if (!checkValidUserInput()) {
            logger.severe("Invalid consume command input detected.");
            return;
        }

        int afterConsumeIndex = validUserInput.indexOf(CONSUME) + CONSUME.length();
        String args = validUserInput.substring(afterConsumeIndex).trim();
        parseIngredients(args);
        // Retrieve the inventory from the MealManager.
        Inventory inventory = mealManager.getInventory();
        // Process each ingredient name provided in the command.

        for (String ingredientName : ingredientNames) {
            Ingredient removedIngredient = inventory.removeIngredient(ingredientName);
            ui.printConsumed(removedIngredient.toString());
        }

        for (Ingredient ingredient : ingredients) {
            inventory.removeIngredient(ingredient);
            ui.printConsumed(ingredient.toString());
        }
    }

    private void parseIngredients(String args) throws InvalidPriceException, IngredientPriceFormatException {
        if (args.isEmpty()) {
            return;
        }
        // Split using "/ing" as the delimiter.
        String ing = "/ing";
        int afterIngIndex = args.indexOf(ing) + ing.length();
        String argsAfterIng = args.substring(afterIngIndex).trim();
        String[] tokens = argsAfterIng.split(",");
        for (String token : tokens) {
            token = token.trim();
            if (!token.isEmpty()) {
                processIngredientToken(token);
            }
        }
    }

    private void processIngredientToken(String token) throws InvalidPriceException, IngredientPriceFormatException {
        if (!hasParentheses(token)) {
            ingredientNames.add(token);
        } else {
            int openParenIndex = token.lastIndexOf('(');
            int closeParenIndex = token.lastIndexOf(')');
            int startIndex = 0;
            int indexAdjustment = 1;

            String name = token.substring(startIndex, openParenIndex).trim();
            String priceStr = token.substring(openParenIndex + indexAdjustment, closeParenIndex).trim();
            Ingredient ingredientToDelete = new Ingredient(name, priceStr);
            ingredients.add(ingredientToDelete);
        }
    }

    private boolean hasParentheses(String token) {
        return token.contains("(") && token.contains(")");
    }

    private boolean checkValidUserInput() throws EZMealPlanException {
        ConsumeChecker checker = new ConsumeChecker(validUserInput);
        checker.check();
        return checker.isPassed();
    }
}

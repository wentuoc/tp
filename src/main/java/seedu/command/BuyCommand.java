package seedu.command;

import seedu.exceptions.InvalidPriceException;
import seedu.logic.MealManager;
import seedu.ui.UserInterface;
import seedu.food.Inventory;
import seedu.food.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class BuyCommand extends Command {
    static final String BUY = "buy";
    private static final List<Ingredient> ingredients = new ArrayList<>();


    public BuyCommand(String userInput) {
        this.validUserInput = userInput.trim();
    }

    /**
     * Executes the buy command by adding each specified ingredient into the inventory.
     *
     * @param mealManager the MealManager providing access to the inventory.
     * @param ui          the UserInterface for printing messages.
     */
    @Override
    public void execute(MealManager mealManager, UserInterface ui) {
        int afterBuyIndex = validUserInput.indexOf(BUY) + BUY.length();
        String args = validUserInput.substring(afterBuyIndex).trim();
        parseIngredientsForBuy(args);
        Inventory inventory = mealManager.getInventory();
        for (Ingredient ingredient : ingredients) {
            // Add the ingredient (with name and price) into the inventory list.
            inventory.addIngredient(ingredient);
            ui.printBought(ingredient);
        }
        ingredients.clear();
    }

    private static void parseIngredientsForBuy(String args) {
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
            addValidToken(token);
        }

    }

    private static void addValidToken(String token) {
        if (!token.isEmpty()) {
            // Expected format: "Chicken (1.0)"
            int openParenIndex = token.lastIndexOf('(');
            int closeParenIndex = token.lastIndexOf(')');
            int invalidIndex = -1;
            if (openParenIndex != invalidIndex && closeParenIndex != invalidIndex &&
                openParenIndex < closeParenIndex) {
                int startIndex = 0;
                int indexAdjustment = 1;
                String name = token.substring(startIndex, openParenIndex).trim();
                String priceStr = token.substring(openParenIndex + indexAdjustment, closeParenIndex).trim();
                addExtractedToken(token, priceStr, name);
            } else {
                System.out.println("Invalid format for ingredient: " + token);
            }
        }
    }

    private static void addExtractedToken(String token, String priceStr, String name) {
        try {
            double price = Double.parseDouble(priceStr);
            ingredients.add(new Ingredient(name, price));
        } catch (NumberFormatException | InvalidPriceException exception) {
            System.out.println("Invalid price format for ingredient: " + token);
        }
    }
}

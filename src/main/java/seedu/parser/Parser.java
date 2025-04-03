package seedu.parser;

import seedu.command.BuyCommand;
import seedu.command.ByeCommand;
import seedu.command.ClearCommand;
import seedu.command.Command;
import seedu.command.ConsumeCommand;
import seedu.command.CreateCommand;
import seedu.command.DeleteCommand;
import seedu.command.FilterCommand;
import seedu.command.HelpCommand;
import seedu.command.ListCommand;
import seedu.command.MealCommand;
import seedu.command.RecommendCommand;
import seedu.command.RemoveCommand;
import seedu.command.SelectCommand;
import seedu.command.UnknownCommand;
import seedu.command.ViewCommand;
import seedu.exceptions.InvalidPriceException;
import seedu.food.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    static String bye = "bye";
    static String create = "create";
    static String filter = "filter";
    static String select = "select";
    static String meal = "meal";
    static String list = "list";
    static String clear = "clear";
    static String help = "help";
    static String remove = "remove";
    static String view = "view";
    static String delete = "delete";
    static String recommend = "recommend";
    static String consume = "consume";
    static String buy = "buy";

    public static Command parse(String userInput) {
        String lowerCaseUserInput = userInput.toLowerCase().trim();
        userInput = userInput.trim();
        if (lowerCaseUserInput.startsWith(bye)) {
            return new ByeCommand();
        } else if (lowerCaseUserInput.startsWith(create)) {
            return new CreateCommand(userInput);
        } else if (lowerCaseUserInput.startsWith(filter)) {
            return new FilterCommand(userInput);
        } else if (lowerCaseUserInput.startsWith(select)) {
            return new SelectCommand(userInput);
        } else if (lowerCaseUserInput.startsWith(list)) {
            return new ListCommand();
        } else if (lowerCaseUserInput.startsWith(meal)) {
            return new MealCommand();
        } else if (lowerCaseUserInput.startsWith(clear)) {
            return new ClearCommand();
        } else if (lowerCaseUserInput.startsWith(help)) {
            return new HelpCommand(userInput);
        } else if (lowerCaseUserInput.startsWith(remove)) {
            return new RemoveCommand(userInput);
        } else if (lowerCaseUserInput.startsWith(view)) {
            return new ViewCommand(userInput);
        } else if (lowerCaseUserInput.startsWith(delete)) {
            return new DeleteCommand(userInput);
        } else if (lowerCaseUserInput.startsWith(recommend)) {
            return new RecommendCommand(userInput);
        } else if (lowerCaseUserInput.startsWith(consume)) {
            String args = userInput.substring(consume.length()).trim();
            List<String> ingredientList = parseIngredients(args);
            return new ConsumeCommand(ingredientList);
        } else if (lowerCaseUserInput.startsWith(buy)) {
            String args = userInput.substring(buy.length()).trim();
            List<Ingredient> ingredientList = parseIngredientsForBuy(args);
            return new BuyCommand(ingredientList);
        } else {
            return new UnknownCommand(userInput);
        }
    }

    private static List<String> parseIngredients(String args) {
        List<String> ingredients = new ArrayList<>();
        if (args.isEmpty()) {
            return ingredients;
        }
        // Split using "/ing" as the delimiter.
        String[] tokens = args.split("/ing");
        for (String token : tokens) {
            token = token.trim();
            if (!token.isEmpty()) {
                ingredients.add(token);
            }
        }
        return ingredients;
    }

    private static List<Ingredient> parseIngredientsForBuy(String args) {
        List<Ingredient> ingredients = new ArrayList<>();
        if (args.isEmpty()) {
            return ingredients;
        }
        // Split using "/ing" as the delimiter.
        String[] tokens = args.split("/ing");
        for (String token : tokens) {
            token = token.trim();
            if (!token.isEmpty()) {
                // Expected format: "Chicken (1.0)"
                int openParenIndex = token.lastIndexOf('(');
                int closeParenIndex = token.lastIndexOf(')');
                if (openParenIndex != -1 && closeParenIndex != -1 && openParenIndex < closeParenIndex) {
                    String name = token.substring(0, openParenIndex).trim();
                    String priceStr = token.substring(openParenIndex + 1, closeParenIndex).trim();
                    try {
                        double price = Double.parseDouble(priceStr);
                        ingredients.add(new Ingredient(name, price));
                    } catch (NumberFormatException | InvalidPriceException e) {
                        System.out.println("Invalid price format for ingredient: " + token);
                    }
                } else {
                    System.out.println("Invalid format for ingredient: " + token);
                }
            }
        }
        return ingredients;
    }
}


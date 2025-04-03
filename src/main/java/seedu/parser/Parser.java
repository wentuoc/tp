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
import seedu.command.InventoryCommand;
import seedu.command.RecipesCommand;
import seedu.command.RecommendCommand;
import seedu.command.RemoveCommand;
import seedu.command.SelectCommand;
import seedu.command.UnknownCommand;
import seedu.command.ViewCommand;
import seedu.command.WishlistCommand;

public class Parser {
    static String bye = "bye";
    static String create = "create";
    static String filter = "filter";
    static String select = "select";
    static String wishlist = "wishlist";
    static String recipes = "recipes";
    static String clear = "clear";
    static String help = "help";
    static String remove = "remove";
    static String view = "view";
    static String delete = "delete";
    static String recommend = "recommend";
    static String consume = "consume";
    static String buy = "buy";
    static String inventory = "inventory";

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
        } else if (lowerCaseUserInput.startsWith(recipes)) {
            return new RecipesCommand();
        } else if (lowerCaseUserInput.startsWith(wishlist)) {
            return new WishlistCommand();
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
            return new ConsumeCommand(userInput);
        } else if (lowerCaseUserInput.startsWith(buy)) {
            return new BuyCommand(userInput);
        } else if (lowerCaseUserInput.startsWith(inventory)) {
            return new InventoryCommand();
        } else {
            return new UnknownCommand(userInput);
        }
    }
}


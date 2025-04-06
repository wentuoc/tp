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
import seedu.exceptions.EZMealPlanException;

public class Parser {
    private static final String BYE = "bye";
    private static final String CREATE = "create";
    private static final String FILTER = "filter";
    private static final String SELECT = "select";
    private static final String WISHLIST = "wishlist";
    private static final String RECIPES = "recipes";
    private static final String CLEAR = "clear";
    private static final String HELP = "help";
    private static final String REMOVE = "remove";
    private static final String VIEW = "view";
    private static final String DELETE = "delete";
    private static final String RECOMMEND = "recommend";
    private static final String CONSUME = "consume";
    private static final String BUY = "buy";
    private static final String INVENTORY = "inventory";

    public static Command parse(String userInput) throws EZMealPlanException {
        String lowerCaseUserInput = userInput.toLowerCase().trim();
        userInput = userInput.trim();
        if (lowerCaseUserInput.startsWith(BYE)) {
            return new ByeCommand();
        } else if (lowerCaseUserInput.startsWith(CREATE)) {
            return new CreateCommand(userInput);
        } else if (lowerCaseUserInput.startsWith(FILTER)) {
            return new FilterCommand(userInput);
        } else if (lowerCaseUserInput.startsWith(SELECT)) {
            return new SelectCommand(userInput);
        } else if (lowerCaseUserInput.startsWith(RECIPES)) {
            return new RecipesCommand();
        } else if (lowerCaseUserInput.startsWith(WISHLIST)) {
            return new WishlistCommand();
        } else if (lowerCaseUserInput.startsWith(CLEAR)) {
            return new ClearCommand();
        } else if (lowerCaseUserInput.startsWith(HELP)) {
            return new HelpCommand(userInput);
        } else if (lowerCaseUserInput.startsWith(REMOVE)) {
            return new RemoveCommand(userInput);
        } else if (lowerCaseUserInput.startsWith(VIEW)) {
            return new ViewCommand(userInput);
        } else if (lowerCaseUserInput.startsWith(DELETE)) {
            return new DeleteCommand(userInput);
        } else if (lowerCaseUserInput.startsWith(RECOMMEND)) {
            return new RecommendCommand(userInput);
        } else if (lowerCaseUserInput.startsWith(CONSUME)) {
            return new ConsumeCommand(userInput);
        } else if (lowerCaseUserInput.startsWith(BUY)) {
            return new BuyCommand(userInput);
        } else if (lowerCaseUserInput.startsWith(INVENTORY)) {
            return new InventoryCommand();
        } else {
            return new UnknownCommand(userInput);
        }
    }
}


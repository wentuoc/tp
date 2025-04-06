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
import seedu.command.MistypedCommand;
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
    private static final String[] allCommands = {BYE, CREATE, FILTER, SELECT, WISHLIST, RECIPES, CLEAR, HELP, REMOVE,
        VIEW, DELETE, RECOMMEND, CONSUME, BUY, INVENTORY};

    public static Command parse(String userInput) throws EZMealPlanException {
        String firstWordLowerCase = getFirstWord(userInput).toLowerCase();
        userInput = userInput.trim();
        if (firstWordLowerCase.equals(BYE)) {
            return new ByeCommand();
        } else if (firstWordLowerCase.equals(CREATE)) {
            return new CreateCommand(userInput);
        } else if (firstWordLowerCase.equals(FILTER)) {
            return new FilterCommand(userInput);
        } else if (firstWordLowerCase.equals(SELECT)) {
            return new SelectCommand(userInput);
        } else if (firstWordLowerCase.equals(RECIPES)) {
            return new RecipesCommand();
        } else if (firstWordLowerCase.equals(WISHLIST)) {
            return new WishlistCommand();
        } else if (firstWordLowerCase.equals(CLEAR)) {
            return new ClearCommand();
        } else if (firstWordLowerCase.equals(HELP)) {
            return new HelpCommand(userInput);
        } else if (firstWordLowerCase.equals(REMOVE)) {
            return new RemoveCommand(userInput);
        } else if (firstWordLowerCase.equals(VIEW)) {
            return new ViewCommand(userInput);
        } else if (firstWordLowerCase.equals(DELETE)) {
            return new DeleteCommand(userInput);
        } else if (firstWordLowerCase.equals(RECOMMEND)) {
            return new RecommendCommand(userInput);
        } else if (firstWordLowerCase.equals(CONSUME)) {
            return new ConsumeCommand(userInput);
        } else if (firstWordLowerCase.equals(BUY)) {
            return new BuyCommand(userInput);
        } else if (firstWordLowerCase.equals(INVENTORY)) {
            return new InventoryCommand();
        } else {
            return parseUnknownInput(firstWordLowerCase);
        }
    }

    private static String getFirstWord(String userInput) {
        int firstSpaceIndex = userInput.indexOf(' ');
        if (firstSpaceIndex == -1) {
            //userInput does not contain a space
            return userInput;
        }
        return userInput.substring(0, firstSpaceIndex);
    }

    private static Command parseUnknownInput(String firstWordLowerCase) {
        for (String commandString : allCommands) {
            if (firstWordLowerCase.startsWith(commandString)) {
                return new MistypedCommand(firstWordLowerCase, commandString);
            }
        }
        return new UnknownCommand(firstWordLowerCase);
    }
}


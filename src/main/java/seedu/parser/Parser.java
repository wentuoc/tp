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
    private static final String[] allCommandStrings = {BYE, CREATE, FILTER, SELECT, WISHLIST, RECIPES, CLEAR, HELP,
        REMOVE, VIEW, DELETE, RECOMMEND, CONSUME, BUY, INVENTORY};

    public static Command parse(String userInput) throws EZMealPlanException {
        String firstWordLowerCase = getFirstWord(userInput).toLowerCase();
        userInput = userInput.trim();
        return switch (firstWordLowerCase) {
        case BYE -> new ByeCommand();
        case CREATE -> new CreateCommand(userInput);
        case FILTER -> new FilterCommand(userInput);
        case SELECT -> new SelectCommand(userInput);
        case RECIPES -> new RecipesCommand();
        case WISHLIST -> new WishlistCommand();
        case CLEAR -> new ClearCommand();
        case HELP -> new HelpCommand(userInput);
        case REMOVE -> new RemoveCommand(userInput);
        case VIEW -> new ViewCommand(userInput);
        case DELETE -> new DeleteCommand(userInput);
        case RECOMMEND -> new RecommendCommand(userInput);
        case CONSUME -> new ConsumeCommand(userInput);
        case BUY -> new BuyCommand(userInput);
        case INVENTORY -> new InventoryCommand();
        default -> parseUnknownInput(firstWordLowerCase);
        };
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
        for (String actualCommandString : allCommandStrings) {
            if (firstWordLowerCase.startsWith(actualCommandString)) {
                return new MistypedCommand(firstWordLowerCase, actualCommandString);
            }
        }
        return new UnknownCommand(firstWordLowerCase);
    }
}


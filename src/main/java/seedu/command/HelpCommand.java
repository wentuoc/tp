package seedu.command;


import seedu.logic.MealManager;
import seedu.ui.UserInterface;

public class HelpCommand extends Command {
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
    String commandDescription;


    public HelpCommand(String userInput) {
        this.commandDescription = userInput.toLowerCase().replace("help", "").trim();
    }

    @Override
    public void execute(MealManager mealManager, UserInterface ui) {
        if (commandDescription.isEmpty()) {
            ui.printGeneralHelp();
            return;
        }
        switch(commandDescription) {
        case RECIPES:
            ui.printRecipesCommandHelp();
            break;
        case WISHLIST:
            ui.printWishlistCommandHelp();
            break;
        case FILTER:
            ui.printFilterCommandHelp();
            break;
        case SELECT:
            ui.printSelectCommandHelp();
            break;
        case REMOVE:
            ui.printRemoveCommandHelp();
            break;
        case CREATE:
            ui.printCreateCommandHelp();
            break;
        case DELETE:
            ui.printDeleteCommandHelp();
            break;
        case VIEW:
            ui.printViewCommandHelp();
            break;
        case CLEAR:
            ui.printClearCommandHelp();
            break;
        case BYE:
            ui.printByeCommandHelp();
            break;
        case HELP:
            ui.printHelpCommandHelp();
            break;
        default:
            ui.printUnknownCommand(commandDescription);
            break;
        }
    }
}


package seedu.command;


import seedu.logic.MealManager;
import seedu.ui.UserInterface;


public class HelpCommand extends Command {
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
        case "list":
            ui.printListCommandHelp();
            break;
        case "meal":
            ui.printMealCommandHelp();
            break;
        case "filter":
            ui.printFilterCommandHelp();
            break;
        case "select":
            ui.printSelectCommandHelp();
            break;
        case "remove":
            ui.printRemoveCommandHelp();
            break;
        case "create":
            ui.printCreateCommandHelp();
            break;
        case "delete":
            ui.printDeleteCommandHelp();
            break;
        case "view":
            ui.printViewCommandHelp();
            break;
        case "clear":
            ui.printClearCommandHelp();
            break;
        case "bye":
            ui.printByeCommandHelp();
            break;
        case "help":
            ui.printHelpCommandHelp();
            break;
        default:
            ui.printUnknownCommand(commandDescription);
            break;
        }
    }
}


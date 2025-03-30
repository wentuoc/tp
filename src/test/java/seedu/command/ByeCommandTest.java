package seedu.command;

import seedu.exceptions.EZMealPlanException;
import seedu.logic.MealManager;
import seedu.storage.Storage;
import seedu.ui.UserInterface;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class ByeCommandTest {
    private static final Logger logger = Logger.getLogger(ByeCommandTest.class.getName());
    final MealManager mealManager = new MealManager();
    final UserInterface ui = new UserInterface();

    public ByeCommandTest() {
        String fileName = "ByeCommandTest.log";
        setupLogger(fileName);
    }

    private static void setupLogger(String fileName) {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.ALL);
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.INFO);
        logger.addHandler(consoleHandler);
        createLogFile(fileName);
    }

    private static void createLogFile(String fileName) {
        try {
            FileHandler fileHandler = new FileHandler(fileName, true);
            fileHandler.setLevel(Level.FINE);
            logger.addHandler(fileHandler);
        } catch (IOException ioException) {
            logger.log(Level.SEVERE, "File logger is not working.", ioException);
        }
    }

    @Test
    public void byeCommand_success() {
        byeCommand_emptyLists_test();
        byeCommand_emptyUserList_test();
        byeCommand_emptyMainList_test();
        byeCommand_filledLists_test();
    }

    @Test
    public void byeCommand_filledLists_test() {

    }

    @Test
    public void byeCommand_emptyMainList_test() {

    }

    @Test
    public void byeCommand_emptyUserList_test() {

    }

    @Test
    public void byeCommand_emptyLists_test() {
        logger.fine("running byeCommand_emptyLists_test()");
        try {
            ByeCommand byeCommand = new ByeCommand();
            byeCommand.execute(mealManager, ui);
        } catch (Exception exception) {
            ui.printErrorMessage(exception);
            fail();
        }

    }
}

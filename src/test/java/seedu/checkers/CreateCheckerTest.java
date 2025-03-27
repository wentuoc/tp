package seedu.checkers;

import seedu.exceptions.EZMealPlanException;
import seedu.exceptions.InvalidCreateIndexException;
import seedu.exceptions.InvalidIngMnameException;
import seedu.exceptions.InvalidIngredientFormatException;
import seedu.exceptions.MissingIngKeywordException;
import seedu.exceptions.MissingIngredientException;
import seedu.exceptions.MissingMealNameException;
import seedu.exceptions.MissingMnameKeywordException;


import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateCheckerTest {
    private static final Logger logger = Logger.getLogger(CreateCheckerTest.class.getName());

    public static void main(String[] args) {
        String fileName = "CreateCheckerTest.log";
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
    public void create_checker_success() throws EZMealPlanException {
        main(null);
        String validUserInput = "create /mname chicken rice /ing chicken breast (2.5)," +
                                " rice (1.5), egg (0.5), cucumber (1)";
        CreateChecker checker = new CreateChecker(validUserInput);
        checker.check();
        assertTrue(checker.isPassed());
    }

    @Test
    public void create_checker_fail() {
        main(null);
        checkMissingMname();
        checkMissingIng();
        checkInvalidCreateIndex();
        checkInvalidIngMnameIndex();
        checkMissingMealName();
        checkMissingIngredient();
        checkInvalidIngredientFormat();
    }

    private void checkInvalidIngredientFormat() {
        String[] invalidIngredientFormat = {"create /mname test /ing ing123", "create /mname test /ing 123"
                , "create /mname test /ing ing()", "create /mname test /ing (1)"};
        logger.fine("running checkInvalidIngredientFormat()");
        checkMatchingException(invalidIngredientFormat, new InvalidIngredientFormatException().getMessage());
    }

    private void checkMissingIngredient() {
        String[] missingMealName = {"create /mname test /ing", "create /mname ing(1) /ing"};
        logger.fine("running checkMissingIngredient()");
        checkMatchingException(missingMealName, new MissingIngredientException("create").getMessage());
    }

    private static void checkMissingMealName() {
        String[] missingMealName = {"create /mname /ing ing(1)", "create /mname /ing ing123"};
        logger.fine("running checkMissingMealName()");
        checkMatchingException(missingMealName, new MissingMealNameException("create").getMessage());
    }

    private static void checkInvalidIngMnameIndex() {
        String[] invalidIngMname = {"create /ing test /mname ing(1)", "create /ing /mname test create ing(1)"
                , "create test ing(1) /ing /mname", "create /ing /mname"};
        logger.fine("running checkInvalidMnameIndex()");
        checkMatchingException(invalidIngMname, new InvalidIngMnameException().getMessage());
    }

    private static void checkMissingIng() {
        String[] missingIng = {"create /mname test ing(1)", "create /mname", "create /mname test "
                , "create ing(1) /mname"};
        logger.fine("running checkMissingIng()");
        checkMatchingException(missingIng, new MissingIngKeywordException().getMessage());
    }

    private static void checkMissingMname() {
        String[] missingMname = {"create test /ing ing(1)", "create /ing", "create test /ing", "create /ing ing(1)"};
        logger.fine("running checkMissingMname()");
        checkMatchingException(missingMname, new MissingMnameKeywordException().getMessage());
    }

    private static void checkInvalidCreateIndex() {
        String[] invalidCreateIndex = {"/mname test create /ing ing(1)", "/mname test /ing create ing(1)"
                , "/ing test create /mname ing(1)", "/ing test /mname ing(1) create"};
        logger.fine("running checkInvalidCreateIndex()");
        checkMatchingException(invalidCreateIndex, new InvalidCreateIndexException().getMessage());
    }

    private static void checkMatchingException(String[] invalidUserInputs, String expectedOutput) {
        for (String invalidUserInput : invalidUserInputs) {
            CreateChecker checker = new CreateChecker(invalidUserInput);
            try {
                checker.check();
            } catch (EZMealPlanException ezMealPlanException) {
                assertEquals(expectedOutput, ezMealPlanException.getMessage());
                logger.info("Expected exception caught!");
            } finally {
                assertFalse(checker.isPassed());
                logger.info("User input is expected to be invalid.");
            }
        }
    }
}

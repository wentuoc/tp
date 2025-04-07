package seedu.checkers;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import seedu.exceptions.EZMealPlanException;
import seedu.exceptions.InvalidKeywordIndexException;
import seedu.exceptions.MissingMealIndexException;
import seedu.exceptions.InvalidViewIndexException;

public class ViewCheckerTest {

    private static final Logger logger = Logger.getLogger(ViewCheckerTest.class.getName());

    @BeforeAll
    public static void setupLogger() {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.ALL);

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.INFO);
        logger.addHandler(consoleHandler);

        try {
            FileHandler fileHandler = new FileHandler("ViewCheckerTest.log", true);
            fileHandler.setLevel(Level.FINE);
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to set up file logger", e);
        }
    }

    @Test
    public void viewChecker_validRecipeIndex_success() throws EZMealPlanException {
        logger.fine("Running viewChecker_validRecipeIndex_success()");
        ViewChecker checker = new ViewChecker("view /r 2", "/r");
        checker.check();
        logger.info("viewChecker_validRecipeIndex_success passed");
    }

    @Test
    public void viewChecker_validWishlistIndex_success() throws EZMealPlanException {
        logger.fine("Running viewChecker_validWishlistIndex_success()");
        ViewChecker checker = new ViewChecker("view /w 1", "/w");
        checker.check();
        logger.info("viewChecker_validWishlistIndex_success passed");
    }

    @Test
    public void viewChecker_keywordBeforeView_throwsInvalidKeywordIndexException() {
        logger.fine("Running viewChecker_keywordBeforeView_throwsInvalidKeywordIndexException()");
        ViewChecker checker = new ViewChecker("/r view 1", "/r");
        assertThrows(InvalidKeywordIndexException.class, checker::check);
        logger.info("viewChecker_keywordBeforeView_throwsInvalidKeywordIndexException passed");
    }

    @Test
    public void viewChecker_missingIndex_throwsMissingMealIndexException() {
        logger.fine("Running viewChecker_missingIndex_throwsMissingMealIndexException()");
        ViewChecker checker = new ViewChecker("view /r", "/r");
        assertThrows(MissingMealIndexException.class, checker::check);
        logger.info("viewChecker_missingIndex_throwsMissingMealIndexException passed");
    }

    @Test
    public void viewChecker_nonIntegerIndex_throwsInvalidViewIndexException() {
        logger.fine("Running viewChecker_nonIntegerIndex_throwsInvalidViewIndexException()");
        ViewChecker checker = new ViewChecker("view /r abc", "/r");
        assertThrows(InvalidViewIndexException.class, checker::check);
        logger.info("viewChecker_nonIntegerIndex_throwsInvalidViewIndexException passed");
    }

    @Test
    public void viewChecker_extraSpacingInput_success() throws EZMealPlanException {
        logger.fine("Running viewChecker_extraSpacingInput_success()");
        ViewChecker checker = new ViewChecker("   view    /w   4  ", "/w");
        checker.check();
        logger.info("viewChecker_extraSpacingInput_success passed");
    }

    @Test
    public void viewChecker_uppercaseInput_success() throws EZMealPlanException {
        logger.fine("Running viewChecker_uppercaseInput_success()");
        ViewChecker checker = new ViewChecker("VIEW /R 3", "/r");
        checker.check();
        logger.info("viewChecker_uppercaseInput_success passed");
    }

    @Test
    public void viewChecker_missingKeyword_throwsInvalidKeywordIndexException() {
        logger.fine("Running viewChecker_missingKeyword_throwsInvalidKeywordIndexException()");
        ViewChecker checker = new ViewChecker("view 1", "/r");
        assertThrows(InvalidKeywordIndexException.class, checker::check);
        logger.info("viewChecker_missingKeyword_throwsInvalidKeywordIndexException passed");
    }

}

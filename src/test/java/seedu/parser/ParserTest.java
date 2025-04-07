package seedu.parser;

import org.junit.jupiter.api.Test;
import seedu.command.MistypedCommand;
import seedu.command.UnknownCommand;
import seedu.exceptions.EZMealPlanException;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class ParserTest {
    private static final Logger logger = Logger.getLogger(Parser.class.getName());

    public ParserTest() {
        String fileName = "ParserTest.log";
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
    public void parse_mistypedInput_returnsMistypedCommand() throws EZMealPlanException {
        logger.fine("Running parse_mistypedInput_returnsMistypedCommand()");
        String testInput1 = "wishlists";
        String testInput2 = "filter/ing ingredient";
        String testInput3 = "delete1";
        String testInput4 = "remove1 1";
        String testInput5 = "viewwishlist";
        String testInput6 = "helpselect";
        assertInstanceOf(MistypedCommand.class, Parser.parse(testInput1));
        assertInstanceOf(MistypedCommand.class, Parser.parse(testInput2));
        assertInstanceOf(MistypedCommand.class, Parser.parse(testInput3));
        assertInstanceOf(MistypedCommand.class, Parser.parse(testInput4));
        assertInstanceOf(MistypedCommand.class, Parser.parse(testInput5));
        assertInstanceOf(MistypedCommand.class, Parser.parse(testInput6));
        logger.info("Correct command object instantiated");
    }

    @Test
    public void parse_unknownInput_returnsUnknownCommand() throws EZMealPlanException {
        logger.fine("Running parse_unknownInput_returnsUnknownCommand()");
        String testInput1 = "wish list";
        String testInput2 = "reccomend /ing 1";
        String testInput3 = "by e";
        String testInput4 = "creat egg /ing raw egg (1)";
        assertInstanceOf(UnknownCommand.class, Parser.parse(testInput1));
        assertInstanceOf(UnknownCommand.class, Parser.parse(testInput2));
        assertInstanceOf(UnknownCommand.class, Parser.parse(testInput3));
        assertInstanceOf(UnknownCommand.class, Parser.parse(testInput4));
        logger.info("Correct command object instantiated");
    }
}

package seedu.parser;

import org.junit.jupiter.api.Test;
import seedu.command.MistypedCommand;
import seedu.command.UnknownCommand;
import seedu.exceptions.EZMealPlanException;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    public void parse_mistypedInput_returnsMistypedCommand() throws EZMealPlanException {
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
    }

    @Test
    public void parse_unknownInput_returnsUnknownCommand() throws EZMealPlanException {
        String testInput1 = "wish list";
        String testInput2 = "reccomend /ing 1";
        String testInput3 = "by e";
        String testInput4 = "creat egg /ing raw egg (1)";
        assertInstanceOf(UnknownCommand.class, Parser.parse(testInput1));
        assertInstanceOf(UnknownCommand.class, Parser.parse(testInput2));
        assertInstanceOf(UnknownCommand.class, Parser.parse(testInput3));
        assertInstanceOf(UnknownCommand.class, Parser.parse(testInput4));
    }
}
package seedu.checkers;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.exceptions.EZMealPlanException;
import seedu.exceptions.InvalidKeywordIndexException;
import seedu.exceptions.MissingMealIndexException;
import seedu.exceptions.InvalidViewIndexException;

public class ViewCheckerTest {

    @Test
    public void testValidViewInput_mainIndex_success() throws EZMealPlanException {
        ViewChecker checker = new ViewChecker("view /m 2", "/m");
        checker.check(); // Should not throw
    }

    @Test
    public void testValidViewInput_userIndex_success() throws EZMealPlanException {
        ViewChecker checker = new ViewChecker("view /u 1", "/u");
        checker.check(); // Should not throw
    }

    @Test
    public void testKeywordBeforeView_throwsInvalidKeywordIndexException() {
        ViewChecker checker = new ViewChecker("/m view 1", "/m");
        assertThrows(InvalidKeywordIndexException.class, checker::check);
    }

    @Test
    public void testMissingMealIndex_throwsMissingMealIndexException() {
        ViewChecker checker = new ViewChecker("view /m", "/m");
        assertThrows(MissingMealIndexException.class, checker::check);
    }

    @Test
    public void testNonIntegerIndex_throwsInvalidViewIndexException() {
        ViewChecker checker = new ViewChecker("view /m abc", "/m");
        assertThrows(InvalidViewIndexException.class, checker::check);
    }

    @Test
    public void testExtraSpacingStillWorks() throws EZMealPlanException {
        ViewChecker checker = new ViewChecker("   view    /u   4  ", "/u");
        checker.check(); // Should still succeed
    }
}

package seedu.checkers;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.exceptions.EZMealPlanException;
import seedu.exceptions.InvalidKeywordIndexException;
import seedu.exceptions.MissingMealIndexException;
import seedu.exceptions.InvalidViewIndexException;

public class ViewCheckerTest {

    @Test
    public void testValidViewInput_recipeIndex_success() throws EZMealPlanException {
        ViewChecker checker = new ViewChecker("view /r 2", "/r");
        checker.check(); // Should not throw
    }

    @Test
    public void testValidViewInput_wishlistIndex_success() throws EZMealPlanException {
        ViewChecker checker = new ViewChecker("view /w 1", "/w");
        checker.check(); // Should not throw
    }

    @Test
    public void testKeywordBeforeView_throwsInvalidKeywordIndexException() {
        ViewChecker checker = new ViewChecker("/r view 1", "/r");
        assertThrows(InvalidKeywordIndexException.class, checker::check);
    }

    @Test
    public void testMissingMealIndex_throwsMissingMealIndexException() {
        ViewChecker checker = new ViewChecker("view /r", "/r");
        assertThrows(MissingMealIndexException.class, checker::check);
    }

    @Test
    public void testNonIntegerIndex_throwsInvalidViewIndexException() {
        ViewChecker checker = new ViewChecker("view /r abc", "/r");
        assertThrows(InvalidViewIndexException.class, checker::check);
    }

    @Test
    public void testExtraSpacingStillWorks() throws EZMealPlanException {
        ViewChecker checker = new ViewChecker("   view    /w   4  ", "/w");
        checker.check(); // Should still succeed
    }
}

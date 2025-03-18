package seedu.checkers;

import seedu.exceptions.EZMealPlanException;

public class FilterChecker extends FilterSelectChecker{
    public FilterChecker(String userInputText, String filterOrSelect) {
        this.userInput = userInputText.trim();
        this.filterOrSelect = filterOrSelect.trim();
    }

    @Override
    public void check() throws EZMealPlanException {
        super.check();
    }
}

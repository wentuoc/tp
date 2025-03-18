package seedu.checkers;

import seedu.exceptions.EZMealPlanException;
import seedu.exceptions.MissingMealIndexException;


public class SelectChecker extends FilterSelectChecker {
    public SelectChecker(String userInputText, String filterOrSelect) {
        this.userInput = userInputText.trim();
        this.filterOrSelect = filterOrSelect.trim();
    }

    @Override
    public void check() throws EZMealPlanException {
        super.check();
        indexStringCheck();
    }

    private void indexStringCheck() throws EZMealPlanException {
        String indexString = getIndexString().trim();
        if (indexString.isEmpty()) {
            throw new MissingMealIndexException(filterOrSelect);
        }
    }

    private String getIndexString() {
        String selectInputIndex;
        int afterSelectIndex = userInput.indexOf(filterOrSelect) + filterOrSelect.length();
        if (userInput.contains(ing)) {
            int ingIndex = userInput.indexOf(ing);
            selectInputIndex = userInput.substring(afterSelectIndex, ingIndex);
        } else if (userInput.contains(mcost)) {
            int mcostIndex = userInput.indexOf(mcost);
            selectInputIndex = userInput.substring(afterSelectIndex, mcostIndex);
        } else if (userInput.contains(mname)) {
            int mnameIndex = userInput.indexOf(mname);
            selectInputIndex = userInput.substring(afterSelectIndex, mnameIndex);
        } else {
            selectInputIndex = userInput.substring(afterSelectIndex);
        }
        return selectInputIndex;
    }
}

package seedu.checkers;

import seedu.exceptions.EZMealPlanException;
import seedu.exceptions.InvalidKeywordIndexException;
import seedu.exceptions.InvalidViewIndexException;
import seedu.exceptions.MissingMealIndexException;

public class ViewChecker extends Checker {
    String mainOrUser;
    String view = "view";

    public ViewChecker(String userInputText, String mainOrUser) {
        this.userInput = userInputText.trim();
        this.lowerCaseInput = userInput.toLowerCase();
        this.mainOrUser = mainOrUser;
    }

    @Override
    public void check() throws EZMealPlanException {
        checkValidKeywordIndex();
        checkMissingMealIndex();
        checkParseMealIndex();
        setPassed(true);
    }

    private void checkParseMealIndex() throws EZMealPlanException {
        try {
            int afterKeywordIndex = lowerCaseInput.indexOf(mainOrUser) + mainOrUser.length();
            String afterKeyword = userInput.substring(afterKeywordIndex).trim();
            Integer.parseInt(afterKeyword);
        } catch (NumberFormatException numberFormatException) {
            throw new InvalidViewIndexException();
        }
    }

    private void checkValidKeywordIndex() throws EZMealPlanException {
        int commandIndex = lowerCaseInput.indexOf(view);
        int keywordIndex = lowerCaseInput.indexOf(mainOrUser);
        if (commandIndex >= keywordIndex) {
            throw new InvalidKeywordIndexException(mainOrUser);
        }
    }

    private void checkMissingMealIndex() throws EZMealPlanException {
        int afterKeywordIndex = lowerCaseInput.indexOf(mainOrUser) + mainOrUser.length();
        String afterKeyword = userInput.substring(afterKeywordIndex).trim();
        if (afterKeyword.isEmpty()) {
            throw new MissingMealIndexException(view);
        }
    }

}

package ezmealplan.command.checkers;

import ezmealplan.exceptions.EZMealPlanException;

public abstract class Checker {
    protected String userInput;
    protected String lowerCaseInput;
    protected boolean isPassed = false;
    public abstract void check() throws EZMealPlanException;

    public boolean isPassed() {
        return isPassed;
    }

    public void setPassed(boolean passed) {
        isPassed = passed;
    }
}

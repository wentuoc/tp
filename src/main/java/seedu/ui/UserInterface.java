package seedu.ui;

import seedu.food.Ingredient;
import seedu.food.Meal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner;

    public UserInterface() {
        scanner = new Scanner(System.in);
    }

    public String readInput() {
        String userCmd = "";
        if (scanner.hasNextLine()) {
            userCmd = scanner.nextLine();
            userCmd = userCmd.trim();
        }
        return userCmd;
    }

    public void printGreetingMessage() {
        System.out.println("Hello! This is EzMealPlan");
        System.out.println("Let me help you in planning your meals");
    }

    public void printGoodbye() {
        scanner.close();
        System.out.print("Bye. Hope to see you again soon!");
    }

    public void printUnknownCommand(String userInput) {
        System.out.println("Invalid command: " + userInput);
        System.out.println("me no understand what you talking");
    }

    public void printErrorMessage(Exception exception) {
        System.out.println(exception.getMessage());
    }

    public void printAddMealMessage(Meal meal, List<Meal> mealList, String mealListName) {
        String successAddMealMessage = "You have successfully added a meal: " + meal + " into " + mealListName + ".";
        System.out.println(successAddMealMessage);
        printMealList(mealList, mealListName);
        String totalMealsMessage = "Currently, you have " + mealList.size() + " meals in " + mealListName + ".";
        System.out.println(totalMealsMessage);
    }

    public void printIngredientList(ArrayList<Ingredient> ingredientList) {
        System.out.println("Here are the ingredients for " + this + ": ");
        int count = 0;
        for (Ingredient ingredient : ingredientList) {
            count++;
            System.out.println("    " + count + ". " + ingredient);
        }
    }

    public void printMealList(List<Meal> mealList, String mealListName) {
        System.out.println("Here are the meals in " + mealListName + ": ");
        int count = 0;
        for (Meal meal : mealList) {
            count++;
            System.out.println("    " + count + ". " + meal);
        }
    }

    public void prompt() {
        System.out.println("How may I help you?");
    }
}

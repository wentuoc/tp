package seedu.ui;

import seedu.food.Ingredient;
import seedu.food.Meal;
import seedu.meallist.MainList;
import seedu.meallist.MealList;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner;

    public UserInterface() {
        scanner = new Scanner(System.in);
    }

    public static void printMessage(String s) {
        System.out.println(s);
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
        System.out.println("Let me help you in planning your meals.");
    }

    public void printGoodbye() {
        scanner.close();
        System.out.print("Bye. Hope to see you again soon!");
    }

    public void printUnknownCommand(String userInput) {
        System.out.println("Invalid command: " + userInput);
        System.out.println("me no understand what you talking.\n");
    }

    public void printErrorMessage(Exception exception) {
        System.out.println(exception.getMessage());
    }

    public void printAddMealMessage(Meal meal, MealList mealList) {
        String mealListName = mealList instanceof MainList ? "main meal list" : "user meal List";
        String successAddMealMessage = "You have successfully added a meal: " + meal + " into " + mealListName + ".";
        System.out.println(successAddMealMessage);
        List<Meal> meals = mealList.getList();
        printMealList(meals, mealListName);
        String totalMealsMessage = "Currently, you have " + meals.size() +
                " meals in " + mealListName + ".\n";
        System.out.println(totalMealsMessage);
    }

    public void printIngredientList(ArrayList<Ingredient> ingredientList) {
        System.out.println("Here are the ingredients for " + this + ":");
        int count = 0;
        for (Ingredient ingredient : ingredientList) {
            count++;
            System.out.println("    " + count + ". " + ingredient);
        }
        System.out.println();
    }

    public void printMealList(List<Meal> meals, String mealListName) {
        if (meals.isEmpty()) {
            System.out.println("No meals found in " + mealListName + ".\n");
            return;
        }

        System.out.println("Here are the meals in " + mealListName + ":");
        int count = 0;
        for (Meal meal : meals) {
            count++;
            System.out.println("    " + count + ". " + meal);
        }
        System.out.println();
    }

    public void printRemovedMessage(Meal meal, int size) {
        System.out.println(meal + " has been removed from your meal list!");
        System.out.printf("You have %d meals in your meal list.\n", size);
    }

    public void printDeletedMessage(Meal meal, int size) {
        System.out.println(meal + " has been removed from the global meal list!");
        System.out.printf("There are now %d meals in the global meal list.\n", size);
    }

    public void prompt() {
        System.out.println("How may I help you?");
    }
}

package seedu.storage;

import seedu.exceptions.InvalidPriceException;
import seedu.food.Ingredient;
import seedu.food.Meal;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private static final String DEFAULT_FILE_PATH = "data/data.txt";
    private static final String MAIN_LIST_PATH = "data/mainlist.txt";

    public static void createFile() throws IOException {
        File file = new File(DEFAULT_FILE_PATH);

        // Ensure the parent directories exist (if there are any)
        if (file.getParentFile() != null) {
            file.getParentFile().mkdirs();
        }
        // Create the file if it doesn't already exist
        if (!file.exists()) {
            file.createNewFile();
        }
    }


    // Reads the file and creates a list of Meal objects
    public static List<Meal> loadMeals() throws IOException {
        List<Meal> meals = new ArrayList<>();
        File file = new File(DEFAULT_FILE_PATH);
        if (file.exists()) {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // Assumes you have implemented a method to parse a Meal from a String.
                Meal meal;
                try {
                    meal = Meal.fromData(line);
                } catch (InvalidPriceException e) {
                    throw new RuntimeException(e);
                }
                meals.add(meal);
            }
            scanner.close();
        }
        return meals;
    }

    public static void createMainListFile() throws IOException {
        File file = new File(MAIN_LIST_PATH);
        if (file.getParentFile() != null) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    public static List<Meal> loadMainList() throws IOException, InvalidPriceException {
        List<Meal> meals = new ArrayList<>();
        File file = new File(MAIN_LIST_PATH);
        if (file.exists()) {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                // Split the line by "|" with optional spaces around it.
                String[] parts = line.split("\\s*\\|\\s*");
                if (parts.length < 2) {
                    continue; // Skip lines that don't have ingredients.
                }
                // The first part is the meal name.
                String mealName = parts[0];
                Meal meal = new Meal(mealName);
                // For each remaining part, extract ingredient name and its actual price.
                for (int i = 1; i < parts.length; i++) {
                    String ingredientStr = parts[i].trim();
                    int openBracketIndex = ingredientStr.indexOf("(");
                    int closeBracketIndex = ingredientStr.indexOf(")");
                    if (openBracketIndex == -1 || closeBracketIndex == -1) {
                        // If no valid price is found, you could either skip or use a default value.
                        throw new IllegalArgumentException("Invalid ingredient format: " + ingredientStr);
                    }
                    String ingredientName = ingredientStr.substring(0, openBracketIndex).trim();
                    String priceStr = ingredientStr.substring(openBracketIndex + 1, closeBracketIndex).trim();
                    double ingredientPrice = Double.parseDouble(priceStr);
                    Ingredient ingredient = new Ingredient(ingredientName, ingredientPrice);
                    meal.getIngredientList().add(ingredient);
                }
                // Optionally, compute the meal's total price as the sum of ingredient prices.
                double mealPrice = 0;
                for (Ingredient ing : meal.getIngredientList()) {
                    mealPrice += ing.getPrice();
                }
                meal.setPrice(mealPrice);
                meals.add(meal);
            }
            scanner.close();
        }
        return meals;
    }

    public static void writeMainList(String input) throws IOException {
        try (FileWriter fw = new FileWriter(MAIN_LIST_PATH, true)) {
            fw.append(input).append(System.lineSeparator());
        }
    }


}

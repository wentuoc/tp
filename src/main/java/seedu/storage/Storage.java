package seedu.storage;

import seedu.exceptions.DuplicateIngredientException;
import seedu.exceptions.InvalidPriceException;
import seedu.food.Ingredient;
import seedu.food.Meal;
import seedu.presetmeals.PresetMeals;
import seedu.ui.UserInterface;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    static File wishListFile;
    static File recipesListFile;
    private static final String WISH_LIST_FILE_PATH = "data/userList.txt";
    private static final String RECIPES_LIST_FILE_PATH = "data/mainList.txt";
    private static final String INVENTORY_LIST_FILE_PATH = "data/inventoryList.txt";

    public static File getWishListFile() {
        return wishListFile;
    }

    public static File getRecipesListFile() {
        return recipesListFile;
    }

    public static String getWishListFilePath() {
        return WISH_LIST_FILE_PATH;
    }

    public static String getRecipesListFilePath() {
        return RECIPES_LIST_FILE_PATH;
    }

    public static void createListFiles() throws IOException {
        wishListFile = new File(WISH_LIST_FILE_PATH);
        recipesListFile = new File(RECIPES_LIST_FILE_PATH);
        createListFile(recipesListFile);
        createListFile(wishListFile);
    }

    public static void createListFile(File listFile) throws IOException {
        // Ensure the parent directories exist (if there are any)
        if (listFile.getParentFile() != null) {
            listFile.getParentFile().mkdirs();
        }
        // Create the file if it doesn't already exist
        if (!listFile.exists()) {
            listFile.createNewFile();
        }
    }

    public static List<Meal> loadExistingList(File selectedFile) throws IOException {
        List<Meal> meals = new ArrayList<>();
        if (selectedFile.exists()) {
            Scanner scanner = new Scanner(selectedFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                // Split the line by "|" with optional spaces around it.
                String[] parts = line.split("\\s*\\|\\s*");
                int minLengthToHaveIng = 2;
                if (parts.length < minLengthToHaveIng) {
                    continue; // Skip lines that don't have ingredients.
                }
                checkValidMeal(parts, meals);
            }
            scanner.close();
        }
        return meals;
    }

    public static List<Meal> loadPresetMeals() {
        String[] allInitialisedMeals = PresetMeals.createPresetMeals();
        List<Meal> meals = new ArrayList<>();
        for (String eachInitialisedMeal : allInitialisedMeals) {
            String[] parts = eachInitialisedMeal.split("\\s*\\|\\s*");
            int minLengthToHaveIng = 2;
            if (parts.length < minLengthToHaveIng) {
                continue; // Skip initialised meal that don't have ingredients if there is any.
            }
            checkValidMeal(parts, meals);
        }
        return meals;
    }

    private static void checkValidMeal(String[] parts, List<Meal> meals) {
        //Throw error message if detected an ingredient with invalid price and skips to the next meal.
        try {
            checkMealsBeforeAdd(parts, meals);
        } catch (InvalidPriceException | DuplicateIngredientException exception) {
            System.err.println(exception.getMessage());
        }
    }

    private static void checkMealsBeforeAdd(String[] parts, List<Meal> meals)
            throws InvalidPriceException, DuplicateIngredientException {
        // The first part is the meal name.
        int mealNameIndex = 0;
        String mealName = parts[mealNameIndex];
        Meal meal = addIngredientsToMeal(mealName, parts);
        // Optionally, compute the meal's total price as the sum of ingredient prices.
        meals.add(meal);
    }

    private static Meal addIngredientsToMeal(String mealName, String[] parts)
            throws InvalidPriceException, DuplicateIngredientException {
        Meal meal = new Meal(mealName);
        // For each remaining part, extract ingredient name and its actual price.
        for (int i = 1; i < parts.length; i++) {
            Ingredient ingredient = getIngredient(parts[i]);
            meal.addIngredient(ingredient);
        }
        return meal;
    }

    private static Ingredient getIngredient(String parts) throws InvalidPriceException {
        String ingredientStr = parts.trim();
        int openBracketIndex = ingredientStr.indexOf("(");
        int closeBracketIndex = ingredientStr.indexOf(")");
        int notFoundIndex = -1;
        if (openBracketIndex == notFoundIndex || closeBracketIndex == notFoundIndex) {
            // If no valid price is found, you could either skip or use a default value.
            throw new IllegalArgumentException("Invalid ingredient format: " + ingredientStr);
        }
        int startIndex = 0;
        int indexAdjustment = 1;
        int afterOpenBracketIndex = openBracketIndex + indexAdjustment;
        String ingredientName = ingredientStr.substring(startIndex, openBracketIndex).trim();
        String priceStr = ingredientStr.substring(afterOpenBracketIndex, closeBracketIndex).trim();
        double ingredientPrice = Double.parseDouble(priceStr);
        return new Ingredient(ingredientName, ingredientPrice);
    }

    public static void writeToFile(String input, String filePath) throws IOException {
        try (FileWriter fileWriter = new FileWriter(filePath, true)) {
            fileWriter.append(input).append(System.lineSeparator());
        }
    }

    public static void clearFile(String filePath) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
        } catch (IOException ioException) {
            UserInterface.printMessage(ioException.getMessage());
        }
    }

    public static String getInventoryFilePath() {
        return INVENTORY_LIST_FILE_PATH;
    }
}

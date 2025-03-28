package seedu.food;

import seedu.exceptions.IngredientPriceFormatException;
import seedu.exceptions.InvalidPriceException;

import java.util.logging.Logger;

public class Ingredient extends Product {
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public Ingredient(String ingredientName, double ingredientPrice) throws InvalidPriceException {
        setName(ingredientName);
        setPrice(ingredientPrice);
    }

    public Ingredient(String ingredientName, String ingredientPriceString)
            throws InvalidPriceException, IngredientPriceFormatException {
        setName(ingredientName);
        setPrice(checkValidIngPrice(ingredientPriceString));
    }

    public void setPrice(double price) throws InvalidPriceException {
        super.setPrice(price);
    }

    public String toString() {
        return super.toString();
    }

    /**
     * Overrides the equals method based on the following criteria: Two Ingredient objects are equal if and only if
     * they have the same name (ignoring case).
     */
    @Override
    public boolean equals(Object otherIngredient) {
        if (otherIngredient instanceof Ingredient) {
            String otherName = ((Ingredient) otherIngredient).getName();
            String thisName = this.getName();
            return thisName.equalsIgnoreCase(otherName);
        } else {
            return false;
        }
    }

    private double checkValidIngPrice(String ingredientPrice) throws IngredientPriceFormatException {
        try {
            double hundred = 100.0;
            double ingredientPriceDouble = Double.parseDouble(ingredientPrice);
            return Math.round(ingredientPriceDouble * hundred) / hundred;
        } catch (NumberFormatException numberFormatException) {
            String message = "Triggers IngredientPriceFormatException()!";
            logger.warning(message);
            throw new IngredientPriceFormatException(getName());
        }
    }

    // Serializes the ingredient into a string format (e.g., "name|price")
    public String toDataString() {
        return getName() + "|" + getPrice();
    }

    // Deserializes the string back into an Ingredient object
    public static Ingredient fromData(String data) throws InvalidPriceException {
        String[] parts = data.split("\\|");
        if (parts.length < 2) {
            throw new IllegalArgumentException("Invalid ingredient data: " + data);
        }
        String name = parts[0];
        double price = Double.parseDouble(parts[1]);
        return new Ingredient(name, price);
    }
}

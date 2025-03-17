package seedu.food;

import seedu.exceptions.InvalidPriceException;

public class Ingredient extends Product {
    public Ingredient(String ingredientName, double ingredientPrice) throws InvalidPriceException {
        setName(ingredientName);
        setPrice(ingredientPrice);
    }

    public void setPrice(double price) throws InvalidPriceException {
        super.setPrice(price);
    }

    public String toString() {
        return super.toString();
    }

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
}

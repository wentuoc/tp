package seedu.food;

import seedu.exceptions.InvalidPriceException;

public class Ingredient extends Product {
    public Ingredient(String ingredientName, double ingredientPrice) throws InvalidPriceException {
        setName(ingredientName);
        setPrice(ingredientPrice); //exception check price cannot <= 0
    }

    @Override
    public void setPrice(double price) throws InvalidPriceException {
        super.setPrice(price);
    }

    @Override
    public String toString(){
        return getName() + " ($" + getPrice() +")";
    }
}

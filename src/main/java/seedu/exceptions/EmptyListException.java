package seedu.exceptions;

public class ViewEmptyListException extends EZMealPlanException {
    String recipeOrWishlistName;

    public ViewEmptyListException(String recipeOrWishlistName) {
        this.recipeOrWishlistName = recipeOrWishlistName;
    }
    @Override
    public String getMessage() {
        return "The " + recipeOrWishlistName + " is empty.\n";
    }
}

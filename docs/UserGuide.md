# User Guide

## Introduction

EZMealPlan is a CLI-based system that helps users to plan their meals. Users can view a list of pre-created meals in the main
recipes list, filter them by meal name, ingredients and cost according to their personal preferences, and add them 
into their personal wishlist. Users can also create their own meals which will be added into the recipes list and remove meals from their personal wishlist and the main recipes list.

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java `17` or above installed in your Computer.
**Mac users**: Ensure you have the precise JDK version prescribed [here](https://se-education.org/guides/tutorials/javaInstallationMac.html).
2. Download the latest version of `ezmealplan.jar` file from [here](https://github.com/AY2425S2-CS2113-F14-4/tp/releases).
3. Copy the file to the folder you want to use as the _home folder_ for your EZMealPlan.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar ezmealplan.jar` command
to run the application. The app will contain some preset meals.
5. Type the command in the command box and press Enter to execute it. e.g. typing `wishlist` and pressing Enter will open 
the wishlist window. Some example commands you can try:

`recipes` : Lists all meals from the recipes list.

`create /mname meal test /ing ingA(1), ingB(2), ingC(3)` : Creates a meal called `meal test` with the following 
ingredients with their respective costs: `ingA ($1.00), ingB ($2.00) and ingC ($3.00)`

`remove 3` : Removes the 3rd meal shown in the user's wishlist.

`clear` : Deletes all meals in the user's wishlist.

`bye` : Saves the meals in the recipes list and the wishlist respectively and exits the app.

Refer to the [Features](https://ay2425s2-cs2113-f14-4.github.io/tp/UserGuide.html#features) below for details of each
command.
## Features 

Notes about the command format:

* Words in `UPPER_CASE` are the parameters to be supplied by the user.
e.g. in `filter /mname MEAL_NAME`, `MEAL_NAME` is a parameter which can be used as `filter /mname roti prata`.

* Items in square brackets are optional.
e.g `select 1 [/mcost 3]` can be used as `select 1 /mcost 3` or as `select 1`.

* Extraneous parameters for commands that do not take in parameters (such as `wishlist`, `recipes`, `bye` and `clear`) will be ignored.
e.g. if the command specifies `bye 123`, it will be interpreted as `bye`.

* The command inputs are case-insensitive. The meal(s) will be sorted alphabetically by the meal name irrespective of the letter casings in both recipes list and the user's wishlist. The ingredient(s) in each meal will also be sorted in the same
manner. 

### Viewing help: `help`
Views the description, respective sample input(s) and sample output(s) of the command function that the user has doubts with.

Format: `help COMMAND_KEYWORD`

* The list of `COMMAND_KEYWORD` includes `bye`,`clear`,`create`,`delete`,`filter`,`help`,`recipes`,`remove`,`select`,`view` and `wishlist`.

Example of usage: 

`help help`

`help select`

### Creating a new meal: `create`
Creates a new meal with the relevant ingredients and adds the meal into the recipes list.

Format: `create /mname MEAL_NAME /ing INGREDIENT_1_NAME (INGREDIENT_1_COST), INGREDIENT_2_NAME (INGREDIENT_2_COST)`

* The ingredient cost such as `INGREDIENT_1_COST` must be enclosed within `()` and parsable into a `double`.
* The order of the ingredients does not matter e.g.

   `/ing INGREDIENT_2_NAME (INGREDIENT_2_COST), INGREDIENT_1_NAME (INGREDIENT_1_COST)`.
* To create a meal that contains more than 1 ingredient, `,` is needed to separate each ingredient.
* Specifications of creating a meal:
  1. **The price of every ingredient must not be negative.**
  2. **Each meal should not have multiple ingredients with the same ingredient name.**
  3. **Duplicate meals are not allowed.**
     * Meals that contain the <ins>exact same set of ingredients</ins> (ignoring both ingredient and meal prices) should
     have <ins>different meal names</ins>.
     * Meals that contain <ins>different sets of ingredients</ins> (ignoring both ingredient and meal prices) can have
     the <ins>same meal name</ins> (optional).
     * To check for any existing meal before creating a new meal: you may use the `recipes` or `filter /mname` command to 
     find meals having the _same meal name that you intend to use_, followed by the `view` or `filter /ing` command to 
     check for _the list of ingredients in the meal(s) having the same meal name_ or _identify the meals having the same 
     set of ingredients._

Example of Usage:

Let A, B and C be ingredients. Let Meal_No. be meal name.

`create /mname Meal_1 /ing A(1.5), B(1.5)`

**Allowed** subsequent `create` commands:

`create /mname Meal_1 /ing A(2)`

`create /mname Meal_1 /ing A(2), C(1)`

`create /mname Meal_2 /ing B(1), A(1.5)` **ETC.**


**Invalid** subsequent `create` commands:

`create /mname Meal_1 /ing A(1.5), B(1.5)`

`create /mname Meal_1 /ing B(1), A(2)`

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`

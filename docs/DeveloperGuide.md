# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

EZMealPlan follows a modular and object-oriented design centered around a command-based architecture.

### Architecture Overview

- **Parser**: Interprets user input and delegates to appropriate command classes.
- **Command classes**: Each command is encapsulated in its own class (e.g., `ListCommand`, `FilterCommand`, `SelectCommand`) that implements an `execute()` method.
- **MealList and UserMealList**: Encapsulate meal storage and operations, such as adding, removing, and viewing meals.
- **Meal and Ingredient**: Core data classes representing recipes and their components.
- **Storage**: Handles saving and loading from `main_meal_list.txt` and `user_meal_list.txt`.

### Logging

- Global logger is initialized in the `EZMealPlan` class.

- Functional classes use `logger.WARNING` for exceptions and `logger.SEVERE` for assertions.

- JUnit test classes use their own logger with `logger.INFO` for exceptions.

## Input Handling

- All user inputs are case-sensitive and normalised to lowercase.

This sequence diagram shows the processes that EZMealPlan system has to undergo while it is being booted up before it is ready for usage.

![img.png](img.png)
This sequence diagram shows the general flow of how the EZMealPlan system process the respective command inputted by the user. Many relevant details and classes have been omitted for the purpose of simplicity. The implementations for the respective commands will be explained in greater details and illustrated with UML diagrams later.

## Product scope
### Target user profile

- **Health-conscious** users who **track food intake** and **prefer meal transparency**.

- **Budget-conscious** users who want to **manage cost-per-meal**.

- **Lazy or busy** users looking for **quick, filtered suggestions**.

- **Home cooks** who want to **create** and **store their own recipes**.

- Users who want a **lightweight, offline meal planning CLI** app.

### Value proposition

EZMealPlan provides a **simple, command-line interface** for **selecting and managing meals**, **filtering by cost or ingredients**, and **building personalized meal plans**. It solves the problem of:

- **Searching manually** for affordable, relevant recipes
- **Remembering** preferred meals or dietary patterns
3- **Planning meals** within budget constraints

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Non-Functional Requirements

- Runs on any **Java 17-compatible environment**.

- Uses only **standard Java libraries** (no external dependencies).

- Supports **fast startup** (<1 second).

## Glossary

- **Meal** – A recipe with a name, total cost, and associated ingredients.

- **Ingredient** – A component of a meal with a name and cost.

- **Main List** – All available meals in the system.

- **User List** – Meals selected by the user for their meal plan.

- **Command** – A user instruction (e.g., `filter`, `view`, `exit`).

- **Filter** – A command to narrow down meals by name, cost, or ingredient.

- **View** – Shows ingredients and costs of a selected meal.

## Instructions for manual testing

### Setup

1. Ensure you have Java 17 installed.

2. Compile using:
```
javac EZMealPlan.java
```
3. Run using:
```
java EZMealPlan
```

### Testing Scenarios

- **`Load data`**: Ensure main_meal_list.txt and user_meal_list.txt are present.

- **`list`**: Shows all meals alphabetically sorted.

- **`meal`**: Displays meals user has selected.

- **`filter /mcost 5.00`**: Shows meals costing exactly $5.00.

- **`filter /ing chicken, rice`**: Filters meals containing all specified ingredients.

- **`select 2 /mname chicken`**: Selects the second filtered result.

- **`create /mname burger /ing bun (1.00), patty (2.00)`**: Adds new meal.

- **`view /m 1`**: Displays first meal from main list.

- **`remove 1`**: Removes first meal from user list.

- **`delete 2`**: Deletes from both lists (if applicable).

- **`clear`**: Empties user meal list.

- **`exit`**: Saves user list and exits.
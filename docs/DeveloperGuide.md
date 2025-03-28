# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

This sequence diagram shows the processes that EZMealPlan system has to undergo while it is being booted up before it is ready for usage.

![img.png](img.png)
This sequence diagram shows the general flow of how the EZMealPlan system process the respective command inputted by the user. Many relevant details and classes have been omitted for the purpose of simplicity. The implementations for the respective commands will be explained in greater details and illustrated with UML diagrams later.

## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

| Version | As a ...                 | I want to ...                                                                                           | So that I can ...                                                             |
|---------|--------------------------|---------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------|
| v1.0    | Health-conscious user    | Add my desired calorie intake per day                                                                   | Track my actual intake against that to see how close I am                     |
| v1.0    | Lazy user                | Search for existing recipes by ingredients                                                              | See what is the easiest item I can prepare                                    |
| v1.0    | User                     | Add recipes I come across online                                                                        | View all recipes available in one place                                       | 
| v1.0    | Health-conscious user    | View a graph or figure of my calorie intake over time                                                   | Track my progress towards my goal                                             |
| v1.0    | Indecisive user          | Get recommended recipes                                                                                 | Have a better idea of what to eat                                             | 
| v1.0    | User                     | Get recipe suggestions based on the ingredients available in the supermarket                            | Know which ingredients to purchase                                            |
| v1.0    | Organised user           | Get recipe suggestions that complement the ingredients I have                                           | Use up most of the ingredients I have have left before buying new ingredients |
| v1.0    | Picky-eater user         | Mark ingredients or recipes that I dislike                                                              | Avoid them                                                                    | 
| v1.0    | Picky-eater user         | Mark my favourite ingredients or recipes                                                                | Receive recommendations that match my preferences                             | 
| v1.0    | Careless user            | Backtrack and change my input preferences before confirmation for recipe recommendations                | Get the right recommended recipes                                             | 
| v1.0    | Creative user that cooks | Add and save personal recipes                                                                           | Choose them at my own discretion in the future                                | 
| v1.0    | User that cooks          | Delete personal recipes                                                                                 | Remove unwanted recipes                                                       | 
| v1.0    | Frugal user              | Get recipes that fulfil my budget                                                                       | Avoid overspending                                                            | 
| v1.0    | User that cooks          | Change some of the main ingredients to something that I like                                            | Eat what I like                                                               |
| v1.0    | User that cooks          | Show recipe instructions                                                                                | Follow as I cook                                                              |
| v1.0    | Organised user           | Organize all recipes by different categories (e.g., family recipes, favorite chef, favorite main, etc.) | Stay organized and find recipes easily                                        |
| v1.0    | User                     | Check the prices of each ingredient                                                                     | Know how much each ingredient costs                                           | 
| v1.0    | User                     | Check for any promotions on ingredients relevant to the recipe                                          | Save more money                                                               | 
| v1.0    | Time-constrained user    | Check for recipes based on cook time                                                                    | Quickly find recipes for myself                                               |
| v1.0    | Social user              | Share my favorite recipes with friends                                                                  | Get feedback from friends or cooks                                            |
| v1.0    | new user                 | see usage instructions                                                                                  | refer to them when I forget how to use the application                        |

|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}

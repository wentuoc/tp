package exmealplan.ui;

import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner;

    public UserInterface() {
        scanner = new Scanner(System.in);
    }

    public String readInput() {
        return scanner.nextLine();
    }

    public void printGreetingMessage() {
        System.out.println("Hello! This is EzMealPlan");
        System.out.println("Let me help you in planning your meals");
    }

    public void printGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printUnknowCommand() {
        System.out.println("me no understand what you talking");
    }
}

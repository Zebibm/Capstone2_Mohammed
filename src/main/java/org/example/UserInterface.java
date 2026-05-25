package org.example;

import java.util.Scanner;

public class UserInterface {

    // Scanner for user input
    private Scanner scanner;

    // Controls whether application keeps running
    private boolean running;

    // Constructor
    public UserInterface() {
        scanner = new Scanner(System.in);
        running = true;
    }

    // Starts the application
    public void start() {

        while (running) {

            showHomeScreen();

            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    startNewOrder();
                    break;

                case 0:
                    exitApp();
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    // Home screen
    private void showHomeScreen() {

        System.out.println("\n===== THE BURGER LAB =====");
        System.out.println("1) New Order");
        System.out.println("0) Exit");
        System.out.print("Choose option: ");
    }

    // Starts a new order
    private void startNewOrder() {

        // Create new order
        Order order = new Order();

        boolean ordering = true;

        while (ordering) {

            // Order menu
            System.out.println("\n----- ORDER MENU -----");
            System.out.println("1) Add Burger");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Side");
            System.out.println("4) Checkout");
            System.out.println("0) Cancel Order");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    addBurger(order);
                    break;

                case 2:
                    System.out.println("Drink feature coming next...");
                    break;

                case 3:
                    System.out.println("Side feature coming next...");
                    break;

                case 4:
                    System.out.println("\n===== CHECKOUT =====");
                    System.out.println(order.getOrderSummary());
                    break;

                case 0:
                    System.out.println("Order cancelled.");
                    ordering = false;
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    // Add burger to order
    private void addBurger(Order order) {

        scanner.nextLine();

        // Burger size
        System.out.println("\nSelect Burger Size:");
        System.out.println("8");
        System.out.println("12");
        System.out.println("16");
        System.out.print("Enter size: ");

        String size = scanner.nextLine();

        // Bun type
        System.out.println("\nSelect Bun Type:");
        System.out.println("White");
        System.out.println("Wheat");
        System.out.println("Brioche");
        System.out.print("Enter bun type: ");

        String bunType = scanner.nextLine();

        // Stuffed option
        System.out.print("\nStuffed Burger? (yes/no): ");

        String stuffedInput = scanner.nextLine();

        boolean stuffed = stuffedInput.equalsIgnoreCase("yes");

        // Create burger object
        Burger burger = new Burger(size, bunType, stuffed);

        // Add toppings
        boolean addingToppings = true;

        while (addingToppings) {

            System.out.println("\nAdd Topping:");
            System.out.println("1) Bacon ($2.00)");
            System.out.println("2) Cheese ($1.50)");
            System.out.println("3) Lettuce ($0.50)");
            System.out.println("4) Tomato ($0.50)");
            System.out.println("0) Done");
            System.out.print("Choose topping: ");

            int toppingChoice = scanner.nextInt();

            switch (toppingChoice) {

                case 1:
                    burger.addTopping(
                            new Topping("Bacon", "PREMIUM", 2.00));
                    break;

                case 2:
                    burger.addTopping(
                            new Topping("Cheese", "PREMIUM", 1.50));
                    break;

                case 3:
                    burger.addTopping(
                            new Topping("Lettuce", "REGULAR", 0.50));
                    break;

                case 4:
                    burger.addTopping(
                            new Topping("Tomato", "REGULAR", 0.50));
                    break;

                case 0:
                    addingToppings = false;
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }

        // Add burger to order
        order.addBurger(burger);

        System.out.println("\nBurger added successfully!");
    }


    // Add drink to order
    private void addDrink(Order order) {

        scanner.nextLine();

        // Drink size
        System.out.println("\nSelect Drink Size:");
        System.out.println("1) Small");
        System.out.println("2) Medium");
        System.out.println("3) Large");
        System.out.print("Choose size: ");

        int sizeChoice = scanner.nextInt();
        scanner.nextLine();

        String size = "";
        double price = 0;

        switch (sizeChoice) {

            case 1:
                size = "Small";
                price = 2.00;
                break;

            case 2:
                size = "Medium";
                price = 2.50;
                break;

            case 3:
                size = "Large";
                price = 3.00;
                break;

            default:
                System.out.println("Invalid size.");
                return;
        }

        // Drink flavor
        System.out.print("Enter drink flavor: ");
        String flavor = scanner.nextLine();

        // Create drink
        Drink drink = new Drink(size, flavor, price);

        // Add drink to order
        order.addDrink(drink);

        System.out.println("\nDrink added successfully!");
    }
    // Exit application
    private void exitApp() {

        System.out.println("Thank you for using The Burger Lab!");

        running = false;
    }
}
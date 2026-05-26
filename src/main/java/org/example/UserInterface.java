package org.example;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserInterface {

    // =========================
    // COLORS
    // =========================

    public static final String RESET = "\u001B[0m";

    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";

    // =========================
    // EMOJIS (UNICODE)
    // =========================

    public static final String BURGER = "\uD83C\uDF54";
    public static final String FRIES = "\uD83C\uDF5F";
    public static final String DRINK = "\uD83E\uDD64";
    public static final String CHECK = "\u2705";
    public static final String CROSS = "\u274C";

    // =========================
    // FIELDS
    // =========================

    private Scanner scanner;
    private boolean running;

    // =========================
    // CONSTRUCTOR
    // =========================

    public UserInterface() {

        scanner = new Scanner(System.in);

        running = true;
    }

    // =========================
    // START APPLICATION
    // =========================

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
                    System.out.println(RED +
                            "\n" + CROSS +
                            " Invalid option. Try again."
                            + RESET);
            }
        }
    }

    // =========================
    // HOME SCREEN
    // =========================

    private void showHomeScreen() {

        System.out.println(CYAN +
                "\n====================================");
        System.out.println("       " + BURGER +
                " THE BURGER LAB " + BURGER);
        System.out.println("===================================="
                + RESET);

        System.out.println(YELLOW +
                "1) New Order"
                + RESET);

        System.out.println(RED +
                "0) Exit"
                + RESET);

        System.out.print(GREEN +
                "\nChoose option: "
                + RESET);
    }

    // =========================
    // START NEW ORDER
    // =========================

    private void startNewOrder() {

        Order order = new Order();

        boolean ordering = true;

        while (ordering) {

            System.out.println(BLUE +
                    "\n========== ORDER MENU =========="
                    + RESET);

            System.out.println(YELLOW +
                    "1) Add Burger " + BURGER
                    + RESET);

            System.out.println(YELLOW +
                    "2) Add Drink " + DRINK
                    + RESET);

            System.out.println(YELLOW +
                    "3) Add Side " + FRIES
                    + RESET);

            System.out.println(GREEN +
                    "4) Checkout"
                    + RESET);

            System.out.println(RED +
                    "0) Cancel Order " + CROSS
                    + RESET);

            System.out.print(CYAN +
                    "\nChoose option: "
                    + RESET);

            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    addBurger(order);
                    break;

                case 2:
                    addDrink(order);
                    break;

                case 3:
                    addSide(order);
                    break;

                case 4:

                    System.out.println(PURPLE +
                            "\n========== CHECKOUT =========="
                            + RESET);

                    System.out.println(
                            order.getOrderSummary());

                    System.out.println(GREEN +
                            "\n1) Confirm " + CHECK
                            + RESET);

                    System.out.println(RED +
                            "0) Cancel " + CROSS
                            + RESET);

                    System.out.print(CYAN +
                            "\nChoose option: "
                            + RESET);

                    int checkoutChoice =
                            scanner.nextInt();

                    if (checkoutChoice == 1) {

                        saveReceipt(order);

                        System.out.println(GREEN +
                                "\n" + CHECK +
                                " Order confirmed!"
                                + RESET);

                        ordering = false;

                    } else {

                        System.out.println(RED +
                                "\n" + CROSS +
                                " Checkout cancelled."
                                + RESET);
                    }

                    break;

                case 0:

                    System.out.println(RED +
                            "\n" + CROSS +
                            " Order cancelled."
                            + RESET);

                    ordering = false;
                    break;

                default:

                    System.out.println(RED +
                            "\n" + CROSS +
                            " Invalid option."
                            + RESET);
            }
        }
    }

    // =========================
    // ADD BURGER
    // =========================

    private void addBurger(Order order) {

        scanner.nextLine();

        // Burger size
        System.out.println(YELLOW +
                "\nSelect Burger Size:"
                + RESET);

        System.out.println("1) Small");
        System.out.println("2) Medium");
        System.out.println("3) Large");

        System.out.print(CYAN +
                "Choose size: "
                + RESET);

        int sizeChoice = scanner.nextInt();

        scanner.nextLine();

        String size = "";

        switch (sizeChoice) {

            case 1:
                size = "8";
                break;

            case 2:
                size = "12";
                break;

            case 3:
                size = "16";
                break;

            default:
                System.out.println(RED +
                        "\n" + CROSS +
                        " Invalid size."
                        + RESET);
                return;
        }

        // Bun type
        System.out.println(YELLOW +
                "\nSelect Bun Type:"
                + RESET);

        System.out.println("1) White");
        System.out.println("2) Wheat");
        System.out.println("3) Brioche");

        System.out.print(CYAN +
                "Choose bun type: "
                + RESET);

        int bunChoice = scanner.nextInt();

        scanner.nextLine();

        String bunType = "";

        switch (bunChoice) {

            case 1:
                bunType = "White";
                break;

            case 2:
                bunType = "Wheat";
                break;

            case 3:
                bunType = "Brioche";
                break;

            default:
                System.out.println(RED +
                        "\n" + CROSS +
                        " Invalid bun type."
                        + RESET);
                return;
        }

        // Stuffed option
        System.out.print(CYAN +
                "\nStuffed Burger? (yes/no): "
                + RESET);

        String stuffedInput =
                scanner.nextLine();

        boolean stuffed =
                stuffedInput.equalsIgnoreCase("yes");

        // Create burger
        Burger burger =
                new Burger(size, bunType, stuffed);

        // Add toppings
        boolean addingToppings = true;

        while (addingToppings) {

            System.out.println(PURPLE +
                    "\n====== TOPPINGS ======"
                    + RESET);

            System.out.println("1) Bacon ($2.00)");
            System.out.println("2) Cheese ($1.50)");
            System.out.println("3) Lettuce ($0.50)");
            System.out.println("4) Tomato ($0.50)");
            System.out.println("0) Done");

            System.out.print(CYAN +
                    "\nChoose topping: "
                    + RESET);

            int toppingChoice =
                    scanner.nextInt();

            switch (toppingChoice) {

                case 1:

                    burger.addTopping(
                            new Topping(
                                    "Bacon",
                                    "PREMIUM",
                                    2.00));

                    System.out.println(GREEN +
                            CHECK +
                            " Bacon added!"
                            + RESET);

                    break;

                case 2:

                    burger.addTopping(
                            new Topping(
                                    "Cheese",
                                    "PREMIUM",
                                    1.50));

                    System.out.println(GREEN +
                            CHECK +
                            " Cheese added!"
                            + RESET);

                    break;

                case 3:

                    burger.addTopping(
                            new Topping(
                                    "Lettuce",
                                    "REGULAR",
                                    0.50));

                    System.out.println(GREEN +
                            CHECK +
                            " Lettuce added!"
                            + RESET);

                    break;

                case 4:

                    burger.addTopping(
                            new Topping(
                                    "Tomato",
                                    "REGULAR",
                                    0.50));

                    System.out.println(GREEN +
                            CHECK +
                            " Tomato added!"
                            + RESET);

                    break;

                case 0:

                    addingToppings = false;
                    break;

                default:

                    System.out.println(RED +
                            "\n" + CROSS +
                            " Invalid option."
                            + RESET);
            }
        }

        // Add burger to order
        order.addBurger(burger);

        System.out.println(GREEN +
                "\n" + CHECK +
                " Burger added successfully!"
                + RESET);
    }

    // =========================
    // ADD DRINK
    // =========================

    private void addDrink(Order order) {

        scanner.nextLine();

        System.out.println(YELLOW +
                "\nSelect Drink Size:"
                + RESET);

        System.out.println("1) Small");
        System.out.println("2) Medium");
        System.out.println("3) Large");

        System.out.print(CYAN +
                "Choose size: "
                + RESET);

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
                System.out.println(RED +
                        "\n" + CROSS +
                        " Invalid size."
                        + RESET);
                return;
        }

        System.out.print(CYAN +
                "Enter drink flavor: "
                + RESET);

        String flavor = scanner.nextLine();

        Drink drink =
                new Drink(size, flavor, price);

        order.addDrink(drink);

        System.out.println(GREEN +
                "\n" + CHECK +
                " Drink added successfully!"
                + RESET);
    }

    // =========================
    // ADD SIDE
    // =========================

    private void addSide(Order order) {

        System.out.println(YELLOW +
                "\nSelect Side:"
                + RESET);

        System.out.println("1) Fries ($2.50)");
        System.out.println("2) Onion Rings ($3.00)");
        System.out.println("3) Nuggets ($4.00)");

        System.out.print(CYAN +
                "Choose side: "
                + RESET);

        int choice = scanner.nextInt();

        Side side;

        switch (choice) {

            case 1:
                side = new Side("Fries", 2.50);
                break;

            case 2:
                side = new Side("Onion Rings", 3.00);
                break;

            case 3:
                side = new Side("Nuggets", 4.00);
                break;

            default:
                System.out.println(RED +
                        "\n" + CROSS +
                        " Invalid option."
                        + RESET);
                return;
        }

        order.addSide(side);

        System.out.println(GREEN +
                "\n" + CHECK +
                " Side added successfully!"
                + RESET);
    }

    // =========================
    // SAVE RECEIPT
    // =========================

    private void saveReceipt(Order order) {

        try {

            File folder =
                    new File("receipts");

            if (!folder.exists()) {

                folder.mkdir();
            }

            String fileName =
                    LocalDateTime.now()
                            .format(
                                    DateTimeFormatter
                                            .ofPattern(
                                                    "yyyyMMdd-HHmmss"));

            File receiptFile =
                    new File(folder,
                            fileName + ".txt");

            FileWriter writer =
                    new FileWriter(receiptFile);

            writer.write(
                    order.getOrderSummary());

            writer.close();

            System.out.println(GREEN +
                    "\n" + CHECK +
                    " Receipt saved successfully!"
                    + RESET);

        } catch (Exception e) {

            System.out.println(RED +
                    "\n" + CROSS +
                    " Error saving receipt."
                    + RESET);
        }
    }

    // =========================
    // EXIT APPLICATION
    // =========================

    private void exitApp() {

        System.out.println(PURPLE +
                "\nThank you for visiting "
                + BURGER +
                " THE BURGER LAB "
                + BURGER
                + RESET);

        running = false;
    }
}

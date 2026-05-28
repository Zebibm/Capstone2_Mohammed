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
    public static final String WHITE = "\u001B[37m";

    public static final String CHECK = "✓";
    public static final String CROSS = "✗";

    // =========================
    // FIELDS
    // =========================
    private Scanner scanner = new Scanner(System.in);

    // controls app loop
    private boolean running = true;

    // =========================
    // START APPLICATION
    // =========================
    public void start() {

        // app title
        System.out.println(CYAN + "====================================" + RESET);
        System.out.println(YELLOW + "   Welcome to THE BURGER LAB!" + RESET);
        System.out.println(CYAN + "====================================" + RESET);

        while (running) {

            showHomeScreen();

            int choice = getIntInput();

            switch (choice) {

                case 1 -> startNewOrder();

                case 0 -> exitApp();

                default ->
                        System.out.println(
                                RED + CROSS + " Invalid option" + RESET);
            }
        }
    }

    // =========================
    // HOME SCREEN
    // =========================
    private void showHomeScreen() {

        // main menu
        System.out.println(PURPLE + "\n BURGER LAB" + RESET);

        System.out.println(GREEN + "1) New Order" + RESET);

        System.out.println(GREEN + "0) Exit" + RESET);

        System.out.print(CYAN + "Choose option: " + RESET);
    }

    // =========================
    // NEW ORDER
    // =========================
    private void startNewOrder() {

        // loading animation
        loadingScreen("Creating new order");

        Order order = new Order();

        // clear scanner buffer
        scanner.nextLine();

        // get customer name
        System.out.print(CYAN + "Enter customer name: " + RESET);

        String name = scanner.nextLine();

        if (!name.isBlank()) {
            order.setCustomerName(name);
        }

        boolean ordering = true;

        while (ordering) {

            // order menu
            System.out.println(
                    YELLOW + "\n====== ORDER MENU ======" + RESET);

            System.out.println(GREEN + "1) Add Burger" + RESET);

            System.out.println(GREEN + "2) Add Drink" + RESET);

            System.out.println(GREEN + "3) Add Side" + RESET);

            System.out.println(GREEN + "4) Checkout" + RESET);

            System.out.println(GREEN + "0) Cancel" + RESET);

            System.out.print(CYAN + "Choose: " + RESET);

            int choice = getIntInput();

            switch (choice) {

                case 1 -> addBurger(order);

                case 2 -> addDrink(order);

                case 3 -> addSide(order);

                case 4 -> {

                    // prevents empty orders
                    if (order.calculateTotal() == 0) {

                        System.out.println(
                                RED +
                                        "You cannot checkout empty order!" +
                                        RESET);

                        break;
                    }

                    // checkout order
                    if (checkout(order)) {

                        ordering = false;
                    }
                }

                case 0 -> {

                    System.out.println(
                            RED +
                                    CROSS +
                                    " Cancelled" +
                                    RESET);

                    ordering = false;
                }

                default ->
                        System.out.println(
                                RED +
                                        CROSS +
                                        " Invalid option" +
                                        RESET);
            }
        }
    }

    // =========================
    // CHECKOUT
    // =========================
    private boolean checkout(Order order) {

        // show order summary
        System.out.println(
                PURPLE + "\n===== CHECKOUT =====" + RESET);

        System.out.println(order.getOrderSummary());

        System.out.println(GREEN + "1) Confirm" + RESET);

        System.out.println(RED + "0) Cancel" + RESET);

        System.out.print(CYAN + "Choose: " + RESET);

        // cancel checkout
        if (getIntInput() != 1) {

            System.out.println(
                    RED +
                            CROSS +
                            " Cancelled" +
                            RESET);

            return false;
        }

        // =========================
        // PAYMENT METHOD
        // =========================

        System.out.println(
                YELLOW + "\nPayment Method:" + RESET);

        System.out.println("1) Cash");

        System.out.println("2) Card");

        int pay = getIntInput();

        // payment validation
        while (pay != 1 && pay != 2) {

            System.out.println(
                    RED +
                            "Choose 1 or 2 only!" +
                            RESET);

            pay = getIntInput();
        }

        String method = (pay == 1)
                ? "Cash"
                : "Card";

        System.out.println(
                BLUE +
                        "Payment: " +
                        method +
                        RESET);

        // =========================
        // CASH PAYMENT
        // =========================

        if (pay == 1) {

            double total =
                    order.calculateTotal();

            System.out.println(
                    YELLOW +
                            "Total: $" +
                            String.format("%.2f", total) +
                            RESET);

            System.out.print(
                    CYAN +
                            "Enter cash: $" +
                            RESET);

            double cash =
                    scanner.nextDouble();

            // checks enough money
            while (cash < total) {

                System.out.print(
                        RED +
                                "Not enough. Try again: $" +
                                RESET);

                cash =
                        scanner.nextDouble();
            }

            // calculate change
            double change =
                    cash - total;

            System.out.println(
                    GREEN +
                            "Change: $" +
                            String.format("%.2f", change) +
                            RESET);
        }

        // loading
        loadingScreen("Processing payment");

        // print receipt
        System.out.println(
                WHITE +
                        "\n========== RECEIPT ==========" +
                        RESET);

        System.out.println(order.getOrderSummary());

        // save receipt file
        saveReceipt(order);

        System.out.println(
                GREEN +
                        CHECK +
                        " Order confirmed!" +
                        RESET);

        return true;
    }

    // =========================
    // ADD BURGER
    // =========================
    private void addBurger(Order order) {

        int s;

        // size validation
        do {

            System.out.println("Select Burger Size:");
            System.out.println("1) Small");
            System.out.println("2) Medium");
            System.out.println("3) Large");

            s = getIntInput();

            if (s < 1 || s > 3) {

                System.out.println(
                        RED +
                                "Choose 1-3 only!" +
                                RESET);
            }

        } while (s < 1 || s > 3);

        // burger size
        String size = switch (s) {

            case 1 -> "Small";

            case 2 -> "Medium";

            default -> "Large";
        };

        int b;

        // bun validation
        do {

            System.out.println("Bun Type:");
            System.out.println("1) White");
            System.out.println("2) Wheat");
            System.out.println("3) Brioche");

            b = getIntInput();

            if (b < 1 || b > 3) {

                System.out.println(
                        RED +
                                "Choose 1-3 only!" +
                                RESET);
            }

        } while (b < 1 || b > 3);

        // bun type
        String bun = switch (b) {

            case 1 -> "White";

            case 2 -> "Wheat";

            default -> "Brioche";
        };

        // stuffed option
        System.out.println("Stuffed? 1 Yes 2 No");

        boolean stuffed =
                getIntInput() == 1;

        // create burger object
        Burger burger =
                new Burger(size, bun, stuffed);

        order.addBurger(burger);

        System.out.println(
                GREEN +
                        CHECK +
                        " Burger added!" +
                        RESET);
    }

    // =========================
    // ADD DRINK
    // =========================
    private void addDrink(Order order) {

        int c;

        // drink size validation
        do {

            System.out.println("Select Drink Size:");
            System.out.println("1) Small");
            System.out.println("2) Medium");
            System.out.println("3) Large");

            c = getIntInput();

            if (c < 1 || c > 3) {

                System.out.println(
                        RED +
                                "Choose 1-3 only!" +
                                RESET);
            }

        } while (c < 1 || c > 3);

        // drink size
        String size = switch (c) {

            case 1 -> "Small";

            case 2 -> "Medium";

            default -> "Large";
        };

        // clear scanner
        scanner.nextLine();

        // drink flavor
        System.out.print("Flavor: ");

        String flavor = scanner.nextLine();

        // drink price
        double price = switch (c) {

            case 1 -> 2.0;

            case 2 -> 2.5;

            default -> 3.0;
        };

        // create drink object
        Drink drink =
                new Drink(size, flavor, price);

        order.addDrink(drink);

        System.out.println(
                GREEN +
                        CHECK +
                        " Drink added!" +
                        RESET);
    }

    // =========================
    // ADD SIDE
    // =========================
    private void addSide(Order order) {

        int c;

        // side validation
        do {

            System.out.println("1) Fries");
            System.out.println("2) Onion Rings");
            System.out.println("3) Nuggets");

            c = getIntInput();

            if (c < 1 || c > 3) {

                System.out.println(
                        RED +
                                "Choose 1-3 only!" +
                                RESET);
            }

        } while (c < 1 || c > 3);

        // create side object
        Side side = switch (c) {

            case 1 -> new Side("Fries", 2.5);

            case 2 -> new Side("Onion Rings", 3.0);

            default -> new Side("Nuggets", 4.0);
        };

        order.addSide(side);

        System.out.println(
                GREEN +
                        CHECK +
                        " Side added!" +
                        RESET);
    }

    // =========================
    // INPUT SAFE
    // =========================
    private int getIntInput() {

        // checks valid integer
        while (!scanner.hasNextInt()) {

            System.out.print(
                    RED +
                            "Enter number: " +
                            RESET);

            scanner.next();
        }

        return scanner.nextInt();
    }

    // =========================
    // SAVE RECEIPT
    // =========================
    private void saveReceipt(Order order) {

        try {

            // create folder
            File folder =
                    new File("receipts");

            if (!folder.exists()) {

                folder.mkdir();
            }

            // receipt file name
            String fileName =
                    LocalDateTime.now()
                            .format(
                                    DateTimeFormatter.ofPattern(
                                            "yyyyMMdd-HHmmss"));

            // save receipt
            FileWriter writer =
                    new FileWriter(
                            new File(folder,
                                    fileName + ".txt"));

            writer.write(order.getOrderSummary());

            writer.close();

        } catch (Exception e) {

            System.out.println("Error saving receipt");
        }
    }

    // =========================
    // LOADING
    // =========================
    private void loadingScreen(String msg) {

        System.out.print(msg);

        // loading dots
        for (int i = 0; i < 4; i++) {

            try {

                System.out.print(".");

                Thread.sleep(300);

            } catch (Exception ignored) {
            }
        }

        System.out.println();
    }

    // =========================
    // EXIT
    // =========================
    private void exitApp() {

        System.out.println(
                PURPLE +
                        "Thanks for visiting THE BURGER LAB!" +
                        RESET);

        running = false;
    }
}
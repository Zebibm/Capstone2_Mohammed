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

    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    // =========================
    // SYMBOLS
    // =========================

    public static final String CHECK = "✓";
    public static final String CROSS = "✗";

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

        System.out.println(
                CYAN +
                        "\n====================================" +
                        RESET);

        System.out.println(
                YELLOW +

                        "      Welcome to THE BURGER LAB!" +
                        RESET);

        System.out.println(
                CYAN +
                        "====================================" +
                        RESET);

        while (running) {

            showHomeScreen();

            int choice = getIntInput();

            switch (choice) {

                case 1 -> startNewOrder();

                case 0 -> exitApp();

                default -> System.out.println(
                        RED +
                                CROSS +
                                " Invalid option" +
                                RESET);
            }
        }
    }

    // =========================
    // HOME SCREEN
    // =========================

    private void showHomeScreen() {

        System.out.println(
                BLUE +
                      //  "\n==============================" +

                        RESET);

        System.out.println(
                PURPLE +
                        "           BURGER LAB" +
                        RESET);

        System.out.println(
                BLUE +
                      //  "==============================" +
                        RESET);

        System.out.println(
                GREEN +
                        "1) New Order" +
                        RESET);

        System.out.println(
                GREEN +
                        "0) Exit" +
                        RESET);

        System.out.print(
                CYAN +
                        "Choose option: " +
                        RESET);
    }

    // =========================
    // START NEW ORDER
    // =========================

    private void startNewOrder() {

        loadingScreen("Creating new order");

        Order order = new Order();

        boolean ordering = true;

        while (ordering) {

            System.out.println(
                    YELLOW +
                            "\n====== ORDER MENU ======" +
                            RESET);

            System.out.println(
                    CYAN +
                            "1) Add Burger" +
                            RESET);

            System.out.println(
                    CYAN +
                            "2) Add Drink" +
                            RESET);

            System.out.println(
                    CYAN +
                            "3) Add Side" +
                            RESET);

            System.out.println(
                    CYAN +
                            "4) Checkout" +
                            RESET);

            System.out.println(
                    CYAN +
                            "0) Cancel" +
                            RESET);

            System.out.print(
                    WHITE +
                            "Choose: " +
                            RESET);

            int choice = getIntInput();

            switch (choice) {

                case 1 -> addBurger(order);

                case 2 -> addDrink(order);

                case 3 -> addSide(order);

                case 4 -> {

                    boolean done = checkout(order);

                    if (done) {
                        ordering = false;
                    }
                }

                case 0 -> {

                    System.out.println(
                            RED +
                                    CROSS +
                                    " Order cancelled" +
                                    RESET);

                    ordering = false;
                }

                default -> System.out.println(
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

        System.out.println(
                PURPLE +
                        "\n===== CHECKOUT =====" +
                        RESET);

        // SHOW RECEIPT BEFORE PAYMENT
        System.out.println(
                WHITE +
                        order.getOrderSummary() +
                        RESET);

        System.out.println(
                GREEN +
                        "\n1) Confirm" +
                        RESET);

        System.out.println(
                RED +
                        "0) Cancel" +
                        RESET);

        System.out.print(
                CYAN +
                        "Choose option: " +
                        RESET);

        int confirm = getIntInput();

        if (confirm != 1) {

            System.out.println(
                    RED +
                            CROSS +
                            " Cancelled" +
                            RESET);

            return false;
        }

        // PAYMENT METHOD

        System.out.println(
                YELLOW +
                        "\nPayment Method:" +
                        RESET);

        System.out.println(
                GREEN +
                        "1) Cash" +
                        RESET);

        System.out.println(
                GREEN +
                        "2) Card" +
                        RESET);

        System.out.print(
                CYAN +
                        "Choose payment: " +
                        RESET);

        int pay = getIntInput();

        String method = switch (pay) {

            case 1 -> "Cash";

            case 2 -> "Card";

            default -> "Unknown";
        };

        System.out.println(
                BLUE +
                        "\nPayment: " +
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
                            "\nTotal Amount: $" +
                            String.format("%.2f", total) +
                            RESET);

            System.out.print(
                    CYAN +
                            "Enter cash amount: $" +
                            RESET);

            double cash =
                    scanner.nextDouble();

            while (cash < total) {

                System.out.println(
                        RED +
                                "Not enough cash." +
                                RESET);

                System.out.print(
                        CYAN +
                                "Enter cash again: $" +
                                RESET);

                cash =
                        scanner.nextDouble();
            }

            double change =
                    cash - total;

            System.out.println(
                    GREEN +
                            "Change: $" +
                            String.format("%.2f", change) +
                            RESET);
        }

        // PROCESS PAYMENT

        loadingScreen("Processing payment");

        // SHOW FINAL RECEIPT

        System.out.println(
                WHITE +
                        "\n========== RECEIPT ==========" +
                        RESET);

        System.out.println(
                WHITE +
                        order.getOrderSummary() +
                        RESET);

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

        System.out.println(
                YELLOW +
                        "\nSelect Burger Size:" +
                        RESET);

        System.out.println(
                GREEN +
                        "1) Small" +
                        RESET);

        System.out.println(
                GREEN +
                        "2) Medium" +
                        RESET);

        System.out.println(
                GREEN +
                        "3) Large" +
                        RESET);

        System.out.print(
                CYAN +
                        "Choose size: " +
                        RESET);

        int sizeChoice =
                getIntInput();

        String size = switch (sizeChoice) {

            case 1 -> "Small";

            case 2 -> "Medium";

            case 3 -> "Large";

            default -> "Small";
        };

        System.out.println(
                YELLOW +
                        "\nSelect Bun Type:" +
                        RESET);

        System.out.println(
                GREEN +
                        "1) White" +
                        RESET);

        System.out.println(
                GREEN +
                        "2) Wheat" +
                        RESET);

        System.out.println(
                GREEN +
                        "3) Brioche" +
                        RESET);

        System.out.print(
                CYAN +
                        "Choose bun: " +
                        RESET);

        int bunChoice =
                getIntInput();

        String bun = switch (bunChoice) {

            case 1 -> "White";

            case 2 -> "Wheat";

            case 3 -> "Brioche";

            default -> "White";
        };

        System.out.println(
                YELLOW +
                        "\nStuffed Burger?" +
                        RESET);

        System.out.println(
                GREEN +
                        "1) Yes" +
                        RESET);

        System.out.println(
                GREEN +
                        "2) No" +
                        RESET);

        System.out.print(
                CYAN +
                        "Choose: " +
                        RESET);

        boolean stuffed =
                getIntInput() == 1;

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

        System.out.println(
                YELLOW +
                        "\nDrink Size:" +
                        RESET);

        System.out.println(
                GREEN +
                        "1) Small" +
                        RESET);

        System.out.println(
                GREEN +
                        "2) Medium" +
                        RESET);

        System.out.println(
                GREEN +
                        "3) Large" +
                        RESET);

        System.out.print(
                CYAN +
                        "Choose size: " +
                        RESET);

        int choice =
                getIntInput();

        String size = switch (choice) {

            case 1 -> "Small";

            case 2 -> "Medium";

            case 3 -> "Large";

            default -> "Small";
        };

        scanner.nextLine();

        System.out.print(
                CYAN +
                        "Flavor: " +
                        RESET);

        String flavor =
                scanner.nextLine();

        double price = switch (choice) {

            case 1 -> 2.00;

            case 2 -> 2.50;

            case 3 -> 3.00;

            default -> 2.00;
        };

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

        System.out.println(
                YELLOW +
                        "\nSides:" +
                        RESET);

        System.out.println(
                GREEN +
                        "1) Fries" +
                        RESET);

        System.out.println(
                GREEN +
                        "2) Onion Rings" +
                        RESET);

        System.out.println(
                GREEN +
                        "3) Nuggets" +
                        RESET);

        System.out.print(
                CYAN +
                        "Choose side: " +
                        RESET);

        int choice =
                getIntInput();

        Side side = switch (choice) {

            case 1 -> new Side("Fries", 2.50);

            case 2 -> new Side("Onion Rings", 3.00);

            case 3 -> new Side("Nuggets", 4.00);

            default -> null;
        };

        if (side != null) {

            order.addSide(side);

            System.out.println(
                    GREEN +
                            CHECK +
                            " Side added!" +
                            RESET);
        }
    }

    // =========================
    // LOADING SCREEN
    // =========================

    private void loadingScreen(String msg) {

        System.out.print(
                BLUE +
                        msg +
                        RESET);

        for (int i = 0; i < 5; i++) {

            try {

                System.out.print(
                        YELLOW + "." + RESET);

                Thread.sleep(300);

            } catch (Exception ignored) {
            }
        }

        System.out.println();
    }

    // =========================
    // SAFE INTEGER INPUT
    // =========================

    private int getIntInput() {

        while (!scanner.hasNextInt()) {

            System.out.print(
                    RED +
                            "Enter valid number: " +
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

            File folder =
                    new File("receipts");

            if (!folder.exists()) {

                folder.mkdir();
            }

            String name =
                    LocalDateTime.now()
                            .format(
                                    DateTimeFormatter.ofPattern(
                                            "yyyyMMdd-HHmmss"));

            File file =
                    new File(folder,
                            name + ".txt");

            FileWriter writer =
                    new FileWriter(file);

            writer.write(
                    order.getOrderSummary());

            writer.close();

            System.out.println(
                    GREEN +
                            "Receipt saved!" +
                            RESET);

        } catch (Exception e) {

            System.out.println(
                    RED +
                            "Error saving receipt" +
                            RESET);
        }
    }

    // =========================
    // EXIT APPLICATION
    // =========================

    private void exitApp() {

        System.out.println(
                PURPLE +
                        "\nThanks for visiting THE BURGER LAB!" +
                        RESET);

        running = false;
    }
}
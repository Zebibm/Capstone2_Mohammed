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
    // EMOJIS
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
    // START
    // =========================

    public void start() {

        while (running) {

            showHomeScreen();

            int choice = getIntInput();

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
                            " Invalid option."
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
        System.out.println("       " + BURGER + " THE BURGER LAB " + BURGER);
        System.out.println("===================================="
                + RESET);

        System.out.println(YELLOW + "1) New Order" + RESET);
        System.out.println(RED + "0) Exit" + RESET);

        System.out.print(GREEN + "\nChoose option: " + RESET);
    }

    // =========================
    // NEW ORDER
    // =========================

    private void startNewOrder() {

        Order order = new Order();
        boolean ordering = true;

        while (ordering) {

            System.out.println(BLUE +
                    "\n========== ORDER MENU =========="
                    + RESET);

            System.out.println(YELLOW + "1) Add Burger " + BURGER + RESET);
            System.out.println(YELLOW + "2) Add Drink " + DRINK + RESET);
            System.out.println(YELLOW + "3) Add Side " + FRIES + RESET);
            System.out.println(GREEN + "4) Checkout" + RESET);
            System.out.println(RED + "0) Cancel Order " + CROSS + RESET);

            System.out.print(CYAN + "\nChoose option: " + RESET);

            int choice = getIntInput();

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

                    System.out.println(order.getOrderSummary());

                    System.out.println(GREEN +
                            "\n1) Confirm " + CHECK
                            + RESET);

                    System.out.println(RED +
                            "0) Cancel " + CROSS
                            + RESET);

                    System.out.print(CYAN +
                            "\nChoose option: "
                            + RESET);

                    int checkoutChoice = getIntInput();

                    if (checkoutChoice == 1) {

                        // PAYMENT METHOD
                        System.out.println(YELLOW +
                                "\nSelect Payment Method:"
                                + RESET);

                        System.out.println("1) Cash");
                        System.out.println("2) Card");

                        System.out.print(CYAN +
                                "Choose option: "
                                + RESET);

                        int paymentChoice = getIntInput();

                        String paymentMethod = "";

                        switch (paymentChoice) {

                            case 1:
                                paymentMethod = "Cash";
                                break;

                            case 2:
                                paymentMethod = "Card";
                                break;

                            default:
                                paymentMethod = "Unknown";
                        }

                        System.out.println(GREEN +
                                "\nPayment Method: " + paymentMethod
                                + RESET);

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

        System.out.println(YELLOW + "\nSelect Burger Size:" + RESET);
        System.out.println("1) Small (8)");
        System.out.println("2) Medium (12)");
        System.out.println("3) Large (16)");

        int sizeChoice = getIntInput();

        String size = "";

        switch (sizeChoice) {

            case 1: size = "8"; break;
            case 2: size = "12"; break;
            case 3: size = "16"; break;
            default: return;
        }

        scanner.nextLine();

        System.out.print(CYAN + "Bun type: " + RESET);
        String bunType = scanner.nextLine();

        System.out.print(CYAN + "Stuffed? (yes/no): " + RESET);
        boolean stuffed = scanner.nextLine().equalsIgnoreCase("yes");

        Burger burger = new Burger(size, bunType, stuffed);

        order.addBurger(burger);

        System.out.println(GREEN + CHECK + " Burger added!" + RESET);
    }

    // =========================
    // ADD DRINK
    // =========================

    private void addDrink(Order order) {

        scanner.nextLine();

        System.out.println(YELLOW + "\nDrink Size:" + RESET);
        System.out.println("1) Small");
        System.out.println("2) Medium");
        System.out.println("3) Large");

        int choice = getIntInput();

        String size = "";
        double price = 0;

        switch (choice) {

            case 1: size = "Small"; price = 2.00; break;
            case 2: size = "Medium"; price = 2.50; break;
            case 3: size = "Large"; price = 3.00; break;
            default: return;
        }

        scanner.nextLine();

        System.out.print("Flavor: ");
        String flavor = scanner.nextLine();

        Drink drink = new Drink(size, flavor, price);

        order.addDrink(drink);

        System.out.println(GREEN + CHECK + " Drink added!" + RESET);
    }

    // =========================
    // ADD SIDE
    // =========================

    private void addSide(Order order) {

        System.out.println(YELLOW + "\nSides:" + RESET);
        System.out.println("1) Fries");
        System.out.println("2) Onion Rings");
        System.out.println("3) Nuggets");

        int choice = getIntInput();

        Side side;

        switch (choice) {

            case 1: side = new Side("Fries", 2.50); break;
            case 2: side = new Side("Onion Rings", 3.00); break;
            case 3: side = new Side("Nuggets", 4.00); break;
            default: return;
        }

        order.addSide(side);

        System.out.println(GREEN + CHECK + " Side added!" + RESET);
    }

    // =========================
    // SAVE RECEIPT
    // =========================

    private void saveReceipt(Order order) {

        try {

            File folder = new File("receipts");
            if (!folder.exists()) folder.mkdir();

            String fileName = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));

            File file = new File(folder, fileName + ".txt");

            FileWriter writer = new FileWriter(file);
            writer.write(order.getOrderSummary());
            writer.close();

        } catch (Exception e) {
            System.out.println("Error saving receipt.");
        }
    }

    // =========================
    // INPUT SAFETY
    // =========================

    private int getIntInput() {

        while (!scanner.hasNextInt()) {
            System.out.println(RED + "Enter valid number!" + RESET);
            scanner.next();
        }

        return scanner.nextInt();
    }

    // =========================
    // EXIT
    // =========================

    private void exitApp() {

        System.out.println(PURPLE +
                "\nThank you for visiting " +
                BURGER + " THE BURGER LAB " + BURGER
                + RESET);

        running = false;
    }
}
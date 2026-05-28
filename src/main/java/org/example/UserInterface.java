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
    private Scanner scanner;
    private boolean running;

    public UserInterface() {
        scanner = new Scanner(System.in);
        running = true;
    }

    // =========================
    // START
    // =========================
    public void start() {

        System.out.println(CYAN + "\n====================================" + RESET);
        System.out.println(YELLOW + "      Welcome to THE BURGER LAB!" + RESET);
        System.out.println(CYAN + "====================================" + RESET);

        while (running) {
            showHomeScreen();
            int choice = getIntInput();

            switch (choice) {
                case 1 -> startNewOrder();
                case 0 -> exitApp();
                default -> System.out.println(RED + CROSS + " Invalid option" + RESET);
            }
        }
    }

    // =========================
    // HOME
    // =========================
    private void showHomeScreen() {

        System.out.println(PURPLE + "\n           BURGER LAB" + RESET);

        System.out.println(GREEN + "1) New Order" + RESET);
        System.out.println(GREEN + "0) Exit" + RESET);

        System.out.print(CYAN + "Choose option: " + RESET);
    }

    // =========================
    // NEW ORDER
    // =========================
    private void startNewOrder() {

        loadingScreen("Creating new order");

        Order order = new Order();

        //  CUSTOMER NAME
        System.out.print(CYAN + "Enter customer name: " + RESET);
        scanner.nextLine(); // clear buffer
        String name = scanner.nextLine();

        if (!name.isBlank()) {
            order.setCustomerName(name);
        }

        boolean ordering = true;

        while (ordering) {

            System.out.println(YELLOW + "\n====== ORDER MENU ======" + RESET);
            System.out.println("1) Add Burger");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Side");
            System.out.println("4) Checkout");
            System.out.println("0) Cancel");

            System.out.print(CYAN + "Choose: " + RESET);

            int choice = getIntInput();

            switch (choice) {
                case 1 -> addBurger(order);
                case 2 -> addDrink(order);
                case 3 -> addSide(order);
                case 4 -> {
                    if (checkout(order)) ordering = false;
                }
                case 0 -> {
                    System.out.println(RED + CROSS + " Cancelled" + RESET);
                    ordering = false;
                }
            }
        }
    }

    // =========================
    // CHECKOUT
    // =========================
    private boolean checkout(Order order) {

        System.out.println(PURPLE + "\n===== CHECKOUT =====" + RESET);

        System.out.println(order.getOrderSummary());

        System.out.println(GREEN + "1) Confirm" + RESET);
        System.out.println(RED + "0) Cancel" + RESET);

        System.out.print(CYAN + "Choose: " + RESET);

        if (getIntInput() != 1) {
            System.out.println(RED + CROSS + " Cancelled" + RESET);
            return false;
        }

        System.out.println(YELLOW + "\nPayment Method:" + RESET);
        System.out.println("1) Cash");
        System.out.println("2) Card");

        int pay = getIntInput();

        String method = (pay == 1) ? "Cash" : "Card";

        System.out.println(BLUE + "Payment: " + method + RESET);

        if (pay == 1) {

            double total = order.calculateTotal();

            System.out.println(YELLOW + "Total: $" + total + RESET);

            System.out.print(CYAN + "Enter cash: $" + RESET);

            double cash = scanner.nextDouble();

            while (cash < total) {
                System.out.print(RED + "Not enough. Try again: $" + RESET);
                cash = scanner.nextDouble();
            }

            double change = cash - total;
            System.out.println(GREEN + "Change: $" + String.format("%.2f", change) + RESET);
        }

        loadingScreen("Processing payment");

        System.out.println(WHITE + "\n========== RECEIPT ==========" + RESET);
        System.out.println(order.getOrderSummary());

        saveReceipt(order);

        System.out.println(GREEN + CHECK + " Order confirmed!" + RESET);

        return true;
    }

    // =========================
    // BURGER
    // =========================
    private void addBurger(Order order) {

        System.out.println("Select Burger Size:");
        System.out.println("1) Small  2) Medium  3) Large");

        int size = getIntInput();

        System.out.println("Select Bun:");
        System.out.println("1) White  2) Wheat  3) Brioche");

        int bun = getIntInput();

        System.out.println("Stuffed? 1=Yes 2=No");
        boolean stuffed = getIntInput() == 1;

        order.addBurger(new Burger(
                size == 1 ? "Small" : size == 2 ? "Medium" : "Large",
                bun == 1 ? "White" : bun == 2 ? "Wheat" : "Brioche",
                stuffed
        ));

        System.out.println(GREEN + CHECK + " Burger added!" + RESET);
    }

    // =========================
    // DRINK
    // =========================
    private void addDrink(Order order) {

        System.out.println("Drink size 1/2/3:");
        int c = getIntInput();

        scanner.nextLine();
        System.out.print("Flavor: ");
        String flavor = scanner.nextLine();

        order.addDrink(new Drink(
                c == 1 ? "Small" : c == 2 ? "Medium" : "Large",
                flavor,
                c == 1 ? 2.0 : c == 2 ? 2.5 : 3.0
        ));

        System.out.println(GREEN + CHECK + " Drink added!" + RESET);
    }

    // =========================
    // SIDE
    // =========================
    private void addSide(Order order) {

        System.out.println("1 Fries 2 Onion Rings 3 Nuggets");

        int c = getIntInput();

        if (c == 1) order.addSide(new Side("Fries", 2.5));
        if (c == 2) order.addSide(new Side("Onion Rings", 3.0));
        if (c == 3) order.addSide(new Side("Nuggets", 4.0));

        System.out.println(GREEN + CHECK + " Side added!" + RESET);
    }

    // =========================
    // INPUT SAFE
    // =========================
    private int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.print(RED + "Enter number: " + RESET);
            scanner.next();
        }
        return scanner.nextInt();
    }

    // =========================
    // RECEIPT
    // =========================
    private void saveReceipt(Order order) {

        try {
            File f = new File("receipts");
            if (!f.exists()) f.mkdir();

            String name = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));

            FileWriter w = new FileWriter(new File(f, name + ".txt"));
            w.write(order.getOrderSummary());
            w.close();

        } catch (Exception e) {
            System.out.println("Error saving receipt");
        }
    }

    private void loadingScreen(String msg) {
        System.out.print(msg);
        for (int i = 0; i < 4; i++) {
            try {
                System.out.print(".");
                Thread.sleep(300);
            } catch (Exception ignored) {}
        }
        System.out.println();
    }

    private void exitApp() {
        System.out.println(PURPLE + "Thanks for visiting THE BURGER LAB!" + RESET);
        running = false;
    }
}
package org.example;

import java.util.ArrayList;

public class Order {

    // =========================
    // FIELDS
    // =========================

    private ArrayList<Burger> burgers;
    private ArrayList<Drink> drinks;
    private ArrayList<Side> sides;

    private String customerName;

    // simple student bonus: order id
    private static int counter = 1;
    private int orderId;

    // =========================
    // CONSTRUCTOR
    // =========================

    public Order() {

        burgers = new ArrayList<>();
        drinks = new ArrayList<>();
        sides = new ArrayList<>();

        customerName = "Unknown";

        orderId = counter++;
    }

    // =========================
    // CUSTOMER NAME
    // =========================

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    // =========================
    // ADD ITEMS
    // =========================

    public void addBurger(Burger burger) {
        burgers.add(burger);
    }

    public void addDrink(Drink drink) {
        drinks.add(drink);
    }

    public void addSide(Side side) {
        sides.add(side);
    }

    // =========================
    // TOTAL CALCULATION
    // =========================

    public double calculateTotal() {

        double total = 0;

        for (Burger burger : burgers) {
            total += burger.calculatePrice();
        }

        for (Drink drink : drinks) {
            total += drink.getPrice();
        }

        for (Side side : sides) {
            total += side.getPrice();
        }

        return total;
    }

    // =========================
    // ORDER SUMMARY (RECEIPT)
    // =========================

    public String getOrderSummary() {

        StringBuilder sb = new StringBuilder();

        sb.append("""
                
                ==============================
                     THE BURGER LAB
                ==============================
                
                """);

        //  FIX: clean order info
        sb.append("Order ID: ").append(orderId).append("\n");
        sb.append("Customer: ").append(customerName).append("\n\n");

        // BURGERS
        sb.append("BURGERS:\n");

        if (burgers.isEmpty()) {
            sb.append("- No burgers\n");
        } else {
            for (Burger burger : burgers) {
                sb.append(burger.getBurgerDetails()).append("\n");
            }
        }

        // DRINKS
        sb.append("\nDRINKS:\n");

        if (drinks.isEmpty()) {
            sb.append("- No drinks\n");
        } else {
            for (Drink drink : drinks) {
                sb.append("- ").append(drink.toString()).append("\n");
            }
        }

        // SIDES
        sb.append("\nSIDES:\n");

        if (sides.isEmpty()) {
            sb.append("- No sides\n");
        } else {
            for (Side side : sides) {
                sb.append("- ").append(side.toString()).append("\n");
            }
        }

        // TOTAL
        sb.append("""
                
                ------------------------------
                TOTAL: $""");

        sb.append(String.format("%.2f", calculateTotal()));

        sb.append("""
                
                ==============================
                """);

        return sb.toString();
    }
}
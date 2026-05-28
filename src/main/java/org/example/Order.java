package org.example;

import java.util.ArrayList;

public class Order {

    // =========================
    // FIELDS
    // =========================

    private ArrayList<Burger> burgers;
    private ArrayList<Drink> drinks;
    private ArrayList<Side> sides;

    // =========================
    // CONSTRUCTOR
    // =========================

    public Order() {

        burgers = new ArrayList<>();
        drinks = new ArrayList<>();
        sides = new ArrayList<>();
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
    // GETTERS
    // =========================

    public ArrayList<Burger> getBurgers() {
        return burgers;
    }

    public ArrayList<Drink> getDrinks() {
        return drinks;
    }

    public ArrayList<Side> getSides() {
        return sides;
    }

    // =========================
    // CALCULATE TOTAL
    // =========================

    public double calculateTotal() {

        double total = 0;

        // Burger prices
        for (Burger burger : burgers) {

            total += burger.calculatePrice();
        }

        // Drink prices
        for (Drink drink : drinks) {

            total += drink.getPrice();
        }

        // Side prices
        for (Side side : sides) {

            total += side.getPrice();
        }

        return total;
    }

    // =========================
    // ORDER SUMMARY
    // =========================

    public String getOrderSummary() {

        StringBuilder sb = new StringBuilder();

        sb.append("""
                
                ==============================
                     THE BURGER LAB 
                ==============================
                
                """);

        // =========================
        // BURGERS
        // =========================

        sb.append("BURGERS:\n");

        if (burgers.isEmpty()) {

            sb.append("- No burgers\n");

        } else {

            for (Burger burger : burgers) {

                sb.append(burger.getBurgerDetails())
                        .append("\n");
            }
        }

        // =========================
        // DRINKS
        // =========================

        sb.append("DRINKS:\n");

        if (drinks.isEmpty()) {

            sb.append("- No drinks\n");

        } else {

            for (Drink drink : drinks) {

                sb.append("- ")
                        .append(drink.toString())
                        .append("\n");
            }
        }

        // =========================
        // SIDES
        // =========================

        sb.append("\nSIDES:\n");

        if (sides.isEmpty()) {

            sb.append("- No sides\n");

        } else {

            for (Side side : sides) {

                sb.append("- ")
                        .append(side.toString())
                        .append("\n");
            }
        }

          // =========================
          // TOTAL
         // =========================

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
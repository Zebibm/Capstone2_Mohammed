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
    // ADD METHODS
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

        sb.append("\n==============================\n");

        sb.append("     THE BURGER LAB 🍔\n");

        sb.append("==============================\n");

        // =========================
        // BURGERS
        // =========================

        if (burgers.size() > 0) {

            sb.append("\nBURGERS:\n");

            for (Burger burger : burgers) {

                sb.append("\n");

                sb.append(burger.getBurgerDetails());
            }
        }

        // =========================
        // DRINKS
        // =========================

        if (drinks.size() > 0) {

            sb.append("\nDRINKS:\n");

            for (Drink drink : drinks) {

                sb.append("- ")
                        .append(drink.getFlavor())
                        .append(" ")
                        .append(drink.getSize())
                        .append(" ($")
                        .append(drink.getPrice())
                        .append(")\n");
            }
        }

        // =========================
        // SIDES
        // =========================

        if (sides.size() > 0) {

            sb.append("\nSIDES:\n");

            for (Side side : sides) {

                sb.append("- ")
                        .append(side.getName())
                        .append(" ($")
                        .append(side.getPrice())
                        .append(")\n");
            }
        }

        // =========================
        // TOTAL
        // =========================

        sb.append("\n------------------------------\n");

        sb.append("TOTAL: $")
                .append(String.format("%.2f",
                        calculateTotal()));

        sb.append("\n==============================\n");

        return sb.toString();
    }
}

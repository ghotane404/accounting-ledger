package com.pluralsight;

public class UserInterface {

    public static void printCentered(String text, int width) {
        int padding = (width - text.length()) / 2;
        System.out.println("=".repeat(width));
        System.out.println(" ".repeat(padding) + text);
        System.out.println("=".repeat(width));
    }

    public static String formatHeaderForDisplay() {
        String date = "Date", time = "Time", description = "Description", vendor = "Vendor", amount = "Amount";
        return String.format("%-10s | %-10s | %-35s | %-20s | %s", date, time, description, vendor, amount);
    }

    public static String formatTotalForDisplay(double totalAmount) {
        String totalTitle = "Net Total";

        System.out.println("-".repeat(100));
        return String.format("%s:   $%-80.2f", totalTitle, totalAmount);
    }
}

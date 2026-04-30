package com.pluralsight;
import java.util.*;

public class UserInterface {
    static Scanner scanner = new Scanner(System.in);

    // homeScreen in UI so main is only used to run the program
    public static void homeScreen(Ledger ledger){
        int width = 40;
        while (true){
            System.out.println();
            UserInterface.printCentered("Home Page", width);
            System.out.println("What would you like to do? ");
            System.out.println("D) Add Deposit - prompt user for the deposit information and save it to the csv file");
            System.out.println("P) Make Payment (Debit) - prompt user for the debit information and save it to the csv file");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");

            System.out.print("Enter your choice: ");
            String userChoice = scanner.nextLine().toUpperCase().strip();

            switch(userChoice){
                case "D":
                    ledger.addDeposit();
                    break;
                case "P":
                    ledger.addPayment();
                    break;
                case "L":
                    ledger.ledgerScreen();
                    break;
                case "X":
                    System.out.println();
                    System.out.println("Thank you for using StrawHat Accounting Ledger. See you next time!");
                    return;
                default:
                    System.out.println();
                    System.out.println("Invalid selection. Please try again.");
                    break;
            }
        }
    }

    public static Transaction promptTransactionInfo(){
        System.out.print("Date (yyyy-MM-dd): ");
        String dateEntered = scanner.nextLine().strip();

        System.out.print("Time (HH:mm:ss): ");
        String timeEntered = scanner.nextLine().strip();

        System.out.print("Description: ");
        String descriptionEntered = scanner.nextLine().strip();

        System.out.print("Vendor: ");
        String vendorEntered = scanner.nextLine().strip();

        System.out.print("Amount: ");
        double amountEntered = Double.parseDouble(scanner.nextLine().strip());

        return new Transaction(dateEntered, timeEntered, descriptionEntered, vendorEntered, amountEntered);

    }

    public static void printCentered(String title, int width) {
        int padding = (width - title.length()) / 2;
        System.out.println("=".repeat(width));
        System.out.println(" ".repeat(padding) + title);
        System.out.println("=".repeat(width));
    }

    public static String formatHeaderForDisplay() {
        String date = "Date", time = "Time", description = "Description", vendor = "Vendor", amount = "Amount";
        return String.format("%-10s | %-10s | %-35s | %-20s | %s", date, time, description, vendor, amount);
    }

    public static void printTransactionTableHeader(String title, int width) {
        System.out.println();
        printCentered(title, width);
        System.out.println("-".repeat(width));
        System.out.println(formatHeaderForDisplay());
        System.out.println("-".repeat(width));
    }

    public static String formatTotalForDisplay(double totalAmount) {
        String totalTitle = "Net Total";

        System.out.println("-".repeat(100));
        return String.format("%s:   $%-80.2f", totalTitle, totalAmount);
    }

    public static void pressEnterToContinue() {
        System.out.println();
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }
}
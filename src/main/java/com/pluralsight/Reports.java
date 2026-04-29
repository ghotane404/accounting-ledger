package com.pluralsight;
import java.util.*;

public class Reports {
    static Scanner scanner = new Scanner(System.in);
    static int width = 100;
//    private Reports reports = new Reports();
    private static ArrayList<Transaction> transactions = new ArrayList<>();
    private static Transaction transaction;


    public void reportScreen(){
//        R) Reports - A new screen that allows the user to run pre-defined reports or to run a custom search
        System.out.println();
        UserInterface.printCentered("Report Page", 40);
        System.out.println("Filter reports by: ");
        System.out.println("1) Month To Date");
        System.out.println("2) Previous Month");
        System.out.println("3) Year To Date");
        System.out.println("4) Previous Year");
        System.out.println("5) Search by Vendor - prompt the user for the vendor name and display all entries for that vendor ");
        System.out.println("0) Back - go back to the Ledger page");

        String userChoice = scanner.nextLine().toUpperCase().strip();

        switch (userChoice) {
            case "1":
                monthToDate();
                break;
            case "2":
                previousMonth();
                break;
            case "3":
                yearToDate();
                break;
            case "4":
                previousYear();
                break;
            case "5":
                searchByVendor();
                break;
            case "0":
                System.out.println();
                System.out.println("Going back to ledger page");
                break;
            default:
                System.out.println();
                System.out.println("Invalid selection. Please try again.");
                break;
        }

    }

    // Current month from the 1st through today
    private static void monthToDate(){
        System.out.println();
        UserInterface.printCentered("Month To Date Transactions", width);
        System.out.println("-".repeat(width));
        System.out.println(UserInterface.formatHeaderForDisplay());
        System.out.println("-".repeat(width));
        for(Transaction transaction : transactions){
            System.out.println(transaction.formatForTransactionDisplay());
        }

    }

    // Last full month
    private static void previousMonth(){
        System.out.println();
        UserInterface.printCentered("Previous Month Transactions", width);
        System.out.println("-".repeat(width));
        System.out.println(UserInterface.formatHeaderForDisplay());
        System.out.println("-".repeat(width));
        for(Transaction transaction : transactions){
            System.out.println(transaction.formatForTransactionDisplay());
        }

    }

    // Current year, from Jan 1 through today
    private static void yearToDate(){
        System.out.println();
        UserInterface.printCentered("Year To Date Transactions", width);
        System.out.println("-".repeat(width));
        System.out.println(UserInterface.formatHeaderForDisplay());
        System.out.println("-".repeat(width));
        for(Transaction transaction : transactions){
            System.out.println(transaction.formatForTransactionDisplay());
        }

    }

    // Last full year
    private static void previousYear(){
        System.out.println();
        UserInterface.printCentered("Previous Year Transactions", width);
        System.out.println("-".repeat(width));
        System.out.println(UserInterface.formatHeaderForDisplay());
        System.out.println("-".repeat(width));
        for(Transaction transaction : transactions){
            System.out.println(transaction.formatForTransactionDisplay());
        }

    }

    // Transactions matching a vendor name
    private static void searchByVendor(){
        System.out.println();
        System.out.println("5) Search by Vendor - prompt the user for the vendor name and display all entries for that vendor ");
        UserInterface.printCentered("Transactions By Vendor", width);
        System.out.println("-".repeat(width));
        System.out.println(UserInterface.formatHeaderForDisplay());
        System.out.println("-".repeat(width));

        for(Transaction transaction : transactions){
            System.out.println(transaction.formatForTransactionDisplay());
        }


    }
}

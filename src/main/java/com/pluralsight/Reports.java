package com.pluralsight;
import java.time.LocalDate;
import java.util.*;

public class Reports {
    static Scanner scanner = new Scanner(System.in);
    private ArrayList<Transaction> transactions;
    private static LocalDate today = LocalDate.now();
    static int width = 100;

    public Reports(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

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
        System.out.print("Enter your choice: ");

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
    private void monthToDate(){
        double transactionTotal = 0;
        int currentMonth = today.getMonthValue();

        UserInterface.printTransactionTableHeader("Month To Date Transactions", width);

        for (Transaction transaction : transactions) {
            LocalDate transactionDate = LocalDate.parse(transaction.getDate());

            // if transaction month equals current month, print it
            if (transactionDate.getMonthValue() == currentMonth) {
                System.out.println(transaction.formatForTransactionDisplay());
                transactionTotal += transaction.getAmount();
            }

        }
        System.out.println(UserInterface.formatTotalForDisplay(transactionTotal));
        UserInterface.pressEnterToContinue();
    }

    // Last full month
    private void previousMonth(){
        double transactionTotal = 0;
        LocalDate previousMonth = today.minusMonths(1);

        UserInterface.printTransactionTableHeader("Previous Month Transactions", width);

        for (Transaction transaction : transactions) {
            LocalDate transactionDate = LocalDate.parse(transaction.getDate());
            // if transaction month equals current month, print it
            if (transactionDate.getMonthValue() == previousMonth.getMonthValue() && transactionDate.getYear() == previousMonth.getYear()) {
                System.out.println(transaction.formatForTransactionDisplay());
                transactionTotal += transaction.getAmount();
            }
        }
        System.out.println(UserInterface.formatTotalForDisplay(transactionTotal));
        UserInterface.pressEnterToContinue();
    }

    // Current year, from Jan 1 through today
    private void yearToDate(){
        double transactionTotal = 0;
        int currentYear = LocalDate.now().getYear();

        UserInterface.printTransactionTableHeader("Year To Date Transactions", width);

        for (Transaction transaction : transactions) {
            LocalDate transactionDate = LocalDate.parse(transaction.getDate());
            // if transaction month equals current month, print it
            if (transactionDate.getYear() == currentYear) {
                System.out.println(transaction.formatForTransactionDisplay());
                transactionTotal += transaction.getAmount();
            }
        }
        System.out.println(UserInterface.formatTotalForDisplay(transactionTotal));
        UserInterface.pressEnterToContinue();
    }

    // Last full year
    private void previousYear(){
        double transactionTotal = 0;
        LocalDate previousYear = today.minusYears(1);

        UserInterface.printTransactionTableHeader("Previous Year Transactions", width);

        for (Transaction transaction : transactions) {
            LocalDate transactionDate = LocalDate.parse(transaction.getDate());
            // if transaction month equals current month, print it
            if (transactionDate.getYear() == previousYear.getYear()) {
                System.out.println(transaction.formatForTransactionDisplay());
                transactionTotal += transaction.getAmount();
            }
        }
        System.out.println(UserInterface.formatTotalForDisplay(transactionTotal));
        UserInterface.pressEnterToContinue();
    }

    // Transactions matching a vendor name
    private void searchByVendor(){
        double transactionTotal = 0;
        System.out.println();
        System.out.print("Enter vendor name: ");
        String vendorName = scanner.nextLine().toUpperCase().strip();

        UserInterface.printTransactionTableHeader("Transactions By Vendor", width);

        for (Transaction transaction : transactions) {
            if (Objects.equals(transaction.getVendor().toUpperCase().strip(), vendorName)) {
                System.out.println(transaction.formatForTransactionDisplay());
                transactionTotal += transaction.getAmount();
            }
        }
        System.out.println(UserInterface.formatTotalForDisplay(transactionTotal));
        UserInterface.pressEnterToContinue();
    }
}
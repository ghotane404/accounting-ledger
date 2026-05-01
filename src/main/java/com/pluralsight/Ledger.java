package com.pluralsight;
import java.io.*;
import java.util.*;

public class Ledger {
    static Scanner scanner = new Scanner(System.in);
    static int width = 100;

    private Reports reports;        //
    private ArrayList<Transaction> transactions;

    public Ledger(ArrayList<Transaction> transactions) {        // a constructor which receives the loaded transaction list
        this.transactions = transactions;       // saves transaction list it receives into this Ledger object
        this.reports = new Reports(transactions);       // creates Reports object with the same transaction list
    }

    //▪ L) Ledger - display the ledger screen
    public void ledgerScreen(){
        //    Ledger - All entries should show the newest entries first???
        while (true){
            System.out.println();
            UserInterface.printCentered("Ledger Page", 40);
            System.out.println("What would you like to do? ");
            System.out.println("A) All - Display all entries");
            System.out.println("D) Deposits - Display only the entries that are deposits into the account");
            System.out.println("P) Payments - Display only the negative entries (or payments)");
            System.out.println("R) Reports - A new screen that allows the user to run pre-defined reports or to run a custom search ");
            System.out.println("H) Home - go back to the home page");
            System.out.print("Enter your choice: ");
            String userChoice = scanner.nextLine().toUpperCase().strip();

            switch (userChoice) {
                case "A":
                    displayAllTransactions();
                    break;      // break - leaves the switch and continues the method
                case "D":
                    displayDeposits();
                    break;
                case "P":
                    displayPayments();
                    break;
                case "R":
                    reports.reportScreen();
                    break;
                case "H":
                    return;     // immediately leaves the current method
                default:
                    System.out.println();
                    System.out.println("Invalid selection. Please try again.");
                    break;
            }
        }
    }

    // adding new transaction made to the file
    private void addTransaction(String date, String time, String description, String vendor, double amount) {
        Transaction newTransaction = new Transaction(date, time, description, vendor, amount);
        transactions.add(newTransaction);

        TransactionFileManager.saveTransactions(newTransaction);
        System.out.println("Transaction saved successfully: ");
        System.out.printf("$%.2f from %s on %s.%n", amount, vendor, date);
        UserInterface.pressEnterToContinue();       // Pauses the screen so the user can read the result.
    }

    //▪ P) Make Payment (Debit) - prompt user for the debit information and save it to the csv file
    public void addDeposit() {
        System.out.println("Please enter the deposit information:");
        Transaction transaction = UserInterface.promptTransactionInfo();

        addTransaction(transaction.getDate(), transaction.getTime(), transaction.getDescription(), transaction.getVendor(), Math.abs(transaction.getAmount()));
    }

    // goes to displayFilteredTransactions method where both deposit and payment transaction are handled.
    public void addPayment(){
        System.out.println("Please enter the payment information:");
        Transaction transaction = UserInterface.promptTransactionInfo();

        addTransaction(transaction.getDate(), transaction.getTime(), transaction.getDescription(), transaction.getVendor(), -Math.abs(transaction.getAmount()));
    }

    // displays only the entries that are deposits into the account
    private void displayDeposits() {
        displayFilteredTransactions("Deposit History", true);
    }

    // displays only the negative entries (or payments)
    private void displayPayments() {
        displayFilteredTransactions("Payment History", false);
    }

    // displays all transaction made on the account to user
    public void displayAllTransactions() {
        double transactionTotal = 0;
        UserInterface.printTransactionTableHeader("All Transactions", width);

        for (Transaction transaction : transactions) {
            System.out.println(transaction.formatForTransactionDisplay());
            transactionTotal += transaction.getAmount();
        }

        System.out.println(UserInterface.formatTotalForDisplay(transactionTotal));
        UserInterface.pressEnterToContinue();
    }

    private void displayFilteredTransactions(String title, boolean showDeposits) {
        double transactionTotal = 0;
        UserInterface.printTransactionTableHeader(title, width);

        for (Transaction transaction : transactions) {
            if (showDeposits && transaction.getAmount() > 0) {
                System.out.println(transaction.formatForTransactionDisplay());
                transactionTotal += transaction.getAmount();

            }
            else if (!showDeposits && transaction.getAmount() < 0) {
                System.out.println(transaction.formatForTransactionDisplay());
                transactionTotal += transaction.getAmount();
            }
        }
        System.out.println(UserInterface.formatTotalForDisplay(transactionTotal));
        UserInterface.pressEnterToContinue();
    }

    // creates a Class with transaction already loaded.
    public static Ledger createLoadedLedger() {
        ArrayList<Transaction> loadedTransactions = TransactionFileManager.loadTransactions();      // loads transactions from the CSV file.
        return new Ledger(loadedTransactions);      // create a Ledger object using the loaded transactions.
    }
}



































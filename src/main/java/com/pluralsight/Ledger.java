package com.pluralsight;
import java.io.*;
import java.util.*;

public class Ledger {
    static Scanner scanner = new Scanner(System.in);
    static int width = 100;

    private Reports reports;
    private static ArrayList<Transaction> transactions  = new ArrayList<>();

    public Ledger() {
        reports = new Reports(transactions);
    }

    //▪ L) Ledger - display the ledger screen
    public void ledgerScreen(){
        //    Ledger - All entries should show the newest entries first
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
                    return;     // immediately leaves the method
                default:
                    System.out.println();
                    System.out.println("Invalid selection. Please try again.");
                    return;
            }
        }
    }

    // loading the transaction.csv file
    private void loadTransaction(){
        try {
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            bufferedReader.readLine();      // skipping first line of the file (since it's the title)
            String line = bufferedReader.readLine();

            while (line != null) {
                String[] cols = line.split("\\|");
                String date = cols[0];
                String time = cols[1];
                String description = cols[2];
                String vendor = cols[3];
                double amount = Double.parseDouble(cols[4]);

                Transaction transaction = new Transaction(date, time, description, vendor, amount);
                transactions.add(transaction);

                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("\n Error");
            throw new RuntimeException(e);
        }
    }

    // saving one transaction at a time from the transaction file
    public static void saveTransactions(String date, String time, String description, String vendor, double amount){
//    public void saveTransactions(){
        try{
            FileWriter fileWriter = new FileWriter("transactions.csv", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            Transaction depositTransactions = new Transaction(date, time, description, vendor, amount);
//            bufferedWriter.write("date|time|description|vendor|amount");
            bufferedWriter.newLine();       // starting a new line
            bufferedWriter.write(depositTransactions.formatForCsv());    // Write the content

            bufferedWriter.close();     // closes the writer and saves changes
        }
        catch (Exception e) {
            System.out.println("Error writing to file. Exiting program...");
            throw new RuntimeException(e);
        }

    }

    // adding new transaction made to the file
    private void addTransaction(String date, String time, String description, String vendor, double amount) {
        System.out.println("addTransaction Screen");
        Transaction newTransaction = new Transaction(date, time, description, vendor, amount);
        transactions.add(newTransaction);
        saveTransactions(date, time, description, vendor, amount);
    }

    //▪ P) Make Payment (Debit) - prompt user for the debit information and save it to the csv file
    public void addDeposit() {
        System.out.println("D) Add Deposit - prompt user for the deposit information and save it to the csv file");
        System.out.print("Date (yyyy-MM-dd): ");
        String dateEntered = scanner.nextLine().toUpperCase().strip();
        System.out.print("Time (HH:mm:ss): ");
        String timeEntered = scanner.nextLine().toUpperCase().strip();
        System.out.print("Description: ");
        String descriptionEntered = scanner.nextLine();
        System.out.print("Vendor: ");
        String vendorEntered = scanner.nextLine();
        System.out.print("Amount: ");
        double amountEntered = scanner.nextDouble();

        Ledger.saveTransactions(dateEntered, timeEntered, descriptionEntered, vendorEntered, amountEntered);
    }

    public void addPayment(){
        System.out.println("P) Make Payment (Debit) - prompt user for the debit information and save it to the csv file");
        System.out.print("Date (yyyy-MM-dd): ");
        String dateEntered = scanner.nextLine().toUpperCase().strip();
        System.out.print("Time (HH:mm:ss): ");
        String timeEntered = scanner.nextLine().toUpperCase().strip();
        System.out.print("Description: ");
        String descriptionEntered = scanner.nextLine();
        System.out.print("Vendor: ");
        String vendorEntered = scanner.nextLine();
        System.out.print("Amount: ");
        double amountEntered = scanner.nextDouble();

        Ledger.saveTransactions(dateEntered, timeEntered, descriptionEntered, vendorEntered, amountEntered);
    }

    // displays all transaction made on the account to user
    public void displayAllTransactions(){
        double transactionTotal = 0;

        System.out.println();
        UserInterface.printCentered("All Transactions", width);
        System.out.println("-".repeat(width));
        System.out.println(UserInterface.formatHeaderForDisplay());
        System.out.println("-".repeat(width));

        for(Transaction transaction : transactions){
            System.out.println(transaction.formatForTransactionDisplay());
            transactionTotal += transaction.getAmount();
        }

        System.out.println(UserInterface.formatTotalForDisplay(transactionTotal));
    }

    // displays only the entries that are deposits into the account
    private void displayDeposits() {
        System.out.println();
        UserInterface.printCentered("Deposit History", width);
    }

    // displays only the negative entries (or payments)
    private void displayPayments() {
        System.out.println();
        UserInterface.printCentered("Payment History", width);
    }

    // creates a Class with transaction already loaded.
    public static Ledger createLoadedLedger() {
        Ledger ledger = new Ledger();
        ledger.loadTransaction();
        return ledger;
    }
}
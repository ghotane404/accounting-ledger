package com.pluralsight;
import java.io.*;
import java.util.*;

public class Ledger {
    static Scanner scanner = new Scanner(System.in);
    private static Ledger ledger = new Ledger();
    private static Reports reports = new Reports();
    private static ArrayList<Transaction> transactions = new ArrayList<>();
    private static Transaction transaction;

    //▪ L) Ledger - display the ledger screen
    public void ledgerScreen(){
        //    Ledger - All entries should show the newest entries first
        while (true){
            System.out.println();
            System.out.println("What would you like to do? ");
            System.out.println("A) All - Display all entries");
            System.out.println("D) Deposits - Display only the entries that are deposits into the account");
            System.out.println("P) Payments - Display only the negative entries (or payments)");
            System.out.println("R) Reports - A new screen that allows the user to run pre-defined reports or to run a custom search \n");
            System.out.println("H) Home - go back to the home page");

            String userChoice = scanner.nextLine().toUpperCase().strip();

            switch (userChoice) {
                case "A":
                    ledger.displayAllTransactions();
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

            bufferedReader.readLine();
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
            System.out.println("This is an exception e error");
            throw new RuntimeException(e);
        }

    }

    // adding new transaction made to the file
    private void addTransaction() {


    }

    // saving one transaction at a time from the transaction file
    private void saveTransactions(){
//        date|time|description|vendor|amount
//        2023-04-15|10:13:25|ergonomic keyboard|Amazon|-89.50
//        2023-04-15|11:15:00|Invoice 1001 paid|Joe|1500.00

//        try{
//            FileWriter fileWriter = new FileWriter("transactions.csv", true);
//            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
//            bufferedWriter.newLine();
//
//            String line = bufferedWriter.newLine();
//
//            System.out.println("date|time|description|vendor|amount");
//
//
//                bufferedWriter.newLine();
//
//        }
//        catch (Exception e) {
//            System.out.println("Error writing to file. Exiting program...");
//            throw new RuntimeException(e);
//        }
//        return;
    }

    // displays all transaction made on the account
    private void displayAllTransactions(){
        System.out.println();
        System.out.println(transaction.formatHeaderForDisplay());
        for(Transaction transaction : transactions){
            System.out.println(transaction.formatForDisplay());
        }
    }

    // displays only the entries that are deposits into the account
    private void displayDeposits() {


    }

    // displays only the negative entries (or payments)
    private void displayPayments() {


    }

    public static Ledger createLoadedLedger() {
        Ledger ledger = new Ledger();
        ledger.loadTransaction();

        return ledger;
    }
}
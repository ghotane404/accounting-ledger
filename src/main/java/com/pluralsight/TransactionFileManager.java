package com.pluralsight;
import java.io.*;
import java.util.*;

public class TransactionFileManager {

    // loading the transaction.csv file
    public static ArrayList<Transaction> loadTransactions(){
        ArrayList<Transaction> loadedTransactions = new ArrayList<>();
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
                loadedTransactions.add(transaction);

                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("\nError loading transactions.");
            throw new RuntimeException(e);
        }
        // return the completed transaction list back to Ledger.
        return loadedTransactions;
    }

    // saving one transaction at a time from the transaction file
    public static void saveTransactions(Transaction transaction){
        try{
            FileWriter fileWriter = new FileWriter("transactions.csv", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

//            Transaction depositTransactions = new Transaction(date, time, description, vendor, amount);
            bufferedWriter.newLine();       // starting a new line
            bufferedWriter.write(transaction.formatForCsv());

            bufferedWriter.close();     // closes the writer and saves changes
        }
        catch (Exception e) {
            System.out.println("Error writing to file. Exiting program...");
            throw new RuntimeException(e);
        }
    }
}
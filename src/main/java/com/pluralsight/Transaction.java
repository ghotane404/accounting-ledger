package com.pluralsight;
import java.io.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private String currentDate;
    private String currentTime;
    private String transactionDescription;
    private String transactionVendor;
    private double transactionAmount;

    Transaction(String currentDate, String currentTime, String transactionDescription, String transactionVendor, double transactionAmount){
        this.currentDate = currentDate;
        this.currentTime = currentTime;
        this.transactionDescription = transactionDescription;
        this.transactionVendor = transactionVendor;
        this.transactionAmount = transactionAmount;
    }

    public String getDate(){
        return currentDate;
    }

    public String getTime(){
        return currentTime;
    }

    public String getDescription(){
        return transactionDescription;
    }

    public String getVendor(){
        return transactionVendor;
    }

    public double getAmount(){
        return transactionAmount;
    }

    // send the transactions from customer to the Ledger file to be saved to cvs file.
    public String formatForCsv(){
//        System.out.println("date|time|description|vendor|amount");
        return String.format("%s|%s|%s|%s|%.2f", currentDate, currentTime, transactionDescription, transactionVendor, transactionAmount);
    }

    public String formatForTransactionDisplay() {
        return String.format("%-10s | %-10s | %-35s | %-20s | $%.2f", currentDate, currentTime, transactionDescription, transactionVendor, transactionAmount);
    }

    // find a use for this
//    public static String currentDate(){
//        LocalDateTime today = LocalDateTime.now();
//        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");      // formatting the date and time
//        return today.format(formatDate);        // returns the formatted date and time
//    }
//
//    public static String currentTime(){
//        LocalDateTime today = LocalDateTime.now();
//        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm");      // formatting the date and time
//        return today.format(formatTime);        // returns the formatted date and time
//    }
}
package com.pluralsight;
import java.io.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private String currentDate;
    private String currentTime;
    private String productDescription;
    private String productVendor;
    private double productAmount;

    Transaction(String currentDate, String currentTime, String productDescription, String productVendor, double productAmount){
        this.currentDate = currentDate;
        this.currentTime = currentTime;
        this.productDescription = productDescription;
        this.productVendor = productVendor;
        this.productAmount = productAmount;
    }

    public String getDate(){
        return currentDate;
    }

    public String getTime(){
        return currentTime;
    }

    public String getDescription(){
        return productDescription;
    }

    public String getVendor(){
        return productVendor;
    }

    public double getAmount(){
        return productAmount;
    }


    // send the transactions from customer to the Ledger file to be saved to cvs file.
    public String formatForCsv(){
        System.out.println("date|time|description|vendor|amount");
        return String.format("%s|%s|%s|%s|%.2f", currentDate(), currentTime(), productDescription, productVendor, productAmount);

    }

    public String formatForDisplay() {
        return String.format("%s | %s | %-35s | %-20s | $%.2f", currentDate, currentTime, productDescription, productVendor, productAmount);
    }

    public String formatHeaderForDisplay() {
        String date = "Date", time = "Time", description = "Description", vendor = "Vendor", amount = "Amount";
        return String.format("%s | %s | %-35s | %-20s | %s", date, time, description, vendor, amount);
    }

    public static String currentDate(){
        LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");      // formatting the date and time
        return today.format(formatDate);        // returns the formatted date and time
    }

    public static String currentTime(){
        LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm:ss");      // formatting the date and time
        return today.format(formatTime);        // returns the formatted date and time
    }

    //▪ P) Make Payment (Debit) - prompt user for the debit information and save it to the csv file

//    isDeposit()
//
//    isPayment()
//
//    toString()





}

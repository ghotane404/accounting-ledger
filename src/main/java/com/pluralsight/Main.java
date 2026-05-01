package com.pluralsight;

public class Main {
//    private static Ledger ledger;
    public static void main (String[] args){
//        ledger = Ledger.createLoadedLedger();
        UserInterface.homeScreen(Ledger.createLoadedLedger());
    }
}
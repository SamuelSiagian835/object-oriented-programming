package fintech.driver;

/**
 * @author 12S21010 Boby Siagian
 * @author 12S21042 Samuel Siagian
 */

import java.util.Collections;
import java.util.Scanner;
import fintech.model.*;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Comparator;

public class Driver2 {
    
    public static void main(String[] args) {
        LinkedList<Account> saveAccounts = new LinkedList<Account>();
        ArrayList<Account> findAccounts = new ArrayList<Account>();
        ArrayList<Transaction> transaksiList = new ArrayList<Transaction>();

        String saveAccount[] = new String[100];
        int i = 0;

        String perintah;
        try (Scanner input = new Scanner(System.in)) {
            while (true) {
                perintah = input.nextLine();
                if (perintah.equals("---")) {
                    break;
                } else if (perintah.startsWith("create-account")) {
                    String split[] = perintah.split("#");
                    String Name = split[1];
                    boolean accountExists = true;
            

                    for (Account account : saveAccounts) {
                        if (account.getAccountName().equals(split[2])) {
                            accountExists = false;
                            break;
                        }
                    }
            
                    if (accountExists) {
                        Account account = new Account(Name, split[2]);
                        saveAccounts.add(account);
                        System.out.println(account.toString());
                    }
            
                } else if (perintah.startsWith("show-accounts")) {
                    String split[] = perintah.split("#");
                    Collections.sort(saveAccounts , new Comparator<Account>() {
                        public int compare(Account t1, Account t2) {
                            return t1.getAccountName().compareTo(t2.getAccountName());
                        }
                    });
                    for (Account prinAccount : saveAccounts) {
                        System.out.println(prinAccount.toString());
                    }
                }else if (perintah.startsWith("remove-account")) {
                    String split[] = perintah.split("#");

                    for (Account prinAccount : saveAccounts) {
                        if (prinAccount.getAccountName().equals(split[1].toLowerCase())) {
                            saveAccounts.remove(prinAccount);
                        }
                       
                    } 
                }
            }
        }

        Collections.sort(transaksiList, new Comparator<Transaction>() {
            public int compare(Transaction t1, Transaction t2) {
                return t1.getWaktuTransaksi().compareTo(t2.getWaktuTransaksi());
            }
        });

        printSaveAccount(saveAccount, i);
        printFindAccount(findAccounts);

        for (Transaction t : transaksiList) {
            System.out.println(t.getId() + "|" + t.getAccountName() + "|" + t.getAmount() + "|"
                    + t.getWaktuTransaksi() + "|" + t.getNote());
        }

    }

    public static void printAccount(LinkedList<Account> saveAccounts) {
        for (Account account : saveAccounts) {
            System.out.println(account);
        }
    }

    public static void printFindAccount(ArrayList<Account> findAccounts) {
        for (Account account : findAccounts) {
            System.out.println(account);
        }
    }

    public static void printSaveAccount(String saveAccount[], int i) {
        for (int y = 0; y < i; y++) {
            System.out.println(saveAccount[y]);
        }
    }

    public static void printTransaction(ArrayList<Transaction> makeTran) {
        for (Transaction transaction : makeTran) {
            System.out.println(transaction.getId() + "|" + transaction.getAccountName().toLowerCase() + "|" + transaction.getAmount() + "|" + transaction.getWaktuTransaksi() + "|" + transaction.getNote());

        }
    }

    public static void printTransactionSave(ArrayList<Transaction> transaksiList) {
        Collections.sort(transaksiList, new Comparator<Transaction>() {
            public int compare(Transaction t1, Transaction t2) {
                return t1.getWaktuTransaksi().compareTo(t2.getWaktuTransaksi());
            }
        });
        for (Transaction transaction : transaksiList) {
            System.out.println(transaction.getId() + "|" + transaction.getAccountName().toLowerCase() + "|" + transaction.getAmount() + "|" + transaction.getWaktuTransaksi() + "|" + transaction.getNote());
        }
    }
    

}
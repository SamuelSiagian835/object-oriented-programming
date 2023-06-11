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

public class Driver1 {
    /**
     * @param args
     */
    public static void main(String[] args) {
        LinkedList<Account> saveAccounts = new LinkedList<Account>();
        ArrayList<Account> findAccounts = new ArrayList<Account>();
        ArrayList<Transaction> makeTran = new ArrayList<Transaction>();
        ArrayList<Transaction> transaksiList = new ArrayList<Transaction>();

        String saveTran[] = new String[100];

        String saveAccount[] = new String[100];
        int i = 0, j = 0;

        String perintah;
        Scanner input = new Scanner(System.in);

        while (true) {
            perintah = input.nextLine();
            if (perintah.equals("---")) {
                break;
            } else if (perintah.startsWith("create-account")) {
                String split[] = perintah.split("#");
                Account account = new Account(split[1], split[2]);
                saveAccount[i] = account.toString();
                saveAccounts.add(account);
                i++;

                // find-account#JAKSEM

            } else if (perintah.startsWith("show-account")) {
                String split[] = perintah.split("#");
                split[1] = split[1].toLowerCase();
                for (Account account : saveAccounts) {
                    if (account.getAccountName().equals(split[1])) {
                        findAccounts.add(account);
                    }


                }

                for (Transaction transaction : makeTran) {
                    if (transaction.getAccountName().equals(split[1])) {
                        transaksiList.add(transaction);
                        // saveTran[j] = transaction.toStringTran();
                        // j++;
                    }
                }

            } else if (perintah.startsWith("create-transaction")) {
                String split[] = perintah.split("#");

                double amount = Double.parseDouble(split[2]);
                String namaalias = split[1];
                namaalias = namaalias.toLowerCase();

                for (Account account : saveAccounts) {
                    if (account.getAccountName().toLowerCase().equals(namaalias)) {
                        Transaction transaction = new Transaction(j, split[1], namaalias, Double.parseDouble(split[2]), split[3],
                                split[4]);
                        // System.out.println(transaction.toStringTran());
                        makeTran.add(transaction);
                        account.addSaldo(amount);

                        break;
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

    // method print array saveAccount
    public static void printSaveAccount(String saveAccount[], int i) {
        for (int y = 0; y < i; y++) {
            System.out.println(saveAccount[y]);
        }
    }

    public static void printTransaction(ArrayList<Transaction> makeTran) {
        for (Transaction transaction : makeTran) {
            System.out.println(transaction.toStringTran());
        }
    }

    // method transaksiList
    public static void printTransactionSave(ArrayList<Transaction> transaksiList) {
        for (Transaction transaction : transaksiList) {
            System.out.println(transaction.toStringTran());
        }
    }

}
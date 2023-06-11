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
    private static Comparator compareByName;

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
            String split[] = perintah.split("#");
            if (perintah.equals("---")) {
                break;
            } else if (perintah.startsWith("create-account")) {
                Account account = new Account(split[1], split[2]);
                saveAccount[i] = account.toString();
                saveAccounts.add(account);
                i++;

                // find-account#JAKSEM

            } else if (split[0].equals("show-account")) {
                split[1] = split[1].toLowerCase();
                findAccounts.clear(); // kosongkan arraylist sebelum melakukan pencarian
            
                for (Account account : saveAccounts) {
                    if (account.getAccountName().equalsIgnoreCase(split[1])) {
                        findAccounts.add(account);
                    }
                }
            
                if (findAccounts.size() > 0) { // Jika ada akun yang ditemukan, tampilkan secara alfabetis
                    Collections.sort(findAccounts, compareByName);
                    printFindAccount(findAccounts);
                }
            }
             else if (perintah.startsWith("create-transaction")) {

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
                // show-account#jaksem
            }else if (perintah.equals("show-accounts")) {
                for (Transaction transaction : makeTran) {
                    System.out.println(makeTran.toStringTran());
                }

            }

        }
        Comparator<Account> compareByName = new Comparator<Account>() {
            public int compare(Account a1, Account a2) {
                return a1.getAccountName().compareToIgnoreCase(a2.getAccountName());
            }
        };
        

        Collections.sort(transaksiList, new Comparator<Transaction>() {
            public int compare(Transaction t1, Transaction t2) {
                return t1.getWaktuTransaksi().compareTo(t2.getWaktuTransaksi());
            }
        });

        printSaveAccount(saveAccount, i);
        printFindAccount(findAccounts);
        // printTransaction(transaksiList);
        // 2|jaksem|-8.4|2023/02/15 15:18:15|SIM credit
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
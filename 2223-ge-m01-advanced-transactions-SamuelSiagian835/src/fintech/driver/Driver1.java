package fintech.driver;

/**
 * @author 12S21010 Bobby Siagian
 * @author 12S21042 Samuel Siagian
 */

import java.util.Collections;
import java.util.Scanner;

import fintech.model.*;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Comparator;

public class Driver1 {
    public static void main(String[] args) {
        LinkedList<Account> kontainerAccount = new LinkedList<Account>();
        ArrayList<Transaction> makeTran = new ArrayList<Transaction>();
        ArrayList<Transaction> transaksiList = new ArrayList<Transaction>();

        String saveTran[] = new String[100];

        String saveAccount[] = new String[100];
        int i = 0, j = 0;

        String perintah;
        Scanner input = new Scanner(System.in);

        while (true) {
            perintah = input.nextLine();
            String pisah[] = perintah.split("#");
            if (perintah.equals("---")) {
                break;
            } else if (perintah.startsWith("create-account")) {
                tambahAkun(perintah, kontainerAccount);
            } else if (pisah[0].equals("show-account")) {

                // System.out.println("zdcsdc");
                String split[] = perintah.split("#");
                split[1] = split[1].toLowerCase();
                for (Account account : kontainerAccount) {
                    if (account.getAccountName().equals(split[1])) {
                        System.out.println(account.toString());
                    }

                }for (Transaction transaction : makeTran) {
                    if (transaction.getAccountName().equals(split[1])) {
                        System.out.println(transaction.toStringTran());

                    }
                }
                
                // Collections.sort(makeTran, new Comparator<Transaction>() {
                //     public int compare(Transaction t1, Transaction t2) {
                //         return t1.getWaktuTransaksi().compareTo(t2.getWaktuTransaksi());
                //     }
                // });

                
            } else if (perintah.startsWith("create-transaction")) {
                String split[] = perintah.split("#");

                double Hepeng = Double.parseDouble(split[2]);
                String namaalias = split[1];
                namaalias = namaalias.toLowerCase();

                for (Account account : kontainerAccount) {
                    if (account.getAccountName().toLowerCase().equals(namaalias)) {
                        if (account.getBalance() + Hepeng > 0) {
                            account.addSaldo(Hepeng);
                            Transaction transaction = new Transaction(j, split[1], namaalias,
                                    Double.parseDouble(split[2]),
                                     split[3],
                                    split[4]);
                            makeTran.add(transaction);

                            break;
                        }

                    }
                }

                
                Collections.sort(makeTran, new Comparator<Transaction>() {
                    public int compare(Transaction t1, Transaction t2) {
                        return t1.getWaktuTransaksi().compareTo(t2.getWaktuTransaksi());
                    }
                });
            } else if (perintah.equals("show-accounts")) {

                Collections.sort(kontainerAccount, new Comparator<Account>() {
                    public int compare(Account t1, Account t2) {
                        return t1.getAccountName().compareTo(t2.getAccountName());
                    }
                });

                for (Account account : kontainerAccount) {
                    System.out.println(account.toString());
                    for (Transaction transHepeng : makeTran) {
                        if (transHepeng.getAccountName().equals(account.getAccountName())) {
                            System.out.println(transHepeng.toStringTran());
                        }

                    }

                }

            } else if (perintah.startsWith("remove-account")) {
                String split[] = perintah.split("#");

                for (Account prinAccount : kontainerAccount) {
                    if (prinAccount.getAccountName().equals(split[1].toLowerCase())) {
                        kontainerAccount.remove(prinAccount);
                        break;
                    }
                }
            }

        }

    }

    public static void printAccount(LinkedList<Account> kontainerAccount) {
        for (Account account : kontainerAccount) {
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

    public static void tambahAkun(String perintah, LinkedList<Account> kontainerAccount) {
        String split[] = perintah.split("#");
        boolean cek = true;
        for (Account accountsAccount : kontainerAccount) {
            if (accountsAccount.getAccountName().equals(split[2])) {
                cek = false;
                break;
            }
        }
        if (cek == true) {
            Account accounts = new Account(split[1], split[2]);
            kontainerAccount.add(accounts);
            System.out.println(accounts.toString());
        }
    }

}
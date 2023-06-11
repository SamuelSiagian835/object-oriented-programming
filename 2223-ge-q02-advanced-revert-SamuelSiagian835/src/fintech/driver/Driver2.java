package fintech.driver;

/**
 * @author 12S21010 Bobby Siagian
 * @author 12S21042 Samuel Siagian
 */
import java.util.Collections;
import java.util.Scanner;

import javax.print.attribute.standard.PrinterMessageFromOperator;
import javax.swing.plaf.SplitPaneUI;

import java.util.LinkedList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import fintech.model.*;

public class Driver2 {
    private static LinkedList<Account> kontainerAccount;
    private static Account[] saveAccounts;

    /**
     * @param args
     */
    public static void main(String[] args) {
        kontainerAccount = new LinkedList<Account>();
        ArrayList<Transaction> makeTran = new ArrayList<Transaction>();

        String perintah, str;
        Scanner input = new Scanner(System.in);

        while (true) {
            perintah = input.nextLine();
            str = perintah;
            String pisah[] = perintah.split("#");
            if (perintah.equals("---")) {
                break;
            } else if (perintah.startsWith("create-account")) {
                tambahAkun(perintah);
            } else if (pisah[0].equals("show-account")) {
                String split[] = perintah.split("#");
                String accountName = split[1].toLowerCase();
                double balance = 0.0;
                for (Account account : kontainerAccount) {
                    if (account.getAccountName().toLowerCase().equals(accountName)) {
                        balance = account.getBalance();
                        System.out.println(account.toString());
                    }
                }

                Collections.sort(makeTran, new Comparator<Transaction>() {
                    public int compare(Transaction t1, Transaction t2) {
                        return t1.getWaktuTransaksi().compareTo(t2.getWaktuTransaksi()); // baru kutambahin
                    }

                });

                for (Transaction transaction : makeTran) {
                    if (transaction.getreceinama() != null) {
                        if (transaction.getAccountName().equals(split[1])
                                || transaction.getreceinama().equals(split[1])) {
                            balance += transaction.getAmount();
                            System.out.println(transaction.toStringTran());
                        }
                    } else {
                        if (transaction.getAccountName().equals(split[1])) {
                            balance += transaction.getAmount();
                            System.out.println(transaction.toStringTran());
                        }
                    }
                }
            } 
            
            
            
            
            
            else if (pisah[0].equals("revert-transaction")) { //1
                String split[] = perintah.split("#");
                String name = split[1];
                int id = Integer.parseInt(split[2]);
                String waktu = split[3];
                boolean con = false;

                for (Transaction transaction : makeTran) { //8
                    if (transaction.getId() == id) { //9
                        String cekTran = transaction.toStringTran(); //11

                        LocalDateTime cekWaktuCreate = LocalDateTime.parse(str.split("#")[3], //13
                                DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
                        LocalDateTime cekWaktuRevert = LocalDateTime.parse(cekTran.split("\\|")[3],
                                DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));

                        Duration diffInMinutes = Duration.between(cekWaktuRevert, cekWaktuCreate);
                        // long diffInMinutes = getDifferenceInMinutes(waktu,
                        // transaction.getTimestamp());

                        if (diffInMinutes.toMinutes() < 10) { //16
                            for (Account account : kontainerAccount) { //17
                                double moneyAcoount = account.getBalance();
                                double moneyTransaction = transaction.getAmount();
                                if (account.getAccountName().equals(transaction.getAccountName())) {
                                    if (moneyAcoount >= moneyTransaction) {
                                        account.setBalance(moneyAcoount - moneyTransaction);
                                        con = true;
                                    } else {
                                        // System.out.println("Saldo tidak mencukupi untuk melakukan revert
                                        // transaksi.");
                                        // return;
                                    }
                                }
                            }
                        } else {
                            // System.out.println("Batas waktu pembatalan telah terlampaui.");

                        }

                    }
                    if (con) {
                        Transaction newTransaction = new Transaction(
                                transaction.getAccountName(),
                                (-1) * transaction.getAmount(),
                                waktu,
                                "REVERT: "
                                        + (transaction.getMasukan() == null ? "note" : transaction.getMasukan()));
                        makeTran.add(newTransaction);
                        break;
                    }
                }



            } else if (perintah.startsWith("create-transaction")) {
                String split[] = perintah.split("#");
                if (split.length <= 5) {

                    double Hepeng = Double.parseDouble(split[2]);
                    String namaalias = split[1];
                    namaalias = namaalias.toLowerCase();

                    for (Account account : kontainerAccount) {
                        if (account.getAccountName().toLowerCase().equals(namaalias)) {

                            if (account.getBalance() + Hepeng >= 0) {
                                account.addSaldo(Hepeng);
                                Transaction transaction = new Transaction(split[1], Hepeng, split[3], split[4]);
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
                } else if (split.length > 5) {
                    double amount = Double.parseDouble(split[3]);
                    if (amount > 0) {
                        String sender = split[1].toLowerCase();
                        String receiver = split[2].toLowerCase();
                        boolean isSenderExist = false, isReceiverExist = false;
                        for (Account account : kontainerAccount) {
                            if (account.getAccountName().toLowerCase().equals(sender)) {
                                if (account.getBalance() - amount >= 0) {
                                    account.addSaldo(-amount);
                                    isSenderExist = true;
                                    for (Account account2 : kontainerAccount) {
                                        if (account2.getAccountName().toLowerCase().equals(receiver)) {
                                            account2.addSaldo(amount);
                                            isReceiverExist = true;

                                            Transaction transfer = new Transaction(sender, receiver, amount, split[4],
                                                    split[5]);
                                            makeTran.add(transfer);
                                            break;
                                        }
                                    }
                                }
                                break;
                            }
                        }
                    }

                }
                Collections.sort(makeTran, new Comparator<Transaction>() {
                    public int compare(Transaction t1, Transaction t2) {
                        return t1.getWaktuTransaksi().compareTo(t2.getWaktuTransaksi());
                    }
                });

            } else if (perintah.startsWith("remove-account")) {
                String split[] = perintah.split("#");
                for (Account prinAccount : kontainerAccount) {
                    if (prinAccount.getAccountName().equals(split[1].toLowerCase())) {
                        kontainerAccount.remove(prinAccount);
                        break;
                    }
                }
            } else if (perintah.equals("show-accounts")) {

                Collections.sort(kontainerAccount, new Comparator<Account>() {
                    public int compare(Account t1, Account t2) {
                        return t1.getAccountName().compareTo(t2.getAccountName());
                    }
                });

                for (Account account : kontainerAccount) {
                    System.out.println(account.toString());
                    for (Transaction transHepeng : makeTran) {
                        if (transHepeng.getreceinama() != null) {
                            if (transHepeng.getAccountName().equals(account.getAccountName())
                                    || transHepeng.getreceinama().equals(account.getAccountName())) {
                                System.out.println(transHepeng.toStringTran());
                            }
                        } else {
                            if (transHepeng.getAccountName().equals(account.getAccountName())) {
                                System.out.println(transHepeng.toStringTran());
                            }
                        }

                    }

                }

            }
        }
    }

    private static long getDifferenceInMinutes(String waktu, LocalDateTime time) {
        return 0;
    }

    public static void tambahAkun(String perintah) {
        String split[] = perintah.split("#");
        boolean cek = true;
        for (Account account : kontainerAccount) {
            if (account.getAccountName().equals(split[2])) {
                cek = false;
                break;
            }
        }
        if (cek == true) {
            Account account = new Account(split[1], split[2]);
            kontainerAccount.add(account);
            System.out.println(account.toString());
        }
    }
}
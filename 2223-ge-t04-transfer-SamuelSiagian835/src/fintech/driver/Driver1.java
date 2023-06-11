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
import java.util.ArrayList;
import java.util.Comparator;
import fintech.model.*;

public class Driver1 {
    private static LinkedList<Account> kontainerAccount;

    public static void main(String[] args) {
        kontainerAccount = new LinkedList<Account>();
        ArrayList<Transaction> makeTran = new ArrayList<Transaction>();

        String perintah;
        Scanner input = new Scanner(System.in);

        while (true) {
            perintah = input.nextLine();        //Membaca input dari user melalui sebuah scanner dengan menggunakan metode nextLine() dan menyimpannya dalam variabel perintah.                
            String pisah[] = perintah.split("#");   //Memecah string perintah yang dibaca menjadi array string menggunakan delimiter "#" dan menyimpannya dalam array pisah.
            if (perintah.equals("---")) {       //Memeriksa apakah nilai dari variabel perintah sama dengan "---". Jika iya, maka program akan keluar dari loop menggunakan kata kunci "break".
                break;
            } else if (perintah.startsWith("create-account")) {     //Memeriksa apakah nilai dari variabel perintah dimulai dengan string "create-account". Jika iya, maka program akan menjalankan metode tambahAkun() dengan parameter nilai variabel perintah.
                tambahAkun(perintah);
            } else if (pisah[0].equals("show-account")) {
                String split[] = perintah.split("#");       //Mendeklarasikan variabel array string split[] yang akan digunakan untuk memecah string input menjadi array berdasarkan delimiter "#".
                String accountName = split[1].toLowerCase();    //Mengambil nama akun dari input yang diberikan dan menyimpannya dalam variabel accountName yang sudah dikonversi ke huruf kecil dengan menggunakan metode toLowerCase
                double balance = 0.0;       //Mendeklarasikan variabel balance dengan nilai awal 0.0. Variabel ini akan digunakan untuk menyimpan saldo akun.
                    for (Account account : kontainerAccount) {      //Melakukan perulangan menggunakan foreach loop untuk setiap objek Account dalam array kontainerAccount.
                    if (account.getAccountName().toLowerCase().equals(accountName)) {       //Di dalam loop, dilakukan pengecekan apakah nama akun yang ditemukan sama dengan nama akun yang diinputkan. Jika ya, maka variabel balance akan diisi dengan nilai saldo dari akun tersebut dengan menggunakan metode getBalance(), dan informasi akun akan dicetak ke layar dengan menggunakan metode toString().
                        balance = account.getBalance();     //
                        System.out.println(account.toString());
                    }
                }
                for (Transaction transaction : makeTran) {
                    if (transaction.getreceinama() != null) {
                        if (transaction.getAccountName().equals(split[1])
                                || transaction.getreceinama().equals(split[1])) {
                            balance += transaction.getAmount();
                            System.out.println(transaction.toStringTran()); 
                        } //Jika ada penerima (receiver), dilakukan pengecekan apakah nama akun yang diberikan sama dengan nama akun yang diinputkan atau nama penerima sama dengan nama akun yang diinputkan. Jika ya, maka saldo dari variabel balance akan ditambah dengan jumlah uang yang ditransaksikan dan informasi transaksi akan dicetak ke layar dengan menggunakan metode toStringTran().
                    } else {
                        if (transaction.getAccountName().equals(split[1])) {
                            balance += transaction.getAmount();
                            System.out.println(transaction.toStringTran());
                        } //Jika tidak ada penerima (receiver), dilakukan pengecekan apakah nama akun yang diberikan sama dengan nama akun yang diinputkan. Jika ya, maka saldo dari variabel balance akan ditambah dengan jumlah uang yang ditransaksikan dan informasi transaksi akan dicetak ke layar dengan menggunakan metode toStringTran().
                    } //Setelah semua objek Account dan Transaction telah diproses, program akan selesai dan output berupa informasi akun dan transaksi akan ditampilkan ke layar.
                }
            } else if (perintah.startsWith("create-transaction")) {
                String split[] = perintah.split("#");   
                if (split.length <= 5) { //Melakukan pengecekan apakah jumlah elemen dalam array split[] kurang dari atau sama dengan 5. Jika ya, maka blok kode berikutnya akan dieksekusi.
            
                    double Hepeng = Double.parseDouble(split[2]); //Mendeklarasikan variabel double Hepeng dan mengkonversi elemen ke-2 dari array split[] menjadi tipe data double dengan menggunakan metode parseDouble().
                    String namaalias = split[1];    //
                    namaalias = namaalias.toLowerCase();
            //Mendeklarasikan variabel string namaalias dan mengisi nilainya dengan elemen pertama dari array split[]. Variabel ini juga dikonversi ke huruf kecil dengan menggunakan metode toLowerCase().
                    for (Account account : kontainerAccount) {  //Melakukan perulangan menggunakan foreach loop untuk setiap objek Account dalam array kontainerAccount.
                        if (account.getAccountName().toLowerCase().equals(namaalias)) { //Di dalam loop, dilakukan pengecekan apakah nama akun yang ditemukan sama dengan nama alias yang diinputkan dan jika iya, maka dilakukan pengecekan apakah saldo akun ditambah dengan jumlah uang yang ditransaksikan akan lebih besar atau sama dengan 0.

                            if (account.getBalance() + Hepeng >= 0) { // add condition to check if balance will be negative
                                account.addSaldo(Hepeng);
                                Transaction transaction = new Transaction(split[1], Hepeng, split[3], split[4]);
                                makeTran.add(transaction);
            //Jika saldo akun cukup, maka saldo akun akan diupdate dengan menambahkan nilai Hepeng menggunakan metode addSaldo(). Selanjutnya, dibuat objek Transaction baru dengan menggunakan nilai dari elemen array split[] dan objek tersebut akan ditambahkan ke dalam array makeTran menggunakan metode add().
                                break;
                            } 
            
                        }
                    }
            
                    Collections.sort(makeTran, new Comparator<Transaction>() {
                        public int compare(Transaction t1, Transaction t2) {
                            return t1.getWaktuTransaksi().compareTo(t2.getWaktuTransaksi());
                        }
                    });
                } else if (split.length > 5) {      //else if (split.length > 5) { -> Awalan kondisi untuk mengecek apakah jumlah kata dalam string split lebih dari 5. Jika benar, blok kode di dalamnya akan dieksekusi.
                    double amount = Double.parseDouble(split[3]);
                    if (amount > 0) {
                        String sender = split[1].toLowerCase();
                        String receiver = split[2].toLowerCase();
                        boolean isSenderExist = false, isReceiverExist = false;
                        for (Account account : kontainerAccount) {
                            if (account.getAccountName().toLowerCase().equals(sender)) {
                                if (account.getBalance() - amount >= 0) { // add condition to check if balance will be negative
                                    account.addSaldo(-amount);
                                    isSenderExist = true;
                                    for (Account account2 : kontainerAccount) {
                                        if (account2.getAccountName().toLowerCase().equals(receiver)) {
                                            account2.addSaldo(amount);
                                            isReceiverExist = true;
            
                                            Transaction transfer = new Transaction(sender, receiver, amount, split[4], split[5]);
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
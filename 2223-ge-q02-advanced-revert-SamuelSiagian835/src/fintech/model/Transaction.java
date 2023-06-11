package fintech.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author 12S21010 Boby Siagian
 * @author 12S21042 Samuel Siagian
 */
public class Transaction {
    private static int nextId = 1;

    public static DateTimeFormatter formatter;

    private int id;
    private String accountName;
    private String targetName;
    private double amount;
    private String postedAt;
    private String note;

    public Transaction(String accountName, double amount, String postedAt, String note) { // untuk
                                                                                          // merepresentasikan
                                                                                          // sebuah
                                                                                          // o                                                                               // transaks
        this.id = nextId++;
        this.accountName = accountName;
        this.targetName = null;
        this.amount = amount;
        this.postedAt = postedAt;
        this.note = note;
    }

    public Transaction(String accountName, String targString, double amount, String postedAt,
            String note) { // untuk merepresentasikan sebuah objek transaks
        this.id = nextId++;
        this.accountName = accountName;
        this.targetName = targString;
        this.amount = amount;
        this.postedAt = postedAt;
        this.note = note;
    }

    public int getId() { // untuk mengembalikan ID atau identitas unik dari sebuah objek transaksi
        return id;
    }

    public String getWaktuTransaksi() { // untuk mengembalikan waktu atau tanggal transaksi dari sebuah objek transaksi
        return postedAt;
    }

    public String getAccountName() { // untuk mengembalikan nama akun dari sebuah objek transaksi
        return accountName;
    }

    public String getreceinama() { // untuk mengembalikan nama akun dari sebuah objek transaksi
        return targetName;
    }

    public double getAmount() { // untuk mengembalikan jumlah uang atau nilai transaksi dari sebuah objek
                                // transaksi
        return amount;
    }

    public String getPostedAt() { // untuk mengembalikan waktu atau tanggal saat transaksi diposting atau
                                  // dilakukan pada sebuah objek transaksi
        return postedAt;
    }

    public String getNote() { // untuk mengembalikan catatan atau keterangan tambahan dari sebuah objek
                              // transaksi
        return note;
    }

    // 2|jaksem|-8.4|2023/02/15 15:18:15|SIM credit
    public String toStringTran() {

        if (targetName == null) {
            return id + "|" + accountName + "|" + amount + "|" + postedAt + "|" + note;

        } else {
            return id + "|" + accountName + "|" + targetName + "|" + amount + "|" + postedAt + "|" + note;
        }

    }

    public boolean isValid() { // untuk memeriksa atau mengecek apakah sebuah objek transaksi (transaction
                               // object) valid atau tidak
        return false;
    }

    public LocalDateTime getTime() { // untuk mengembalikan waktu atau tanggal transaksi
        return null;
    }

    public Object getFromAccount() {
        return null;
    }

    public Object getToAccount() {
        return null;
    }

    public String getTransactionID() {
        return null;
    }

    public String getDeskripsi() {
        return null;
    }

    public char[] toStringTranWithAccountName() {
        return null;
    }

    public int getDescription() {
        return 0;
    }

    public String getSenderName() {
        return accountName;
    }

    public String getRecipientName() {
        return toString();
    }

    public String getTransactionNote() {
        return null;
    }

    public String getMasukan() {
        return note;
    }

}
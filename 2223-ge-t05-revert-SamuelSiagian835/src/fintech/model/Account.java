package fintech.model;

/**
 * @author 12S21010 Boby Siagian
 * @author 12S21042 Samuel Siagian
 */
public class Account {
    private String accountName;
    private String owner;
    private double balance;

    public Account(String accountName, String owner) { //untuk membuat atau menginisialisasi sebuah objek akun
        this.accountName = accountName;
        this.owner = owner;
        this.balance = 0.0;
    }

    public String toString() { //untuk mengembalikan representasi string dari sebuah objek.//
        return owner + "|" + accountName + "|" + String.format("%.1f", balance);
    }

    // getAccountName
    public String getAccountName() { // untuk mengembalikan nama akun dari sebuah objek akun (account object)//
        return owner;
    }

    public void addSaldo(double amount) { // untuk menambahkan saldo atau nilai uang pada sebuah objek akun 
        this.balance += amount;
    }

    public void updateBalance(double parseDouble) { // untuk mengupdate atau memperbarui nilai saldo atau nilai uang pada sebuah objek akun
    }

    public String getOwner() { //untuk mengembalikan nama pemilik atau owner dari sebuah objek akun 
        return null;
    }

    public double getBalance() { //untuk mengembalikan nilai saldo atau nilai uang dari sebuah objek akun 
        return balance;
    }

    public void addTransaction(Transaction transaction) { //untuk menambahkan transaksi atau riwayat transaksi pada sebuah objek akun 
    }

    public void remove(String[] saveAccount) { //untuk menghapus atau menghilangkan satu atau beberapa objek akun
    }

    public double getSaldo() {
        return 0;
    }

    public void setBalance(double d) {
        this.balance = d;
    } 

}
package fintech.driver;
import java.util.Scanner;
import fintech.model.Transaction;

/**
 * @author 12S21010 Bobby Siagian
 * @author 12S21042 Samuel Siagian
 */
public class Driver2 {

    public static void main(String[] _args) {

        Scanner sc = new Scanner(System.in);

        String perintah = sc.nextLine();        //perintah1
        String name = sc.nextLine();            //nama pemilik akun
        String acount = sc.nextLine();          //nama akun
        String perintah2 = sc.nextLine();       //perintah2
        String account2 = sc.nextLine();        //account kedua
       Double amount = sc.nextDouble();         //nilai transaksi
       sc.nextLine();                           //nilai pada line berikutnya
        String posted_at = sc.nextLine();       //waktu transaksi
        String note = sc.nextLine();            //catatan
       

        Transaction transaction = new Transaction (name, acount);                           //menampilkan keluaran pada baris pertama yaitu nama 
        Transaction transaction2 = new Transaction (account2,amount, posted_at, note);
        
        System.out.println(transaction.toString()); 
        System.out.println(transaction2.toString2());

        
        sc.close();

    }

}
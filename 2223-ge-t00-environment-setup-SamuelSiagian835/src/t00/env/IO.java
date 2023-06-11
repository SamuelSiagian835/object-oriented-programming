package t00.env;

import java.util.Scanner;

/**
 * @author 12S21042 Samuel Siagian
 * 
 */

public class IO {
    public static void main(String[] _args) {
    Scanner in=new Scanner(System.in);
    String id, nama;
    id=in.nextLine();
    nama=in.nextLine();

    System.out.println(id+"|"+nama);
    }
}


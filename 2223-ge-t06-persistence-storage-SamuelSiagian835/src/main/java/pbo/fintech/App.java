package pbo.fintech;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import pbo.fintech.model.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private static EntityManagerFactory factory;
    private static EntityManager entityManager;

    public static void main(String[] args) {
        factory = Persistence.createEntityManagerFactory("study_plan_pu");
        entityManager = factory.createEntityManager();
        
        String command;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            command = scanner.nextLine();
            String[] split = command.split("#");

            if (command.equals("---")) {
                cleanTables();
                break;
            } else {
                if (split[0].equals("create-account")) {
                    String username = split[2];
                    String password = split[1];

                    // Periksa apakah akun dengan nama yang sama sudah ada
                    String jpqlCheck = "SELECT COUNT(a) FROM Account a WHERE a.accountName = :username";
                    TypedQuery<Long> queryCheck = entityManager.createQuery(jpqlCheck, Long.class);
                    queryCheck.setParameter("username", username);
                    long count = queryCheck.getSingleResult();

                    if (count > 0) {
                    } else {
                        Account account = new Account(username, password);

                        try {
                            entityManager.getTransaction().begin();
                            entityManager.persist(account);
                            entityManager.flush();
                            entityManager.getTransaction().commit();
                            System.out.println(account.toString());
                        } catch (Exception e) {
                            entityManager.getTransaction().rollback();
                            System.out.println("Failed to create account: " + e.getMessage());
                        }
                    }
                } else if (split[0].equals("show-accounts")) {
                    List<Account> accounts = entityManager.createQuery(
                        "SELECT a FROM Account a ORDER BY a.accountName ASC", Account.class)
                        .getResultList();

                    if (accounts.isEmpty()) {
                    } else {
                        for (Account account : accounts) {
                            System.out.println(account.toString());
                        }
                    }
                }else if (split[0].equals("remove-account")) {
                    String accountName = split[1].toLowerCase();

                    // Periksa apakah akun dengan nama yang dicari ada
                    String jpqlCheck = "SELECT COUNT(a) FROM Account a WHERE a.accountName = :accountName";
                    TypedQuery<Long> queryCheck = entityManager.createQuery(jpqlCheck, Long.class);
                    queryCheck.setParameter("accountName", accountName);
                    long count = queryCheck.getSingleResult();

                    if (count == 0) {
                    } else {
                        try {
                            entityManager.getTransaction().begin();
                            entityManager.createQuery("DELETE FROM Account a WHERE a.accountName = :accountName")
                                .setParameter("accountName", accountName)
                                .executeUpdate();
                            entityManager.getTransaction().commit();
                        } catch (Exception e) {
                            entityManager.getTransaction().rollback();
                            System.out.println("Failed to remove account: " + e.getMessage());
                        }
                    }
                }
            }
        }
        
        entityManager.close();
        factory.close();
    }
        private static void cleanTables() {
            try {
                entityManager.getTransaction().begin();
        
                // Hapus data dari tabel Account
                entityManager.createQuery("DELETE FROM Account a").executeUpdate();
        
                // Tambahkan pembersihan untuk tabel lain di sini (jika ada)
        
                entityManager.getTransaction().commit();

            } catch (Exception e) {
                entityManager.getTransaction().rollback();
            }
        }
    

}

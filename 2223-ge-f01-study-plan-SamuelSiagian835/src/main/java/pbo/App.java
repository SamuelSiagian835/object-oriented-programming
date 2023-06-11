package pbo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import pbo.model.Student;
import pbo.model.Course;
import pbo.model.Enrollments;

import java.util.List;
import java.util.Scanner;

public class App {
    private static EntityManagerFactory factory;
    private static EntityManager entityManager;

    public static void main(String[] args) {
        factory = Persistence.createEntityManagerFactory("StudentPU");
        entityManager = factory.createEntityManager();
    
        String command;
        Scanner scanner = new Scanner(System.in);
    
        while (true) {
            command = scanner.nextLine();
            String[] split = command.split("#");
    
            if (command.equals("---")) {
                cleanTables();
                break;
            } else if (split[0].equals("student-add")) {
                if (split.length < 4) {
                    System.out.println("Invalid command format!");
                    continue;
                }
    
                String nim = split[1];
                String nama = split[2];
                String prodi = split[3];
    
                // Periksa apakah mahasiswa dengan nim yang sama sudah ada
                Student existingStudent = entityManager.find(Student.class, nim);
                if (existingStudent != null) {
                    System.out.println("Student already exists.");
                    continue;
                }
    
                Student student = new Student(nim, nama, prodi);
    
                try {
                    entityManager.getTransaction().begin();
                    entityManager.persist(student);
                    entityManager.flush();
                    entityManager.getTransaction().commit();
                } catch (Exception e) {
                    entityManager.getTransaction().rollback();
                    System.out.println("Failed to add student: " + e.getMessage());
                }
            } else if (split[0].equals("student-show-all")) {
                entityManager.getTransaction().begin();
                TypedQuery<Student> query = entityManager.createQuery("SELECT s FROM Student s ORDER BY s.nim ASC", Student.class);
                List<Student> students = query.getResultList();
                entityManager.getTransaction().commit();
    
                if (students.isEmpty()) {
                    System.out.println("No students found.");
                } else {
                    for (Student student : students) {
                        System.out.println(student.getNim() + "|" + student.getNama() + "|" + student.getProdi());
                    }
                }
            } else if (split[0].equals("course-add")) {
                if (split.length < 5) {
                    System.out.println("Invalid command format!");
                    continue;
                }
    
                String kode = split[1];
                String nama = split[2];
                int semester = Integer.parseInt(split[3]);
                int kredit = Integer.parseInt(split[4]);
    
                // Periksa apakah mata kuliah dengan kode yang sama sudah ada
                Course existingCourse = entityManager.find(Course.class, kode);
                if (existingCourse != null) {
                    System.out.println("Course already exists.");
                    continue;
                }
    
                Course course = new Course(kode, nama, semester, kredit);
    
                try {
                    entityManager.getTransaction().begin();
                    entityManager.persist(course);
                    entityManager.flush();
                    entityManager.getTransaction().commit();
                } catch (Exception e) {
                    entityManager.getTransaction().rollback();
                    System.out.println("Failed to add course: " + e.getMessage());
                }
            } else if (split[0].equals("course-show-all")) {
                entityManager.getTransaction().begin();
                TypedQuery<Course> query = entityManager.createQuery("SELECT c FROM Course c ORDER BY c.semester ASC, c.kode ASC", Course.class);
                List<Course> courses = query.getResultList();
                entityManager.getTransaction().commit();
    
                if (courses.isEmpty()) {
                    System.out.println("No courses found.");
                } else {
                    for (Course course : courses) {
                        System.out.println(course.getKode() + "|" + course.getNama() + "|" + course.getSemester() + "|" + course.getKredit());
                    }
                }
            } else if (split[0].equals("enroll")) {
                if (split.length < 3) {
                    System.out.println("Invalid command format!");
                    continue;
                }
            
                String nim = split[1];
                String kode = split[2];
            
                // Periksa apakah mahasiswa dengan nim yang diberikan ada di database
                Student student = entityManager.find(Student.class, nim);
                if (student == null) {
                    System.out.println("Student not found.");
                    continue;
                }
            
                // Periksa apakah mata kuliah dengan kode yang diberikan ada di database
                Course course = entityManager.find(Course.class, kode);
                if (course == null) {
                    System.out.println("Course not found.");
                    continue;
                }
            
                // Periksa apakah mahasiswa sudah terdaftar di mata kuliah tersebut
                TypedQuery<Enrollments> enrollmentQuery = entityManager.createQuery("SELECT e FROM Enrollments e WHERE e.student = :student AND e.course = :course", Enrollments.class)
                        .setParameter("student", student)
                        .setParameter("course", course);
            
                List<Enrollments> existingEnrollments = enrollmentQuery.getResultList();
            
                if (!existingEnrollments.isEmpty()) {
                    System.out.println("Student is already enrolled in the course.");
                    continue;
                }
            
                Enrollments enrollment = new Enrollments(student, course);
            
                try {
                    entityManager.getTransaction().begin();
                    entityManager.persist(enrollment);
                    entityManager.flush();
                    entityManager.getTransaction().commit();
                } catch (Exception e) {
                    entityManager.getTransaction().rollback();
                    System.out.println("Failed to enroll student: " + e.getMessage());
                }
            } else if (split[0].equals("student-detail")) {
                if (split.length < 2) {
                    System.out.println("Invalid command format!");
                    continue;
                }
            
                String nim = split[1];
            
                // Periksa apakah mahasiswa dengan nim yang diberikan ada di database
                Student student = entityManager.find(Student.class, nim);
                if (student == null) {
                    System.out.println("Student not found.");
                    continue;
                }
            
                System.out.println(student.getNim() + "|" + student.getNama() + "|" + student.getProdi());
            
                TypedQuery<Course> query = entityManager.createQuery("SELECT e.course FROM Enrollments e WHERE e.student = :student ORDER BY e.course.semester ASC, e.course.kode ASC", Course.class)
                        .setParameter("student", student);
                List<Course> enrolledCourses = query.getResultList();
            
                if (enrolledCourses.isEmpty()) {
                    System.out.println("No enrolled courses found.");
                } else {
                    for (Course course : enrolledCourses) {
                        System.out.println(course.getKode() + "|" + course.getNama() + "|" + course.getSemester() + "|" + course.getKredit());
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
    
            entityManager.createQuery("DELETE FROM Enrollment").executeUpdate();
            entityManager.createQuery("DELETE FROM Student").executeUpdate();
            entityManager.createQuery("DELETE FROM Course").executeUpdate();
    
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("Failed to clean tables: " + e.getMessage());
        }
    }
    
}    
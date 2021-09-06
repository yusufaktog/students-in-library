
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joseph
 */

public class Main {

    static Student[] students;
    static Librarian[] librarians;
    static ArrayList<Book> books;
    static BlockingQueue<Student> libraryOrder;

    static final int STUDENT_NUMBER = 40;
    static final int LIBRARIAN_NUMBER = 3;
    static final int BOOK_NUMBER = 6;

    public Main() {
        students = new Student[STUDENT_NUMBER];
        librarians = new Librarian[LIBRARIAN_NUMBER];
        books = new ArrayList<>();
        libraryOrder = new ArrayBlockingQueue<>(STUDENT_NUMBER);

    }
    
    /*Generating necessary objects and adding them into corresponding lists*/
    
    public void generateBooks(int bookNumber) { 
        for (int i = 0; i < bookNumber; i++) {
            books.add(new Book(i + ".Book"));

        }
    }

    public void generateLibrarians(int librarianNumber) {

        for (int i = 0; i < librarianNumber; i++) {
            librarians[i] = new Librarian(i + ".Librarian");

        }
    }

    public void generateStudents(int studentNumber) {

        for (int i = 0; i < studentNumber; i++) {
            students[i] = new Student(i, i + ".Student");
        }

    }

    public void randomizeStudentsOrder() { // randomizes the student order

        for (int i = 0; i < students.length / 2; i++) {
            int randomIndex = ThreadLocalRandom.current().nextInt(students.length);
            Student temp = students[i];
            students[i] = students[randomIndex];
            students[randomIndex] = temp;

        }

    }

    public static boolean isFinished() { // checks all students done reading the necessary books

        for (int i = 0; i < students.length; i++) {

            if (!students[i].allBooksReaded()) {
                return false;
            }

        }
        return true;
    }

    public static void main(String[] args) {
        Main main = new Main();

        main.generateBooks(BOOK_NUMBER);
        main.generateStudents(STUDENT_NUMBER);
        main.generateLibrarians(LIBRARIAN_NUMBER);
        main.randomizeStudentsOrder();
        libraryOrder.addAll(Arrays.asList(students));// generate queue

        for (Thread librarian : librarians) {

            librarian.start();

        }

        for (Librarian librarian : librarians) {
            try {
                librarian.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (isFinished()) {
            System.out.println("All students done reading the necessary books for term project...");
        }

    }
}

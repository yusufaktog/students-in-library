
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author joseph
 */
public class Student {

    private int schoolNumber;
    private String studentName;
    private ArrayList<Book> booksToRead;

    public Student(int schoolNumber, String name) {
        this.schoolNumber = schoolNumber;
        this.studentName = name;
        booksToRead = new ArrayList<>();
        booksToRead.addAll((Main.books)); // each student must have a list of books to read 
    }

    public boolean allBooksReaded() {
        return booksToRead.isEmpty();
    }

    public ArrayList<Book> getBooksToRead() {
        return booksToRead;
    }

    public void setBooksToRead(ArrayList<Book> booksToRead) {
        this.booksToRead = booksToRead;
    }

    public int getSchoolNumber() {
        return schoolNumber;
    }

    public void setSchoolNumber(int schoolNumber) {
        this.schoolNumber = schoolNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void readBook() {

        System.out.println(this.studentName + " is reading book now....");
        sleep();

    }

    public void sleep() {
        int miliseconds = ThreadLocalRandom.current().nextInt(1000);

        try {
            Thread.sleep(miliseconds);
        } catch (InterruptedException ex) {
            java.util.logging.Logger.getLogger(Student.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
}

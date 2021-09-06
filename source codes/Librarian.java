
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joseph
 */
public class Librarian extends Thread implements Runnable {

    private String librarianName;

    public Librarian(String librarianName) {
        this.librarianName = librarianName;
    }

    @Override
    public void run() {

        while (!Main.isFinished()) {
            try {
                takeStudent();
            } catch (NullPointerException e) { // if queue is empty wait for some duration
                try {
                    System.out.println("Queue is empty " + this.librarianName + " is waiting for new students...");
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Librarian.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }

    public void takeStudent() { // takes students from the queue sequentially

        Student student;
        Book book;
        int bookIndex, miliseconds;

        synchronized (Main.libraryOrder) {
            student = Main.libraryOrder.poll();

        }

        //generating a random book and checking whether it is in the library's list
        boolean flag;
        bookIndex = student.getBooksToRead().size() == 1 ? 0 : ThreadLocalRandom.current().nextInt(student.getBooksToRead().size());
        book = student.getBooksToRead().get(bookIndex);

        System.out.println(student.getStudentName() + " wants the " + book.getName() + " from " + this.librarianName);

        synchronized (Main.books) {
            flag = Main.books.contains(book);
            if (flag) {
                Main.books.remove(book);
                student.getBooksToRead().remove(book);
            }

        }

        if (!flag) { // student didn't get the book
            miliseconds = ThreadLocalRandom.current().nextInt(1000);
            System.out.println("The book wanted" + "(" + book.getName() + ")" + "is already borrowed by someone else ");

            System.out.println(student.getStudentName() + " is waiting  " + miliseconds + " ms ");
            try {
                sleep(miliseconds);

            } catch (InterruptedException ex) {
                Logger.getLogger(Librarian.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {// student gets the book
            miliseconds = ThreadLocalRandom.current().nextInt(1000);
            System.out.println(book.getName() + " is assigned to the " + student.getStudentName() + " for " + miliseconds + " miliseconds");
            assingBook(book, student);

            releaseBook(book, student);
            Main.books.add(book);
        }
        synchronized (Main.libraryOrder) { // add student back to the end of the qeueue
            if (!student.allBooksReaded()) {
                Main.libraryOrder.add(student);
            } else {
                System.out.println(student.getStudentName() + " done reading the needed books... ");
            }

        }

    }

    public void releaseBook(Book book, Student student) {
        book.setOwner(null);
        student.getBooksToRead().remove(book);

    }

    public void assingBook(Book book, Student student) {
        book.setOwner(student);
        student.readBook();

    }

    public void checkBookState(Book book) {
        System.out.println(book.getName() + " is belonging to the " + book.getOwner());

    }

    public String getLibrarianName() {
        return librarianName;
    }

    public void setLibrarianName(String librarianName) {
        this.librarianName = librarianName;
    }

}
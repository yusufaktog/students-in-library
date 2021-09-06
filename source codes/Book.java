
/**
 *
 * @author joseph
 */
public class Book {

    private Student owner;
    private String name;

    public Book(String name) {
        this.name = name;
        this.owner = null;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student getOwner() {
        return owner;
    }

    public void setOwner(Student owner) {
        this.owner = owner;
    }

}

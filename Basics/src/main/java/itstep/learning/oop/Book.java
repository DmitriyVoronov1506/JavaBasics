package itstep.learning.oop;

public class Book extends Literature implements Printed {
    private String author;

    public Book(String author, String title) {
        this.author = author;
        super.setTitle(title);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return this.author + ": " + super.toString();
    }
}

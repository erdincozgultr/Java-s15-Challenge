package Media;

import Person.Author;

import java.time.LocalDate;
import java.util.Objects;

public class Journals extends Book {
    private LocalDate publication_date;

    public Journals(long book_ID, Author author, String name, double price, String edition, MediaType mediaType, LocalDate publication_date) {
        super(book_ID, author, name, price, edition, mediaType);
        this.publication_date = publication_date;
    }

    public LocalDate getPublication_date() {
        return publication_date;
    }

    public void setPublication_date(LocalDate localDate) {
        this.publication_date = localDate;
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Publication Date: " + publication_date);
    }

    @Override
    public String toString() {
        return super.toString() +
                ", publication_date=" + publication_date;
    }

}
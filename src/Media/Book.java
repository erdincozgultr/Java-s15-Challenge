package Media;

import Person.Author;
import Person.Reader;
import Person.member_Record;

import java.time.LocalDateTime;
import java.util.Objects;

public class Book {
    private long book_ID;
    private Author author;
    private String name;
    private double price;
    private boolean status;
    private String edition;
    private LocalDateTime date_of_purchase;
    private LocalDateTime date_of_borrow;
    private Reader owner;
    private final MediaType mediaType;

    public Book(long book_ID, Author author, String name, double price, String edition, MediaType mediaType) {
        this.book_ID = book_ID;
        this.author = author;
        this.name = name;
        this.price = price;
        this.edition = edition;
        this.status = true;
        this.mediaType = mediaType;
    }

    public long getBook_ID() {
        return book_ID;
    }

    public Author get_author() {
        return author;
    }

    public String get_title() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public boolean getStatus() {
        return status;
    }

    public String getEdition() {
        return edition;
    }

    public LocalDateTime getDate_of_purchase() {
        return date_of_purchase;
    }

    public LocalDateTime getDate_of_borrow() {
        return date_of_borrow;
    }

    public Reader get_owner() {
        return owner;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.name = title;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }



    public void setDate_of_purchase() {
        this.date_of_purchase = LocalDateTime.now();
    }

    public void setDate_of_borrow() {
        this.date_of_borrow = LocalDateTime.now();
    }

    public void change_owner(Reader reader) {
            this.owner = reader;
            update_status(reader == null);
            System.out.println("Owner is changed for " + get_title());
    }

    public void display() {
        System.out.println("------------------------------------");
        System.out.println("TPYE: " + mediaType);
        System.out.println("ID: " + book_ID);
        System.out.println("Tittle: " + name);
        System.out.println("Author: " + (author != null ? author.getName() : "Unknown"));
        System.out.println("Price: " + price + " TL");
        System.out.println("Edition: " + edition);
        System.out.println("Status: " + (status ? "Available" : "Not Available"));

        if (date_of_purchase != null)
            System.out.println("Purchase date: " + date_of_purchase.toLocalDate());

        System.out.println("Owner: " + (owner != null ? "Temp: " + owner.getName() : "Library"));
    }

    public void update_status(boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;

        if (object == null || object.getClass() != getClass())
            return false;

        Book book = (Book) object;

        return book.book_ID == this.book_ID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(book_ID);
    }

    @Override
    public String toString() {
        return "Book:" +
                " type= " + mediaType +
                ", book_ID= " + book_ID +
                ", author= " + author.getName() +
                ", name='" + name + '\'' +
                ", price= " + price +
                ", edition='" + edition + '\'' +
                ", status= " + status;
    }
}

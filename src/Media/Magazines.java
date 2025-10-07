package Media;

import Person.Author;

import java.util.Objects;

public class Magazines extends Book {
    private String volume;

    public Magazines(long book_ID, Author author, String name, double price, String edition, MediaType mediaType, String volume) {
        super(book_ID, author, name, price, edition, mediaType);
        this.volume = volume;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public void display() {
        super.display();
        System.out.println("volume: " + volume);
    }

    public String toString() {
        return super.toString() +
                ", volume=" + volume;
    }

}
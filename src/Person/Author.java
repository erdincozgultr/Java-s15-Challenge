package Person;

import Core.BookNew;
import Core.BookRemove;
import Core.BookShow;
import Media.Book;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Author extends Person implements BookNew, BookShow, BookRemove {
    private final Set<Book> books = new HashSet<>();

    public Author(String name) {
        super(name);
    }

    public Set<Book> getBooks() {
        return Collections.unmodifiableSet(books);
    }

    @Override
    public String whoyouare() {
        return "Author";
    }

    @Override
    public void new_book(Book book) {
        this.books.add(book);
        System.out.println("New book '" + book.get_title() + "' added for author " + getName());
    }

    @Override
    public void show_book() {
        if (books.isEmpty()) {
            System.out.println("Author has not published any books yet.");
        } else {
            System.out.println("Books by " + getName() + ":");
            for (Book book : books) {
                System.out.println("    - " + book.get_title());
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Author author = (Author) o;
        return Objects.equals(getName(), author.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public void remove_book(Book book) {
        if (this.books.remove(book)) {
            System.out.println("Book '" + book.get_title() + "' removed from author's published list.");
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", books= " + books;
    }
}

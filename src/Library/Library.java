package Library;

import Core.BookNew;
import Core.BookRemove;
import Core.BookShow;
import Media.Book;
import Person.Author;
import Person.Reader;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Library implements BookShow, BookNew, BookRemove {
    private static Library instance;
    private Map<Long, Book> books = new HashMap<>();
    private Map<Long, Reader> readers = new HashMap<>();
    private Map<Long, Book> sales = new HashMap<>();

    private Library() {
    }

    public static Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    public Map<Long, Book> get_books() {
        return Collections.unmodifiableMap(books);
    }

    public Map<Long, Reader> get_reader() {
        return Collections.unmodifiableMap(readers);
    }

    public Map<Long, Book> get_sales() {
        return Collections.unmodifiableMap(sales);
    }

    public void lend_book(Book book, Reader reader) {
        if (reader.getMemberRecord().getMax_book_limit() <= reader.getMemberRecord().getNo_books_issued()) {
            return;
        }
        if (book.getStatus()) {
            reader.borrow_book(book);
            book.change_owner(reader);
            book.setDate_of_borrow();
        } else {
            System.out.println("Book is not available. Current owner: " + (book.get_owner() == null ? "Library" : book.get_owner()));
        }
    }

    public void sell_book(Book book, Reader reader) {
        if (book.getStatus()) {
            reader.getMemberRecord().addToBill(book.getPrice());
            reader.purchase_book(book);
            book.setDate_of_purchase();
            this.sales.put(book.getBook_ID(), book);
            books.remove(book.getBook_ID());
            System.out.println("The purchase of " + reader.getName() + " has been added to the system.");
        }
    }

    public void take_back_book(Book book, Reader reader) {
        if (!reader.getBooks().contains(book)) {
            System.out.println("The reader did not take the book from the library");
            return;
        }

        if (book.getStatus()) {
            System.out.println("Book '" + book.get_title() + "' is already marked as available.");
            return;
        }

        reader.return_book(book);
        book.change_owner(null);

        System.out.println("Book '" + book.get_title() + "' returned by " + reader.getName());
    }

    public boolean buy_back_book(Book book, Reader reader) {
        if (!sales.containsKey(book.getBook_ID()) || book.get_owner() == null || !book.get_owner().equals(reader)) {
            return false;
        }

        books.put(book.getBook_ID(), book);
        sales.remove(book.getBook_ID());

        book.change_owner(null);

        return true;
    }

    @Override
    public void show_book() {
        System.out.println("Books in Library:");
        if (books.isEmpty()) {
            System.out.println("There is no book in the Library");
        } else {
            for (Book book : books.values()) {
                System.out.println("    -" + book.toString());
            }
        }
    }

    @Override
    public void new_book(Book book) {
        this.books.put(book.getBook_ID(), book);
        System.out.println("New book '" + book.get_title() + "' added to the library catalog.");
    }

    public void remove_book(Book book) {
        if (this.books.remove(book.getBook_ID()) != null) {
            Author author = book.get_author();
            if (author != null) {
                author.remove_book(book);
            }
            System.out.println("Book '" + book.get_title() + "' successfully removed from the Library.");
        } else {
            System.out.println("Book '" + book.get_title() + "' was not found in the library catalog.");
        }
    }

    public void add_reader(Reader reader) {
        if (reader != null) {
            this.readers.put(reader.getMemberRecord().get_member(), reader);
            System.out.println("New reader '" + reader.getName() + "' added to the library.");
        }
    }

    @Override
    public String toString() {
        return "Library {" +
                "Total Book Size=" + books.size() +
                ", Total Reader Size=" + readers.size() +
                ", Total Sale Size=" + sales.size() +
                '}';
    }
}

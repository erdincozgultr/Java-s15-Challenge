package Library;

import Media.Book;
import Media.MediaType;
import Person.Author;
import Person.Reader;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Librarian {
    private String name;
    private String password;
    private Library library = Library.getInstance();

    public Librarian(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Book search_book(long bookID) {
        Book book = library.get_books().get(bookID);
        if (book != null) {
            System.out.println("Book found: " + book.get_title());
            System.out.println("\n" + book);
            return book;
        } else {
            System.out.println("Book not found with  ID: " + bookID);
            return null;
        }
    }

    public Map<Long, Book> search_book(String title) {
        Map<Long, Book> foundBooks = new HashMap<>();
        for (Book book : library.get_books().values()) {
            if (book.get_title().equalsIgnoreCase(title)) {
                System.out.println(book);
                foundBooks.put(book.getBook_ID(), book);
            }
        }

        if (foundBooks.isEmpty()) {
            System.out.println("'" + title + "' book not found");
        } else {
            System.out.println(foundBooks.size() + " Book Found.");
        }
        return foundBooks;
    }

    public Map<Long, Book> search_book(Author author) {
        Map<Long, Book> foundBooks = new HashMap<>();
        for (Book book : library.get_books().values()) {
            if (book.get_author().equals(author)) {
                foundBooks.put(book.getBook_ID(), book);
            }
        }

        if (foundBooks.isEmpty()) {
            System.out.println("'" + author.getName() + "' books not found");
        } else {
            System.out.println(foundBooks.size() + " Book Found.");
        }
        return foundBooks;
    }

    public void search_book(MediaType mediaType) {
        long count = library.get_books().values().stream()
                .filter(book -> book.getMediaType().equals(mediaType))
                .peek(Book::display)
                .count();

        if (count == 0) {
            System.out.println("No " + mediaType.toString() + "s found in the library.");
        }
        System.out.println("---------------------------------");
    }


    public boolean verify_member(Reader reader) {
        if (library.get_reader().containsKey(reader.getMemberRecord().get_member())) {
            System.out.println("Member verified successfully: " + reader.getName());
            return true;
        } else {
            System.out.println("Member not found: " + reader.getName());
            return false;
        }
    }


    public void issue_book(Book book, Reader reader) {
        if (verify_member(reader)) {
            library.lend_book(book, reader);
        }
    }

    public double calculate_fine(Reader reader) {
        if (reader.getBooks().isEmpty()) {
            System.out.println("There are no books borrowed by the reader");
            return 0.0;
        }
        Set<Book> books = reader.getBooks();
        double totalFine = 0.0;
        long daysBetween;
        for (Book book : books) {
            daysBetween = ChronoUnit.DAYS.between(book.getDate_of_borrow(), LocalDateTime.now());
            if (daysBetween > 14) {
                totalFine = totalFine + (daysBetween - 14) * 3.50;
            }
        }
        return totalFine;
    }

    public String create_bill(Reader reader) {
        if (reader == null) {
            return "ERROR: Reader object is invalid.";
        }

        double totalBill = reader.getMemberRecord().getBill();

        return "\n--- BILL FOR " + reader.getName().toUpperCase() + " ---\n" +
                "Total Outstanding Bill (Sales + Fines): " + totalBill + " TL\n" +
                "-----------------------------------------\n";
    }

    public void return_book(Book book, Reader reader) {
        double fineAmount = calculate_fine(reader);
        library.take_back_book(book, reader);

        if (fineAmount > 0) {
            reader.getMemberRecord().addToBill(fineAmount);
            System.out.println("Overdue fine of " + fineAmount + " TL added to bill.");
        }

        System.out.println("Book '" + book.get_title() + "' successfully returned by " + reader.getName());
    }

    public void sell_Book(Book book, Reader reader) {
        if (verify_member(reader)) {
            library.sell_book(book, reader);
        }
    }

    public void delete_book(long bookID) {
        Book book = this.search_book(bookID);

        if (book == null) {
            System.out.println("Media with ID " + bookID + " not found.");
            return;
        }

        if (!book.getStatus()) {
            System.out.println("ERROR: Book '" + book.get_title() + "' cannot be deleted.");
            System.out.println("It is currently owned/borrowed by" + book.get_author());
            return;
        }

        library.remove_book(book);
    }

    @Override
    public String toString() {
        return "Librarian{" +
                "name='" + name + '\'' +
                '}';
    }
}

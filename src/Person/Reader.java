package Person;

import Core.BookBorrow;
import Core.BookPurchase;
import Core.BookReturn;
import Core.BookShow;
import Media.Book;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Reader extends Person implements BookPurchase, BookBorrow, BookReturn, BookShow {
    private final Set<Book> books = new HashSet<>();
    private member_Record memberRecord;

    public Reader(String name, member_Record memberRecord) {
        super(name);
        this.memberRecord = memberRecord;
    }

    public Set<Book> getBooks() {
        return Collections.unmodifiableSet(books);
    }

    public member_Record getMemberRecord() {
        return memberRecord;
    }

    @Override
    public void purchase_book(Book book) {
        book.change_owner(this);
    }

    @Override
    public void borrow_book(Book book) {
        this.books.add(book);
        this.memberRecord.inc_book_issued();
        System.out.println("New book '" + book.get_title() + "' borrowed for reader " + getName());
    }

    @Override
    public void return_book(Book book) {
        this.books.remove(book);
        this.memberRecord.dec_book_issued();
        System.out.println("'" + book.get_title() + "' is returned to library");
    }

    @Override
    public void show_book() {
        if (books.isEmpty()) {
            System.out.println("There is no book that the reader '" + getName() + "'borrows");
        } else {
            System.out.println("Books: ");
            for (Book book : books) {
                System.out.println("    -" + book.get_title());
            }
        }
    }

    @Override
    public String whoyouare() {
        return "Reader";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reader reader = (Reader) o;
        return Objects.equals(this.getMemberRecord(), reader.getMemberRecord());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getMemberRecord());
    }

    @Override
    public String toString() {
        return "Reader{" +
                "name='" + getName() + '\'' +
                ", memberId=" + getMemberRecord().get_member() +
                ", books=" + getBooks().size() + "}";
    }
}

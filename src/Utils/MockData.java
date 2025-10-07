package Utils;

import Library.Library;
import Media.Book;
import Media.Journals;
import Media.Magazines;
import Media.MediaType;
import Media.StudyBooks;
import Person.Author;
import Person.MemberType;
import Person.Reader;
import Person.member_Record;

import java.time.LocalDate;

public class MockData {
    public static void initializeData(Library library) {

        // NORMAL BOOK
        Author author = new Author("Brandon Sanderson");
        Book book1 = new Book(101, author, "MISTBORN: THE FINAL EMPIRE", 25.50, "First Edition", MediaType.BOOK);
        Book book2 = new Book(102, author, "MISTBORN: THE WELL OF ASCENSION", 30.00, "Second Edition", MediaType.BOOK);
        Book book3 = new Book(103, author, "MISTBORN: THE HERO OF AGES", 28.00, "First Edition", MediaType.BOOK);
        Book book4 = new Book(104, author, "THE STORMLIGHT ARCHIVE: THE WAY OF KINGS I", 35.00, "Second Edition", MediaType.BOOK);
        Book book5 = new Book(105, author, "THE STORMLIGHT ARCHIVE: THE WAY OF KINGS II", 35.00, "Third Edition", MediaType.BOOK);
        Book book6 = new Book(106, author, "THE STORMLIGHT ARCHIVE: WORDS OF RADIANCE I", 35.00, "First Edition", MediaType.BOOK);
        Book book7 = new Book(107, author, "THE STORMLIGHT ARCHIVE: WORDS OF RADIANCE II", 35.00, "Third Edition", MediaType.BOOK);
        Book book8 = new Book(108, author, "THE STORMLIGHT ARCHIVE: OATHBRINGER I", 35.00, "Second Edition", MediaType.BOOK);
        Book book9 = new Book(109, author, "THE STORMLIGHT ARCHIVE: OATHBRINGER II", 35.00, "First Edition", MediaType.BOOK);
        Book book10 = new Book(110, author, "THE STORMLIGHT ARCHIVE: RHYTHM OF WAR I", 35.00, "Second Edition", MediaType.BOOK);
        Book book11 = new Book(111, author, "THE STORMLIGHT ARCHIVE: RHYTHM OF WAR II", 35.00, "First Edition", MediaType.BOOK);
        Book book12 = new Book(112, author, "THE STORMLIGHT ARCHIVE: WIND AND TRUTH I", 35.00, "Second Edition", MediaType.BOOK);
        Book book13 = new Book(113, author, "THE STORMLIGHT ARCHIVE: WIND AND TRUTH II", 35.00, "Third Edition", MediaType.BOOK);

        // NORMAL BOOK ADD
        library.new_book(book1);
        library.new_book(book2);
        library.new_book(book3);
        library.new_book(book4);
        library.new_book(book5);
        library.new_book(book6);
        library.new_book(book7);
        library.new_book(book8);
        library.new_book(book9);
        library.new_book(book10);
        library.new_book(book11);
        library.new_book(book12);
        library.new_book(book13);

        // NORMAL BOOK AUTHOR ADD
        author.new_book(book1);
        author.new_book(book2);
        author.new_book(book3);
        author.new_book(book4);
        author.new_book(book5);
        author.new_book(book6);
        author.new_book(book7);
        author.new_book(book8);
        author.new_book(book9);
        author.new_book(book10);
        author.new_book(book11);
        author.new_book(book12);
        author.new_book(book13);

        // JOURNAL
        Author journalAuthor = new Author("Cumhuriyet");
        Journals journal1 = new Journals(114, journalAuthor, "Cumhuriyet Journal", 10.00, "Fall 1995", MediaType.JOURNAL, LocalDate.of(1995, 12, 5));
        Journals journal2 = new Journals(115, journalAuthor, "Cumhuriyet Journal", 10.00, "Summer 1972", MediaType.JOURNAL, LocalDate.of(1995, 10, 2));

        // JOURNAL ADD
        library.new_book(journal1);
        library.new_book(journal2);

        // JOURNAL AUTHOR ADD
        journalAuthor.new_book(journal1);
        journalAuthor.new_book(journal2);

        // MAGAZINE
        Author magazineAuthor = new Author("Penguen");
        Magazines magazine1 = new Magazines(116, magazineAuthor, "Penguen Magazine", 18.00, "Spring", MediaType.MAGAZINE, "2015 Vol 2");
        Magazines magazine2 = new Magazines(117, magazineAuthor, "Penguen Magazine", 18.00, "Summer", MediaType.MAGAZINE, "2004 Vol 10");

        // MAGAZINE ADD
        library.new_book(magazine1);
        library.new_book(magazine2);

        // MAGAZINE AUTHOR ADD
        magazineAuthor.new_book(magazine1);
        magazineAuthor.new_book(magazine2);

        // STUDYBOOK
        Author studyBookAuthor = new Author("Abc");
        StudyBooks studyBook1 = new StudyBooks(118, studyBookAuthor, "Step by step Java", 45.00, "First Edition", MediaType.STUDYBOOK, "Java");
        StudyBooks studyBook2 = new StudyBooks(119, studyBookAuthor, "What is React?", 40.00, "Second Edition", MediaType.STUDYBOOK, "React");

        // STUDYBOOK ADD
        library.new_book(studyBook1);
        library.new_book(studyBook2);

        // STUDYBOOK AUTHOR ADD
        studyBookAuthor.new_book(studyBook1);
        studyBookAuthor.new_book(studyBook2);

        // READERS
        member_Record readerRecord1 = new member_Record(1001, MemberType.STUDENT, "Erdinç ÖZGÜL", "123 Main St", "500 500 50 50");
        Reader reader1 = new Reader("Erdinç ÖZGÜL", readerRecord1);
        library.add_reader(reader1);

        member_Record readerRecord2 = new member_Record(1002, MemberType.STUDENT, "OĞUZ ALİŞ", "456 Main St", "500 600 60 60");
        Reader reader2 = new Reader("OĞUZ ALİŞ", readerRecord2);
        library.add_reader(reader2);

        member_Record readerRecord3 = new member_Record(1003, MemberType.STUDENT, "Ersin ÖZTÜRK", "789 Main St", "500 700 70 70");
        Reader reader3 = new Reader("Ersin ÖZTÜRK", readerRecord3);
        library.add_reader(reader3);
    }
}

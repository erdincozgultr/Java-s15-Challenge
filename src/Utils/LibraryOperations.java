package Utils;

import Library.Library;
import Library.Librarian;
import Media.*;
import Person.Author;
import Person.Reader;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LibraryOperations {

    public static void addBookToLibrary(Library library, Scanner scanner) {
        System.out.println("\n************ ADD A NEW BOOK ************");
        System.out.println("Select the type of media you want to add");
        System.out.println("1. Book");
        System.out.println("2. Journal");
        System.out.println("3. Magazine");
        System.out.println("4. Study Book");
        System.out.print("\nEnter your choice: ");

        int type = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nYour choice: " + (type == 1 ? "Book" : type == 2 ? "Journal" : type == 3 ? "Magazine" : "Study Book"));

        System.out.print("Enter ID: ");
        long bookId = scanner.nextLong();
        scanner.nextLine();

        System.out.print("Enter Title: ");
        String title = scanner.nextLine();

        System.out.print("Enter Author Name: ");
        String authorName = scanner.nextLine();
        Author author = new Author(authorName);

        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter Edition: ");
        String edition = scanner.nextLine();

        Book newBook;

        switch (type) {
            case 1:
                newBook = new Book(bookId, author, title, price, edition, MediaType.BOOK);
                break;
            case 2:
                System.out.println("Enter Publishing Date (Year, Month, Day):");
                System.out.print("Year: ");
                int year = scanner.nextInt();
                System.out.print("Month: ");
                int month = scanner.nextInt();
                System.out.print("Day: ");
                int day = scanner.nextInt();
                scanner.nextLine();
                LocalDate date = LocalDate.of(year, month, day);
                newBook = new Journals(bookId, author, title, price, edition, MediaType.JOURNAL, date);
                break;
            case 3:
                System.out.print("Enter Volume: ");
                String volume = scanner.nextLine();
                newBook = new Magazines(bookId, author, title, price, edition, MediaType.MAGAZINE, volume);
                break;
            case 4:
                System.out.print("Enter Lesson: ");
                String lesson = scanner.nextLine();
                newBook = new StudyBooks(bookId, author, title, price, edition, MediaType.STUDYBOOK, lesson);
                break;
            default:
                System.out.println("Invalid media type selected.");
                return;
        }
        System.out.println("\n");

        library.new_book(newBook);
        author.new_book(newBook);
        System.out.println("\n" + newBook.get_title() + " has been added to the library.");
        System.out.println("************ DONE ************");
    }

    public static void searchBook(Librarian librarian, Scanner scanner) {
        System.out.println("\n************ SEARCH BOOK ************");
        System.out.println("Search media by:");
        System.out.println("1. Book ID");
        System.out.println("2. Book Title");
        System.out.println("3. Author");
        System.out.print("\nEnter your choice: ");
        int method = scanner.nextInt();
        scanner.nextLine();

        switch (method) {
            case 1:
                System.out.print("Enter Book ID to search: ");
                long id = scanner.nextLong();
                scanner.nextLine();
                librarian.search_book(id);
                break;
            case 2:
                System.out.print("Enter Book Title to search: ");
                String title = scanner.nextLine();
                librarian.search_book(title);
                break;
            case 3:
                System.out.print("Enter Author to search: ");
                String authorName = scanner.nextLine();
                Author authorToSearch = new Author(authorName);
                librarian.search_book(authorToSearch);
                break;
            default:
                System.out.println("Invalid search method selected.");
                break;
        }

        System.out.println("************ DONE ************");
    }

    public static void updateBook(Librarian librarian, Scanner scanner) {
        System.out.println("\n************ UPDATE BOOK ************");
        System.out.println("Enter the ID of the media you want to change");
        long id = scanner.nextLong();
        scanner.nextLine();
        System.out.println(" ");

        Book foundBook = librarian.search_book(id);
        System.out.println(" ");
        if (foundBook == null) {
            System.out.println("Media with ID " + id + " not found.");
            return;
        }

        System.out.println("Select the information you want to change");
        System.out.println("1. Author");
        System.out.println("2. Title");
        System.out.println("3. Price");
        System.out.println("4. Edition");

        if (foundBook instanceof Journals) {
            System.out.println("5. Publish Date");
        } else if (foundBook instanceof Magazines) {
            System.out.println("6. Volume");
        } else if (foundBook instanceof StudyBooks) {
            System.out.println("7. Lesson");
        }

        int method = scanner.nextInt();
        scanner.nextLine();

        switch (method) {
            case 1:
                System.out.print("Convert [Author] to: ");
                String authorName = scanner.nextLine();
                Author authorChange = new Author(authorName);
                foundBook.setAuthor(authorChange);
                System.out.println("Author is changed to: " + authorName);
                break;
            case 2:
                System.out.print("Convert [Title] to: ");
                String title = scanner.nextLine();
                foundBook.setTitle(title);
                System.out.println("Title is changed to: " + title);
                break;
            case 3:
                System.out.print("Convert [Price] to: ");
                double price = scanner.nextDouble();
                scanner.nextLine();
                foundBook.setPrice(price);
                System.out.println("Price is changed to: " + price);
                break;
            case 4:
                System.out.print("Convert [Edition] to: ");
                String edition = scanner.nextLine();
                foundBook.setEdition(edition);
                System.out.println("Edition is changed to: " + edition);
                break;
            case 5:
                if (foundBook instanceof Journals) {
                    System.out.println("Convert [Publish Date] to: ");
                    System.out.print("Year: ");
                    int year = scanner.nextInt();
                    System.out.print("Month: ");
                    int month = scanner.nextInt();
                    System.out.print("Day: ");
                    int day = scanner.nextInt();
                    scanner.nextLine();
                    LocalDate date = LocalDate.of(year, month, day);
                    ((Journals) foundBook).setPublication_date(date);
                    System.out.println("Publish Date is changed to: " + date);
                } else {
                    System.out.println("Invalid change method selected.");
                }
                break;
            case 6:
                if (foundBook instanceof Magazines) {
                    System.out.print("Convert [Volume] to: ");
                    String volume = scanner.nextLine();
                    ((Magazines) foundBook).setVolume(volume);
                    System.out.println("Volume is changed to: " + volume);
                } else {
                    System.out.println("Invalid change method selected.");
                }
                break;
            case 7:
                if (foundBook instanceof StudyBooks) {
                    System.out.print("Convert [Lesson] to: ");
                    String lesson = scanner.nextLine();
                    ((StudyBooks) foundBook).setLesson(lesson);
                    System.out.println("Lesson is changed to: " + lesson);
                } else {
                    System.out.println("Invalid change method selected.");
                }
                break;
            default:
                System.out.println("Invalid change method selected.");
                break;
        }
        System.out.println("************ DONE ************");
    }

    public static void removeBook(Librarian librarian, Scanner scanner) {
        System.out.println("\n************ REMOVE BOOK ************");
        System.out.println("Enter the ID of the book you want to remove: ");
        long id = scanner.nextLong();
        librarian.delete_book(id);
        System.out.println("\n************ DONE ************");
    }

    public static void mediaList(Librarian librarian, Scanner scanner) {
        System.out.println("\n************ MEDIA LIST ************");
        System.out.println("Select the media type you want to list");
        System.out.println("1. Books");
        System.out.println("2. Journals");
        System.out.println("3. Magazines");
        System.out.println("4. Study Books");
        int type = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nYour choice: " + (type == 1 ? "Book" : type == 2 ? "Journal" : type == 3 ? "Magazine" : "Study Book"));
        switch (type) {
            case 1:
                librarian.search_book(MediaType.BOOK);
                break;
            case 2:
                librarian.search_book(MediaType.JOURNAL);
                break;
            case 3:
                librarian.search_book(MediaType.MAGAZINE);
                break;
            case 4:
                librarian.search_book(MediaType.STUDYBOOK);
                break;
            default:
                System.out.println("Invalid type selected.");
                break;
        }
        System.out.println("\n************ DONE ************");
    }

    public static void authorList(Librarian librarian, Scanner scanner) {
        System.out.println("\n************ AUTHOR'S BOOK ************");
        System.out.print("Enter Author's name to search: ");
        String authorName = scanner.nextLine();
        Author authorToSearch = new Author(authorName);

        Map<Long, Book> foundBooks = librarian.search_book(authorToSearch);

        if (!foundBooks.isEmpty()) {
            foundBooks.values().stream()
                    .forEach(Book::display);
        }

        System.out.println("\n************ DONE ************");
    }

    public static void lendBook(Librarian librarian, Scanner scanner, Library library) {
        System.out.println("\n************ LEND BOOK ************");
        System.out.println("Enter the reader's ID");
        long id = scanner.nextLong();
        scanner.nextLine();

        Reader reader = library.get_reader().get(id);

        System.out.println("Enter the ID of the book to be given");
        long bookID = scanner.nextLong();
        scanner.nextLine();

        Book book = librarian.search_book(bookID);
        librarian.issue_book(book, reader);
        System.out.println("\n************ DONE ************");
    }

    public static void returnBook(Librarian librarian, Scanner scanner, Library library) {
        System.out.println("\n************ RETURN BOOK ************");
        System.out.println("Enter the reader's ID");
        long id = scanner.nextLong();
        scanner.nextLine();

        Reader reader = library.get_reader().get(id);

        System.out.println("Enter the ID of the book to be given");
        long bookID = scanner.nextLong();
        scanner.nextLine();

        Book book = librarian.search_book(bookID);
        librarian.return_book(book, reader);
        System.out.println("\n************ DONE ************");
    }

    public static void sellBook(Librarian librarian, Scanner scanner, Library library) {
        System.out.println("\n************ SELL BOOK ************");
        System.out.println("Enter the reader's ID");
        long readerID = scanner.nextLong();
        scanner.nextLine();

        Reader reader = library.get_reader().get(readerID);
        if (reader == null) {
            System.out.println("ERROR: Reader not found with ID: " + readerID);
            return;
        }

        if (!librarian.verify_member(reader)) {
            return;
        }

        System.out.println("Enter the number of books to sell.");
        int numberToSell = scanner.nextInt();
        scanner.nextLine();

        if (numberToSell <= 0) {
            System.out.println("Sale cancelled. Number of books must be greater than zero.");
            return;
        }

        Map<Long, Book> booksToSell = new HashMap<>();

        for (int i = 0; i < numberToSell; i++) {

            System.out.println("--- Book " + (i + 1) + " of " + numberToSell + " ---");
            System.out.println("Enter the book's ID");
            long bookID = scanner.nextLong();
            scanner.nextLine();

            Book book = librarian.search_book(bookID);

            if (book == null || !book.getStatus() || booksToSell.containsKey(bookID)) {
                System.out.println("Book is not available");
                continue;
            }

            booksToSell.put(bookID, book);
        }

        if (booksToSell.isEmpty()) {
            System.out.println("No valid books were entered for sale. Sale cancelled.");
            return;
        }

        System.out.println("\n--- Processing Sale for " + booksToSell.size() + " Book(s) ---");
        booksToSell.forEach((soldID, soldBook) -> librarian.sell_Book(soldBook, reader));


        String billSummary = librarian.create_bill(reader);
        System.out.println(billSummary);

        payBill(scanner, reader);
        System.out.println("\n************ DONE ************");
    }

    public static void payBill(Scanner scanner, Reader reader) {
        System.out.println("\n************ PAY BILL ************");
        double currentBill = reader.getMemberRecord().getBill();

        if (currentBill <= 0) {
            System.out.println("INFO: The reader has no outstanding bill.");
            return;
        }

        System.out.println("\n--- PAYMENT SECTION ---");
        System.out.println("Total Outstanding Bill: " + currentBill + " TL");
        System.out.println("Enter the amount paid by the reader (Enter 0.0 for no payment):");

        double paidMoney = scanner.nextDouble();
        scanner.nextLine();

        if (paidMoney < 0.0) {
            System.out.println("ERROR: Payment amount cannot be negative. No payment processed.");
            return;
        }

        if (paidMoney > 0.0) {
            reader.getMemberRecord().pay_bill(paidMoney);
        } else {
            System.out.println("Payment skipped. Total outstanding bill remains: " + currentBill + " TL");
        }
        System.out.println("\n************ DONE ************");
    }

    public static void handlePayment(Librarian librarian, Scanner scanner, Library library) {
        System.out.println("\n************ PAYMENT ************");
        System.out.println("Enter the reader's ID to check the bill:");
        long readerID = scanner.nextLong();
        scanner.nextLine();

        Reader reader = library.get_reader().get(readerID);
        if (reader == null) {
            System.out.println("ERROR: Reader not found with ID: " + readerID);
            return;
        }

        String billSummary = librarian.create_bill(reader);
        System.out.println(billSummary);

        payBill(scanner, reader);
        System.out.println("\n************ DONE ************");
    }

    public static void buyBackBook(Librarian librarian, Scanner scanner, Library library) {
        System.out.println("\n************ BUY BACK BOOK ************");
        System.out.println("Enter the ID of the reader who originally purchased the book:");
        long readerID = scanner.nextLong();
        scanner.nextLine();

        Reader reader = library.get_reader().get(readerID);
        if (reader == null || !librarian.verify_member(reader)) {
            System.out.println("ERROR: Reader not found or verification failed.");
            return;
        }

        System.out.println("Enter the ID of the book to be bought back:");
        long bookID = scanner.nextLong();
        scanner.nextLine();

        Book bookToBuyBack = library.get_sales().get(bookID);

        if (bookToBuyBack == null) {
            System.out.println("ERROR: Book ID " + bookID + " was not found in the sales record.");
            return;
        }

        double purchasePrice = bookToBuyBack.getPrice();
        System.out.println("Original Purchase Price was: " + purchasePrice + " TL.");
        System.out.print("Enter the buyback (refund) amount to be paid to the reader: ");
        double refundAmount = scanner.nextDouble();
        scanner.nextLine();

        if (refundAmount <= 0) {
            System.out.println("ERROR: Refund amount must be greater than zero. Buyback cancelled.");
            return;
        }

        boolean success = library.buy_back_book(bookToBuyBack, reader);

        if (success) {

            System.out.println("\nSUCCESS: Book '" + bookToBuyBack.get_title() + "' bought back successfully.");
            System.out.println("The amount of " + refundAmount + " TL has been paid to the reader.");
        } else {
            System.out.println("\nFAILURE: Buyback failed. Check book ownership.");
        }
        System.out.println("\n************ DONE ************");
    }
}
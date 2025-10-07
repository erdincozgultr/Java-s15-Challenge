import Library.Library;
import Library.Librarian;
import Media.*;
import Person.Author;
import Person.MemberType;
import Person.Reader;
import Person.member_Record;
import Utils.LibraryOperations;
import Utils.MockData;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    private static final Library library = Library.getInstance();
    private static final Librarian librarian = new Librarian("John Doe", "123456");
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        MockData.initializeData(library);

        System.out.println("Library Management system running...");
        boolean isRunning = true;
        while (isRunning) {
            displayMenu();
            try {
                int userChoise = scanner.nextInt();
                scanner.nextLine();
                switch (userChoise) {
                    case 1:
                        LibraryOperations.addBookToLibrary(library, scanner);
                        break;
                    case 2:
                        LibraryOperations.searchBook(librarian, scanner);
                        break;
                    case 3:
                        LibraryOperations.updateBook(librarian, scanner);
                        break;
                    case 4:
                        LibraryOperations.removeBook(librarian, scanner);
                        break;
                    case 5:
                        LibraryOperations.mediaList(librarian, scanner);
                        break;
                    case 6:
                        LibraryOperations.authorList(librarian, scanner);
                        break;
                    case 7:
                        LibraryOperations.lendBook(librarian, scanner, library);
                        break;
                    case 8:
                        LibraryOperations.returnBook(librarian, scanner, library);
                        break;
                    case 9:
                        LibraryOperations.sellBook(librarian, scanner, library);
                        break;
                    case 10:
                        LibraryOperations.buyBackBook(librarian, scanner, library);
                        break;
                    case 11:
                        LibraryOperations.handlePayment(librarian, scanner, library);
                        break;
                    case 0:
                        isRunning = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input. Please enter a number.");
                scanner.nextLine();
            }
        }
        System.out.println("Application closing!");
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Add a new book to the library");
        System.out.println("2. Search for a book");
        System.out.println("3. Update book information");
        System.out.println("4. Remove book from Library");
        System.out.println("5. List media by types");
        System.out.println("6. List media by Author");
        System.out.println("7. Assign books to the reader");
        System.out.println("8. Get book back from reader");
        System.out.println("9. Sell books to the reader");
        System.out.println("10. Buy back books from reader");
        System.out.println("11. Handle payment");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

}
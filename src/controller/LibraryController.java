package controller;

import model.*;
import service.LibrarianService;
import service.ReaderService;
import service.ServiceResult;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class LibraryController {
    private final LibrarianService librarianService;
    private final ReaderService readerService;
    private final Scanner scanner = new Scanner(System.in);

    public LibraryController(LibrarianService librarianService, ReaderService readerService) {
        this.librarianService = librarianService;
        this.readerService = readerService;
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            printMenu();
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> handleAddBook();
                case "2" -> handleListBooks();
                case "3" -> handleSearchBook();
                case "4" -> handleUpdateBook();
                case "5" -> handleRemoveBook();
                case "6" -> handleListBooksByType();
                case "7" -> handleListBooksByAuthor();
                case "8" -> handleBorrowBook();
                case "9" -> handleReturnBook();
                case "10" -> handleCreateBill();
                case "11" -> handleAddMember();
                case "12" -> handleListMembers();
                case "13" -> handleListBorrowedBooks();
                case "0" -> exit = true;
                default -> System.out.println("Invalid choice");
            }
        }
    }

    private void printMenu() {
        System.out.println("""
1-Add Book
2-List Books
3-Search Book
4-Update Book
5-Remove Book
6-List Books By Type
7-List Books By Author
8-Borrow Book
9-Return Book
10-Create Bill
11-Add Member
12-List Members
13-List Borrowed Books
0-Exit""");
        System.out.print("Choice: ");
    }

    private void handleAddBook() {
        System.out.print("Book ID: ");
        String id = scanner.nextLine();
        System.out.print("Author name: ");
        String authorName = scanner.nextLine();
        Author author = new Author(authorName);
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Price: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("Status (AVAILABLE/ISSUED): ");
        BookStatus status = BookStatus.valueOf(scanner.nextLine().trim().toUpperCase());
        System.out.print("Edition: ");
        String edition = scanner.nextLine();
        System.out.print("Type (STUDYBOOK/JOURNAL/MAGAZINE): ");
        BookType type = BookType.valueOf(scanner.nextLine().trim().toUpperCase());

        Book book = new Book(id, author, title, price, status, edition, type);
        ServiceResult result = librarianService.addBook(book);
        System.out.println(result.getMessage());
    }

    private void handleListBooks() {
        List<Book> books = librarianService.listAllBooks();
        if (books.isEmpty()) {
            System.out.println("No books");
            return;
        }
        books.forEach(b -> System.out.println(b.getBookId() + " - " + b.getTitle() + " (" + b.getStatus() + ")"));
    }

    private void handleBorrowBook() {
        System.out.print("Member ID: ");
        String memberId = scanner.nextLine();
        System.out.print("Book ID: ");
        String bookId = scanner.nextLine();
        ServiceResult result = librarianService.issueBook(bookId, memberId);
        System.out.println(result.getMessage());
    }

    private void handleReturnBook() {
        System.out.print("Member ID: ");
        String memberId = scanner.nextLine();
        System.out.print("Book ID: ");
        String bookId = scanner.nextLine();
        ServiceResult result = librarianService.returnBook(bookId, memberId);
        System.out.println(result.getMessage());
    }

    private void handleAddMember() {
        System.out.print("Reader name: ");
        String name = scanner.nextLine();
        System.out.print("Member ID: ");
        String memberId = scanner.nextLine();
        System.out.print("Address: ");
        String address = scanner.nextLine();
        System.out.print("Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Type (student/faculty): ");
        String type = scanner.nextLine().trim().toLowerCase();
        LocalDate joinDate = LocalDate.now();
        MemberRecord record = type.startsWith("f")
                ? new Faculty(memberId, joinDate, name, address, phone)
                : new Student(memberId, joinDate, name, address, phone);
        Reader reader = new Reader(name, record);
        librarianService.registerReader(reader);
        System.out.println("Reader registered");
    }

    private void handleListMembers() {
        List<Reader> readers = librarianService.listAllReaders();
        if (readers.isEmpty()) {
            System.out.println("No readers");
            return;
        }
        readers.forEach(r -> System.out.println(r.whoYouAre()));
    }

    private void handleSearchBook() {
        System.out.print("Search by (1-ID, 2-Title): ");
        String option = scanner.nextLine();
        if ("1".equals(option)) {
            System.out.print("Book ID: ");
            String id = scanner.nextLine();
            librarianService.searchBookById(id)
                    .ifPresentOrElse(
                            b -> System.out.println(b.getBookId() + " - " + b.getTitle()),
                            () -> System.out.println("Book not found"));
        } else if ("2".equals(option)) {
            System.out.print("Title: ");
            String title = scanner.nextLine();
            List<Book> books = librarianService.searchBooksByTitle(title);
            if (books.isEmpty()) {
                System.out.println("No books found");
            } else {
                books.forEach(b -> System.out.println(b.getBookId() + " - " + b.getTitle()));
            }
        } else {
            System.out.println("Invalid option");
        }
    }

    private void handleUpdateBook() {
        System.out.print("Book ID to update: ");
        String id = scanner.nextLine();
        System.out.print("New Title: ");
        String title = scanner.nextLine();
        System.out.print("New Price: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("New Edition: ");
        String edition = scanner.nextLine();
        System.out.print("New Type (STUDYBOOK/JOURNAL/MAGAZINE): ");
        BookType type = BookType.valueOf(scanner.nextLine().trim().toUpperCase());
        ServiceResult result = librarianService.updateBook(id, title, price, edition, type);
        System.out.println(result.getMessage());
    }

    private void handleRemoveBook() {
        System.out.print("Book ID to remove: ");
        String id = scanner.nextLine();
        ServiceResult result = librarianService.deleteBook(id);
        System.out.println(result.getMessage());
    }

    private void handleListBooksByType() {
        System.out.print("Type (STUDYBOOK/JOURNAL/MAGAZINE): ");
        BookType type = BookType.valueOf(scanner.nextLine().trim().toUpperCase());
        List<Book> books = librarianService.listBooksByType(type);
        if (books.isEmpty()) {
            System.out.println("No books for type");
        } else {
            books.forEach(b -> System.out.println(b.getBookId() + " - " + b.getTitle()));
        }
    }

    private void handleListBooksByAuthor() {
        System.out.print("Author name: ");
        String author = scanner.nextLine();
        List<Book> books = librarianService.listBooksByAuthor(author);
        if (books.isEmpty()) {
            System.out.println("No books for author");
        } else {
            books.forEach(b -> System.out.println(b.getBookId() + " - " + b.getTitle()));
        }
    }

    private void handleCreateBill() {
        System.out.print("Book ID: ");
        String id = scanner.nextLine();
        librarianService.searchBookById(id)
                .ifPresentOrElse(
                        b -> System.out.println("Bill amount: " + b.getPrice()),
                        () -> System.out.println("Book not found"));
    }

    private void handleListBorrowedBooks() {
        System.out.print("Member ID: ");
        String memberId = scanner.nextLine();
        Set<Book> books = readerService.listBorrowedBooks(memberId);
        if (books.isEmpty()) {
            System.out.println("No borrowed books");
        } else {
            books.forEach(b -> System.out.println(b.getBookId() + " - " + b.getTitle()));
        }
    }
}
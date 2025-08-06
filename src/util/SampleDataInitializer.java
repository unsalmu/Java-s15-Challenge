package util;

import model.*;
import repository.InMemoryLibraryRepository;
import service.LibrarianService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class SampleDataInitializer {

    public static void initializeData(InMemoryLibraryRepository repository, LibrarianService librarianService) {
        // Initialize books
        createSampleBooks(repository);

        // Initialize readers
        createSampleReaders(librarianService);
    }

    private static void createSampleBooks(InMemoryLibraryRepository repository) {
        List<Book> books = Arrays.asList(
                new Book("B001", new Author("John Smith"), "Introduction to Java", 29.99, BookStatus.AVAILABLE, "3rd", BookType.STUDYBOOK),
                new Book("B002", new Author("Emily Davis"), "Data Structures", 34.50, BookStatus.AVAILABLE, "2nd", BookType.STUDYBOOK),
                new Book("B003", new Author("Michael Brown"), "Algorithms", 45.00, BookStatus.AVAILABLE, "5th", BookType.STUDYBOOK),
                new Book("B004", new Author("Sarah Wilson"), "Database Systems", 38.75, BookStatus.AVAILABLE, "4th", BookType.STUDYBOOK),
                new Book("B005", new Author("David Lee"), "Software Engineering", 42.25, BookStatus.AVAILABLE, "1st", BookType.STUDYBOOK),
                new Book("B006", new Author("Science Today"), "Recent Discoveries", 12.99, BookStatus.AVAILABLE, "May 2023", BookType.JOURNAL),
                new Book("B007", new Author("Tech Monthly"), "AI Trends", 9.99, BookStatus.AVAILABLE, "June 2023", BookType.MAGAZINE),
                new Book("B008", new Author("Research Group"), "Physics Journal", 15.50, BookStatus.AVAILABLE, "Vol 45", BookType.JOURNAL),
                new Book("B009", new Author("Computer World"), "Programming Digest", 8.75, BookStatus.AVAILABLE, "July 2023", BookType.MAGAZINE),
                new Book("B010", new Author("Academic Press"), "Mathematics Review", 18.25, BookStatus.AVAILABLE, "Vol 22", BookType.JOURNAL)
        );

        books.forEach(repository::saveBook);
    }

    private static void createSampleReaders(LibrarianService librarianService) {
        // Create 5 students
        List<Reader> students = Arrays.asList(
                new Reader("Alice Johnson", new Student("S001", LocalDate.now().minusMonths(3), "Alice Johnson", "123 Campus Dr", "555-1234")),
                new Reader("Bob Williams", new Student("S002", LocalDate.now().minusMonths(6), "Bob Williams", "456 College Ave", "555-2345")),
                new Reader("Carol Martinez", new Student("S003", LocalDate.now().minusYears(1), "Carol Martinez", "789 University Blvd", "555-3456")),
                new Reader("Daniel Taylor", new Student("S004", LocalDate.now().minusDays(45), "Daniel Taylor", "101 Student St", "555-4567")),
                new Reader("Eva Garcia", new Student("S005", LocalDate.now().minusWeeks(8), "Eva Garcia", "202 Scholar Ln", "555-5678"))
        );

        // Create 5 faculty members
        List<Reader> faculty = Arrays.asList(
                new Reader("Prof. Frank Robinson", new Faculty("F001", LocalDate.now().minusYears(5), "Frank Robinson", "303 Faculty Row", "555-6789")),
                new Reader("Dr. Grace Lee", new Faculty("F002", LocalDate.now().minusYears(3), "Grace Lee", "404 Professor Ave", "555-7890")),
                new Reader("Prof. Henry Wilson", new Faculty("F003", LocalDate.now().minusYears(8), "Henry Wilson", "505 Academic Ct", "555-8901")),
                new Reader("Dr. Isabella Brown", new Faculty("F004", LocalDate.now().minusYears(2), "Isabella Brown", "606 Research Dr", "555-9012")),
                new Reader("Prof. James Miller", new Faculty("F005", LocalDate.now().minusMonths(18), "James Miller", "707 Teaching Ln", "555-0123"))
        );

        // Register all readers
        students.forEach(librarianService::registerReader);
        faculty.forEach(librarianService::registerReader);
    }
}
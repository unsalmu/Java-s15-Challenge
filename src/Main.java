import controller.LibraryController;
import model.*;
import repository.InMemoryLibraryRepository;
import service.LibrarianService;
import service.ReaderService;
import validation.ReaderValidator;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        InMemoryLibraryRepository repository = new InMemoryLibraryRepository();
        BookValidator bookValidator = new BookValidator(repository);
        ReaderValidator readerValidator = new ReaderValidator(repository);
        ReaderService readerService = new ReaderService(repository, readerValidator);
        LibrarianService librarianService = new LibrarianService(repository, bookValidator, readerService);
        LibraryController controller = new LibraryController(librarianService, readerService);

        // Preload sample data
        Author author = new Author("Author One");
        Book book = new Book("B001", author, "Sample Book", 10.0, BookStatus.AVAILABLE, "1st", BookType.STUDYBOOK);
        librarianService.addBook(book);
        MemberRecord record = new Student("S001", LocalDate.now(), "Alice", "", "");
        Reader reader = new Reader("Alice", record);
        librarianService.registerReader(reader);

        controller.run();
    }
}
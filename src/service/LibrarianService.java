package service;

import model.Book;
import model.BookType;
import model.Reader;
import repository.InMemoryLibraryRepository;
import validation.BookValidator;
import validation.ReaderValidator;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

public class LibrarianService {
    private static final double FINE_PER_DAY = 1.0;
    private final InMemoryLibraryRepository repository;
    private final BookValidator bookValidator;
    private final ReaderService readerService;
    private final ReaderValidator readerValidator;

    public LibrarianService(InMemoryLibraryRepository repository,
                            BookValidator bookValidator,
                            ReaderService readerService, ReaderValidator readerValidator) {
        this.repository = repository;
        this.bookValidator = bookValidator;
        this.readerService = readerService;
        this.readerValidator = readerValidator;
    }

    public ServiceResult addBook(Book book) {
        Optional<String> validation = bookValidator.validateForCreate(book);
        if (validation.isPresent()) {
            return new ServiceResult(false, validation.get());
        }
        repository.saveBook(book);
        return new ServiceResult(true, "Book added");
    }

    public ServiceResult updateBook(String bookId, String title, double price,
                                    String edition, BookType type) {
        Optional<Book> opt = repository.findBookById(bookId);
        if (opt.isEmpty()) {
            return new ServiceResult(false, "Book not found");
        }
        Book book = opt.get();
        book.setTitle(title);
        book.setPrice(price);
        book.setEdition(edition);
        book.setType(type);
        return new ServiceResult(true, "Book updated");
    }

    public ServiceResult deleteBook(String bookId) {
        boolean removed = repository.deleteBook(bookId);
        return removed
                ? new ServiceResult(true, "Book removed")
                : new ServiceResult(false, "Book not found");
    }

    public Optional<Book> searchBookById(String bookId) {
        return repository.findBookById(bookId);
    }

    public List<Book> searchBooksByTitle(String title) {
        return repository.findBooksByTitle(title);
    }

    public List<Book> listBooksByType(BookType type) {
        return repository.findBooksByType(type);
    }

    public List<Book> listBooksByAuthor(String authorName) {
        return repository.findBooksByAuthor(authorName);
    }

    public ServiceResult issueBook(String bookId, String memberId) {
        return readerService.borrowBook(memberId, bookId);
    }

    public ServiceResult returnBook(String bookId, String memberId) {
        return readerService.returnBook(memberId, bookId);
    }

    public List<Book> listAllBooks() {
        return repository.findAllBooks();
    }

    public void registerReader(Reader reader) {
        repository.saveReader(reader);
    }

    public List<Reader> listAllReaders() {
        return repository.findAllReaders();
    }



    /* ctor'a ReaderValidator'ı enjekte etmeyi unutmayın */

    public ServiceResult calculateFine(String memberId, String bookId) {

        Optional<String> err = readerValidator.validateFine(memberId, bookId);
        if (err.isPresent()) {
            return new ServiceResult(false, err.get());   // ➜ hata mesajı
        }


        // Artık doğrulandı—kitap gerçekten bu üyede
        Reader reader = repository.findReaderById(memberId).get();
        Book   book   = repository.findBookById(bookId).get();

        long overdueDays = ChronoUnit.DAYS
                .between(book.getBorrowedDate(), LocalDate.now()) - 14;

        double fine = overdueDays > 0 ? overdueDays * FINE_PER_DAY : 0;

        String msg = fine > 0
                ? String.format("Overdue fine: %.2f$", fine)
                : "No overdue fine.";

        return new ServiceResult(true, msg);
    }

}
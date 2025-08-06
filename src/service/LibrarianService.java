package service;

import model.Book;
import model.BookType;
import model.Reader;
import repository.InMemoryLibraryRepository;

import java.util.List;
import java.util.Optional;

public class LibrarianService {
    private final InMemoryLibraryRepository repository;
    private final BookValidator bookValidator;
    private final ReaderService readerService;

    public LibrarianService(InMemoryLibraryRepository repository,
                            BookValidator bookValidator,
                            ReaderService readerService) {
        this.repository = repository;
        this.bookValidator = bookValidator;
        this.readerService = readerService;
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
}

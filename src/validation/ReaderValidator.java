package validation;

import model.Book;
import model.BookStatus;
import model.Reader;
import repository.InMemoryLibraryRepository;

import java.util.Optional;

public class ReaderValidator {
    private final InMemoryLibraryRepository repository;

    public ReaderValidator(InMemoryLibraryRepository repository) {
        this.repository = repository;
    }

    public Optional<String> validateBorrow(String memberId, String bookId) {
        Reader reader = repository.findReaderById(memberId).orElse(null);
        if (reader == null) {
            return Optional.of("Reader not found");
        }
        Book book = repository.findBookById(bookId).orElse(null);
        if (book == null) {
            return Optional.of("Book not found");
        }
        if (book.getStatus() != BookStatus.AVAILABLE) {
            return Optional.of("Book is not available");
        }
        if (reader.hasBook(book)) {
            return Optional.of("Reader already has this book");
        }
        if (reader.getRecord().getNoBooksIssued() >= reader.getRecord().getMaxBookLimit()) {
            return Optional.of("Reader exceeded book limit");
        }
        return Optional.empty();
    }

    public Optional<String> validateReturn(String memberId, String bookId) {
        Reader reader = repository.findReaderById(memberId).orElse(null);
        if (reader == null) {
            return Optional.of("Reader not found");
        }
        Book book = repository.findBookById(bookId).orElse(null);
        if (book == null) {
            return Optional.of("Book not found");
        }
        if (!reader.hasBook(book)) {
            return Optional.of("Reader does not have this book");
        }
        return Optional.empty();
    }

    public Optional<String> validateFine(String memberId, String bookId) {
        Reader reader = repository.findReaderById(memberId).orElse(null);
        if (reader == null)           return Optional.of("Reader not found");

        Book book = repository.findBookById(bookId).orElse(null);
        if (book == null)             return Optional.of("Book not found");

        if (!reader.hasBook(book))    return Optional.of("Reader never borrowed this book");

        return Optional.empty();      // her şey yolunda
    }
}
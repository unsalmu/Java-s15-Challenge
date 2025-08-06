package validation;

import model.Book;
import repository.InMemoryLibraryRepository;

import java.util.Optional;

public class BookValidator {
    private final InMemoryLibraryRepository repository;

    public BookValidator(InMemoryLibraryRepository repository) {
        this.repository = repository;
    }

    public Optional<String> validateForCreate(Book book) {
        if (book == null) {
            return Optional.of("Book cannot be null");
        }
        if (CommonValidations.isBlank(book.getBookId())) {
            return Optional.of("Book ID cannot be blank");
        }
        if (CommonValidations.isBlank(book.getTitle())) {
            return Optional.of("Book title cannot be blank");
        }
        if (book.getPrice() < 0) {
            return Optional.of("Price cannot be negative");
        }
        if (book.getEdition() == null) {
            return Optional.of("Edition cannot be null");
        }
        if (book.getType() == null) {
            return Optional.of("Type cannot be null");
        }
        if (book.getStatus() == null) {
            return Optional.of("Status cannot be null");
        }
        if (repository.findBookById(book.getBookId()).isPresent()) {
            return Optional.of("Book ID already exists");
        }
        return Optional.empty();
    }
}
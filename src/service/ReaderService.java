package service;

import model.Book;
import model.Reader;
import repository.InMemoryLibraryRepository;
import validation.ReaderValidator;

import java.util.Optional;
import java.util.Set;

public class ReaderService {
    private final InMemoryLibraryRepository repository;
    private final ReaderValidator validator;

    public ReaderService(InMemoryLibraryRepository repository, ReaderValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public ServiceResult borrowBook(String memberId, String bookId) {
        Optional<String> validation = validator.validateBorrow(memberId, bookId);
        if (validation.isPresent()) {
            return new ServiceResult(false, validation.get());
        }
        Reader reader = repository.findReaderById(memberId).get();
        Book book = repository.findBookById(bookId).get();
        reader.borrowBook(book);
        double amount = book.getPrice();
        return new ServiceResult(true, "Bill generated: " + amount);
    }

    public ServiceResult returnBook(String memberId, String bookId) {
        Optional<String> validation = validator.validateReturn(memberId, bookId);
        if (validation.isPresent()) {
            return new ServiceResult(false, validation.get());
        }
        Reader reader = repository.findReaderById(memberId).get();
        Book book = repository.findBookById(bookId).get();
        reader.returnBook(book);
        double amount = book.getPrice();
        return new ServiceResult(true, "Refund processed: " + amount);
    }

    public Set<Book> listBorrowedBooks(String memberId) {
        return repository.findReaderById(memberId)
                .map(Reader::getBorrowedBooks)
                .orElse(Set.of());
    }
}
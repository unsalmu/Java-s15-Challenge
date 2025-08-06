package repository;

import model.Book;
import model.BookType;
import model.Reader;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryLibraryRepository {
    private final Map<String, Book> booksById = new HashMap<>();
    private final Map<String, Reader> readersById = new HashMap<>();

    // Book operations
    public Optional<Book> findBookById(String id) {
        return Optional.ofNullable(booksById.get(id));
    }

    public List<Book> findBooksByTitle(String title) {
        return booksById.values().stream()
                .filter(b -> b.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());
    }

    public List<Book> findBooksByAuthor(String authorName) {
        return booksById.values().stream()
                .filter(b -> b.getAuthor().getName().equalsIgnoreCase(authorName))
                .collect(Collectors.toList());
    }

    public List<Book> findBooksByType(BookType type) {
        return booksById.values().stream()
                .filter(b -> b.getType() == type)
                .collect(Collectors.toList());
    }

    public List<Book> findAllBooks() {
        return new ArrayList<>(booksById.values());
    }

    public void saveBook(Book book) {
        booksById.put(book.getBookId(), book);
    }

    public boolean deleteBook(String id) {
        return booksById.remove(id) != null;
    }

    // Reader operations
    public void saveReader(Reader reader) {
        readersById.put(reader.getRecord().getMemberId(), reader);
    }

    public Optional<Reader> findReaderById(String memberId) {
        return Optional.ofNullable(readersById.get(memberId));
    }

    public List<Reader> findReadersByName(String name) {
        return readersById.values().stream()
                .filter(r -> r.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    public List<Reader> findAllReaders() {
        return new ArrayList<>(readersById.values());
    }
}
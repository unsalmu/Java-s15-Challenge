package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Author extends Person {
    private final List<Book> books = new ArrayList<>();

    public Author(String name) {
        super(name);
    }

    public Book createBook(String bookId, String title, double price,
                           BookStatus status, String edition, BookType type) {
        Book book = new Book(bookId, this, title, price, status, edition, type);
        books.add(book);
        return book;
    }

    public List<Book> getBooks() {
        return Collections.unmodifiableList(books);
    }

    @Override
    public String whoYouAre() {
        return "Author " + getName();
    }
}
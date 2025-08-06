package model;

import java.time.LocalDate;

public class Book {
    private final String bookId;
    private final Author author;
    private String title;
    private double price;
    private BookStatus status; // AVAILABLE or ISSUED
    private String edition;
    private LocalDate dateOfPurchase;
    private LocalDate borrowedDate;
    private Reader owner;
    private BookType type;

    public Book(String bookId, Author author, String title,
                double price, BookStatus status, String edition, BookType type) {
        this.bookId = bookId;
        this.author = author;
        this.title = title;
        this.price = price;
        this.status = status;
        this.edition = edition;
        this.type = type;
    }

    public String getBookId() { return bookId; }
    public Author getAuthor() { return author; }
    public String getTitle() { return title; }
    public double getPrice() { return price; }
    public BookStatus getStatus() { return status; }
    public String getEdition() { return edition; }
    public LocalDate getDateOfPurchase() { return dateOfPurchase; }
    public LocalDate getBorrowedDate() { return borrowedDate; }
    public Reader getOwner() { return owner; }
    public BookType getType() { return type; }

    public void setTitle(String title) { this.title = title; }
    public void setPrice(double price) { this.price = price; }
    public void updateStatus(BookStatus status) { this.status = status; }
    public void setEdition(String edition) { this.edition = edition; }
    public void setDateOfPurchase(LocalDate dateOfPurchase) { this.dateOfPurchase = dateOfPurchase; }
    public void setBorrowedDate(LocalDate borrowedDate) { this.borrowedDate = borrowedDate; }
    public void changeOwner(Reader owner) { this.owner = owner; }
    public void setType(BookType type) { this.type = type; }
}
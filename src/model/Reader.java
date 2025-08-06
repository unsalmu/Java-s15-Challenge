package model;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Reader extends Person {
    private final MemberRecord record;
    private final Set<Book> borrowedBooks = new HashSet<>();

    public Reader(String name, MemberRecord record) {
        super(name);
        this.record = record;
    this.record.setMember(this);
    }

    public MemberRecord getRecord() { return record; }

    public Set<Book> getBorrowedBooks() {
        return Collections.unmodifiableSet(borrowedBooks);
    }

    public void purchaseBook(Book book) {
        borrowedBooks.add(book);
    }

    public boolean hasBook(Book book) {
        return borrowedBooks.contains(book);
    }

    public int getBorrowedCount() {
        return borrowedBooks.size();
    }

    public boolean borrowBook(Book book) {
        boolean added = borrowedBooks.add(book);
        if (added) {
            book.changeOwner(this);
            book.updateStatus(BookStatus.ISSUED);
            book.setBorrowedDate(LocalDate.now());
            record.incBookIssued();
        }
        return added;
    }

    public boolean returnBook(Book book) {
        boolean removed = borrowedBooks.remove(book);
        if (removed) {
            book.changeOwner(null);
            book.updateStatus(BookStatus.AVAILABLE);
            book.setBorrowedDate(null);
            record.decBookIssued();
        }
        return removed;
    }

    @Override
    public String whoYouAre() {
        return "Reader " + getName() + " (" + record.getMemberId() + ")";
    }

    public Set<Book> showBook() {
        return getBorrowedBooks();
    }
}
import java.time.LocalDate;

public class Book {
    private String bookId;
    private Author author;
    private String name;
    private double price;
    private String  status;
    private String edition;
    private LocalDate dateOfPurchase;
    private LocalDate borrowedDate;
    private Person owner;
    private BookType type;


    public Book(String bookId, Author author, String name,
                double price, String status, String edition,
                LocalDate date_of_purchase, BookType type) {
        this.bookId = bookId;
        this.author = author;
        this.name = name;
        this.price = price;
        this.status = status; //issued ve available
        this.edition = edition;
        this.dateOfPurchase = dateOfPurchase;
        this.borrowedDate = borrowedDate;
        this.owner = owner;
        this.type = type;
    }
    public String getBookId(){
        return bookId;
    }
    public LocalDate getDateOfPurchase(){
        return dateOfPurchase;
    }
    public void setDateOfPurchase(LocalDate dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }


    public String  getTitle() {
        return name;
    }
    public LocalDate getBorrowedDate() {
        return borrowedDate;
    }
    public void setBorrowedDate(LocalDate borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public Author getAuthor() {
        return author;
    }

    public String getStatus(){
        return status;
    }

    public void changeOwner(Person newOwner ) {
        this.owner = newOwner;
        updatedStatus("issued");

    }

    public void updatedStatus(String status) {
        this.status = status;
    }

    public Person getOwner() {
        return owner;
    }

    public void display(){
        String authorName = (author != null) ? author.getName() : "Bilinmiyor";
        System.out.printf("\u001B[32mKitap: %s\u001B[0m, ID: %s, Yazar: %s, Durum: %s%n",
                name, bookId, authorName, status);
    }


    @Override
    public String toString() {
        return "Book{" +
                "book_id='" + bookId + '\'' +
                ", author=" + author +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", status='" + status + '\'' +
                ", edition='" + edition + '\'' +
                ", date_of_purchase=" + dateOfPurchase +
                ", owner=" + owner +
                '}';
    }
}

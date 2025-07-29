import java.util.*;
import java.util.stream.Collectors;

public class Library {
    private final Map<String, Book> books = new HashMap<>();
    private final Map<String, Reader> readers = new HashMap<>();

    public List<Reader> getAllReaders() {
        return new ArrayList<>(readers.values());
    }

    public Book getBook(String bookId){
        return books.get(bookId);
    }

    public Reader getReader(String memberId){
        return readers.get(memberId);
    }

    public List<Book> getBooks() {
        return new ArrayList<>(books.values());
    }
    public Book findById(String bookId) {
        return books.get(bookId);
    }
    public List<Book> findByTitle(String title) {
        return books.values().stream()
                .filter(b -> b.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());
    }
    public List<Book> findByAuthor(String authorName) {
        return books.values().stream()
                .filter(b -> b.getAuthor().getName().equalsIgnoreCase(authorName))
                .collect(Collectors.toList());
    }
    public List<Book> findByType(BookType type) {
        return books.values().stream()
                .filter(b -> b.getType() == type)
                .collect(Collectors.toList());
    }



    public void updateBook(String bookId,
                           String newTitle,
                           double newPrice,
                           String newEdition,
                           BookType newType) {
        Book b = books.get(bookId);
        if (b == null) {
            System.out.println("Güncellenecek kitap bulunamadı: " + bookId);
            return;
        }
        b.setTitle(newTitle);
        b.setPrice(newPrice);
        b.setEdition(newEdition);
        b.setType(newType);
        System.out.println("Kitap güncellendi: " + b.getTitle());
    }

    public void removeBook(String bookId) {
        if (books.remove(bookId) == null) {
            System.out.println("Silinecek kitap bulunamadı: " + bookId);
        } else {
            System.out.println("Kitap silindi: " + bookId);
        }
    }


    public MemberRecord getMemberId(String memberId){
        Reader r = readers.get(memberId);
        return (r != null ? r.getMembership() : null);
    }

    public List<Reader> getReaderByName(String name) {
        return readers.values().stream()
                .filter(r -> r.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    public void registerReader(Reader reader){
        String id = reader.getMembership().getMemberId();
        readers.put(id, reader);
    }


    public Book newBook(Book book) {
        if(books.containsKey(book.getBookId())) {
            System.out.println("Bu ID'ye sahip bir kitap zaten var!");
            return null;
        }

        books.put(book.getBookId(), book);
        return book;
    }

    public void lendBook(String bookId, String memberId) {
        Book book = books.get(bookId);
        Reader reader = readers.get(memberId);
        if (book == null) {
            System.out.println("Kitap bulunamadı!");
            return;
        }

        if ("issued".equalsIgnoreCase(book.getStatus())) {
            System.out.println("Kitap başkasının kullanımında.");
            return;
        }
        if (reader == null) {
            System.out.println("Üye bulunamadı");
            return;
        }
        try {
            reader.borrowBook(book);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

    }


    public void takeBackBook(String bookId, String memberId, Librarian librarian) {
        Book book = books.get(bookId);
        Reader reader = readers.get(memberId);

        if(book == null) {
            System.out.println("Kitap bulunamadı.");
            return;
        }
        if (reader == null) {
            System.out.println("Üye bulunamadı.");
            return;
        }
        reader.returnBook(book);
        librarian.refundBill(memberId, book.getPrice(), this);




    }

    public void showBook(){
        books.values().forEach(Book::display);
    }








}

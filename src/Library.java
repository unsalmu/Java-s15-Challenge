import java.util.*;
import java.util.stream.Collectors;

public class Library {
    private final Map<String, Book> books = new HashMap<>();
    private final Map<String, Reader> readers = new HashMap<>();

    public List<Book> getBooks() {
        return new ArrayList<>(books.values());
    }

    public MemberRecord getMemberRecord(String memberId){
        Reader r = readers.get(memberId);
        return (r != null ? r.getMembership() : null);
    }

    public Book getBook(String bookId){
        return books.get(bookId);
    }
    public Reader getReader(String memberId){
        return readers.get(memberId);
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


    public void newBook(Book book) {
        if(books.containsKey(book.getBookId())) {
            System.out.println("Bu ID'ye sahip bir kitap zaten var!");
            return;
        }

        books.put(book.getBookId(), book);
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
        reader.borrowBook(book);
    }


    public void takeBackBook(String bookId, String memberId) {
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
    }

    public void showBook(){
        books.values().forEach(Book::display);
    }








}

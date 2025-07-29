import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Author extends Person {
    private List<Book> books = new ArrayList<>();

    public Author(String name) {
        super(name);
    }


    public Book newBook(String bookId, String author, String name,
                        double price, String  status, String edition, BookType type){
        Book b = new Book(bookId, this, name, price, status, edition, type );
        books.add(b);
        return b;
    }

    public List<Book> showBooks(){
        return Collections.unmodifiableList(books);
    }


    @Override
    public String whoYouAre() {
        return "Author " + getName();
    }

}

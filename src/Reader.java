import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class Reader extends Person {
    private final Set<Book> books = new LinkedHashSet<>();
    private final MemberRecord membership;

    public Reader(MemberRecord membership) {
        super(membership.getName());
        this.membership = membership;
    }

    public MemberRecord getMembership() {
        return membership;
    }


    public void purchaseBook(Book book){
        if(!books.add(book)) {
            System.out.println("Zaten bu kitabı satın almışsınız.");
            return;
        }
        book.changeOwner(this);
        book.setDateOfPurchase(LocalDate.now());
        book.updatedStatus("purchased");
        System.out.println("' " + book.getTitle() + " " + book.getDateOfPurchase() + " Tarihinde satın alındı.");
    }

    public void borrowBook(Book book){
        if(!"available".equalsIgnoreCase(book.getStatus())) {
            System.out.println("Kitap başka kişide.");
            return;
        }
        if(books.contains(book)){
            System.out.println("Kitap sizde");
            return;
        }
        if(membership.getBooksIssued() >= membership.getMaxBookLimit() ){
            throw new IllegalArgumentException("Kitap ödünç limitini aştınız." + membership.getMaxBookLimit());
        }
        if(!books.add(book)) {
            System.out.println("Kitabı zaten ödünç almıştınız");
            return;
        }
        membership.incBookIssued();
        books.add(book);
        book.changeOwner(this);
        System.out.println("'" + book.getTitle() + " ' ödünç alındı.");
    }

    public void returnBook(Book book) {
        if(books.remove(book)){
            membership.decBookIssued();
            book.updatedStatus("available");
            System.out.println("'" + book.getTitle() + "' iade edildi.");
        } else {
            System.out.println("Bu kitabı siz almadınız.");
        }
    }

    public Set<Book> getBorrowedBooks() {
        return Collections.unmodifiableSet(books);
    }

    @Override
    public String whoYouAre() {
        return "Reader: " + getName() + "Member ID: " + membership.getMemberId();
    }
}

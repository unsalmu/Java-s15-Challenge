import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Librarian {
    private String password;
    private String name;
    private static final int ALLOWED_DAYS = 14;
    private static final double FINE_PER_DAY = 1.0;

    public Librarian(String name, String password) {
        this.name = name;
        this.password = password;

    }

    public Book searchBook( String bookId, Library lib) {
        return lib.getBook(bookId);
    }
    public boolean verifyMember(String memberId, Library lib) {
        return lib.getReader(memberId) != null;
    }

    public void issueBook(String bookId, String memberId, Library lib) {
        lib.lendBook(bookId, memberId);
    }
    public double calculateFine(String bookId, int daysOverdue, Library lib){
        Book b = lib.getBook(bookId);
        if ( b == null) throw new IllegalArgumentException("Kitap yok" + bookId);
        if (b.getBorrowedDate() == null) return 0;
        long daysBorrowed = ChronoUnit.DAYS.between(b.getBorrowedDate(), LocalDate.now());
        long overDueDays = daysBorrowed - ALLOWED_DAYS;

        if (overDueDays <= 0 ) return 0;

        return daysOverdue * FINE_PER_DAY;

    }

    public void createBill(String memberId, double amount, Library lib) {
        MemberRecord memberRecord = lib.getMemberRecord(memberId);
        if(memberRecord == null) {
            System.out.println("Üye bulunamadı: " + memberId);
            return;
        }
        memberRecord.payBill(amount);
        System.out.printf("Fatura kesildi: " + memberId + " Tutar: " + amount);
        }

    }





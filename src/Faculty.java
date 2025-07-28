import java.time.LocalDate;

public class Faculty extends MemberRecord {
    public Faculty(String member_id, LocalDate date_of_membership, int no_books_issued, int max_book_limit, String name, String address, String phone_no) {
        super(member_id, "Faculty", date_of_membership, no_books_issued, max_book_limit, name, address, phone_no);
    }
}

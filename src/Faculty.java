import java.time.LocalDate;

public class Faculty extends MemberRecord {
    public Faculty(String memberId, String type, LocalDate dateOfMemberShip, int booksIssued, int maxBookLimit, String name, String address, String phoneNo) {
        super(memberId, "Faculty", dateOfMemberShip, booksIssued, maxBookLimit, name, address, phoneNo);
    }
}

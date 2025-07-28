import java.time.LocalDate;

public class Student extends MemberRecord{


    public Student(String memberId, String type, LocalDate dateOfMembership, int booksIssued, int maxBookLimit, String name, String address, String phoneNo) {
        super(memberId, type, dateOfMembership, booksIssued, maxBookLimit, name, address, phoneNo);
    }
}

package model;

import java.time.LocalDate;

public class Faculty extends MemberRecord {
    public Faculty(String memberId, LocalDate joinDate, String name, String address, String phoneNo) {
        super(memberId, MemberType.FACULTY, joinDate, 5, name, address, phoneNo);
    }
}
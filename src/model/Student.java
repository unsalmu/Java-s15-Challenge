package model;

import java.time.LocalDate;

public class Student extends MemberRecord {
    public Student(String memberId, LocalDate joinDate, String name, String address, String phoneNo) {
        super(memberId, MemberType.STUDENT, joinDate, 5, name, address, phoneNo);
    }
}
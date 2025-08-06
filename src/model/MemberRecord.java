package model;

import java.time.LocalDate;

public class MemberRecord {
    private final String memberId;
    private final MemberType type;
    private final LocalDate dateOfMembership;
    private int noBooksIssued;
    private final int maxBookLimit;
    private final String name;
    private final String address;
    private final String phoneNo;
    private Reader member;

    public MemberRecord(String memberId, MemberType type, LocalDate dateOfMembership,
                        int maxBookLimit, String name, String address, String phoneNo) {
        this.memberId = memberId;
        this.type = type;
        this.dateOfMembership = dateOfMembership;
        this.maxBookLimit = maxBookLimit;
        this.name = name;
        this.address = address;
        this.phoneNo = phoneNo;
    }

    public String getMemberId() { return memberId; }
    public MemberType getType() { return type; }
    public LocalDate getDateOfMembership() { return dateOfMembership; }
    public int getNoBooksIssued() { return noBooksIssued; }
    public int getMaxBookLimit() { return maxBookLimit; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getPhoneNo() { return phoneNo; }

    public Reader getMember() { return member; }
    public void setMember(Reader member) { this.member = member; }

    public void incBookIssued() { noBooksIssued++; }
    public void decBookIssued() { if (noBooksIssued > 0) noBooksIssued--; }
    public void payBill(double amount) { /* billing logic placeholder */ }
}
import java.time.LocalDate;

public abstract class MemberRecord {
    private String memberId;
    private String type;
    private LocalDate dateOfMembership;
    private int booksIssued;
    private int maxBookLimit = 5;
    private String name;
    private String address;
    private String phoneNo;
    private int noBooksIssued = 0;

    public MemberRecord(String memberId, String type, LocalDate dateOfMembership,
                        int booksIssued, int maxBookLimit, String name,
                        String address, String phoneNo) {
        this.memberId = memberId;
        this.type = type;
        this.dateOfMembership = dateOfMembership;
        this.booksIssued = booksIssued;
        this.maxBookLimit = maxBookLimit;
        this.name = name;
        this.address = address;
        this.phoneNo = phoneNo;
        this.noBooksIssued = noBooksIssued;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getType() {
        return type;
    }

    public LocalDate getDateOfMembership() {
        return dateOfMembership;
    }

    public int getBooksIssued() {
        return booksIssued;
    }

    public int getMaxBookLimit() {
        return maxBookLimit;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }
    public int getNoBooksIssued(){
        return noBooksIssued;
    }

    public void incBookIssued() {
        if(booksIssued < maxBookLimit) {
            booksIssued++;
        } else {
            throw new IllegalArgumentException("Kitap limiti aşıldı: max " + maxBookLimit);
        }
    }
    public void decBookIssued() {
        if(booksIssued > 0 ) {
            booksIssued--;
        } else {
            throw new IllegalArgumentException("İade edilecek kitap yok.");
        }
    }
    public void payBill(double amount) {
        System.out.println(memberId + "id, " + name + ", için tutar: " + amount);
    }


}

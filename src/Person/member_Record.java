package Person;

import java.time.LocalDateTime;
import java.util.Objects;

public class member_Record {
    private long member_id;
    private MemberType type;
    private LocalDateTime date_of_membership;
    private int no_books_issued;
    private int max_book_limit;
    private double bill;
    private String name;
    private String address;
    private String phone_no;

    public member_Record(long member_id, MemberType type, String name, String address, String phone_no) {
        this.member_id = member_id;
        this.type = type;
        this.date_of_membership = LocalDateTime.now();
        this.no_books_issued = 0;
        this.max_book_limit = type.getLimit();
        this.bill = 0.0;
        this.name = name;
        this.address = address;
        this.phone_no = phone_no;
    }

    public long get_member() {
        return this.member_id;
    }

    public MemberType getType() {
        return type;
    }

    public LocalDateTime getDate_of_membership() {
        return date_of_membership;
    }

    public double getBill() {
        return bill;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone_no() {
        return phone_no;
    }


    public void inc_book_issued() {
        no_books_issued++;
    }

    public void dec_book_issued() {
        no_books_issued--;
    }

    public void setType(MemberType type) {
        this.type = type;
    }

    public void addToBill(double amount) {
        if (amount > 0) {
            this.bill += amount;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public void pay_bill(double money) {
        if (money < 0) {
            System.out.println("The amount paid cannot be negative.");
            return;
        }

        if (money >= bill) {
            double change = money - this.bill;
            this.bill = 0;
            System.out.println("Bill paid successfully. Change: " + change + " TL");
        } else {
            this.bill -= money;
            System.out.println("Bill paid successfully. Remaining bill: " + this.bill + " TL");
        }
    }

    public void addCredit(double amount) {
        if (amount > 0) {
            this.bill -= amount;
            System.out.println("Credit of " + amount + " TL added. Remaining bill: " + this.bill + " TL");
        }
    }

    public int getMax_book_limit() {
        return max_book_limit;
    }

    public int getNo_books_issued() {
        return no_books_issued;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        member_Record member = (member_Record) o;
        return member_id == member.member_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(member_id);
    }

    @Override
    public String toString() {
        return "member_Record{" +
                "member_id=" + member_id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", no_books_issued=" + no_books_issued +
                ", total bill=" + bill +
                '}';
    }

}

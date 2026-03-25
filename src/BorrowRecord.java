import java.time.LocalDate;
public class BorrowRecord {
    private String bookID;
    private String memberID;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    public BorrowRecord(String bID,String MID){
        this.bookID=bID;
        this.memberID=MID;
        this.borrowDate=LocalDate.now();
        this.dueDate=borrowDate.plusDays(14);
        this.returnDate=null;
    }

    public String getBookId(){
        return bookID;
    }
    public String getMemberId(){
        return memberID;
    }
    public LocalDate getBorrowDate(){
        return borrowDate;
    }
    public LocalDate getDuDate(){
        return dueDate;
    }
    public LocalDate getReturnDate() {
        return returnDate;
    }
    public boolean isReturned() {
        return returnDate != null;
    }

    public void returnBook(){
        this.returnDate = LocalDate.now();
    }

    @Override
    public String toString(){
        return String.format("ISBN: %s | Member: %s | Borrowed: %s | Due: %s | Returned: %s",
                bookID, memberID, borrowDate, dueDate, (returnDate == null ? "Not yet" : returnDate));
    }
}

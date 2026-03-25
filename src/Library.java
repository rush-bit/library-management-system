import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Library {
    private List<Book> books=new ArrayList<>();
    private Map<String,Book> bookMap=new HashMap<>();
    private List<Member> members=new ArrayList<>();
    private Map<String,Member> memberMap = new HashMap<>();
    private List<BorrowRecord> borrowRecords = new ArrayList<>();


    // this method adds a new book in list and ain map
    public boolean addBook(Book book){
        String bookID=book.getbookId();
        if(bookMap.containsKey(bookID)){
            System.out.println("Error: Book with ISBN " + bookID + " already exists!");
            return false;
        }
        books.add(book);
        bookMap.put(bookID, book);
        System.out.println("Book added successfully: " + book.getTitle());
        return true;
    }


    public void displayAllBooks(){
        if(books.isEmpty()){
            System.out.println("No books in the library yet.");
            return;
        }
        
        System.out.println("\n === All Books in the Library ===");
        System.out.println("ID            Title                          Author                   Copies");
        System.out.println("--------------------------------------------------------------------------------");
        
        for (Book book : books) {
            System.out.println(book.toDisplayString());
        }
        System.out.println("--------------------------------------------------------------------------------");
    }

    public Book findBookbyID(String bookID){
        return bookMap.get(bookID.trim());
    }
    
    public void searchByID(String bookID){
        Book book = findBookbyID(bookID);

        if(book!=null){
            System.out.println("\nBook Found:");
            System.out.println("BookID            : " + book.getbookId());
            System.out.println("Title           : " + book.getTitle());
            System.out.println("Author          : " + book.getauthor());
            System.out.println("Available Copies: " + book.getavailable() + "/" + book.getTotalCopies());
        }else{
            System.out.println("No book found with BookID "+bookID);
        }
    }

    public void searchByTitle(String Titlee){
        List<Book> results=new ArrayList<>();
        String keyword = Titlee.toLowerCase().trim();

        for(Book book : books){
            if(book.getTitle().toLowerCase().contains(keyword)){
                results.add(book);
            }
        }
        if(results.isEmpty()){
            System.out.println("No books found with title containing: " + Titlee);
            return;
        }
        System.out.println("\n=== Search Results for Title: \"" + Titlee + "\" ===");
        System.out.println("ID            Title                          Author                   Copies");
        System.out.println("--------------------------------------------------------------------------------");
        
        for (Book book : results) {
            System.out.println(book.toDisplayString());
        }
        System.out.println("--------------------------------------------------------------------------------");
    }

    public void searchByAuthor(String authorr) {
        List<Book> results = new ArrayList<>();
        
        String keyword = authorr.toLowerCase().trim();
        
        for (Book book : books) {
            if (book.getauthor().toLowerCase().contains(keyword)) {
                results.add(book);
            }
        }
        
        if (results.isEmpty()) {
            System.out.println("No books found with author containing: " + authorr);
            return;
        }
        
        System.out.println("\n=== Search Results for Author: \"" + authorr + "\" ===");
        System.out.println("ID            Title                          Author                   Copies");
        System.out.println("--------------------------------------------------------------------------------");
        
        for (Book book : results) {
            System.out.println(book.toDisplayString());
        }
        System.out.println("--------------------------------------------------------------------------------");
    }

    public boolean addMember(Member member){
        String id=member.getMemberId();
        if(memberMap.containsKey(id)){
            System.out.println("Error: Member with ID: "+id+" already exists");
            return false;
        }

        members.add(member);
        memberMap.put(id, member);
        System.out.println("Member added successfully");
        return true;
    }

    public void displayAllMembers(){
        if(members.isEmpty()){
            System.out.println("No members registered yet. ");
            return;
        }

        System.out.println("\n=== All Library Members ===");
        System.out.println("Member ID       Name                       Contact");
        System.out.println("-------------------------------------------------------------");
        
        for (Member member : members) {
            System.out.println(member.toDisplayString());
        }
        System.out.println("-------------------------------------------------------------");
    }

    public Member findMemberById(String memberId) {
        return memberMap.get(memberId.trim());
    }

    public void issueBook(String bookId,String memberId){
        Book book=findBookbyID(bookId);
        Member member=findMemberById(memberId);

        if(book==null){
            System.out.println("Error: Book with BookID "+bookId+" not found");
        }
        if(member == null){
            System.out.println("Error: Book with MemberID "+memberId+" not found");
        }
        if(!book.canBorrow()){
            System.out.println("Error: Book is currently not available");
            return;
        }

        BorrowRecord record = new BorrowRecord(bookId, memberId);
        borrowRecords.add(record);
        book.borrow();

        System.out.println("Book issued successfully");
        System.out.println("   Title     : " + book.getTitle());
        System.out.println("   Member    : " + member.getName());
        System.out.println("   Due Date  : " + record.getDuDate());
    }

    public void returnBook(String bookID,String memberID){
        Book book=findBookbyID(bookID);

        if(book == null){
            System.out.println("Error: Book not found!");
            return;
        }

        BorrowRecord record = null;
        for(BorrowRecord br:borrowRecords){
            if(br.getBookId().equals(bookID) && br.getMemberId().equals(memberID)){
                record = br;
                break;
            }
        }

        if(record == null){
            System.out.println("Error: No active borrow record for this book and member");
            return;
        }

        record.returnBook();
        book.returnBook();

        System.out.println("Book returned successfully!");
        System.out.println("   Title     : " + book.getTitle());
        System.out.println("   Member    : " + findMemberById(memberID).getName());
        System.out.println("   Returned on: " + record.getReturnDate());
    }

    public void displayBorrowedBooks(){
        boolean hasBorrowed=false;
        System.out.println("\n=== Currently Borrowed Books ===");
        System.out.println("BookID           Member ID      Borrow Date   Due Date     Status");
        System.out.println("--------------------------------------------------------------------------------");

        for (BorrowRecord record : borrowRecords) {
            if (!record.isReturned()) {
                hasBorrowed = true;
                Book book = findBookbyID(record.getBookId());
                Member member = findMemberById(record.getMemberId());
                
                System.out.printf("%-15s %-15s %-12s %-12s Active\n",
                        record.getBookId(),
                        record.getMemberId(),
                        record.getBorrowDate(),
                        record.getDuDate());
            }
        }

        if (!hasBorrowed) {
            System.out.println("No books are currently borrowed.");
        }
    }

    public void saveData() {
        FileHandler.saveBooks(books);
        FileHandler.saveMembers(members);
        FileHandler.saveBorrowRecords(borrowRecords);
    }

    public void loadData() {
        books.clear();
        bookMap.clear();
        members.clear();
        memberMap.clear();
        borrowRecords.clear();

        List<Book> loadedBooks = FileHandler.loadBooks();
        for (Book b : loadedBooks) {
            books.add(b);
            bookMap.put(b.getbookId(), b);
        }

        List<Member> loadedMembers = FileHandler.loadMembers();
        for (Member m : loadedMembers) {
            members.add(m);
            memberMap.put(m.getMemberId(), m);
        }
        borrowRecords.addAll(FileHandler.loadBorrowRecords());

        System.out.println("Data loaded from files.");
    }
}

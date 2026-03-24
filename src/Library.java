import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Library {
    private List<Book> books=new ArrayList<>();
    private Map<String,Book> bookMap=new HashMap<>();
    private List<Member> members=new ArrayList<>();
    private Map<String,Member> memberMap = new HashMap<>();


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


}

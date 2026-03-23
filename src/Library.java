import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Library {
    private List<Book> books=new ArrayList<>();
    private Map<String,Book> bookMap=new HashMap<>();


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
    
}

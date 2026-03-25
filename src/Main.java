import java.util.Scanner;

public class Main {
    
    private static Library library = new Library(); 
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=====================================");
        System.out.println("   Library Management System - v0.2   ");
        System.out.println("=====================================");

        while (running) {
            printMenu();
            
            int choice;
            try {
                System.out.print("\nEnter your choice (0-7): ");
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1:
                    addBookMenu(scanner);
                    break;
                case 2:
                    library.displayAllBooks();
                    break;
                case 3:
                    searchMenu(scanner);
                    break;
                case 4:
                    addMemberMenu(scanner);
                    break;
                case 5:
                    issueBookMenu(scanner); 
                    break;
                case 6:
                    returnBookMenu(scanner);
                    break;
                case 7:
                    library.displayBorrowedBooks(); 
                    break;
                case 0:
                    System.out.println("\nThank you for using the Library System. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please select 0-6.");
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. Add a new book");
        System.out.println("2. View all books");
        System.out.println("3. Search for a book");
        System.out.println("4. Add a new member");
        System.out.println("5. Issue a book");
        System.out.println("6. Return a book");
        System.out.println("7. Display all borrowed Books");
        System.out.println("0. Exit");
    }

    private static void addBookMenu(Scanner scanner) {
        System.out.println("\n--- Add New Book ---");
        
        System.out.print("Enter BookID (unique): ");
        String bookID = scanner.nextLine().trim();
        
        if (bookID.isEmpty()) {
            System.out.println("BookID cannot be empty!");
            return;
        }
        
        System.out.print("Enter Title: ");
        String title = scanner.nextLine().trim();
        
        System.out.print("Enter Author: ");
        String author = scanner.nextLine().trim();
        
        System.out.print("Enter Total Number of Copies: ");
        int totalCopies;
        try {
            totalCopies = Integer.parseInt(scanner.nextLine().trim());
            if (totalCopies <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            System.out.println("Please enter a positive number for copies.");
            return;
        }
        
        Book newBook = new Book(bookID, title, author, totalCopies);
        library.addBook(newBook);
    }

    private static void searchMenu(Scanner scanner){
        System.out.println("\n--- Search Book ---");
        System.out.println("1. Search by BookID");
        System.out.println("2. Search by author");
        System.out.println("3. Search by title");
        System.out.println("0. Back to the menu");

        System.out.println("Enter Search option: ");
        int option;
        try {
            option = Integer.parseInt(scanner.nextLine().trim());
        } 
        catch (NumberFormatException e) {
            System.out.println("Invalid Option ");
            return;
        }

        switch(option){
            case 1:
                System.out.println("Enter BookID");
                String bookId = scanner.nextLine().trim();
                library.searchByID(bookId);
                break;
            case 2:
                System.out.println("Enter author name");
                String author=scanner.nextLine().trim();
                library.searchByAuthor(author);
                break;
            case 3:
                System.out.println("Enter the title of the book");
                String title=scanner.nextLine().trim();
                library.searchByTitle(title);
                break;
            case 0:
                break;
            default:
                System.out.println("Invalid search option");
        }
    }

    private static void addMemberMenu(Scanner scanner) {
        System.out.println("\n--- Add New Member ---");
        
        System.out.print("Enter Member ID (Any unique hexnum combination) : ");
        String memberId = scanner.nextLine().trim();
        
        if (memberId.isEmpty()) {
            System.out.println("Member ID cannot be empty!");
            return;
        }
        
        System.out.print("Enter Name : ");
        String name = scanner.nextLine().trim();
        
        System.out.print("Enter Contact (phone/email) : ");
        String contact = scanner.nextLine().trim();
        
        Member newMember = new Member(memberId, name, contact);
        library.addMember(newMember);
    }

    private static void issueBookMenu(Scanner scanner) {
        System.out.println("\n--- Issue Book ---");
        System.out.print("Enter BookID      : ");
        String bookid = scanner.nextLine().trim();
        
        System.out.print("Enter Member ID : ");
        String memberId = scanner.nextLine().trim();
        
        library.issueBook(bookid, memberId);
    }

    private static void returnBookMenu(Scanner scanner) {
        System.out.println("\n--- Return Book ---");
        System.out.print("Enter BookID      : ");
        String bookid = scanner.nextLine().trim();
        
        System.out.print("Enter Member ID : ");
        String memberId = scanner.nextLine().trim();
        
        library.returnBook(bookid, memberId);
    }
}
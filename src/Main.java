import java.util.Scanner;

public class Main {
    
    private static Library library = new Library();  // One library for the whole program
    
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
                System.out.print("\nEnter your choice (0-6): ");
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
                    System.out.println("→ Search book (coming soon)");
                    break;
                case 4:
                    System.out.println("→ Add member (coming soon)");
                    break;
                case 5:
                    System.out.println("→ Issue book (coming soon)");
                    break;
                case 6:
                    System.out.println("→ Return book (coming soon)");
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
}
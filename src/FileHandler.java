import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    private static final String BOOKS_FILE = "data/books.csv";
    private static final String MEMBERS_FILE = "data/members.csv";
    private static final String BORROW_FILE = "data/borrow_records.csv";

    public static void saveBooks(List<Book> books) {
        createDataFolder();
        String filePath = "data/books.csv";
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println("ISBN,Title,Author,TotalCopies,AvailableCopies");
            for (Book b : books) {
                writer.println(String.format("%s,%s,%s,%d,%d",
                        escapeCSV(b.getbookId()),
                        escapeCSV(b.getTitle()),
                        escapeCSV(b.getauthor()),
                        b.getTotalCopies(),
                        b.getavailable()));
            }
            System.out.println("Books saved successfully to " + filePath);
        } catch (IOException e) {
            System.out.println("ERROR saving books: " + e.getMessage());
            e.printStackTrace();   // This will show exact reason
        }
    }

    public static void saveMembers(List<Member> members) {
        createDataFolder();
        String filePath = "data/members.csv";
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println("MemberID,Name,Contact");
            for (Member m : members) {
                writer.println(String.format("%s,%s,%s",
                        escapeCSV(m.getMemberId()),
                        escapeCSV(m.getName()),
                        escapeCSV(m.getContact())));
            }
            System.out.println("Members saved successfully to " + filePath);
        } catch (IOException e) {
            System.out.println("ERROR saving members: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void saveBorrowRecords(List<BorrowRecord> records) {
        createDataFolder();
        String filePath = "data/borrow_records.csv";
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println("ISBN,MemberID,BorrowDate,DueDate,ReturnDate");
            for (BorrowRecord r : records) {
                String returnDateStr = (r.getReturnDate() == null) ? "" : r.getReturnDate().toString();
                writer.println(String.format("%s,%s,%s,%s,%s",
                        escapeCSV(r.getBookId()),
                        escapeCSV(r.getMemberId()),
                        r.getBorrowDate(),
                        r.getDuDate(),
                        returnDateStr));
            }
            System.out.println("Borrow records saved successfully to " + filePath);
        } catch (IOException e) {
            System.out.println("ERROR saving borrow records: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void createDataFolder() {
        File dataDir = new File("../data");
        if (!dataDir.exists()) {
            if (dataDir.mkdirs()) {
                System.out.println("Created 'data' folder in project root.");
            } else {
                System.out.println("Failed to create 'data' folder.");
            }
        }
    }

    public static List<Book> loadBooks() {
        List<Book> books = new ArrayList<>();
        File file = new File("../data/books.csv");
        if (!file.exists()) return books;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",", -1);
                if (parts.length >= 5) {
                    String bookId = parts[0].trim();
                    String title = parts[1].trim();
                    String author = parts[2].trim();
                    int total = Integer.parseInt(parts[3].trim());
                    int available = Integer.parseInt(parts[4].trim());

                    Book book = new Book(bookId, title, author, total);
                    for (int i = 0; i < (total - available); i++) {
                        book.borrow();
                    }
                    books.add(book);
                }
            }
        } catch (Exception e) {
            System.out.println("Warning: Error loading books - " + e.getMessage());
        }
        return books;
    }

    public static List<Member> loadMembers() {
        List<Member> members = new ArrayList<>();
        File file = new File("../data/members.csv");
        if (!file.exists()) return members;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",", -1);
                if (parts.length >= 3) {
                    members.add(new Member(parts[0].trim(), parts[1].trim(), parts[2].trim()));
                }
            }
        } catch (Exception e) {
            System.out.println("Warning: Error loading members - " + e.getMessage());
        }
        return members;
    }

    public static List<BorrowRecord> loadBorrowRecords() {
        List<BorrowRecord> records = new ArrayList<>();
        File file = new File("../data/borrow_records.csv");
        if (!file.exists()) return records;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",", -1);
                if (parts.length >= 4) {
                    String bookId = parts[0].trim();
                    String memberId = parts[1].trim();
                    LocalDate borrowDate = LocalDate.parse(parts[2].trim());
                    LocalDate dueDate = LocalDate.parse(parts[3].trim());
                    LocalDate returnDate = (parts.length > 4 && !parts[4].trim().isEmpty()) 
                                            ? LocalDate.parse(parts[4].trim()) : null;

                    BorrowRecord record = new BorrowRecord(bookId, memberId);
                
                    records.add(record);
                }
            }
        } catch (Exception e) {
            System.out.println("Warning: Error loading borrow records - " + e.getMessage());
        }
        return records;
    }

    // Helper to handle commas in names/titles
    private static String escapeCSV(String field) {
        if (field.contains(",")) {
            return "\"" + field.replace("\"", "\"\"") + "\"";
        }
        return field;
    }
}

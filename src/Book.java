public class Book {
    private String bookID;
    private String title;
    private String author;
    private int totalCopies;
    private int availableCopies;

    public Book(String bookID,String title,String author,int totalCopies){
        this.bookID=bookID;
        this.title=title.trim();
        this.author=author.trim();
        this.totalCopies=totalCopies;
        this.availableCopies=totalCopies;
    }

    public String getbookId() {
        return bookID;
    }
    public String getTitle(){
        return title;
    }
    public String getauthor(){
        return author;
    }
    public int getTotalCopies(){
        return totalCopies;
    }
    public int getavailable(){
        return availableCopies;
    }

    public boolean canBorrow(){
        return availableCopies>0;
    }

    public void borrow(){
        if(canBorrow()){
            availableCopies--;
        }
    }

    public void returnBook(){
        if(availableCopies < totalCopies){
            availableCopies++;
        }
    }

    @Override
    public String toString(){
        return "Book{" +
                " BookId='"+ bookID+'\''+
                ", Title='" + title + '\'' +
                ", Author='" + author + '\'' +
                ", Copies=" + availableCopies + "/" + totalCopies +
                '}';
    }

    public String toDisplayString() {
        return String.format("%-15s %-30s %-25s %d/%d",
                 bookID,title, author, availableCopies, totalCopies);
    }
}

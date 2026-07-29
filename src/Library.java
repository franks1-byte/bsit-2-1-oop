import java.util.ArrayList;

class Book {
    private String title, author;
    private boolean borrowed;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isBorrowed() { return borrowed; }
    public void borrow() { borrowed = true; }
    public void returnBook() { borrowed = false; }

    public String describe() {
        return title + " by " + author + " [" + (borrowed ? "Borrowed" : "Available") + "]";
    }
}

public class Library {
    private ArrayList<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
        System.out.println(">> Book added successfully.");
    }

    public void listBooks() {
        if (books.isEmpty()) {
            System.out.println(">> No books in the library yet.");
            return;
        }
        System.out.println("--- Library Catalog ---");
        for (int i = 0; i < books.size(); i++)
            System.out.println((i + 1) + ". " + books.get(i).describe());
    }

    private Book findBook(String title) {
        for (Book b : books)
            if (b.getTitle().equalsIgnoreCase(title)) return b;
        return null;
    }

    public void borrowBook(String title) {
        Book b = findBook(title);
        if (b == null) System.out.println(">> No book found with the title '" + title + "'.");
        else if (b.isBorrowed()) System.out.println(">> '" + b.getTitle() + "' is already borrowed.");
        else { b.borrow(); System.out.println(">> You borrowed '" + b.getTitle() + "'."); }
    }

    public void returnBook(String title) {
        Book b = findBook(title);
        if (b == null) System.out.println(">> No book found with the title '" + title + "'.");
        else if (!b.isBorrowed()) System.out.println(">> '" + b.getTitle() + "' was not borrowed.");
        else { b.returnBook(); System.out.println(">> You returned '" + b.getTitle() + "'."); }
    }

    public void searchBook(String title) {
        Book b = findBook(title);
        if (b == null) System.out.println(">> '" + title + "' was not found in the library.");
        else System.out.println(">> Found: " + b.describe());
    }
}
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        System.out.println("===== LIBRARY INFORMATION SYSTEM =====");
        System.out.println("1. Add a book");
        System.out.println("2. List all books");
        System.out.println("3. Borrow a book");
        System.out.println("4. Return a book");
        System.out.println("5. Search a book");
        System.out.println("0. Exit");

        int choice = -1;
        while (choice != 0) {
            System.out.print("Enter your choice: ");
            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(">> Please enter a valid number.\n");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter title : ");
                    String t = sc.nextLine().trim();
                    System.out.print("Enter author: ");
                    String a = sc.nextLine().trim();
                    library.addBook(new Book(t, a));
                    break;
                case 2:
                    library.listBooks();
                    break;
                case 3:
                    System.out.print("Enter title to borrow: ");
                    library.borrowBook(sc.nextLine().trim());
                    break;
                case 4:
                    System.out.print("Enter title to return: ");
                    library.returnBook(sc.nextLine().trim());
                    break;
                case 5:
                    System.out.print("Enter title to search: ");
                    library.searchBook(sc.nextLine().trim());
                    break;
                case 0:
                    System.out.println(">> Thank you for using the Library System. Goodbye!");
                    break;
                default:
                    System.out.println(">> Invalid choice. Please try again.");
            }

            if (choice != 0) System.out.println();
        }
        sc.close();
    }
}
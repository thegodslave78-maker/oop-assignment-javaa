import java.util.List;
import java.util.Scanner;

/**
 * Entry point and console UI for the Library Management System.
 * Run using: javac *.java && java LibraryApp
 */
public class LibraryApp {
    private final Scanner scanner;
    private final LibraryManager libraryManager;

    public LibraryApp() {
        scanner = new Scanner(System.in);
        libraryManager = new LibraryManager();
        addSampleData();
    }

    private void addSampleData() {
        libraryManager.addItem(new PrintedBook(
                "PB101", "Java Fundamentals", "Nora Lim", "A-12"));
        libraryManager.addItem(new PrintedBook(
                "PB102", "Data Structures", "Amir Hassan", "B-07"));
        libraryManager.addItem(new EBook(
                "EB201", "Software Design", "Grace Tan", "PDF"));
        libraryManager.addItem(new EBook(
                "EB202", "Programming Logic", "Daniel Wong", "EPUB"));
    }

    private void showMenu() {
        System.out.println("\n===============================================================");
        System.out.println("              LIBRARY MANAGEMENT SYSTEM");
        System.out.println("===============================================================");
        System.out.println("1. Display all library items");
        System.out.println("2. Add printed book");
        System.out.println("3. Add e-book");
        System.out.println("4. Search by title or author");
        System.out.println("5. Borrow an item");
        System.out.println("6. Return an item");
        System.out.println("7. Library summary");
        System.out.println("0. Exit");
    }

    public void run() {
        System.out.println("Welcome! Four example resources are ready to explore.");
        int choice;
        do {
            showMenu();
            choice = readInt("Select an option: ");
            switch (choice) {
                case 1 -> displayAllItems();
                case 2 -> addPrintedBook();
                case 3 -> addEBook();
                case 4 -> searchItems();
                case 5 -> borrowItem();
                case 6 -> returnItem();
                case 7 -> showSummary();
                case 0 -> System.out.println("Thank you for using the system. Goodbye!");
                default -> System.out.println("Invalid option. Choose 0 to 7.");
            }
        } while (choice != 0);
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                System.out.println("Please enter a whole number.");
            }
        }
    }

    private String readRequiredText(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("This field cannot be empty.");
        }
    }

    private void printHeader() {
        System.out.println("ID       | Type         | Title                    | Author             | Status    | Loan");
        System.out.println("---------+--------------+--------------------------+--------------------+-----------+---------");
    }

    private void displayItems(List<LibraryItem> items) {
        if (items.isEmpty()) {
            System.out.println("No matching library items found.");
            return;
        }
        printHeader();
        // Runtime polymorphism: superclass references execute subclass overrides.
        for (LibraryItem item : items) {
            item.displayDetails();
        }
    }

    private void displayAllItems() {
        System.out.println("\n--- All Library Items ---");
        displayItems(libraryManager.getAllItems());
    }

    private void addPrintedBook() {
        System.out.println("\n--- Add Printed Book ---");
        String id = readRequiredText("Item ID: ");
        if (libraryManager.findItemById(id) != null) {
            System.out.println("An item with that ID already exists.");
            return;
        }
        String title = readRequiredText("Title: ");
        String author = readRequiredText("Author: ");
        String shelf = readRequiredText("Shelf location: ");
        boolean added = libraryManager.addItem(new PrintedBook(id, title, author, shelf));
        System.out.println(added ? "Printed book added successfully." : "Could not add the book.");
    }

    private void addEBook() {
        System.out.println("\n--- Add E-Book ---");
        String id = readRequiredText("Item ID: ");
        if (libraryManager.findItemById(id) != null) {
            System.out.println("An item with that ID already exists.");
            return;
        }
        String title = readRequiredText("Title: ");
        String author = readRequiredText("Author: ");
        String format = readRequiredText("File format (e.g. PDF/EPUB): ");
        boolean added = libraryManager.addItem(new EBook(id, title, author, format));
        System.out.println(added ? "E-book added successfully." : "Could not add the e-book.");
    }

    private void searchItems() {
        String keyword = readRequiredText("Search title or author: ");
        System.out.println("\n--- Search Results ---");
        displayItems(libraryManager.searchItems(keyword));
    }

    private void borrowItem() {
        String id = readRequiredText("Enter item ID to borrow: ");
        LibraryItem item = libraryManager.findItemById(id);
        if (item == null) {
            System.out.println("Item not found.");
        } else if (libraryManager.borrowItem(id)) {
            System.out.println("Borrow successful: " + item.getTitle()
                    + " (loan period: " + item.getLoanPeriodDays() + " days).");
        } else {
            System.out.println("This item is already borrowed.");
        }
    }

    private void returnItem() {
        String id = readRequiredText("Enter item ID to return: ");
        LibraryItem item = libraryManager.findItemById(id);
        if (item == null) {
            System.out.println("Item not found.");
        } else if (libraryManager.returnItem(id)) {
            System.out.println("Return successful: " + item.getTitle());
        } else {
            System.out.println("This item is already available.");
        }
    }

    private void showSummary() {
        int total = libraryManager.getTotalCount();
        int available = libraryManager.getAvailableCount();
        System.out.println("\n--- Library Summary ---");
        System.out.println("Total items     : " + total);
        System.out.println("Available items : " + available);
        System.out.println("Borrowed items  : " + (total - available));
    }

    public static void main(String[] args) {
        new LibraryApp().run();
    }
}

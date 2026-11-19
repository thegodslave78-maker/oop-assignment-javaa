/**
 * Abstract superclass representing a library resource.
 * Demonstrates abstraction, encapsulation and common shared behaviour.
 */
public abstract class LibraryItem {
    private final String itemId;
    private String title;
    private String author;
    private boolean available;

    public LibraryItem(String itemId, String title, String author) {
        if (itemId == null || itemId.trim().isEmpty()) {
            throw new IllegalArgumentException("Item ID cannot be empty.");
        }
        this.itemId = itemId.trim().toUpperCase();
        setTitle(title);
        setAuthor(author);
        this.available = true;
    }

    public String getItemId() {
        return itemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty.");
        }
        this.title = title.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Author cannot be empty.");
        }
        this.author = author.trim();
    }

    public boolean isAvailable() {
        return available;
    }

    /** Returns true only when this item was successfully borrowed. */
    public boolean borrowItem() {
        if (!available) {
            return false;
        }
        available = false;
        return true;
    }

    /** Returns true only when this item was previously on loan. */
    public boolean returnItem() {
        if (available) {
            return false;
        }
        available = true;
        return true;
    }

    // Each subclass must specify its resource type and loan policy.
    public abstract String getItemType();

    public abstract int getLoanPeriodDays();

    /** Subclasses override this to add their own details. */
    public void displayDetails() {
        System.out.printf("%-8s | %-12s | %-24s | %-18s | %-9s | %2d days%n",
                itemId, getItemType(), shorten(title, 24), shorten(author, 18),
                available ? "Available" : "Borrowed", getLoanPeriodDays());
    }

    protected String shorten(String input, int maxLength) {
        return input.length() <= maxLength
                ? input : input.substring(0, maxLength - 3) + "...";
    }
}

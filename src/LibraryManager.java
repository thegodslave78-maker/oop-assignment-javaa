import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Stores resources and implements library operations independently of the UI. */
public class LibraryManager {
    private final List<LibraryItem> items;

    public LibraryManager() {
        items = new ArrayList<>();
    }

    /** Rejects duplicate IDs, regardless of letter case. */
    public boolean addItem(LibraryItem item) {
        if (item == null || findItemById(item.getItemId()) != null) {
            return false;
        }
        return items.add(item);
    }

    public LibraryItem findItemById(String itemId) {
        if (itemId == null) {
            return null;
        }
        for (LibraryItem item : items) {
            if (item.getItemId().equalsIgnoreCase(itemId.trim())) {
                return item;
            }
        }
        return null;
    }

    /** Case-insensitive search across title and author. */
    public List<LibraryItem> searchItems(String keyword) {
        List<LibraryItem> matches = new ArrayList<>();
        if (keyword == null || keyword.trim().isEmpty()) {
            return matches;
        }
        String query = keyword.trim().toLowerCase();
        for (LibraryItem item : items) {
            if (item.getTitle().toLowerCase().contains(query)
                    || item.getAuthor().toLowerCase().contains(query)) {
                matches.add(item);
            }
        }
        return matches;
    }

    public boolean borrowItem(String itemId) {
        LibraryItem item = findItemById(itemId);
        return item != null && item.borrowItem();
    }

    public boolean returnItem(String itemId) {
        LibraryItem item = findItemById(itemId);
        return item != null && item.returnItem();
    }

    public List<LibraryItem> getAllItems() {
        return Collections.unmodifiableList(items);
    }

    public int getTotalCount() {
        return items.size();
    }

    public int getAvailableCount() {
        int count = 0;
        for (LibraryItem item : items) {
            if (item.isAvailable()) {
                count++;
            }
        }
        return count;
    }
}

/** A physical library book stored at a specific shelf location. */
public class PrintedBook extends LibraryItem {
    private String shelfLocation;

    public PrintedBook(String itemId, String title, String author, String shelfLocation) {
        super(itemId, title, author);
        setShelfLocation(shelfLocation);
    }

    public String getShelfLocation() {
        return shelfLocation;
    }

    public void setShelfLocation(String shelfLocation) {
        if (shelfLocation == null || shelfLocation.trim().isEmpty()) {
            throw new IllegalArgumentException("Shelf location cannot be empty.");
        }
        this.shelfLocation = shelfLocation.trim();
    }

    @Override
    public String getItemType() {
        return "Printed Book";
    }

    @Override
    public int getLoanPeriodDays() {
        return 21;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("           Shelf location: " + shelfLocation);
    }
}

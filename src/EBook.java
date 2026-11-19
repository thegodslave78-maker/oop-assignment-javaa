/** A digital library resource distributed in a particular file format. */
public class EBook extends LibraryItem {
    private String fileFormat;

    public EBook(String itemId, String title, String author, String fileFormat) {
        super(itemId, title, author);
        setFileFormat(fileFormat);
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public void setFileFormat(String fileFormat) {
        if (fileFormat == null || fileFormat.trim().isEmpty()) {
            throw new IllegalArgumentException("File format cannot be empty.");
        }
        this.fileFormat = fileFormat.trim().toUpperCase();
    }

    @Override
    public String getItemType() {
        return "E-Book";
    }

    @Override
    public int getLoanPeriodDays() {
        return 14;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("           Digital format: " + fileFormat);
    }
}

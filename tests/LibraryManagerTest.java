/** Lightweight tests without third-party dependencies. */
public class LibraryManagerTest {
    private static int checks = 0;

    private static void check(boolean condition, String description) {
        checks++;
        if (!condition) {
            throw new AssertionError("FAIL: " + description);
        }
        System.out.println("PASS: " + description);
    }

    public static void main(String[] args) {
        LibraryManager manager = new LibraryManager();
        LibraryItem physical = new PrintedBook("P1", "Java", "Alex", "A-1");
        LibraryItem digital = new EBook("E1", "Design", "Sam", "pdf");
        check(manager.addItem(physical), "add printed book");
        check(manager.addItem(digital), "add e-book");
        check(!manager.addItem(new EBook("p1", "Duplicate", "Sam", "EPUB")),
                "reject case-insensitive duplicate ID");
        check(manager.getTotalCount() == 2, "count resources");
        check(manager.findItemById("p1") == physical, "find by ID ignoring case");
        check(manager.searchItems("DESIGN").size() == 1, "case-insensitive search");
        check(physical.getLoanPeriodDays() == 21, "printed-book loan policy");
        check(digital.getLoanPeriodDays() == 14, "e-book loan policy");
        check(manager.borrowItem("P1"), "borrow available item");
        check(!manager.borrowItem("P1"), "prevent double borrowing");
        check(manager.getAvailableCount() == 1, "available count after borrowing");
        check(manager.returnItem("P1"), "return borrowed item");
        check(!manager.returnItem("P1"), "prevent double returning");
        check(manager.getAvailableCount() == 2, "available count after returning");
        check(!manager.borrowItem("MISSING"), "reject unknown item ID");
        System.out.println("ALL TESTS PASSED (" + checks + " checks).");
    }
}

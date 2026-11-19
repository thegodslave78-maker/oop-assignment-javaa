# Library Management System (Java)

**Object-Oriented Programming - Assignment 2 (Pair)**

A standalone, dependency-free Java console application. It manages printed books and e-books, supports search, borrowing, returns, and summary counts, and demonstrates classes/objects, encapsulation, inheritance, polymorphism, abstraction, Scanner input, control structures, and output formatting.

## Requirements

- JDK 17 or newer (tested with OpenJDK 21)
- Terminal, Command Prompt, IntelliJ IDEA, Eclipse, NetBeans, or VS Code with Java extension

## How to run

Open a terminal inside the `src` folder and run:

```bash
javac *.java
java LibraryApp
```

Windows Command Prompt: same commands work with a correctly configured Java JDK.

The app starts with 4 sample resources. Choose from the console menu to browse, add, search, borrow, and return items. All data is held in memory for the current session. It is **not saved** after exit.

## Run unit-style tests

From the `src` directory, run:

```bash
javac *.java ../tests/LibraryManagerTest.java
java -cp .:../tests LibraryManagerTest
```

On Windows replace the `:` in the final classpath with `;`:

```bat
java -cp .;../tests LibraryManagerTest
```

## Source files

| File | Purpose |
|---|---|
| LibraryItem.java | Abstract superclass, shared fields, borrowing and returning methods |
| PrintedBook.java | Physical book with shelf location, overridden methods |
| EBook.java | Digital book with file format, overridden methods |
| LibraryManager.java | In-memory collection, search, borrowing/returning, statistics |
| LibraryApp.java | Scanner-based menu and main method |
| tests/LibraryManagerTest.java | 15 repeatable checks of core operations |

## GitHub submission

Create a repository in your GitHub account, upload the project contents (at minimum the `src` files and this README), and put that repository's real URL on the title/links page of the PDF report. The URL field is intentionally left blank because no repository can be created without an account.

## Report and declaration

See `docs/Assignment_Report.pdf` and `docs/Student_AI_Declaration.pdf`. Complete the partner details, student IDs, class code, program, repository URL, and genuine signatures prior to submission. AI use must be declared as Level 2 (Collaborative) under the assignment instructions.
